/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions;

import com.quartercode.minecartrevolution.basicexpressions.command.AnnounceCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.ChestCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.ClearCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.CollectCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.CommandCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.EffectCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.EjectCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.FarmCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.FileCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.FurnaceCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.GrabCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.HealthCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.HoldCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.IntersectionCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.KillCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.LockCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.ReverseCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.SensorCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.SpeedCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.TimeCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.VerticalCommand;
import com.quartercode.minecartrevolution.basicexpressions.command.WeatherCommand;
import com.quartercode.minecartrevolution.basicexpressions.constant.HealthConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.PlayerConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.SpeedConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.TimeConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.XConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.YConstant;
import com.quartercode.minecartrevolution.basicexpressions.constant.ZConstant;
import com.quartercode.minecartrevolution.basicexpressions.term.AnimalTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.CommandMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.EastTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.HopperMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.InventoryTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.ItemHoldTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.ItemTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.MobTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.MonsterTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.NPCTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.NorthTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.PassengerTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.PlayerTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.PoweredMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.RandomTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.RideableMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.SouthTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.SpawnerMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.StorageMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.TNTMinecartTerm;
import com.quartercode.minecartrevolution.basicexpressions.term.WestTerm;
import com.quartercode.minecartrevolution.basicexpressions.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.core.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginInfo;

public class BasicExpressionsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicExpressionsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicExpressions");
    }

    @Override
    public void enable() {

        config = new BasicExpressionConfig(this);
        config.setDefaults();
        config.save();

        addExpressionCommand(new AnnounceCommand(this));
        addExpressionCommand(new ChestCommand());
        addExpressionCommand(new ClearCommand());
        addExpressionCommand(new CollectCommand(this));
        addExpressionCommand(new CommandCommand());
        addExpressionCommand(new EffectCommand());
        addExpressionCommand(new EjectCommand());
        addExpressionCommand(new FarmCommand(this));
        addExpressionCommand(new FileCommand());
        addExpressionCommand(new FurnaceCommand());
        addExpressionCommand(new GrabCommand(this));
        addExpressionCommand(new HealthCommand());
        addExpressionCommand(new HoldCommand());
        addExpressionCommand(new IntersectionCommand(this));
        addExpressionCommand(new KillCommand());
        addExpressionCommand(new LockCommand());
        addExpressionCommand(new ReverseCommand());
        addExpressionCommand(new SensorCommand(this));
        addExpressionCommand(new SpeedCommand());
        addExpressionCommand(new TimeCommand());
        addExpressionCommand(new VerticalCommand());
        addExpressionCommand(new WeatherCommand());

        addExpressionConstant(new HealthConstant());
        addExpressionConstant(new PlayerConstant());
        addExpressionConstant(new SpeedConstant());
        addExpressionConstant(new TimeConstant());
        addExpressionConstant(new XConstant());
        addExpressionConstant(new YConstant());
        addExpressionConstant(new ZConstant());

        addMinecartTerm(new NorthTerm());
        addMinecartTerm(new EastTerm());
        addMinecartTerm(new SouthTerm());
        addMinecartTerm(new WestTerm());

        addMinecartTerm(new RideableMinecartTerm());
        addMinecartTerm(new StorageMinecartTerm());
        addMinecartTerm(new PoweredMinecartTerm());
        addMinecartTerm(new HopperMinecartTerm());
        addMinecartTerm(new TNTMinecartTerm());
        addMinecartTerm(new SpawnerMinecartTerm());
        addMinecartTerm(new CommandMinecartTerm());
        addMinecartTerm(new InventoryTerm());

        addMinecartTerm(new PassengerTerm());
        addMinecartTerm(new PlayerTerm());
        addMinecartTerm(new AnimalTerm());
        addMinecartTerm(new NPCTerm());
        addMinecartTerm(new MonsterTerm());
        addMinecartTerm(new MobTerm());

        addMinecartTerm(new ItemTerm());
        addMinecartTerm(new ItemHoldTerm());

        addMinecartTerm(new RandomTerm());
    }

}
