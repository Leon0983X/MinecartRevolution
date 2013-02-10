
package com.quartercode.minecartrevolution.exception;

import org.bukkit.Bukkit;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.qcutil.QcUtil;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;
import com.quartercode.quarterbukkit.api.exception.GameException;
import com.quartercode.quarterbukkit.api.exception.InstallException;
import com.quartercode.quarterbukkit.api.exception.NoCommandFoundException;
import com.quartercode.quarterbukkit.api.exception.NoPermissionException;

public class MinecartRevolutionExceptionHandler extends ExceptionHandler {

    public MinecartRevolutionExceptionHandler(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution.getPlugin());
    }

    @Override
    public void handle(final GameException exception) {

        if (exception instanceof MinecartRevolutionException) {
            if (exception instanceof MinecartRevolutionSilenceException) {
                if ( ((MinecartRevolutionException) exception).getMinecartRevolution().getConfiguration().getBool(GlobalConfig.PRINT_SILENCE_ERRORS)) {
                    QcUtil.handleThrowable(exception);
                }
            } else {
                QcUtil.handleThrowable(exception);
            }
        } else if (exception instanceof InstallException) {
            Bukkit.getConsoleSender().sendMessage(exception.getLocalizedMessage());
        } else if (exception instanceof NoPermissionException) {
            ((NoPermissionException) exception).getCauser().sendMessage(Lang.getValue("command.noPermission"));
        } else if (exception instanceof NoCommandFoundException) {
            ((NoCommandFoundException) exception).getCommand().getSender().sendMessage(Lang.getValue("command.noCommand", "label", ((NoCommandFoundException) exception).getCommand().getLabel()));
        } else {
            Bukkit.getConsoleSender().sendMessage(exception.toString());
        }
    }

}
