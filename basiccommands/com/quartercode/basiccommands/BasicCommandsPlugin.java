
package com.quartercode.basiccommands;

import com.quartercode.basiccommands.command.ClearCommand;
import com.quartercode.basiccommands.command.HelpCommand;
import com.quartercode.basiccommands.listener.PlayerListener;
import com.quartercode.basiccommands.util.BasicCommandsConfig;
import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginInfo;
import com.quartercode.qcutil.version.Version;

public class BasicCommandsPlugin extends MinecartRevolutionPlugin {

    public BasicCommandsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicCommands", new Version("0"));
    }

    @Override
    public void onEnable() {

        config = new BasicCommandsConfig(this);
        config.setDefaults();
        config.save();

        new PlayerListener(minecartRevolution);

        addCommand(new HelpCommand(minecartRevolution));
        addCommand(new ClearCommand());
    }

}
