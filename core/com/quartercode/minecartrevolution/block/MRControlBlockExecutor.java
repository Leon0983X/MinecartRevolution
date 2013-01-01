
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

    public void setControlBlocks(final List<ControlBlock> controlBlocks) {

        this.controlBlocks = controlBlocks;
    }

    public List<Block> getBlocks(final Location railLocation) {

        final List<Block> blocks = new ArrayList<Block>();
        final Location location = railLocation.clone();

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

    private boolean isControlBlock(final Block block) {

        final int blockId = block.getTypeId();

        for (final ControlBlock controlBlock : controlBlocks) {
            final ControlBlockInfo info = controlBlock.getInfo();

            for (final int cbBlockId : info.getBlockIds()) {
                if (cbBlockId == blockId) {
                    return true;
                }
            }
        }

        return false;
    }

    public void execute(final Minecart minecart) {

        final Location location = minecart.getLocation();

        for (final Block block : getBlocks(location)) {
            executeControlBlock(block, minecart);
        }
    }

    private void executeControlBlock(final Block block, final Minecart minecart) {

        if (block.isBlockIndirectlyPowered()) {
            return;
        }

        final int blockId = block.getTypeId();

        for (final ControlBlock controlBlock : controlBlocks) {
            final ControlBlockInfo controlBlockInfo = controlBlock.getInfo();

            for (final int cbBlockId : controlBlockInfo.getBlockIds()) {
                if (cbBlockId == blockId) {
                    controlBlock.execute(minecart, block.getLocation(), blockId, block);
                    break;
                }
            }
        }
    }

}
