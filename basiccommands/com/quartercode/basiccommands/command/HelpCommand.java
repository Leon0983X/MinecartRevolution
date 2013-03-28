
package com.quartercode.basiccommands.command;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandHandler;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class HelpCommand extends MRCommandHandler {

    public HelpCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.help.description"), "minecartrevolution.command.help", "<empty>", "help");
    }

    @Override
    public void execute(final Command command) {

        command.getSender().sendMessage(ChatColor.GREEN + "==========[ " + Lang.getValue("basiccommands.help.start") + " ]==========");

        String aliases = ChatColor.DARK_GREEN + "/minecartrevolution" + ChatColor.AQUA + ", ";
        for (final String alias : minecartRevolution.getPlugin().getCommand("minecartrevolution").getAliases()) {
            aliases += ChatColor.DARK_GREEN + "/" + alias + ChatColor.AQUA + ", ";
        }
        aliases = aliases.substring(0, aliases.length() - 2);
        command.getSender().sendMessage(Lang.getValue("basiccommands.help.aliases", "aliases", aliases));

        final List<CommandHandler> commandHandlers = minecartRevolution.getCommandExecutor().getCommandHandlers();

        for (final CommandHandler commandHandler : commandHandlers) {
            final CommandInfo info = commandHandler.getInfo();

            if (! (command.getSender() instanceof Player) || info.getPermission() == null || ((Player) command.getSender()).hasPermission(info.getPermission())) {
                for (final String label : info.getLabels()) {
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
