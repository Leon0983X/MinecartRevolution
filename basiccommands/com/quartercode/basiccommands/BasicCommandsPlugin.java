
package com.quartercode.basiccommands;

import com.quartercode.basiccommands.command.EjectCommand;
import com.quartercode.basiccommands.command.GetVersionCommand;
import com.quartercode.basiccommands.command.HelpCommand;
import com.quartercode.basiccommands.command.InfoCommand;
import com.quartercode.basiccommands.command.RemovecartsCommand;
import com.quartercode.basiccommands.command.StopcartsCommand;
import com.quartercode.basiccommands.command.UpdateCommand;
import com.quartercode.basiccommands.command.VersioncheckCommand;
import com.quartercode.basiccommands.listener.PlayerListener;
import com.quartercode.basiccommands.listener.ServerListener;
import com.quartercode.basiccommands.util.BasicCommandsConfig;
import com.quartercode.minecartrevolution.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginInfo;
import com.quartercode.qcutil.version.Version;

public class BasicCommandsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicCommandsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicCommands", new Version("1.0"));
    }

    @Override
    public void enable() {

        config = new BasicCommandsConfig(this);
        config.setDefaults();
        config.save();

        new PlayerListener(getMinecartRevolution());
        new ServerListener(getMinecartRevolution());

        addCommand(new HelpCommand(getMinecartRevolution()));
        addCommand(new InfoCommand());
        addCommand(new GetVersionCommand());
        addCommand(new VersioncheckCommand());
        addCommand(new UpdateCommand(getMinecartRevolution()));
        addCommand(new RemovecartsCommand());
        addCommand(new StopcartsCommand());
        addCommand(new EjectCommand());
    }

}
