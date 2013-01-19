
package com.quartercode.minecartrevolution;

import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import com.quartercode.basiccommands.BasicCommandsPlugin;
import com.quartercode.basiccontrols.BasicControlsPlugin;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.minecartrevolution.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionExceptionHandler;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.minecartrevolution.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.listener.BlockListener;
import com.quartercode.minecartrevolution.listener.MinecartListener;
import com.quartercode.minecartrevolution.plugin.PluginManager;
import com.quartercode.minecartrevolution.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.Metrics;
import com.quartercode.quarterbukkit.QuarterBukkit;

public class MinecartRevolution {

    private static MinecartRevolution minecartRevolution;

    public static MinecartRevolutionException createException(final boolean silence, final Throwable cause, final String message) {

        if (silence) {
            if (cause == null && message == null) {
                return new MinecartRevolutionSilenceException(minecartRevolution);
            } else if (cause != null && message == null) {
                return new MinecartRevolutionSilenceException(minecartRevolution, cause);
            } else if (cause == null && message != null) {
                return new MinecartRevolutionSilenceException(minecartRevolution, message);
            } else if (cause != null && message != null) {
                return new MinecartRevolutionSilenceException(minecartRevolution, cause, message);
            }
        } else {
            if (cause == null && message == null) {
                return new MinecartRevolutionException(minecartRevolution);
            } else if (cause != null && message == null) {
                return new MinecartRevolutionException(minecartRevolution, cause);
            } else if (cause == null && message != null) {
                return new MinecartRevolutionException(minecartRevolution, message);
            } else if (cause != null && message != null) {
                return new MinecartRevolutionException(minecartRevolution, cause, message);
            }
        }

        return null;
    }

    private final MinecartRevolutionPlugin plugin;

    private MRCommandExecutor              commandExecutor;
    private ControlBlockExecutor           controlBlockExecutor;
    private ControlSignExecutor            controlSignExecutor;
    private ExpressionExecutor             expressionExecutor;
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

    public ControlBlockExecutor getControlBlockExecutor() {

        return controlBlockExecutor;
    }

    public ControlSignExecutor getControlSignExecutor() {

        return controlSignExecutor;
    }

    public ExpressionExecutor getExpressionExecutor() {

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

    public void enable() {

        QuarterBukkit.setExceptionHandler(new MinecartRevolutionExceptionHandler(this));
        PluginManager.registerMinecartRevolution(this);

        configuration = new GlobalConfig(this);
        configuration.setDefaults();
        configuration.save();

        Lang.setMinecartRevolution(this);
        Lang.extractDefaults();
        Lang.setLanguage(configuration.get(GlobalConfig.LANGUAGE));

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

        commandExecutor = new MRCommandExecutor(this, "minecartrevolution");

        controlBlockExecutor = new ControlBlockExecutor(this);
        controlSignExecutor = new ControlSignExecutor(this);
        expressionExecutor = new ExpressionExecutor(this);
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
            QuarterBukkit.exception(createException(true, e, "Error while initalizing Metrics"));
        }
    }

}
