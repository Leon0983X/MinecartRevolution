
package com.quartercode.basiccommands.command;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;
import com.quartercode.quarterbukkit.api.exception.ExceptionManager;

public class VersioncheckCommand extends MRCommandHandler {

    public VersioncheckCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.versioncheck.description"), "minecartrevolution.command.versions.check", "versioncheck", "checkversions", "checkv");
    }

    @Override
    public void execute(final Command command) {

        for (final Updater updater : minecartRevolution.getUpdaters()) {
            try {
                if (updater.isNewVersionAvaiable()) {
                    command.getSender().sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "plugin", updater.getUpdatePlugin().getName(), "newVersion", updater.getLatestVersion(), "updateCommand", "/mr update"));
                } else {
                    command.getSender().sendMessage(Lang.getValue("basiccommands.versioncheck.latestVersion", "plugin", updater.getUpdatePlugin().getName()));
                }
            }
            catch (final IOException e) {
                ExceptionManager.exception(new MinecartRevolutionException(minecartRevolution, e, "Versioncheck: Something went wrong with the file system"));
            }
            catch (final XMLStreamException e) {
                ExceptionManager.exception(new MinecartRevolutionException(minecartRevolution, e, "Versioncheck: Something went wrong with the version XML-feed"));
            }
        }
    }

}
