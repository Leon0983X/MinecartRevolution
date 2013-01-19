
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class VerticalCommand extends ExpressionCommand {

    public VerticalCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "v", "vert", "vertical");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        double height = 5;
        if (Type.DOUBLE.isInstance(parameter)) {
            height = (Double) parameter;
        }

        final Location location = minecart.getLocation();
        location.add(0, height, 0);
        minecart.teleport(location);
    }

}
