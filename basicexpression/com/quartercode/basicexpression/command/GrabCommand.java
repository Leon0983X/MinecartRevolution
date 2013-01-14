
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class GrabCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public GrabCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "g", "grab");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() == null;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        double radius = 5;

        if (parameter != null) {
            if (plugin.getConfiguration().getLong(BasicExpressionConfig.GRAB_MAX_RADIUS) < 0 || (Double) parameter <= plugin.getConfiguration().getLong(BasicExpressionConfig.GRAB_MAX_RADIUS)) {
                radius = (Double) parameter;
            }
        }

        for (final Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Player) {
                minecart.setPassenger(entity);
                return;
            }
        }
    }

}
