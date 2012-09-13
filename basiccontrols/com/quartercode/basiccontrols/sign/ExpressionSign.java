
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;
import com.quartercode.minecartrevolution.util.expression.MRExpressionExecutor;

public class ExpressionSign extends ControlSign {

    public ExpressionSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.expression.name"), Lang.getValue("basiccontrols.signs.expression.description"), "expression.place", "expression.destroy", "control", "script", "expression");
    }

    @Override
    public void execute(Minecart minecart, Location signLocation, String label, Sign sign) {

        String[] lines = { sign.getLine(1), sign.getLine(2), sign.getLine(3) };
        String expression = MRExpressionExecutor.getExpression(lines);
        if (expression == null || expression.isEmpty()) {
            return;
        }

        executeExpression(minecart, expression);
    }

}
