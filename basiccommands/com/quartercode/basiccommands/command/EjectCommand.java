
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
    protected CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.eject.description"), "eject", "eject");
    }

    @Override
    public void execute(final CommandSender commandSender, final String usedMrCommand, final String label, final Arguments arguments) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;

            if (player.getVehicle() != null) {
                player.getVehicle().eject();
            }
        }
    }

}
