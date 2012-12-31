
package com.quartercode.basicexpression;

import com.quartercode.basicexpression.command.AnnounceCommand;
import com.quartercode.basicexpression.command.ClearCommand;
import com.quartercode.basicexpression.command.CollectCommand;
import com.quartercode.basicexpression.command.CommandCommand;
import com.quartercode.basicexpression.command.EffectCommand;
import com.quartercode.basicexpression.command.EjectCommand;
import com.quartercode.basicexpression.command.FileCommand;
import com.quartercode.basicexpression.command.GrabCommand;
import com.quartercode.basicexpression.command.HealthCommand;
import com.quartercode.basicexpression.command.IntersectionCommand;
import com.quartercode.basicexpression.command.KillCommand;
import com.quartercode.basicexpression.command.SpeedCommand;
import com.quartercode.basicexpression.command.TimeCommand;
import com.quartercode.basicexpression.command.VerticalCommand;
import com.quartercode.basicexpression.command.WeatherCommand;
import com.quartercode.basicexpression.constant.HealthConstant;
import com.quartercode.basicexpression.constant.SpeedConstant;
import com.quartercode.basicexpression.constant.TimeConstant;
import com.quartercode.basicexpression.constant.XConstant;
import com.quartercode.basicexpression.constant.YConstant;
import com.quartercode.basicexpression.constant.ZConstant;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginInfo;
import com.quartercode.qcutil.version.Version;

public class BasicExpressionPlugin extends MinecartRevolutionPlugin {

    public BasicExpressionPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicExpression", new Version("1.0"));
    }

    @Override
    public void onEnable() {

        config = new BasicExpressionConfig(this);
        config.setDefaults();
        config.save();

        addExpressionCommand(new AnnounceCommand());
        addExpressionCommand(new ClearCommand(minecartRevolution));
        addExpressionCommand(new CollectCommand(minecartRevolution));
        addExpressionCommand(new CommandCommand(minecartRevolution));
        addExpressionCommand(new EffectCommand());
        addExpressionCommand(new EjectCommand());
        addExpressionCommand(new FileCommand(getExpressionExecutor()));
        addExpressionCommand(new GrabCommand());
        addExpressionCommand(new HealthCommand());
        addExpressionCommand(new IntersectionCommand(minecartRevolution));
        addExpressionCommand(new KillCommand(minecartRevolution));
        addExpressionCommand(new SpeedCommand());
        addExpressionCommand(new TimeCommand());
        addExpressionCommand(new VerticalCommand());
        addExpressionCommand(new WeatherCommand());

        addExpressionConstant(new HealthConstant());
        addExpressionConstant(new SpeedConstant());
        addExpressionConstant(new TimeConstant());
        addExpressionConstant(new XConstant());
        addExpressionConstant(new YConstant());
        addExpressionConstant(new ZConstant());
    }

}
