
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.MRControlBlockExecutor;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.MRControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.MRExpressionExecutor;
import com.quartercode.qcutil.io.File;

public abstract class MinecartRevolutionPlugin {

    protected MinecartRevolution minecartRevolution;
    protected Config             config;

    protected MinecartRevolutionPlugin() {

        PluginManager.registerPlugin(this);
    }

    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    public MRCommandExecutor getCommandExecutor() {

        return minecartRevolution.getCommandExecutor();
    }

    public MRControlBlockExecutor getControlBlockExecutor() {

        return minecartRevolution.getControlBlockExecutor();
    }

    public MRControlSignExecutor getControlSignExecutor() {

        return minecartRevolution.getControlSignExecutor();
    }

    public MRExpressionExecutor getExpressionExecutor() {

        return minecartRevolution.getExpressionExecutor();
    }

    public File getPluginFolder() {

        return new File(FileConf.PLUGINS, getInfo().getName());
    }

    public File getConfigFile() {

        return new File(getPluginFolder(), FileConf.MAIN_CONF_NAME);
    }

    public Config getConfig() {

        return config;
    }

    public void addCommand(Command command) {

        getCommandExecutor().getCommands().add(command);
    }

    public void addControlBlock(ControlBlock controlBlock) {

        getControlBlockExecutor().getControlBlocks().add(controlBlock);
    }

    public void addControlSign(ControlSign controlSign) {

        getControlSignExecutor().getControlSigns().add(controlSign);
    }

    public void addExpressionCommand(ExpressionCommand expressionCommand) {

        getExpressionExecutor().getExpressionCommands().add(expressionCommand);
    }

    public void addExpressionConstant(ExpressionConstant expressionConstant) {

        getExpressionExecutor().getExpressionConstants().add(expressionConstant);
    }

    public abstract PluginInfo getInfo();

    public abstract void onEnable();

}
