
package com.quartercode.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.ItemData;

public class ReverseBlock extends ControlBlock {

    public ReverseBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.reverse.name"), Lang.getValue("basiccontrols.blocks.reverse.description"), "reverse.place", "reverse.destroy", new ItemData(Material.WOOL));
    }

    @Override
    public void execute(final Minecart minecart, final Block block) {

        executeExpression(minecart, "reverse");
    }

}
