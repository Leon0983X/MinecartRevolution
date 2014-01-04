/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.control.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.quarterbukkit.api.ItemData;

public class ControlBlockExecutor {

    private final MinecartRevolution minecartRevolution;
    private List<ControlBlock>       controlBlocks;

    public ControlBlockExecutor(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        controlBlocks = new ArrayList<ControlBlock>();
    }

    public List<ControlBlock> getControlBlocks() {

        return Collections.unmodifiableList(controlBlocks);
    }

    public ControlBlock getControlBlock(Class<? extends ControlBlock> c) {

        for (ControlBlock controlBlock : controlBlocks) {
            if (controlBlock.getClass().equals(c)) {
                return controlBlock;
            }
        }

        return null;
    }

    public void setControlBlocks(List<ControlBlock> controlBlocks) {

        this.controlBlocks = controlBlocks;
    }

    public void addControlBlock(ControlBlock controlBlock) {

        controlBlock.setMinecartRevolution(minecartRevolution);
        controlBlocks.add(controlBlock);
        controlBlock.enable();
    }

    public List<Block> getBlocks(Location railLocation) {

        List<Block> blocks = new ArrayList<Block>();
        Location location = railLocation.clone();

        location.subtract(0, 1, 0);
        if (isControlBlock(location.getBlock())) {
            blocks.add(location.getBlock());
        }

        while (true) {
            location.subtract(0, 1, 0);
            if (isControlBlock(location.getBlock())) {
                blocks.add(location.getBlock());
            } else {
                break;
            }
        }

        return blocks;
    }

    private boolean isControlBlock(Block block) {

        for (ControlBlock controlBlock : controlBlocks) {
            ControlBlockInfo info = controlBlock.getInfo();

            for (ItemData itemData : info.getItemDatas()) {
                if (itemData.equals(block)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void execute(Minecart minecart) {

        Location location = minecart.getLocation();

        for (Block block : getBlocks(location)) {
            executeControlBlock(block, minecart);
        }
    }

    private void executeControlBlock(Block block, Minecart minecart) {

        if (block.isBlockIndirectlyPowered()) {
            return;
        }

        for (ControlBlock controlBlock : controlBlocks) {
            ControlBlockInfo info = controlBlock.getInfo();

            for (ItemData itemData : info.getItemDatas()) {
                if (itemData.equals(block)) {
                    controlBlock.execute(minecart, block);
                    break;
                }
            }
        }
    }

}
