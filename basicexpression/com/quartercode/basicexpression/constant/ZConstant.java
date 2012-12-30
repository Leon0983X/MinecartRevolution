
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class ZConstant implements ExpressionConstant {

    public ZConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("z");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return minecart.getLocation().getZ();
    }

}
