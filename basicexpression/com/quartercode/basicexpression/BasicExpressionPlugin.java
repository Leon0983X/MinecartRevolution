
package com.quartercode.basicexpression;

import com.quartercode.basicexpression.command.AnnounceCommand;
import com.quartercode.basicexpression.command.ClearCommand;
import com.quartercode.basicexpression.command.EjectCommand;
import com.quartercode.basicexpression.command.FileCommand;
import com.quartercode.basicexpression.command.HealthCommand;
import com.quartercode.basicexpression.command.SpeedCommand;
import com.quartercode.basicexpression.constant.SpeedConstant;
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

        return new PluginInfo("BasicExpression", new Version("0"));
    }

    @Override
    public void onEnable() {

        config = new BasicExpressionConfig(this);
        config.setDefaults();
        config.save();

        addExpressionCommand(new AnnounceCommand());
        addExpressionCommand(new FileCommand(getExpressionExecutor()));
        addExpressionCommand(new SpeedCommand());
        addExpressionCommand(new EjectCommand());
        addExpressionCommand(new ClearCommand(minecartRevolution));
        addExpressionCommand(new HealthCommand());

        addExpressionConstant(new SpeedConstant());
    }

}
