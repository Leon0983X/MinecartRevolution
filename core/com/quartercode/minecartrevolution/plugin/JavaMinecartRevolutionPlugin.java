
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

public abstract class JavaMinecartRevolutionPlugin implements MinecartRevolutionPlugin {

    private MinecartRevolution minecartRevolution;
    protected Config           config;

    protected JavaMinecartRevolutionPlugin() {

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
    public MRControlBlockExecutor getControlBlockExecutor() {

        return minecartRevolution.getControlBlockExecutor();
    }

    @Override
    public MRControlSignExecutor getControlSignExecutor() {

        return minecartRevolution.getControlSignExecutor();
    }

    @Override
    public MRExpressionExecutor getExpressionExecutor() {

        return minecartRevolution.getExpressionExecutor();
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
    public void addCommand(final Command command) {

        getCommandExecutor().getCommands().add(command);
    }

    @Override
    public void addControlBlock(final ControlBlock controlBlock) {

        getControlBlockExecutor().getControlBlocks().add(controlBlock);
    }

    @Override
    public void addControlSign(final ControlSign controlSign) {

        getControlSignExecutor().getControlSigns().add(controlSign);
    }

    @Override
    public void addExpressionCommand(final ExpressionCommand expressionCommand) {

        getExpressionExecutor().getExpressionCommands().add(expressionCommand);
    }

    @Override
    public void addExpressionConstant(final ExpressionConstant expressionConstant) {

        getExpressionExecutor().getExpressionConstants().add(expressionConstant);
    }

}
