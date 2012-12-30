
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class YConstant implements ExpressionConstant {

    public YConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("y");
    }

    @Override
    public Object getValue(Minecart minecart) {

        return minecart.getLocation().getZ();
    }

}
