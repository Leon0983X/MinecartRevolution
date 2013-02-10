
package com.quartercode.basiccommands.command;

import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class UpdateCommand extends MRCommandHandler {

    public UpdateCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.update.description"), "minecartrevolution.command.versions.update", "update");
    }

    @Override
    public void execute(final Command command) {

        for (final Updater updater : minecartRevolution.getUpdaters()) {
            command.getSender().sendMessage(Lang.getValue("basiccommands.update.update", "plugin", updater.getUpdatePlugin().getName()));
            if (updater.tryInstall(command.getSender())) {
                command.getSender().sendMessage(Lang.getValue("basiccommands.update.updated", "plugin", updater.getUpdatePlugin().getName()));
            }
        }
    }

}
