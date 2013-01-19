
package com.quartercode.basiccommands.listener;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import com.quartercode.basiccommands.util.MinecartRevolutionUpdater;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.Updater;

public class ServerListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public ServerListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onPluginEnable(final PluginEnableEvent event) {

        if (event.getPlugin().equals(minecartRevolution)) {
            final Updater updater = new MinecartRevolutionUpdater(minecartRevolution);
            if (minecartRevolution.getConfiguration().getBool(GlobalConfig.AUTO_UPDATE)) {
                updater.tryInstall();
            } else if (minecartRevolution.getConfiguration().getBool(GlobalConfig.CHECK_VERSION_ON_START)) {
                try {
                    if (updater.isNewVersionAvaiable()) {
                        if (Bukkit.getConsoleSender() != null) {
                            Bukkit.getConsoleSender().sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", updater.getLatestVersion(), "updateCommand", "mr update"));
                        } else {
                            minecartRevolution.getLogger().info(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", updater.getLatestVersion(), "updateCommand", "mr update"));
                        }
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
    }

}
