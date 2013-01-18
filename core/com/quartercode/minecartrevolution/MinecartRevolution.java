
package com.quartercode.minecartrevolution;

import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import com.quartercode.basiccommands.BasicCommandsPlugin;
import com.quartercode.basiccontrols.BasicControlsPlugin;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.minecartrevolution.block.MRControlBlockExecutor;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.listener.BlockListener;
import com.quartercode.minecartrevolution.listener.MinecartListener;
import com.quartercode.minecartrevolution.plugin.PluginManager;
import com.quartercode.minecartrevolution.sign.MRControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.Metrics;
import com.quartercode.minecartrevolution.util.expression.MRExpressionExecutor;
import com.quartercode.qcutil.QcUtil;

public class MinecartRevolution {

    private static MinecartRevolution minecartRevolution;

    public static void handleThrowable(final Throwable throwable) {

        QcUtil.handleThrowable(throwable);
    }

    public static void handleSilenceThrowable(final Throwable throwable) {

        if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PRINT_SILENCE_ERRORS)) {
            handleThrowable(throwable);
        }
    }

    private final MinecartRevolutionPlugin plugin;

    private MRCommandExecutor              commandExecutor;
    private MRControlBlockExecutor         controlBlockExecutor;
    private MRControlSignExecutor          controlSignExecutor;
    private MRExpressionExecutor           expressionExecutor;
    private Config                         configuration;
    private Metrics                        metrics;

    private BasicCommandsPlugin            commandsPlugin;
    private BasicControlsPlugin            controlsPlugin;
    private BasicExpressionPlugin          expressionPlugin;

    public MinecartRevolution(final MinecartRevolutionPlugin plugin) {

        this.plugin = plugin;
        minecartRevolution = this;
    }

    public MinecartRevolutionPlugin getPlugin() {

        return plugin;
    }

    public MRCommandExecutor getCommandExecutor() {

        return commandExecutor;
    }

    public MRControlBlockExecutor getControlBlockExecutor() {

        return controlBlockExecutor;
    }

    public MRControlSignExecutor getControlSignExecutor() {

        return controlSignExecutor;
    }

    public MRExpressionExecutor getExpressionExecutor() {

        return expressionExecutor;
    }

    public Config getConfiguration() {

        return configuration;
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

    public void load() {

        PluginManager.registerMinecartRevolution(this);
    }

    public void enable() {

        configuration = new GlobalConfig();
        configuration.setDefaults();
        configuration.save();

        Lang.setLanguage(configuration.get(GlobalConfig.LANGUAGE));

        if (!Lang.isCurrentLanguageAvaiable()) {
            Lang.extractCurrentLanguage();
        }

        enableListeners();
        enableExecutors();
        enablePlugins();
        enableMetrics();
    }

    private void enableListeners() {

        new MinecartListener(this);
        new BlockListener(this);
    }

    private void enableExecutors() {

        commandExecutor = new MRCommandExecutor();
        plugin.getCommand("minecartrevolution").setExecutor(commandExecutor);

        controlBlockExecutor = new MRControlBlockExecutor();

        controlSignExecutor = new MRControlSignExecutor();

        expressionExecutor = new MRExpressionExecutor();
    }

    private void enablePlugins() {

        commandsPlugin = new BasicCommandsPlugin();
        controlsPlugin = new BasicControlsPlugin();
        expressionPlugin = new BasicExpressionPlugin();

        PluginManager.registerPlugin(commandsPlugin);
        PluginManager.registerPlugin(controlsPlugin);
        PluginManager.registerPlugin(expressionPlugin);
    }

    private void enableMetrics() {

        try {
            metrics = new Metrics(plugin);
            metrics.start();
        }
        catch (final IOException e) {
            handleSilenceThrowable(e);
        }
    }

}
