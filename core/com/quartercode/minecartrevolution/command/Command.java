
package com.quartercode.minecartrevolution.command;

import org.bukkit.command.CommandSender;
import com.quartercode.qcutil.args.Arguments;

public abstract class Command {

    private CommandInfo info;

    public final CommandInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract CommandInfo createInfo();

    public abstract void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments);

}
