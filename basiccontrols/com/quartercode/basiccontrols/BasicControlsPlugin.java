
package com.quartercode.basiccontrols;

import com.quartercode.basiccontrols.block.BoosterBlock;
import com.quartercode.basiccontrols.block.BrakeBlock;
import com.quartercode.basiccontrols.block.ControlSignBlock;
import com.quartercode.basiccontrols.block.EjectBlock;
import com.quartercode.basiccontrols.block.ElevatorBlock;
import com.quartercode.basiccontrols.block.KillBlock;
import com.quartercode.basiccontrols.block.ReverseBlock;
import com.quartercode.basiccontrols.sign.AnnounceSign;
import com.quartercode.basiccontrols.sign.ClearSign;
import com.quartercode.basiccontrols.sign.CollectSign;
import com.quartercode.basiccontrols.sign.CommandSign;
import com.quartercode.basiccontrols.sign.EffectSign;
import com.quartercode.basiccontrols.sign.ExpressionSign;
import com.quartercode.basiccontrols.sign.FileSign;
import com.quartercode.basiccontrols.sign.GrabSign;
import com.quartercode.basiccontrols.sign.HealthSign;
import com.quartercode.basiccontrols.sign.IntersectionSign;
import com.quartercode.basiccontrols.sign.JumpSign;
import com.quartercode.basiccontrols.sign.SpeedSign;
import com.quartercode.basiccontrols.sign.TimeSign;
import com.quartercode.basiccontrols.sign.WeatherSign;
import com.quartercode.basiccontrols.util.BasicControlsConfig;
import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginInfo;
import com.quartercode.qcutil.version.Version;

public class BasicControlsPlugin extends MinecartRevolutionPlugin {

    public BasicControlsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicControls", new Version("1.0"));
    }

    @Override
    public void onEnable() {

        config = new BasicControlsConfig(this);
        config.setDefaults();
        config.save();

        addControlBlock(new BoosterBlock());
        addControlBlock(new BrakeBlock());
        addControlBlock(new ControlSignBlock(this));
        addControlBlock(new EjectBlock());
        addControlBlock(new ElevatorBlock());
        addControlBlock(new KillBlock());
        addControlBlock(new ReverseBlock());

        addControlSign(new AnnounceSign());
        addControlSign(new ClearSign());
        addControlSign(new CollectSign());
        addControlSign(new CommandSign());
        addControlSign(new EffectSign());
        addControlSign(new ExpressionSign());
        addControlSign(new FileSign());
        addControlSign(new GrabSign());
        addControlSign(new HealthSign());
        addControlSign(new IntersectionSign());
        addControlSign(new JumpSign());
        addControlSign(new SpeedSign());
        addControlSign(new TimeSign());
        addControlSign(new WeatherSign());

        ((BasicControlsConfig) config).addBlocks(getControlBlockExecutor().getControlBlocks());
        config.save();
    }

}
