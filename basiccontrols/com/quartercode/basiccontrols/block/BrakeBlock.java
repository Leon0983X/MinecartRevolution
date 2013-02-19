
package com.quartercode.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.ItemData;

public class BrakeBlock extends ControlBlock {

    public BrakeBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.brake.name"), Lang.getValue("basiccontrols.blocks.brake.description"), "brake.place", "brake.destroy", new ItemData(Material.SOUL_SAND));
    }

    @Override
    public void execute(final Minecart minecart, final Block block) {

        executeExpression(minecart, "speed $speed / 5");
    }

}
