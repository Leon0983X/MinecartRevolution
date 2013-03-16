
package com.quartercode.minecartrevolution.exception;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.qcutil.QcUtil;
import com.quartercode.quarterbukkit.api.exception.InstallException;
import com.quartercode.quarterbukkit.api.exception.NoCommandFoundException;
import com.quartercode.quarterbukkit.api.exception.NoCommandPermissionException;

public class ExceptionListener implements Listener {

    private MinecartRevolution minecartRevolution;

    public ExceptionListener(final MinecartRevolution minecartRevolution) {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void minecartRevolutionException(final MinecartRevolutionException exception) {

        if (exception instanceof MinecartRevolutionSilenceException) {
            if (exception.getMinecartRevolution().getConfiguration().getBool(GlobalConfig.PRINT_SILENCE_ERRORS)) {
                QcUtil.handleThrowable(exception.getCause());
            }
        } else {
            QcUtil.handleThrowable(exception.getCause());
        }
    }

    @EventHandler
    public void installException(final InstallException exception) {

        if (exception.getCauser() != null) {
            exception.getCauser().sendMessage(ChatColor.RED + "Can't update QuarterBukkit: " + exception.getMessage());
        } else {
            minecartRevolution.getPlugin().getLogger().warning("Can't update QuarterBukkit: " + exception.getMessage());
        }
    }

    @EventHandler
    public void noCommandPermissionException(final NoCommandPermissionException exception) {

        exception.getCauser().sendMessage(Lang.getValue("command.noPermission"));
    }

    @EventHandler
    public void noCommandFoundException(final NoCommandFoundException exception) {

        exception.getCommand().getSender().sendMessage(Lang.getValue("command.noCommand", "label", exception.getCommand().getLabel()));
    }

}
