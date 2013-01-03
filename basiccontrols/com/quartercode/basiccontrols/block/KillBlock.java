
package com.quartercode.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.ItemData;

public class KillBlock extends ControlBlock {

    public KillBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.kill.name"), Lang.getValue("basiccontrols.blocks.kill.description"), "kill.place", "kill.destroy", new ItemData(Material.MOSSY_COBBLESTONE));
    }

    @Override
    public void execute(final Minecart minecart, final Block block) {

        executeExpression(minecart, "kill");
    }

}
