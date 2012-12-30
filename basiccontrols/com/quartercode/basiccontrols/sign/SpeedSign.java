
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class SpeedSign extends ControlSign {

    public SpeedSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.speed.name"), Lang.getValue("basiccontrols.signs.speed.description"), "speed.place", "speed.destroy", "speed", "Speed");
    }

    @Override
    public void execute(final Minecart minecart, final Location signLocation, final String label, final Sign sign) {

        executeExpression(minecart, "speed " + sign.getLine(1));
    }

}
