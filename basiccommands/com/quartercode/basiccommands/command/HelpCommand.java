
package com.quartercode.basiccommands.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class HelpCommand extends Command {

    private MinecartRevolution minecartRevolution;

    public HelpCommand(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public CommandInfo getCommandInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.help.description"), "help", "<empty>", "help");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        showHelp(commandSender, usedMrCommand);
    }

    private void showHelp(CommandSender commandSender, String usedMrCommand) {

        List<Command> commands = minecartRevolution.getCommandExecutor().getCommands();
        List<String> helpStrings = new ArrayList<String>();

        for (Command command : commands) {
            CommandInfo commandInfo = command.getCommandInfo();

            for (String label : commandInfo.getLabels()) {
                String printLabel = "";
                if (!label.equalsIgnoreCase("<empty>")) {
                    printLabel = " " + label;
                }

                String parameterUsage = "";
                if (commandInfo.getParameterUsage() != null && !commandInfo.getParameterUsage().isEmpty()) {
                    parameterUsage = " " + commandInfo.getParameterUsage();
                }

                helpStrings.add(colorHelpMessage("/" + usedMrCommand + printLabel + parameterUsage, commandInfo.getDescription()));
            }
        }

        commandSender.sendMessage(ChatColor.GREEN + "========== [" + Lang.getValue("basiccommands.help.start") + "] ==========");
        for (String helpString : helpStrings) {
            commandSender.sendMessage(helpString);
        }
    }

    private String colorHelpMessage(String command, String description) {

        return ChatColor.DARK_GREEN + command + ChatColor.BLUE + " > " + ChatColor.DARK_AQUA + description;
    }

}
