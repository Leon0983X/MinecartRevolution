
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class GrabCommand implements ExpressionCommand {

    public GrabCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("g", "grab");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() == null;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (minecart.getPassenger() == null) {
            double radius = 5;

            if (parameter != null && parameter instanceof Double) {
                radius = (Double) parameter;
            }

            for (final Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
                if (entity instanceof Player) {
                    minecart.setPassenger(entity);
                    return;
                }
            }
        }
    }

}
