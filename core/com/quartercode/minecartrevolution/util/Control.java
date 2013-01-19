
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;

public abstract class Control {

    protected MinecartRevolution minecartRevolution;

    public void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    public void enable() {

    }

    protected boolean executeExpression(final Minecart minecart, final String expression) {

        if (expression != null && !expression.isEmpty()) {
            minecartRevolution.getExpressionExecutor().execute(minecart, expression);
            return true;
        } else {
            return false;
        }
    }

}
