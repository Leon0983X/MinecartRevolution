
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class TimeCommand extends ExpressionCommand {

    public TimeCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING, Type.DOUBLE), "t", "time");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (Type.DOUBLE.isInstance(parameter)) {
            minecart.getWorld().setTime( ((Double) parameter).longValue());
        } else {
            if (String.valueOf(parameter).equalsIgnoreCase("d") || String.valueOf(parameter).equalsIgnoreCase("day")) {
                minecart.getWorld().setTime(0);
            } else if (String.valueOf(parameter).equalsIgnoreCase("n") || String.valueOf(parameter).equalsIgnoreCase("night")) {
                minecart.getWorld().setTime(12500);
            }
        }
    }

}
