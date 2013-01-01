
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class YConstant extends ExpressionConstant {

    public YConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("y");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return minecart.getLocation().getZ();
    }

}
