
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class TimeCommand implements ExpressionCommand {

    public TimeCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("t", "time");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (parameter != null) {
            if (parameter instanceof Double) {
                minecart.getWorld().setTime( ((Double) parameter).longValue());
            } else if (parameter instanceof String) {
                if (String.valueOf(parameter).equalsIgnoreCase("d") || String.valueOf(parameter).equalsIgnoreCase("day")) {
                    minecart.getWorld().setTime(0);
                } else if (String.valueOf(parameter).equalsIgnoreCase("n") || String.valueOf(parameter).equalsIgnoreCase("night")) {
                    minecart.getWorld().setTime(12500);
                }
            }
        }
    }

}
