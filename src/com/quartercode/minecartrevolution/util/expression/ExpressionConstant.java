
package com.quartercode.minecartrevolution.util.expression;

import org.bukkit.entity.Minecart;

public abstract class ExpressionConstant {

    private ExpressionConstantInfo info;

    public final ExpressionConstantInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract ExpressionConstantInfo createInfo();

    public abstract Object getValue(Minecart minecart);

}
