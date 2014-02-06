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
import org.bukkit.plugin.java.JavaPlugin;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandExecutor;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;
import com.quartercode.minecartrevolution.core.control.block.ControlBlockExecutor;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignExecutor;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.core.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.core.get.FileConf;
import com.quartercode.minecartrevolution.core.util.Updater;
import com.quartercode.minecartrevolution.core.util.VehicleMetdataStorage;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.minecartrevolution.core.util.config.Config;
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
    public void setMinecartRevolution(MinecartRevolution minecartRevolution) {

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
    public VehicleMetdataStorage getMetdataStorage() {

        return minecartRevolution.getMetadataStorage();
    }

    @Override
    public void addCommandHandler(CommandHandler commandHandler) {

        getCommandExecutor().addCommandHandler(commandHandler);
    }

    @Override
    public void addControlBlock(ControlBlock controlBlock) {

        getControlBlockExecutor().addControlBlock(controlBlock);
    }

    @Override
    public void addControlSign(ControlSign controlSign) {

        getControlSignExecutor().addControlSign(controlSign);
    }

    @Override
    public void addExpressionCommand(ExpressionCommand expressionCommand) {

        getExpressionExecutor().addExpressionCommand(expressionCommand);
    }

    @Override
    public void addExpressionConstant(ExpressionConstant expressionConstant) {

        getExpressionExecutor().addExpressionConstant(expressionConstant);
    }

    @Override
    public void addMinecartTerm(MinecartTerm minecartTerm) {

        minecartRevolution.addMinecartTerm(minecartTerm);
    }

    @Override
    public void addUpdater(Updater updater) {

        minecartRevolution.addUpdater(updater);
    }

    @Override
    public void onEnable() {

        PluginManager.registerPlugin(this);
    }

}
