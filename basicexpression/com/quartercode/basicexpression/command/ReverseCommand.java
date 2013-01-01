
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class ReverseCommand extends ExpressionCommand {

    public ReverseCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo("r", "reverse");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        final Vector vector = minecart.getVelocity();
        vector.setX(-vector.getX());
        vector.setZ(-vector.getZ());

        minecart.setVelocity(vector);
    }

}
