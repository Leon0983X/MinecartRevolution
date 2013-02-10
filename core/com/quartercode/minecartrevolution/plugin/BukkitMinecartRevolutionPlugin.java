
package com.quartercode.minecartrevolution.plugin;

import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.qcutil.io.File;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.CommandHandler;

public abstract class BukkitMinecartRevolutionPlugin extends JavaPlugin implements MinecartRevolutionPlugin {

    private MinecartRevolution minecartRevolution;
    protected Config           config;

    protected BukkitMinecartRevolutionPlugin() {

    }

    @Override
    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    @Override
    public void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public MRCommandExecutor getCommandExecutor() {

        return minecartRevolution.getCommandExecutor();
    }

    @Override
    public ControlBlockExecutor getControlBlockExecutor() {

        return minecartRevolution.getControlBlockExecutor();
    }

    @Override
    public ControlSignExecutor getControlSignExecutor() {

        return minecartRevolution.getControlSignExecutor();
    }

    @Override
    public ExpressionExecutor getExpressionExecutor() {

        return minecartRevolution.getExpressionExecutor();
    }

    @Override
    public List<MinecartTerm> getMinecartTerms() {

        return minecartRevolution.getMinecartTerms();
    }

    @Override
    public List<Updater> getUpdaters() {

        return minecartRevolution.getUpdaters();
    }

    @Override
    public File getPluginFolder() {

        return new File(FileConf.PLUGINS, getInfo().getName());
    }

    @Override
    public File getConfigFile() {

        return new File(getPluginFolder(), FileConf.MAIN_CONF_NAME);
    }

    @Override
    public Config getConfiguration() {

        return config;
    }

    @Override
    public void addCommandHandler(final CommandHandler commandHandler) {

        getCommandExecutor().addCommandHandler(commandHandler);
    }

    @Override
    public void addControlBlock(final ControlBlock controlBlock) {

        getControlBlockExecutor().addControlBlock(controlBlock);
    }

    @Override
    public void addControlSign(final ControlSign controlSign) {

        getControlSignExecutor().addControlSign(controlSign);
    }

    @Override
    public void addExpressionCommand(final ExpressionCommand expressionCommand) {

        getExpressionExecutor().addExpressionCommand(expressionCommand);
    }

    @Override
    public void addExpressionConstant(final ExpressionConstant expressionConstant) {

        getExpressionExecutor().addExpressionConstant(expressionConstant);
    }

    @Override
    public void addMinecartTerm(final MinecartTerm minecartTerm) {

        minecartRevolution.addMinecartTerm(minecartTerm);
    }

    @Override
    public void addUpdater(final Updater updater) {

        minecartRevolution.addUpdater(updater);
    }

    @Override
    public final void onEnable() {

        PluginManager.registerPlugin(this);
    }

}
