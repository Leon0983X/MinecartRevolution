
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class TimeConstant extends ExpressionConstant {

    public TimeConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("t", "time");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return minecart.getWorld().getTime();
    }

}
