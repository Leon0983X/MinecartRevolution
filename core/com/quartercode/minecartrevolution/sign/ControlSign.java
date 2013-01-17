
package com.quartercode.minecartrevolution.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
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

    public abstract void execute(Minecart minecart, String label, Sign sign);

    public boolean allowPlace(final Player player, String[] lines, final Sign sign) {

        return true;
    }

    public boolean allowDestroy(final Player player, String[] lines, final Sign sign) {

        return true;
    }

}
