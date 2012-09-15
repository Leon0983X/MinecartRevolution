
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class EjectCommand implements ExpressionCommand {

    public EjectCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("e", "eject");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart.getPassenger() != null && minecart.getPassenger() instanceof Entity;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        minecart.eject();
    }

}