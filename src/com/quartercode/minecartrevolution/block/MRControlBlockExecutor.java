
package com.quartercode.minecartrevolution.block;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;

public class MRControlBlockExecutor {

    private List<ControlBlock> controlBlocks;

    public MRControlBlockExecutor() {

        controlBlocks = new ArrayList<ControlBlock>();
    }

    public List<ControlBlock> getControlBlocks() {

        return controlBlocks;
    }

    public void setControlBlocks(List<ControlBlock> controlBlocks) {

        this.controlBlocks = controlBlocks;
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

    public boolean isControlBlock(Block block) {

        int blockId = block.getTypeId();

        for (ControlBlock controlBlock : controlBlocks) {
            ControlBlockInfo info = controlBlock.getInfo();

            for (int cbBlockId : info.getBlockIds()) {
                if (cbBlockId == blockId) {
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

    public void executeControlBlock(Block block, Minecart minecart) {

        int blockId = block.getTypeId();

        for (ControlBlock controlBlock : controlBlocks) {
            ControlBlockInfo controlBlockInfo = controlBlock.getInfo();

            for (int cbBlockId : controlBlockInfo.getBlockIds()) {
                if (cbBlockId == blockId) {
                    controlBlock.execute(minecart, block.getLocation(), blockId, block);
                    break;
                }
            }
        }
    }

}
