
package com.quartercode.basiccommands.command;

import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class GetVersionCommand extends MRCommandHandler {

    public GetVersionCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.getversion.description"), "minecartrevolution.command.version.get", "getversion", "getv", "version");
    }

    @Override
    public void execute(final Command command) {

        command.getSender().sendMessage(Lang.getValue("basiccommands.getversion.return"));
    }

}
