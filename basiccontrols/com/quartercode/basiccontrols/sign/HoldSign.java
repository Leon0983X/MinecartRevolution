
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class HoldSign extends ControlSign {

    public HoldSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.hold.name"), Lang.getValue("basiccontrols.signs.hold.description"), "hold.place", "hold.destroy", "hold");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        executeExpression(minecart, ("hold " + sign.getLine(1)).trim());
    }

}
