
package com.quartercode.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class IntersectionSign extends ControlSign {

    public IntersectionSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.intersection.name"), Lang.getValue("basiccontrols.signs.intersection.description"), "intersection.place", "intersection.destroy", "intersection");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

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

    @Override
    public boolean allowPlace(final Player player, final String[] lines, final Sign sign) {

        for (final String line : lines) {
            if (line.split(":").length == 2) {
                final String action = line.split(":")[1];
                if (action.toLowerCase().startsWith("c-") || action.toLowerCase().startsWith("cmd-") || action.toLowerCase().startsWith("command-")) {
                    if (!Perm.has(player, "control.sign.intersection.command")) {
                        return false;
                    }
                } else if (!action.equalsIgnoreCase("r") && !action.equalsIgnoreCase("l") && !action.equalsIgnoreCase("m") && !action.equalsIgnoreCase("re") && !action.equalsIgnoreCase("n") && !action.equalsIgnoreCase("e") && !action.equalsIgnoreCase("s") && !action.equalsIgnoreCase("w")) {
                    if (!Perm.has(player, "control.sign.intersection.expression")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
