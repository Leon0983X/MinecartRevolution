
package com.quartercode.basicexpression.constant;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstant;
import com.quartercode.minecartrevolution.util.expression.ExpressionConstantInfo;

public class PlayerConstant implements ExpressionConstant {

    public PlayerConstant() {

    }

    @Override
    public ExpressionConstantInfo getInfo() {

        return new ExpressionConstantInfo("p", "player");
    }

    @Override
    public Object getValue(final Minecart minecart) {

        if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            return ((Player) minecart.getPassenger()).getName();
        } else {
            return "Empty";
        }
    }

}
