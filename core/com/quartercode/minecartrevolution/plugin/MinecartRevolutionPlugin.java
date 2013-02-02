
package com.quartercode.minecartrevolution.plugin;

import java.util.List;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.util.Config;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.qcutil.io.File;
import com.quartercode.quarterbukkit.api.command.CommandHandler;

public interface MinecartRevolutionPlugin {

    public MinecartRevolution getMinecartRevolution();

    public void setMinecartRevolution(MinecartRevolution minecartRevolution);

    public MRCommandExecutor getCommandExecutor();

    public ControlBlockExecutor getControlBlockExecutor();

    public ControlSignExecutor getControlSignExecutor();

    public ExpressionExecutor getExpressionExecutor();

    public List<MinecartTerm> getMinecartTerms();

    public void addMinecartTerm(MinecartTerm minecartTerm);

    public File getPluginFolder();

    public File getConfigFile();

    public Config getConfiguration();

    public void addCommandHandler(final CommandHandler command);

    public void addControlBlock(final ControlBlock controlBlock);

    public void addControlSign(final ControlSign controlSign);

    public void addExpressionCommand(final ExpressionCommand expressionCommand);

    public void addExpressionConstant(final ExpressionConstant expressionConstant);

    public abstract PluginInfo getInfo();

    public abstract void enable();

}
