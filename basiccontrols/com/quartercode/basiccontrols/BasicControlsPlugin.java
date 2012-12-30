
package com.quartercode.basiccontrols;

import com.quartercode.basiccontrols.block.BoosterBlock;
import com.quartercode.basiccontrols.block.BrakeBlock;
import com.quartercode.basiccontrols.block.ControlSignBlock;
import com.quartercode.basiccontrols.sign.AnnounceSign;
import com.quartercode.basiccontrols.sign.ExpressionSign;
import com.quartercode.basiccontrols.sign.HealthSign;
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

        addControlBlock(new ControlSignBlock(this));
        addControlBlock(new BoosterBlock(this));
        addControlBlock(new BrakeBlock(this));

        addControlSign(new ExpressionSign());
        addControlSign(new AnnounceSign());
        addControlSign(new HealthSign());
        addControlSign(new TimeSign());
        addControlSign(new WeatherSign());
        addControlSign(new SpeedSign());
    }

}
