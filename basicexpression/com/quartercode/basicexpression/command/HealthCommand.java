
package com.quartercode.basicexpression.command;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class HealthCommand extends ExpressionCommand {

    public HealthCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "he", "health");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() instanceof LivingEntity;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        double health = 20;

        if (parameter != null) {
            health = (Double) parameter;
        }

        if (health < 0) {
            health = 0;
        } else if (health > 20) {
            health = 20;
        }

        ((LivingEntity) minecart.getPassenger()).setHealth((int) health);
    }

}
