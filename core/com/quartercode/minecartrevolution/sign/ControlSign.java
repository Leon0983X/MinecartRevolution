
package com.quartercode.minecartrevolution.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.Control;

public abstract class ControlSign extends Control {

    private ControlSignInfo info;

    public final ControlSignInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract ControlSignInfo createInfo();

    public abstract void execute(Minecart minecart, Location signLocation, String label, Sign sign);

}
