
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class XConstant implements ExpressionConstant {

    public XConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("x");
    }

    @Override
    public Object getValue(Minecart minecart) {

        return minecart.getLocation().getX();
    }

}
