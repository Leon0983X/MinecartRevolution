
package com.quartercode.basiccommands.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import com.quartercode.basiccommands.util.VersionUtil;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.GlobalConfig;

public class ServerListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public ServerListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        minecartRevolution.getServer().getPluginManager().registerEvents(this, minecartRevolution);
    }

    @EventHandler
    public void onPluginEnable(final PluginEnableEvent event) {

        if ((Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.autoUpdate)) {
            VersionUtil.tryUpdate(null, Bukkit.getConsoleSender());
        } else if ((Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.checkVersionOnStart)) {
            if (VersionUtil.newVersionAvaiable()) {
                if (Bukkit.getConsoleSender() != null) {
                    Bukkit.getConsoleSender().sendMessage(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", VersionUtil.getLatestVersion().getVersionString(), "updateCommand", "mr update"));
                } else {
                    minecartRevolution.getLogger().info(Lang.getValue("basiccommands.versioncheck.newVersion", "newVersion", VersionUtil.getLatestVersion().getVersionString(), "updateCommand", "mr update"));
                }
            }
        }
    }

}
