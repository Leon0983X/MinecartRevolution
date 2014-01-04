/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.get.Perm;
import com.quartercode.minecartrevolution.core.util.config.GlobalConfig;

public class PlayerListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public PlayerListener(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if (minecartRevolution.getConfiguration().getBool(GlobalConfig.AUTO_UPDATE)) {
            new Thread() {

                @Override
                public void run() {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecartrevolution update");
                }
            }.start();
        }

        final Player player = event.getPlayer();
        if (Perm.has(player, "command.versions.check") && minecartRevolution.getConfiguration().getBool(GlobalConfig.CHECK_VERSION_ON_JOIN) && !minecartRevolution.getConfiguration().getBool(GlobalConfig.AUTO_UPDATE)) {
            new Thread() {

                @Override
                public void run() {

                    Bukkit.dispatchCommand(player, "minecartrevolution checkversions");
                }
            }.start();
        }
    }

}
