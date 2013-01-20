
package com.quartercode.basiccommands.command;

import com.quartercode.basiccommands.util.MinecartRevolutionUpdater;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class UpdateCommand extends MRCommandHandler {

    public UpdateCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.update.description"), "minecartrevolution.command.version.update", "update");
    }

    @Override
    public void execute(final Command command) {

        command.getSender().sendMessage(Lang.getValue("basiccommands.update.update"));
        command.getSender().sendMessage(Lang.getValue("basiccommands.update.download"));
        new MinecartRevolutionUpdater(minecartRevolution).tryInstall(command.getSender());
    }

}
