
package com.quartercode.basiccommands.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class RemovecartsCommand extends Command {

    public RemovecartsCommand() {

    }

    @Override
    public CommandInfo getCommandInfo() {

        return new CommandInfo(true, "[-w World1,World2...] [-r Radius]", Lang.getValue("basiccommands.removecarts.description"), "removecarts", "removecarts", "delcarts");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        List<World> worlds = new ArrayList<World>();
        int radius = -1;

        if (arguments.isParameterSet("w", true)) {
            if (arguments.getParameter("w", true).equalsIgnoreCase("all")) {
                worlds.addAll(Bukkit.getWorlds());
            } else {
                for (String worldName : arguments.getParameter("w", true).split(",")) {
                    if (Bukkit.getWorld(worldName) != null) {
                        worlds.add(Bukkit.getWorld(worldName));
                    }
                }
            }
        } else {
            if (commandSender instanceof Player) {
                worlds.add( ((Entity) commandSender).getWorld());
            } else {
                worlds.addAll(Bukkit.getWorlds());
            }
        }

        if (arguments.isParameterSet("r", true)) {
            try {
                radius = Integer.parseInt(arguments.getParameter("r", true));
            }
            catch (NumberFormatException e) {
                MinecartRevolution.handleSilenceThrowable(e);
            }
        }

        if (commandSender instanceof Player) {
            for (World world : worlds) {
                clear(world, radius, ((Player) commandSender).getLocation(), commandSender, commandSender.getName());
            }
        } else {
            for (World world : worlds) {
                clear(world, radius, new Location(world, 0, 0, 0), commandSender, "Console");
            }
        }
    }

    private void clear(World world, int radius, Location location, CommandSender commandSender, String sender) {

        int count = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Minecart) {
                if (entity.getLocation().distance(location) <= radius) {
                    entity.remove();
                    count++;
                } else if (radius < 0) {
                    entity.remove();
                    count++;
                }
            }
        }

        commandSender.sendMessage(Lang.getValue("basiccommands.removecarts.cleared", "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
        Bukkit.broadcastMessage(Lang.getValue("basiccommands.removecarts.broadcast", "sender", sender, "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
    }

}
