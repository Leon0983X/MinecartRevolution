
package com.quartercode.basiccommands.command;

import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class EjectCommand extends MRCommandHandler {

    public EjectCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.eject.description"), "minecartrevolution.command.eject", "eject");
    }

    @Override
    public void execute(final Command command) {

        if (command.getSender() instanceof Player) {
            final Player player = (Player) command.getSender();

            if (player.getVehicle() != null) {
                player.getVehicle().eject();
            }
        }
    }

}
