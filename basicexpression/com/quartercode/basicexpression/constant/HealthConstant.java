
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class HealthConstant extends ExpressionConstant {

    public HealthConstant() {

    }

    @Override
    protected ExpressionConstantInfo createInfo() {

        return new ExpressionConstantInfo("h", "health");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            return ((Player) minecart.getPassenger()).getHealth();
        } else {
            return 0;
        }
    }

}
