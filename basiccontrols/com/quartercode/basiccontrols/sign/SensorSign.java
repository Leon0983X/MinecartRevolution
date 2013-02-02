
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class SensorSign extends ControlSign {

    public SensorSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.sensor.name"), Lang.getValue("basiccontrols.signs.sensor.description"), "sensor.place", "sensor.destroy", "sensor");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        String expression = "sensor ";

        if (!sign.getLine(1).isEmpty()) {
            expression += sign.getLine(1);
        }
        if (!sign.getLine(2).isEmpty()) {
            if (!sign.getLine(1).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(2);
        }
        if (!sign.getLine(3).isEmpty()) {
            if (!sign.getLine(1).isEmpty() || !sign.getLine(2).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(3);
        }

        executeExpression(minecart, expression);
    }

}
