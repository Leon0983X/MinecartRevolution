
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class TimeConstant implements ExpressionConstant {

    public TimeConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("t", "time");
    }

    @Override
    public Object getValue(Minecart minecart) {

        return minecart.getWorld().getTime();
    }

}
