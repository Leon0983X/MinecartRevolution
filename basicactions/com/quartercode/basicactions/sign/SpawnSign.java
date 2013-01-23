
package com.quartercode.basicactions.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class SpawnSign extends ControlSign {

    public SpawnSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basicactions.spawn.name"), Lang.getValue("basicactions.spawn.description"), "spawn.place", "spawn.destroy", "spawn");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

    }

}
