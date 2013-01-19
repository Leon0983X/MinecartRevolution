
package com.quartercode.basiccommands.command;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import com.quartercode.basiccommands.util.MinecartRevolutionUpdater;
import com.quartercode.minecartrevolution.command.MRCommandHandler;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class VersioncheckCommand extends MRCommandHandler {

    public VersioncheckCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, Lang.getValue("basiccommands.versioncheck.description"), "minecartrevolution.command.version.check", "versioncheck", "checkversion", "checkv");
    }

    @Override
    public void execute(final Command command) {

        final Updater updater = new MinecartRevolutionUpdater(minecartRevolution);

        try {
            if (updater.isNewVersionAvaiable()) {
                command.getSender().sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", updater.getLatestVersion(), "updateCommand", "/mr update"));
            }
        }
        catch (final IOException e) {
            QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Versioncheck: Something went wrong with the file system"));
        }
        catch (final XMLStreamException e) {
            QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Versioncheck: Something went wrong with the version XML-feed"));
        }
    }

}
