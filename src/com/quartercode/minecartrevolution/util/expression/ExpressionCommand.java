
package com.quartercode.minecartrevolution.util.expression;

import org.bukkit.entity.Minecart;

public interface ExpressionCommand {

    public ExpressionCommandInfo getInfo();

    public boolean canExecute(Minecart minecart);

    public void execute(Minecart minecart, Object parameter);

}
