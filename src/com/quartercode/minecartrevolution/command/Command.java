
package com.quartercode.minecartrevolution.command;

import org.bukkit.command.CommandSender;
import com.quartercode.qcutil.args.Arguments;

public abstract class Command {

    public abstract CommandInfo getCommandInfo();

    public abstract void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments);

}
