
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.expression.ExpressionConstantInfo;

public class XConstant extends ExpressionConstant {

    public XConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("x");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return minecart.getLocation().getX();
    }

}
