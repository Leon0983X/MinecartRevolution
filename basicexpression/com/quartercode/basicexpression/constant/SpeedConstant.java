
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class SpeedConstant extends ExpressionConstant {

    public SpeedConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("s", "speed");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        return MinecartUtil.getSpeed(minecart);
    }

}
