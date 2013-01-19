
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
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class StopcartsCommand extends MRCommandHandler {

    public StopcartsCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, "[-w World1,World2...] [-r Radius]", Lang.getValue("basiccommands.stopcarts.description"), "minecartrevolution.command.stopcarts", "stopcarts");
    }

    @Override
    public void execute(final Command command) {

        final List<World> worlds = new ArrayList<World>();
        int radius = -1;

        final Arguments arguments = new Arguments(command.getArguments());

        if (arguments.isParameterSet("w", true)) {
            if (arguments.getParameter("w", true).equalsIgnoreCase("all")) {
                worlds.addAll(Bukkit.getWorlds());
            } else {
                for (final String worldName : arguments.getParameter("w", true).split(",")) {
                    if (Bukkit.getWorld(worldName) != null) {
                        worlds.add(Bukkit.getWorld(worldName));
                    }
                }
            }
        } else {
            if (command.getSender() instanceof Player) {
                worlds.add( ((Entity) command.getSender()).getWorld());
            } else {
                worlds.addAll(Bukkit.getWorlds());
            }
        }

        if (arguments.isParameterSet("r", true)) {
            try {
                radius = Integer.parseInt(arguments.getParameter("r", true));
            }
            catch (final NumberFormatException e) {
                QuarterBukkit.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Failed to parse stopcarts radius: " + radius));
            }
        }

        if (command.getSender() instanceof Player) {
            for (final World world : worlds) {
                stop(world, radius, ((Player) command.getSender()).getLocation(), command.getSender(), command.getSender().getName());
            }
        } else {
            for (final World world : worlds) {
                stop(world, radius, new Location(world, 0, 0, 0), command.getSender(), "Console");
            }
        }
    }

    private void stop(final World world, final int radius, final Location location, final CommandSender commandSender, final String sender) {

        int count = 0;
        for (final Entity entity : world.getEntities()) {
            if (entity instanceof Minecart) {
                if (entity.getLocation().distance(location) <= radius) {
                    entity.setVelocity(new Vector(0, 0, 0));
                    count++;
                } else if (radius < 0) {
                    entity.setVelocity(new Vector(0, 0, 0));
                    count++;
                }
            }
        }

        commandSender.sendMessage(Lang.getValue("basiccommands.stopcarts.stopped", "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
        Bukkit.broadcastMessage(Lang.getValue("basiccommands.stopcarts.broadcast", "sender", sender, "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
    }

}
