
package com.quartercode.basiccommands.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class ClearCommand extends Command {

    public ClearCommand() {

    }

    @Override
    public CommandInfo getCommandInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.clear.description"), "clear", "clear", "delcarts");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        if (commandSender instanceof Entity) {
            clear( ((Entity) commandSender).getWorld(), commandSender, commandSender.getName());

        } else {
            for (World world : Bukkit.getWorlds()) {
                clear(world, commandSender, "Console");
            }
        }
    }

    private void clear(World world, CommandSender commandSender, String sender) {

        int count = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Minecart) {
                entity.remove();
                count++;
            }
        }

        commandSender.sendMessage(Lang.getValue("basiccommands.clear.cleared", "count", String.valueOf(count), "world", world.getName()));
        Bukkit.broadcastMessage(Lang.getValue("basiccommands.clear.broadcast", "sender", sender, "count", String.valueOf(count), "world", world.getName()));
    }

}
