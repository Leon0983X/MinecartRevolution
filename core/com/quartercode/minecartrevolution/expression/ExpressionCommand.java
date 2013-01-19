
package com.quartercode.minecartrevolution.expression;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;

public abstract class ExpressionCommand {

    protected MinecartRevolution  minecartRevolution;
    private ExpressionCommandInfo info;

    public void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    public final ExpressionCommandInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    public void enable() {

    }

    protected abstract ExpressionCommandInfo createInfo();

    public abstract boolean canExecute(Minecart minecart);

    public abstract void execute(Minecart minecart, Object parameter);

}
