
package com.quartercode.basiccommands.command;

import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class GetVersionCommand extends Command {

    public GetVersionCommand() {

    }

    @Override
    public CommandInfo getInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.getversion.description"), "getversion", "getversion", "getv", "version");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        commandSender.sendMessage(Lang.getValue("basiccommands.getversion.return"));
    }

}
