
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionExecutor;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class ExpressionSign extends ControlSign {

    public ExpressionSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.expression.name"), Lang.getValue("basiccontrols.signs.expression.description"), "expression.place", "expression.destroy", "revo", "control", "script", "expression");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        final String[] lines = { sign.getLine(1), sign.getLine(2), sign.getLine(3) };
        final String expression = ExpressionExecutor.getExpression(lines);
        if (expression == null || expression.isEmpty()) {
            return;
        }

        executeExpression(minecart, expression);
    }

}
