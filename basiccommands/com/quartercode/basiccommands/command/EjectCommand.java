
package com.quartercode.basiccommands.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class EjectCommand extends Command {

    public EjectCommand() {

    }

    @Override
    public CommandInfo getCommandInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.eject.description"), "eject", "eject");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        if (commandSender instanceof Player) {
            ((Player) commandSender).getVehicle().eject();
        }
    }

}
