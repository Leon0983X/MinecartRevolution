
package com.quartercode.basiccommands.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.quartercode.basiccommands.util.VersionUtil;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.minecartrevolution.util.GlobalConfig;

public class PlayerListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public PlayerListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        minecartRevolution.getServer().getPluginManager().registerEvents(this, minecartRevolution);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (Perm.has(player, "update.update") && (Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.checkVersionOnJoin)) {
            if (VersionUtil.newVersionAvaiable()) {
                player.sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", VersionUtil.getLatestVersion().getVersionString(), "updateCommand", "/mr update"));
            }
        }
    }

}
