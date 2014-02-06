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

package com.quartercode.minecartrevolution.core.plugin;

import java.io.File;
import java.util.List;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;
import com.quartercode.minecartrevolution.core.control.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.core.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.core.util.Updater;
import com.quartercode.minecartrevolution.core.util.VehicleMetdataStorage;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.minecartrevolution.core.util.config.Config;
import com.quartercode.quarterbukkit.api.command.CommandHandler;

public interface MinecartRevolutionPlugin {

    public MinecartRevolution getMinecartRevolution();

    public void setMinecartRevolution(MinecartRevolution minecartRevolution);

    public MRCommandExecutor getCommandExecutor();

    public ControlBlockExecutor getControlBlockExecutor();

    public ControlSignExecutor getControlSignExecutor();

    public ExpressionExecutor getExpressionExecutor();

    public List<MinecartTerm> getMinecartTerms();

    public List<Updater> getUpdaters();

    public File getPluginFolder();

    public File getConfigFile();

    public Config getConfiguration();

    public VehicleMetdataStorage getMetdataStorage();

    public void addCommandHandler(CommandHandler command);

    public void addControlBlock(ControlBlock controlBlock);

    public void addControlSign(ControlSign controlSign);

    public void addExpressionCommand(ExpressionCommand expressionCommand);

    public void addExpressionConstant(ExpressionConstant expressionConstant);

    public void addMinecartTerm(MinecartTerm minecartTerm);

    public void addUpdater(Updater updater);

    public abstract PluginInfo getInfo();

    public abstract void enable();

}
