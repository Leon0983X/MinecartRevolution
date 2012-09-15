
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class HealthCommand implements ExpressionCommand {

    public HealthCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("h", "health");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart.getPassenger() != null && minecart.getPassenger() instanceof Player;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            int health = 20;

            if (parameter != null && parameter instanceof Double) {
                health = (Integer) parameter;
            }

            ((Player) minecart.getPassenger()).setHealth(health);
        }
    }

}
