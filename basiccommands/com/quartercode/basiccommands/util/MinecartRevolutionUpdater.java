
package com.quartercode.basiccommands.util;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;
import com.quartercode.quarterbukkit.api.exception.InstallException;

public class MinecartRevolutionUpdater extends Updater {

    public MinecartRevolutionUpdater(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution.getPlugin(), minecartRevolution.getPlugin(), "minecartrevolution");
    }

    @Override
    protected String parseVersion(final String title) {

        return title.replaceAll("MinecartRevolution ", "");
    }

    @Override
    protected boolean doInstall(final File downloadedFile, final CommandSender causer) throws IOException {

        try {
            Bukkit.getPluginManager().disablePlugin(updatePlugin);
            Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().loadPlugin(new File("plugins", "MinecartRevolution.jar")));
            return true;
        }
        catch (final Exception e) {
            ExceptionHandler.exception(new InstallException(plugin, this, e, Lang.getValue("basiccommands.update.error", "plugin", updatePlugin.getName(), "error", "Error while reloading: " + e.getLocalizedMessage())));
        }

        return false;
    }

}
