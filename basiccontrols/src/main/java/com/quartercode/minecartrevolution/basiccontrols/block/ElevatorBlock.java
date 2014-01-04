/*
 * This file is part of MinecartRevolution-Basiccontrols.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccontrols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccontrols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccontrols. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;
import com.quartercode.minecartrevolution.core.control.block.ControlBlockInfo;
import com.quartercode.quarterbukkit.api.ItemData;

public class ElevatorBlock extends ControlBlock {

    public ElevatorBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(MinecartRevolution.getLang().get("basiccontrols.blocks.elevator.name"), MinecartRevolution.getLang().get("basiccontrols.blocks.elevator.description"), "elevator.place", "elevator.destroy", new ItemData(Material.DIAMOND_BLOCK));
    }

    @Override
    public void execute(Minecart minecart, Block block) {

        int heightCounter = minecart.getLocation().getBlockY() + 1;

        while (heightCounter <= minecart.getWorld().getMaxHeight()) {
            heightCounter++;

            Block elevBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter, block.getZ());
            Block railBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter + 1, block.getZ());
            for (ItemData itemData : getInfo().getItemDatas()) {
                if (itemData.equals(elevBlock2) && (railBlock2.getType() == Material.RAILS || railBlock2.getType() == Material.POWERED_RAIL || railBlock2.getType() == Material.DETECTOR_RAIL)) {
                    executeExpression(minecart, "vertical " + (heightCounter - minecart.getLocation().getBlockY() + 1));
                    return;
                }
            }
        }
    }

}
