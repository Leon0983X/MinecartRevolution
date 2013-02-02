
package com.quartercode.basiccommands.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.quartercode.basiccommands.util.Patcher;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class PatchCommand extends MRCommandHandler {

    public PatchCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, "<Patcher> [-w World1,World2...] [-r Radius]", Lang.getValue("basiccommands.patch.description"), "minecartrevolution.command.patch", "patch");
    }

    @Override
    public void execute(final Command command) {

        if (command.getArguments().length < 1) {
            String patchers = "";
            for (final Patcher patcher : Patcher.getPatchers()) {
                patchers += ChatColor.AQUA + patcher.getName() + Lang.DEFAULT + ",";
            }
            if (patchers.length() <= 0) {
                command.getSender().sendMessage(Lang.getValue("basiccommands.patch.noPatchers"));
            } else {
                patchers = patchers.substring(0, patchers.length() - 1);
                command.getSender().sendMessage(Lang.getValue("basiccommands.patch.patchers", "patchers", patchers));
            }

            return;
        }

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
                QuarterBukkit.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Failed to parse patch radius: " + radius));
            }
        }

        for (final World world : worlds) {
            patch(command.getSender(), command.getArguments()[0], world, radius);
        }
    }

    private void patch(final CommandSender commandSender, final String patcherName, final World world, final int radius) {

        final List<Patcher> proposals = new ArrayList<Patcher>();
        for (final Patcher patcher : Patcher.getPatchers()) {
            if (patcher.getName().toLowerCase().startsWith(patcherName.toLowerCase())) {
                proposals.add(patcher);
            }
        }

        if (proposals.size() == 1) {
            final Patcher patcher = proposals.get(0);
            commandSender.sendMessage(Lang.getValue("basiccommands.patch.start", "world", world.getName(), "patcher", patcher.getName()));
            final int count = patcher.execute(commandSender, new World[] { world }, radius);
            commandSender.sendMessage(Lang.getValue("basiccommands.patch.finish", "count", String.valueOf(count), "world", world.getName(), "patcher", patcher.getName()));
        } else {
            commandSender.sendMessage(Lang.getValue("basiccommands.patch.noPatcher", "patcher", patcherName));
        }
    }

}
