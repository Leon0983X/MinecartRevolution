
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class SpeedConstant implements ExpressionConstant {

    public SpeedConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("s", "speed");
    }

    @Override
    public Object getValue(Minecart minecart) {

        return MinecartUtil.getSpeed(minecart);
    }

}
