
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.MinecartUtil;

public class BoosterBlock extends ControlBlock {

    public BoosterBlock() {

        info = new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.booster.name"), Lang.getValue("basiccontrols.blocks.booster.description"), "booster.place", "booster.destroy", Material.GOLD_BLOCK.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        executeExpression(minecart, "speed $speed * 5");

        if (MinecartUtil.getSpeed(minecart) >= 1000) {
            MinecartUtil.setSpeed(minecart, 1000);
        }
    }

}
