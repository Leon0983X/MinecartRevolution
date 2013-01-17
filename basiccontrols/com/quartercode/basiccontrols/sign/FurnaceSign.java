
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class FurnaceSign extends ControlSign {

    public FurnaceSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.furnace.name"), Lang.getValue("basiccontrols.signs.furnace.description"), "furnace.place", "furnace.destroy", "furnace", "smelt");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        executeExpression(minecart, "furnace " + sign.getLine(1));
        executeExpression(minecart, "furnace " + sign.getLine(2));
        executeExpression(minecart, "furnace " + sign.getLine(3));
    }

}
