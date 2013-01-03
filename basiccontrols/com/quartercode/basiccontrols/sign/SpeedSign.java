
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class SpeedSign extends ControlSign {

    public SpeedSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.speed.name"), Lang.getValue("basiccontrols.signs.speed.description"), "speed.place", "speed.destroy", "speed");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        executeExpression(minecart, "speed " + sign.getLine(1));
    }

}
