
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;

public class KillBlock extends ControlBlock {

    public KillBlock() {

    }

    @Override
    public ControlBlockInfo getInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.kill.name"), Lang.getValue("basiccontrols.blocks.kill.description"), "kill.place", "kill.destroy", Material.MOSSY_COBBLESTONE.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        executeExpression(minecart, "kill");
    }

}
