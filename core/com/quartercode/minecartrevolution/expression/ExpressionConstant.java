
package com.quartercode.minecartrevolution.expression;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;

public abstract class ExpressionConstant {

    protected MinecartRevolution   minecartRevolution;
    private ExpressionConstantInfo info;

    public void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    public final ExpressionConstantInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    public void enable() {

    }

    protected abstract ExpressionConstantInfo createInfo();

    public abstract Object getValue(Minecart minecart);

}
