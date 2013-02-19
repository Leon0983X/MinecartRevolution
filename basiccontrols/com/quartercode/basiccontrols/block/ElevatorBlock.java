
package com.quartercode.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.quarterbukkit.api.ItemData;

public class ElevatorBlock extends ControlBlock {

    public ElevatorBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.elevator.name"), Lang.getValue("basiccontrols.blocks.elevator.description"), "elevator.place", "elevator.destroy", new ItemData(Material.DIAMOND_BLOCK));
    }

    @Override
    public void execute(final Minecart minecart, final Block block) {

        int heightCounter = minecart.getLocation().getBlockY() + 1;

        while (heightCounter <= minecart.getWorld().getMaxHeight()) {
            heightCounter++;

            final Block elevBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter, block.getZ());
            final Block railBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter + 1, block.getZ());
            for (final ItemData itemData : getInfo().getItemDatas()) {
                if (itemData.equals(elevBlock2) && (railBlock2.getType() == Material.RAILS || railBlock2.getType() == Material.POWERED_RAIL || railBlock2.getType() == Material.DETECTOR_RAIL)) {
                    executeExpression(minecart, "vertical " + (heightCounter - minecart.getLocation().getBlockY() + 1));
                    return;
                }
            }
        }
    }

}
