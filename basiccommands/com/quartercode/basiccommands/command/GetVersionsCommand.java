
package com.quartercode.basiccommands.command;

import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class GetVersionsCommand extends MRCommandHandler {

    public GetVersionsCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.getversions.description"), "minecartrevolution.command.versions.get", "getversions", "getv", "versions");
    }

    @Override
    public void execute(final Command command) {

        for (final Updater updater : minecartRevolution.getUpdaters()) {
            command.getSender().sendMessage(Lang.getValue("basiccommands.getversions.return", "plugin", updater.getUpdatePlugin().getName(), "pluginVersion", updater.getUpdatePlugin().getDescription().getVersion()));
        }
    }

}
