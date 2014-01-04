/*
 * This file is part of MinecartRevolution-Basiccontrols.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccontrols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccontrols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccontrols. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccontrols;

import com.quartercode.minecartrevolution.basiccontrols.block.BoosterBlock;
import com.quartercode.minecartrevolution.basiccontrols.block.BrakeBlock;
import com.quartercode.minecartrevolution.basiccontrols.block.ControlSignBlock;
import com.quartercode.minecartrevolution.basiccontrols.block.ElevatorBlock;
import com.quartercode.minecartrevolution.basiccontrols.block.KillBlock;
import com.quartercode.minecartrevolution.basiccontrols.block.ReverseBlock;
import com.quartercode.minecartrevolution.basiccontrols.sign.AnnounceSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.ChestSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.ClearSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.CollectSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.CommandSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.EffectSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.EjectSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.ExpressionSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.FarmSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.FileSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.FurnaceSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.GrabSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.HealthSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.HoldSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.IntersectionSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.JumpSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.LockSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.SensorSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.SpeedSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.StationSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.TimeSign;
import com.quartercode.minecartrevolution.basiccontrols.sign.WeatherSign;
import com.quartercode.minecartrevolution.basiccontrols.util.BasicControlsConfig;
import com.quartercode.minecartrevolution.core.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginInfo;

public class BasicControlsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicControlsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicControls");
    }

    @Override
    public void enable() {

        config = new BasicControlsConfig(this);
        config.setDefaults();
        config.save();

        addControlBlock(new BoosterBlock());
        addControlBlock(new BrakeBlock());
        addControlBlock(new ControlSignBlock());
        addControlBlock(new ElevatorBlock());
        addControlBlock(new KillBlock());
        addControlBlock(new ReverseBlock());

        addControlSign(new AnnounceSign());
        addControlSign(new ChestSign());
        addControlSign(new ClearSign());
        addControlSign(new CollectSign());
        addControlSign(new CommandSign());
        addControlSign(new EffectSign());
        addControlSign(new EjectSign());
        addControlSign(new ExpressionSign());
        addControlSign(new FarmSign());
        addControlSign(new FileSign());
        addControlSign(new FurnaceSign());
        addControlSign(new GrabSign());
        addControlSign(new HealthSign());
        addControlSign(new HoldSign());
        addControlSign(new IntersectionSign());
        addControlSign(new JumpSign());
        addControlSign(new LockSign());
        addControlSign(new SensorSign());
        addControlSign(new SpeedSign());
        addControlSign(new StationSign());
        addControlSign(new TimeSign());
        addControlSign(new WeatherSign());

        ((BasicControlsConfig) config).addBlocks(getControlBlockExecutor().getControlBlocks());
        config.save();
    }

}
