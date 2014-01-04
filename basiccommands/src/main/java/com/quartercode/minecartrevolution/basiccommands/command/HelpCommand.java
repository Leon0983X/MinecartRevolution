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

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandHandler;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class HelpCommand extends MRCommandHandler {

    public HelpCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, MinecartRevolution.getLang().get("basiccommands.help.description"), "minecartrevolution.command.help", "<empty>", "help");
    }

    @Override
    public void execute(Command command) {

        command.getSender().sendMessage(ChatColor.GREEN + "==========[ " + MinecartRevolution.getLang().get("basiccommands.help.start") + " ]==========");

        String aliases = ChatColor.DARK_GREEN + "/minecartrevolution" + ChatColor.AQUA + ", ";
        for (String alias : minecartRevolution.getPlugin().getCommand("minecartrevolution").getAliases()) {
            aliases += ChatColor.DARK_GREEN + "/" + alias + ChatColor.AQUA + ", ";
        }
        aliases = aliases.substring(0, aliases.length() - 2);
        command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.help.aliases", "aliases", aliases));

        List<CommandHandler> commandHandlers = minecartRevolution.getCommandExecutor().getCommandHandlers();

        for (CommandHandler commandHandler : commandHandlers) {
            CommandInfo info = commandHandler.getInfo();

            if (! (command.getSender() instanceof Player) || info.getPermission() == null || ((Player) command.getSender()).hasPermission(info.getPermission())) {
                for (String label : info.getLabels()) {
                    String printLabel = "";
                    if (!label.equalsIgnoreCase("<empty>")) {
                        printLabel = " " + label;
                    }

                    String parameterUsage = "";
                    if (info.getParameterUsage() != null && !info.getParameterUsage().isEmpty()) {
                        parameterUsage = " " + info.getParameterUsage();
                    }

                    command.getSender().sendMessage(ChatColor.GOLD + "/" + command.getGlobalLabel() + printLabel + parameterUsage);
                }

                command.getSender().sendMessage(ChatColor.DARK_RED + "  > " + ChatColor.GRAY + info.getDescription());
            }
        }
    }

}
