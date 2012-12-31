
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;

public class BrakeBlock extends ControlBlock {

    public BrakeBlock() {

        info = new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.brake.name"), Lang.getValue("basiccontrols.blocks.brake.description"), "brake.place", "brake.destroy", Material.SOUL_SAND.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        executeExpression(minecart, "speed $speed / 5");
    }

}
