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
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.basiccommands.util.Patcher;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.get.LanguageBundle;
import com.quartercode.minecartrevolution.core.util.args.Arguments;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class PatchCommand extends MRCommandHandler {

    public PatchCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, "<Patcher> [-w World1,World2...] [-r Radius]", MinecartRevolution.getLang().get("basiccommands.patch.description"), "minecartrevolution.command.patch", "patch");
    }

    @Override
    public void execute(Command command) {

        if (command.getArguments().length < 1) {
            String patchers = "";
            for (Patcher patcher : Patcher.getPatchers()) {
                patchers += ChatColor.AQUA + patcher.getName() + LanguageBundle.DEFAULT_FORMAT + ",";
            }
            if (patchers.length() <= 0) {
                command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.patch.noPatchers"));
            } else {
                patchers = patchers.substring(0, patchers.length() - 1);
                command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.patch.patchers", "patchers", patchers));
            }

            return;
        }

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
            }
            catch (NumberFormatException e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Failed to parse patch radius: " + radius));
            }
        }

        for (World world : worlds) {
            patch(command.getSender(), command.getArguments()[0], world, radius);
        }
    }

    private void patch(CommandSender commandSender, String patcherName, World world, int radius) {

        List<Patcher> proposals = new ArrayList<Patcher>();
        for (Patcher patcher : Patcher.getPatchers()) {
            if (patcher.getName().toLowerCase().startsWith(patcherName.toLowerCase())) {
                proposals.add(patcher);
            }
        }

        if (proposals.size() == 1) {
            Patcher patcher = proposals.get(0);
            commandSender.sendMessage(MinecartRevolution.getLang().get("basiccommands.patch.start", "world", world.getName(), "patcher", patcher.getName()));
            int count = patcher.execute(commandSender, new World[] { world }, radius);
            commandSender.sendMessage(MinecartRevolution.getLang().get("basiccommands.patch.finish", "count", String.valueOf(count), "world", world.getName(), "patcher", patcher.getName()));
        } else {
            commandSender.sendMessage(MinecartRevolution.getLang().get("basiccommands.patch.noPatcher", "patcher", patcherName));
        }
    }

}
