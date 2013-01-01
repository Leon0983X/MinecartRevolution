
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class HoldCommand extends ExpressionCommand {

    public HoldCommand() {

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

        double time = 10;

        if (parameter != null && parameter instanceof Double) {
            time = (Double) parameter;
        }

        if (time > 0) {
            time = 0;
        }
    }

}
