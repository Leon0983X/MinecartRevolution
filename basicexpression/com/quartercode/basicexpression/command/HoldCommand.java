
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class HoldCommand extends ExpressionCommand {

    public HoldCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "ho", "hold");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        double time = 10;
        if (Type.DOUBLE.isInstance(parameter)) {
            time = (Double) parameter;
        }

        if (time > 0) {
            final Vector oldVelocity = minecart.getVelocity();
            minecart.setVelocity(new Vector(0, 0, 0));

            new ScheduleTask(minecartRevolution.getPlugin()) {

                @Override
                public void run() {

                    minecart.setVelocity(oldVelocity);
                }
            }.run(true, (long) (time * 1000D));
        }
    }

}
