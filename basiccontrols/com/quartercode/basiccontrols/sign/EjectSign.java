
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class EjectSign extends ControlSign {

    public EjectSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.eject.name"), Lang.getValue("basiccontrols.signs.eject.description"), "eject.place", "eject.destroy", "eject");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        executeExpression(minecart, "eject" + (sign.getLine(1).isEmpty() ? "" : " " + sign.getLine(1)));
    }

}
