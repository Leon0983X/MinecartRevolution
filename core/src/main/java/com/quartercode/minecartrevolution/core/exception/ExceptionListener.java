/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.exception;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.util.config.GlobalConfig;
import com.quartercode.quarterbukkit.api.exception.InstallException;
import com.quartercode.quarterbukkit.api.exception.NoCommandFoundException;
import com.quartercode.quarterbukkit.api.exception.NoCommandPermissionException;

public class ExceptionListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public ExceptionListener(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void minecartRevolutionException(MinecartRevolutionException exception) {

        if (exception instanceof SilentMinecartRevolutionException) {
            if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PRINT_SILENT_ERROR_MESSAGES)) {
                if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PRINT_SILENT_ERROR_STACK_TRACES) && exception.getCause() != null) {
                    minecartRevolution.getLogger().log(Level.SEVERE, exception.getMessage(), exception.getCause());
                } else {
                    minecartRevolution.getLogger().severe(exception.getMessage());
                }
            }
        } else {
            if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PRINT_ERROR_MESSAGES)) {
                if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PRINT_ERROR_STACK_TRACES) && exception.getCause() != null) {
                    minecartRevolution.getLogger().log(Level.SEVERE, exception.getMessage(), exception.getCause());
                } else {
                    minecartRevolution.getLogger().severe(exception.getMessage());
                }
            }
        }
    }

    @EventHandler
    public void installException(InstallException exception) {

        if (exception.getCauser() != null) {
            exception.getCauser().sendMessage(ChatColor.RED + "Can't update " + exception.getPlugin().getName() + ": " + exception.getMessage());
        } else {
            minecartRevolution.getPlugin().getLogger().warning("Can't update " + exception.getPlugin().getName() + ": " + exception.getMessage());
        }
    }

    @EventHandler
    public void noCommandPermissionException(NoCommandPermissionException exception) {

        exception.getCauser().sendMessage(MinecartRevolution.getLang().get("command.noPermission"));
    }

    @EventHandler
    public void noCommandFoundException(NoCommandFoundException exception) {

        exception.getCommand().getSender().sendMessage(MinecartRevolution.getLang().get("command.noCommand", "label", exception.getCommand().getLabel()));
    }

}
