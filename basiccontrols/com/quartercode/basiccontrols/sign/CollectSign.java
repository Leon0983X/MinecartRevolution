
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class CollectSign extends ControlSign {

    public CollectSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.collect.name"), Lang.getValue("basiccontrols.signs.collect.description"), "collect.place", "collect.destroy", "collect");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        String expression = "";
        if (!sign.getLine(1).isEmpty()) {
            expression += "r" + sign.getLine(1);
        }

        if (!sign.getLine(2).isEmpty()) {
            if (!sign.getLine(1).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(2);
        }
        if (!sign.getLine(3).isEmpty()) {
            if (!sign.getLine(2).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(3);
        }

        executeExpression(minecart, "collect " + expression);
    }

}
