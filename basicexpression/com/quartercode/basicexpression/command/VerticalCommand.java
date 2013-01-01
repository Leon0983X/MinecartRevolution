
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class VerticalCommand extends ExpressionCommand {

    public VerticalCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo("v", "vert", "vertical");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (parameter != null && parameter instanceof Double) {
            final Location location = minecart.getLocation();
            location.add(0, (Double) parameter, 0);
            minecart.teleport(location);
        }
    }

}
