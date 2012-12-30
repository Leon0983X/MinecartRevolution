
package com.quartercode.basiccommands.command;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class HelpCommand extends Command {

    private final MinecartRevolution minecartRevolution;

    public HelpCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public CommandInfo getInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.help.description"), "help", "<empty>", "help");
    }

    @Override
    public void execute(final CommandSender commandSender, final String usedMrCommand, final String label, final Arguments arguments) {

        showHelp(commandSender, usedMrCommand);
    }

    private void showHelp(final CommandSender commandSender, final String usedMrCommand) {

        commandSender.sendMessage(ChatColor.GREEN + "==========[ " + Lang.getValue("basiccommands.help.start") + " ]==========");

        String aliases = ChatColor.DARK_GREEN + "/minecartrevolution" + ChatColor.AQUA + ", ";
        for (final String alias : minecartRevolution.getCommand("minecartrevolution").getAliases()) {
            aliases += ChatColor.DARK_GREEN + "/" + alias + ChatColor.AQUA + ", ";
        }
        aliases = aliases.substring(0, aliases.length() - 2);
        commandSender.sendMessage("Aliases: " + aliases);

        final List<Command> commands = minecartRevolution.getCommandExecutor().getCommands();

        for (final Command command : commands) {
            final CommandInfo commandInfo = command.getInfo();

            if (! (commandSender instanceof Player) || commandInfo.getPermission() == null || ((Player) commandSender).hasPermission(commandInfo.getPermission())) {
                for (final String label : commandInfo.getLabels()) {
                    String printLabel = "";
                    if (!label.equalsIgnoreCase("<empty>")) {
                        printLabel = " " + label;
                    }

                    String parameterUsage = "";
                    if (commandInfo.getParameterUsage() != null && !commandInfo.getParameterUsage().isEmpty()) {
                        parameterUsage = " " + commandInfo.getParameterUsage();
                    }

                    commandSender.sendMessage(ChatColor.GOLD + "/" + usedMrCommand + printLabel + parameterUsage);
                }
            }

            commandSender.sendMessage(ChatColor.DARK_RED + "  > " + ChatColor.GRAY + commandInfo.getDescription());
        }
    }

}
