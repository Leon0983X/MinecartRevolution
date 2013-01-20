
package com.quartercode.basiccommands.util;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.exception.InstallException;

/**
 * This class is for checking the QuarterBukkit-version and updating the plugin.
 */
public class MinecartRevolutionUpdater extends Updater {

    /**
     * Creates a new QuarterBukkit updater.
     * 
     * @param plugin The {@link QuarterBukkit}-{@link Plugin}.
     */
    public MinecartRevolutionUpdater(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution.getPlugin(), minecartRevolution.getPlugin(), "minecartrevolution");
    }

    @Override
    protected String parseVersion(final String title) {

        return title.replaceAll("MinecartRevolution ", "");
    }

    @Override
    protected void doInstall(final File downloadedFile, final CommandSender causer) throws IOException {

        if (causer != null) {
            causer.sendMessage(Lang.getValue("basiccommands.update.downloaded", "file", downloadedFile.getName()));
            causer.sendMessage(Lang.getValue("basiccommands.update.reload"));
        } else {
            plugin.getLogger().info(Lang.getValue("basiccommands.update.downloaded", "file", downloadedFile.getName()));
            plugin.getLogger().info(Lang.getValue("basiccommands.update.reload"));
        }

        try {
            Bukkit.getPluginManager().disablePlugin(plugin);
            Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().loadPlugin(new File("plugins", "MinecartRevolution.jar")));
        }
        catch (final Exception e) {
            QuarterBukkit.exception(new InstallException(plugin, e, Lang.getValue("basiccommands.update.error", "error", "Error while reloading: " + e.getLocalizedMessage())));
            return;
        }

        if (causer != null) {
            causer.sendMessage(Lang.getValue("basiccommands.update.reloaded"));
            causer.sendMessage(Lang.getValue("basiccommands.update.updated"));
        } else {
            plugin.getLogger().info(Lang.getValue("basiccommands.update.reloaded"));
            plugin.getLogger().info(Lang.getValue("basiccommands.update.updated"));
        }
    }

}
