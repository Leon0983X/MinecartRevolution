/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;
import com.quartercode.minecartrevolution.core.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.core.control.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.core.exception.ExceptionListener;
import com.quartercode.minecartrevolution.core.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.core.get.FileConf;
import com.quartercode.minecartrevolution.core.get.LanguageBundle;
import com.quartercode.minecartrevolution.core.listener.BlockListener;
import com.quartercode.minecartrevolution.core.listener.MinecartListener;
import com.quartercode.minecartrevolution.core.plugin.PluginManager;
import com.quartercode.minecartrevolution.core.util.ExtractionUtil;
import com.quartercode.minecartrevolution.core.util.JarUpdater;
import com.quartercode.minecartrevolution.core.util.ResourceLister;
import com.quartercode.minecartrevolution.core.util.Updater;
import com.quartercode.minecartrevolution.core.util.VehicleMetdataStorage;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.minecartrevolution.core.util.config.Config;
import com.quartercode.minecartrevolution.core.util.config.GlobalConfig;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;
import com.quartercode.quarterbukkit.api.query.FilesQuery.ProjectFile;
import com.quartercode.quarterbukkit.api.query.FilesQuery.VersionParser;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class MinecartRevolution {

    private static Locale locale;

    public static Locale getLocale() {

        return locale;
    }

    public static LanguageBundle getLang() {

        return new LanguageBundle(LanguageBundle.DEFAULT_BASE_NAME, locale);
    }

    private static MinecartRevolution minecartRevolution;

    private final JavaPlugin          plugin;

    private MRCommandExecutor         commandExecutor;
    private ControlBlockExecutor      controlBlockExecutor;
    private ControlSignExecutor       controlSignExecutor;
    private ExpressionExecutor        expressionExecutor;
    private List<MinecartTerm>        minecartTerms;
    private List<Updater>             updaters;

    private Config                    configuration;
    private VehicleMetdataStorage     metadataStorage;

    public MinecartRevolution(JavaPlugin plugin) {

        this.plugin = plugin;
        minecartRevolution = this;

        // Check for the optional OddItem dependency
        if (!Bukkit.getPluginManager().isPluginEnabled("OddItem")) {
            getLogger().warning("In order to enable item aliases, you need to install OddItem: http://dev.bukkit.org/bukkit-plugins/odditem/");
        }
    }

    public JavaPlugin getPlugin() {

        return plugin;
    }

    public MRCommandExecutor getCommandExecutor() {

        return commandExecutor;
    }

    public ControlBlockExecutor getControlBlockExecutor() {

        return controlBlockExecutor;
    }

    public ControlSignExecutor getControlSignExecutor() {

        return controlSignExecutor;
    }

    public ExpressionExecutor getExpressionExecutor() {

        return expressionExecutor;
    }

    public List<MinecartTerm> getMinecartTerms() {

        return Collections.unmodifiableList(minecartTerms);
    }

    public void addMinecartTerm(MinecartTerm minecartTerm) {

        minecartTerms.add(minecartTerm);
    }

    public List<Updater> getUpdaters() {

        return Collections.unmodifiableList(updaters);
    }

    public void addUpdater(Updater updater) {

        updaters.add(updater);
    }

    public Config getConfiguration() {

        return configuration;
    }

    public VehicleMetdataStorage getMetadataStorage() {

        return metadataStorage;
    }

    public String getName() {

        return plugin.getName();
    }

    public PluginDescriptionFile getDescription() {

        return plugin.getDescription();
    }

    public Logger getLogger() {

        return plugin.getLogger();
    }

    public void enable() {

        new ExceptionListener(this);
        PluginManager.registerMinecartRevolution(this);

        // Generate/Load Configuration
        configuration = new GlobalConfig(this);
        configuration.setDefaults();
        configuration.save();

        // Extract files from 'extract'
        getLogger().info("Extracting files from 'extract' ...");
        ExtractionUtil.setMinecartRevolution(minecartRevolution);
        try {
            for (String resourcePath : ResourceLister.getResources("/extract", false)) {
                URL resource = MinecartRevolution.class.getResource(resourcePath);
                String extractionPath = resource.getPath();
                extractionPath = extractionPath.substring(extractionPath.indexOf("extract")).replace("extract/", "");
                ExtractionUtil.extractFromJAR(resource, new File(FileConf.DATA + File.separator + extractionPath));
            }
        } catch (IOException e1) {
            // TODO: Remove extract
            e1.printStackTrace();
        }

        // Set locale
        String[] localeParts = configuration.get(GlobalConfig.LANGUAGE).split("_");
        String language = "";
        String country = "";
        String variant = "";
        if (localeParts.length >= 1) {
            language = localeParts[0];
        }
        if (localeParts.length >= 2) {
            country = localeParts[1];
        }
        if (localeParts.length >= 3) {
            variant = localeParts[2];
        }
        locale = new Locale(language, country, variant);

        loadMetadataStorage();
        enableListeners();
        enableExecutors();

        // Enable metrics
        try {
            MetricsLite metrics = new MetricsLite(plugin);
            metrics.start();
        } catch (IOException e) {
            ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Error while initalizing Metrics"));
        }

        // Updater
        addUpdater(new JarUpdater(plugin, 36965, new VersionParser() {

            @Override
            public String parseVersion(ProjectFile file) {

                return file.getName().replace("MinecartRevolution ", "");
            }
        }));
    }

    private void loadMetadataStorage() {

        metadataStorage = new VehicleMetdataStorage();

        File metadataFile = new File(FileConf.DATA, "metadata.properties");
        if (metadataFile.exists()) {
            FileReader reader = null;
            try {
                reader = new FileReader(metadataFile);
                Properties metadataProperties = new Properties();
                metadataProperties.load(reader);
                metadataStorage.deserialize(metadataProperties);
            } catch (IOException e) {
                ExceptionHandler.exception(new MinecartRevolutionException(minecartRevolution, e, "Error while loading stored metadata"));
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Error while closing metadata loading stream"));
                    }
                }
            }
        } else {
            metadataFile.getParentFile().mkdirs();
        }

        new ScheduleTask(plugin) {

            @Override
            public void run() {

                serializeMetadataStorage();
            }
        }.run(true, 0, configuration.getLong(GlobalConfig.SAVE_TIME_INTERVAL) * 1000);
    }

    private void serializeMetadataStorage() {

        File metadataFile = new File(FileConf.DATA, "metadata.properties");
        FileWriter writer = null;
        try {
            writer = new FileWriter(metadataFile);
            metadataStorage.serialize().store(writer, "Entity metadata storage; Do not edit!");
        } catch (IOException e) {
            ExceptionHandler.exception(new MinecartRevolutionException(minecartRevolution, e, "Error while loading saving metadata"));
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Error while closing metadata saving stream"));
                }
            }
        }
    }

    private void enableListeners() {

        new MinecartListener(this);
        new BlockListener(this);
    }

    private void enableExecutors() {

        commandExecutor = new MRCommandExecutor(this, "minecartrevolution");

        controlBlockExecutor = new ControlBlockExecutor(this);
        controlSignExecutor = new ControlSignExecutor(this);
        expressionExecutor = new ExpressionExecutor(this);

        minecartTerms = new ArrayList<MinecartTerm>();
        updaters = new ArrayList<Updater>();
    }

    public void disable() {

        serializeMetadataStorage();
    }

}
