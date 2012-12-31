
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class CommandSign extends ControlSign {

    public CommandSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.command.name"), Lang.getValue("basiccontrols.signs.command.description"), "command.place", "command.destroy", "command");
    }

    @Override
    public void execute(final Minecart minecart, final Location signLocation, final String label, final Sign sign) {

        executeExpression(minecart, "command " + sign.getLine(1) + sign.getLine(2) + sign.getLine(3));
    }

}
