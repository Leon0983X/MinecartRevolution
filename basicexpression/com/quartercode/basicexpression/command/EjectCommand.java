
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class EjectCommand extends ExpressionCommand {

    public EjectCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE), "ej", "eject");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() instanceof Entity;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        minecart.eject();
    }

}
