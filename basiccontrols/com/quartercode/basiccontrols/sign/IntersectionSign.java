
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class IntersectionSign extends ControlSign {

    public IntersectionSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.intersection.name"), Lang.getValue("basiccontrols.signs.intersection.description"), "intersection.place", "intersection.destroy", "intersection");
    }

    @Override
    public void execute(final Minecart minecart, final Location signLocation, final String label, final Sign sign) {

        if (!sign.getLine(1).isEmpty()) {
            executeExpression(minecart, "intersection " + sign.getLine(1));
        }
        if (!sign.getLine(2).isEmpty()) {
            executeExpression(minecart, "intersection " + sign.getLine(2));
        }
        if (!sign.getLine(3).isEmpty()) {
            executeExpression(minecart, "intersection " + sign.getLine(3));
        }
    }

}
