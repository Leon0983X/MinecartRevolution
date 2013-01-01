
package com.quartercode.basiccommands.command;

import org.bukkit.command.CommandSender;
import com.quartercode.basiccommands.util.VersionUtil;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class UpdateCommand extends Command {

    private final MinecartRevolution minecartRevolution;

    public UpdateCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    protected CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.update.description"), "version.update", "update");
    }

    @Override
    public void execute(final CommandSender commandSender, final String usedMrCommand, final String label, final Arguments arguments) {

        VersionUtil.forceUpdate(minecartRevolution.getLogger(), commandSender);
    }

}
