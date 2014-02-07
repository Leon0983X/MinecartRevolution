/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.util.args.Arguments;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class RemovecartsCommand extends MRCommandHandler {

    public RemovecartsCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, "[-w World1,World2...] [-r Radius]", MinecartRevolution.getLang().get("basiccommands.removecarts.description"), "minecartrevolution.command.removecarts", "removecarts", "delcarts");
    }

    @Override
    public void execute(Command command) {

        List<World> worlds = new ArrayList<World>();
        int radius = -1;
        Arguments arguments = new Arguments(command.getArguments());

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
            if (command.getSender() instanceof Player) {
                worlds.add( ((Entity) command.getSender()).getWorld());
            } else {
                worlds.addAll(Bukkit.getWorlds());
            }
        }

        if (arguments.isParameterSet("r", true)) {
            try {
                radius = Integer.parseInt(arguments.getParameter("r", true));
            } catch (NumberFormatException e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Failed to parse removecarts radius: " + radius));
            }
        }

        if (command.getSender() instanceof Player) {
            for (World world : worlds) {
                clear(world, radius, ((Player) command.getSender()).getLocation(), command.getSender(), command.getSender().getName());
            }
        } else {
            for (World world : worlds) {
                clear(world, radius, new Location(world, 0, 0, 0), command.getSender(), "Console");
            }
        }
    }

    private void clear(World world, int radius, Location location, CommandSender commandSender, String sender) {

        int count = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Minecart) {
                if (entity.getLocation().distance(location) <= radius || radius < 0) {
                    MinecartUtil.remove((Minecart) entity);
                    count++;
                }
            }
        }

        commandSender.sendMessage(MinecartRevolution.getLang().get("basiccommands.removecarts.cleared", "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
        Bukkit.broadcastMessage(MinecartRevolution.getLang().get("basiccommands.removecarts.broadcast", "sender", sender, "count", String.valueOf(count), "world", world.getName(), "radius", radius < 0 ? "unlimited" : String.valueOf(radius)));
    }

}
