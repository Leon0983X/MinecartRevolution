
package com.quartercode.minecartrevolution;

import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
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
import com.quartercode.qcutil.io.File;

public class MinecartRevolution extends JavaPlugin {

    private static MinecartRevolution minecartRevolution;

    public static void handleThrowable(final Throwable throwable) {

        QcUtil.handleThrowable(throwable);
    }

    public static void handleSilenceThrowable(final Throwable throwable) {

        if ((Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.printSilenceErrors)) {
            handleThrowable(throwable);
        }
    }

    private MRCommandExecutor      commandExecutor;
    private MRControlBlockExecutor controlBlockExecutor;
    private MRControlSignExecutor  controlSignExecutor;
    private MRExpressionExecutor   expressionExecutor;
    private Config                 configuration;
    private Metrics                metrics;

    private BasicCommandsPlugin    commandsPlugin;
    private BasicControlsPlugin    controlsPlugin;
    private BasicExpressionPlugin  expressionPlugin;

    public MinecartRevolution() {

        minecartRevolution = this;
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

    @Override
    public File getFile() {

        return (File) super.getFile();
    }

    @Override
    public void onLoad() {

        PluginManager.registerMinecartRevolution(this);
    }

    @Override
    public void onEnable() {

        configuration = new GlobalConfig();
        configuration.setDefaults();
        configuration.save();

        Lang.setLanguage((String) configuration.get(GlobalConfig.language));

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
        getCommand("minecartrevolution").setExecutor(commandExecutor);

        controlBlockExecutor = new MRControlBlockExecutor();

        controlSignExecutor = new MRControlSignExecutor();

        expressionExecutor = new MRExpressionExecutor();
    }

    private void enablePlugins() {

        commandsPlugin = new BasicCommandsPlugin();
        controlsPlugin = new BasicControlsPlugin();
        expressionPlugin = new BasicExpressionPlugin();

        PluginManager.enablePlugin(commandsPlugin);
        PluginManager.enablePlugin(controlsPlugin);
        PluginManager.enablePlugin(expressionPlugin);
    }

    private void enableMetrics() {

        try {
            metrics = new Metrics(this);
            metrics.start();
        }
        catch (final IOException e) {
            handleSilenceThrowable(e);
        }
    }

    @Override
    public void onDisable() {

    }

}