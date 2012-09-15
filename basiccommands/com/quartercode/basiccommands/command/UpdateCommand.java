
package com.quartercode.basiccommands.command;

import org.bukkit.command.CommandSender;
import com.quartercode.basiccommands.util.VersionUtil;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class UpdateCommand extends Command {

    private MinecartRevolution minecartRevolution;

    public UpdateCommand(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public CommandInfo getInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.update.description"), "update", "update");
    }

    @Override
    public void execute(CommandSender commandSender, String usedMrCommand, String label, Arguments arguments) {

        VersionUtil.forceUpdate(minecartRevolution.getLogger(), commandSender);
    }

}
