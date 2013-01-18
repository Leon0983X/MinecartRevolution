
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.MRControlBlockExecutor;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.MRControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.MRExpressionExecutor;
import com.quartercode.qcutil.io.File;

public interface MinecartRevolutionPlugin {

    public MinecartRevolution getMinecartRevolution();

    public void setMinecartRevolution(MinecartRevolution minecartRevolution);

    public MRCommandExecutor getCommandExecutor();

    public MRControlBlockExecutor getControlBlockExecutor();

    public MRControlSignExecutor getControlSignExecutor();

    public MRExpressionExecutor getExpressionExecutor();

    public File getPluginFolder();

    public File getConfigFile();

    public Config getConfiguration();

    public void addCommand(final Command command);

    public void addControlBlock(final ControlBlock controlBlock);

    public void addControlSign(final ControlSign controlSign);

    public void addExpressionCommand(final ExpressionCommand expressionCommand);

    public void addExpressionConstant(final ExpressionConstant expressionConstant);

    public abstract PluginInfo getInfo();

    public abstract void enable();

}
