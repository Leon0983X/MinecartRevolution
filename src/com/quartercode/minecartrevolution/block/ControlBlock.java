
package com.quartercode.minecartrevolution.block;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.Control;

public abstract class ControlBlock extends Control {

    private static final int[][] controlSignOffsets = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 0, -1, 0 } };

    public boolean hasSontrolSign(final Location blockLocation) {

        return getControlSigns(blockLocation).size() > 0;
    }

    protected ControlBlockInfo info;

    public List<Sign> getControlSigns(final Location blockLocation) {

        final List<Sign> signs = new ArrayList<Sign>();

        for (final int[] offsets : controlSignOffsets) {
            final Location location = blockLocation.clone();
            location.add(offsets[0], offsets[1], offsets[2]);

            if (location.getBlock().getType() == Material.SIGN || location.getBlock().getType() == Material.SIGN_POST || location.getBlock().getType() == Material.WALL_SIGN) {
                signs.add((Sign) location.getBlock().getState());
            }
        }

        return signs;
    }

    public ControlBlockInfo getInfo() {

        return info;
    }

    public abstract void execute(Minecart minecart, Location blockLocation, int blockId, Block block);

}
