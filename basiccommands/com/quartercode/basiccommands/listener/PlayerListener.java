
package com.quartercode.basiccommands.listener;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.quartercode.basiccommands.util.MinecartRevolutionUpdater;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.quarterbukkit.QuarterBukkit;
import com.quartercode.quarterbukkit.api.Updater;

public class PlayerListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public PlayerListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (Perm.has(player, "update.update") && minecartRevolution.getConfiguration().getBool(GlobalConfig.CHECK_VERSION_ON_JOIN)) {
            final Updater updater = new MinecartRevolutionUpdater(minecartRevolution);
            try {
                if (updater.isNewVersionAvaiable()) {
                    player.sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", updater.getLatestVersion(), "updateCommand", "/mr update"));
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
