
package com.quartercode.minecartrevolution.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.quarterbukkit.api.ItemData;

public class ControlBlockExecutor {

    private final MinecartRevolution minecartRevolution;
    private List<ControlBlock>       controlBlocks;

    public ControlBlockExecutor(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        controlBlocks = new ArrayList<ControlBlock>();
    }

    public List<ControlBlock> getControlBlocks() {

        return Collections.unmodifiableList(controlBlocks);
    }

    public ControlBlock getControlBlock(final Class<? extends ControlBlock> c) {

        for (final ControlBlock controlBlock : controlBlocks) {
            if (controlBlock.getClass().equals(c)) {
                return controlBlock;
            }
        }

        return null;
    }

    public void setControlBlocks(final List<ControlBlock> controlBlocks) {

        this.controlBlocks = controlBlocks;
    }

    public void addControlBlock(final ControlBlock controlBlock) {

        controlBlock.setMinecartRevolution(minecartRevolution);
        controlBlocks.add(controlBlock);
        controlBlock.enable();
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

        for (final ControlBlock controlBlock : controlBlocks) {
            final ControlBlockInfo info = controlBlock.getInfo();

            for (final ItemData itemData : info.getItemDatas()) {
                if (itemData.equals(block)) {
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

        for (final ControlBlock controlBlock : controlBlocks) {
            final ControlBlockInfo info = controlBlock.getInfo();

            for (final ItemData itemData : info.getItemDatas()) {
                if (itemData.equals(block)) {
                    controlBlock.execute(minecart, block);
                    break;
                }
            }
        }
    }

}
