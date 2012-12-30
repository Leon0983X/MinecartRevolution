
package com.quartercode.minecartrevolution.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.qcutil.args.Arguments;

public class MRCommandExecutor implements CommandExecutor {

    private List<Command> commands;

    public MRCommandExecutor() {

        commands = new ArrayList<Command>();
    }

    public List<Command> getCommands() {

        return commands;
    }

    public void setCommands(final List<Command> commands) {

        this.commands = commands;
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final org.bukkit.command.Command command, final String commandLabel, final String[] arguments) {

        if (arguments.length > 0) {
            if (!executeCommand(commandSender, commandLabel, arguments[0], new Arguments(new ArrayList<String>(Arrays.asList(arguments)).subList(1, arguments.length)))) {
                commandSender.sendMessage(Lang.getValue("command.noCommand", "label", arguments[0]));
            }
        } else {
            for (final Command listCommand : commands) {
                final CommandInfo commandInfo = listCommand.getInfo();

                for (final String label : commandInfo.getLabels()) {
                    if (label.equalsIgnoreCase("<empty>")) {
                        if (canExecute(commandSender, listCommand)) {
                            listCommand.execute(commandSender, commandLabel, "", new Arguments());
                            return true;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean executeCommand(final CommandSender commandSender, final String usedMrCommand, final String commandLabel, final Arguments arguments) {

        for (final Command listCommand : commands) {
            final CommandInfo commandInfo = listCommand.getInfo();

            for (final String label : commandInfo.getLabels()) {
                boolean execute = false;

                if (commandInfo.isIgnoreCase()) {
                    if (label.equalsIgnoreCase(commandLabel)) {
                        execute = true;
                    }
                } else {
                    if (label.equals(commandLabel)) {
                        execute = true;
                    }
                }

                if (execute) {
                    if (canExecute(commandSender, listCommand)) {
                        listCommand.execute(commandSender, usedMrCommand, commandLabel, arguments);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canExecute(final CommandSender commandSender, final Command command) {

        if (commandSender instanceof Player) {
            if (Perm.has((Player) commandSender, command.getInfo().getPermission())) {
                return true;
            } else {
                commandSender.sendMessage(Lang.getValue("command.noPermission"));
                return false;
            }
        } else {
            return true;
        }
    }

}
