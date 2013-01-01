
package com.quartercode.minecartrevolution.util.expression;

import org.bukkit.entity.Minecart;

public abstract class ExpressionCommand {

    private ExpressionCommandInfo info;

    public final ExpressionCommandInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract ExpressionCommandInfo createInfo();

    public abstract boolean canExecute(Minecart minecart);

    public abstract void execute(Minecart minecart, Object parameter);

}
