
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.expression.ExpressionConstantInfo;

public class HealthConstant extends ExpressionConstant {

    public HealthConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("h", "health");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        if (minecart.getPassenger() instanceof LivingEntity) {
            return ((LivingEntity) minecart.getPassenger()).getHealth();
        } else {
            return 0;
        }
    }

}
