
package com.quartercode.basicexpression.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class HoldCommand extends ExpressionCommand {

    private final MinecartRevolution minecartRevolution;

    public HoldCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo("ho", "hold");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (parameter instanceof Double) {
            final double time = (Double) parameter;

            if (time > 0) {
                final Vector oldVelocity = minecart.getVelocity();
                minecart.setVelocity(new Vector(0, 0, 0));

                Bukkit.getScheduler().scheduleSyncDelayedTask(minecartRevolution, new Runnable() {

                    @Override
                    public void run() {

                        minecart.setVelocity(oldVelocity);
                    }
                }, (long) (time * 1000D / 50D));
            }
        }
    }

}