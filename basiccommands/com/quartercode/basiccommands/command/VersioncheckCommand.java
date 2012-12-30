
package com.quartercode.basiccommands.command;

import org.bukkit.command.CommandSender;
import com.quartercode.basiccommands.util.VersionUtil;
import com.quartercode.minecartrevolution.command.Command;
import com.quartercode.minecartrevolution.command.CommandInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.args.Arguments;

public class VersioncheckCommand extends Command {

    public VersioncheckCommand() {

    }

    @Override
    public CommandInfo getInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.versioncheck.description"), "version.versioncheck", "versioncheck", "checkversion", "checkv");
    }

    @Override
    public void execute(final CommandSender commandSender, final String usedMrCommand, final String label, final Arguments arguments) {

        if (VersionUtil.newVersionAvaiable()) {
            commandSender.sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", VersionUtil.getLatestVersion().getVersionString(), "updateCommand", "/mr update"));
        }
    }

}
