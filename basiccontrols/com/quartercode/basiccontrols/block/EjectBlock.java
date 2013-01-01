
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;

public class EjectBlock extends ControlBlock {

    public EjectBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.eject.name"), Lang.getValue("basiccontrols.blocks.eject.description"), "eject.place", "eject.destroy", Material.IRON_BLOCK.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        executeExpression(minecart, "eject");
    }

}
