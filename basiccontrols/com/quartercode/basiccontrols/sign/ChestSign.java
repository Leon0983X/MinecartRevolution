
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class ChestSign extends ControlSign {

    public ChestSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.chest.name"), Lang.getValue("basiccontrols.signs.chest.description"), "chest.place", "chest.destroy", "chest");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        executeExpression(minecart, "chest " + sign.getLine(1));
        executeExpression(minecart, "chest " + sign.getLine(2));
        executeExpression(minecart, "chest " + sign.getLine(3));
    }

}
