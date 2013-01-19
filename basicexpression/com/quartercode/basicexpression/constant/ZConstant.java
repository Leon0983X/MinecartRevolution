
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.expression.ExpressionConstantInfo;

public class ZConstant extends ExpressionConstant {

    public ZConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("z");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return minecart.getLocation().getZ();
    }

}
