
package com.quartercode.minecartrevolution.block;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.Control;

public abstract class ControlBlock extends Control {

    private static final int[][] controlSignOffsets = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 0, -1, 0 } };

    public boolean hasSontrolSign(final Location blockLocation) {

        return getControlSigns(blockLocation).size() > 0;
    }

    private ControlBlockInfo info;

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

    public final ControlBlockInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract ControlBlockInfo createInfo();

    public abstract void execute(Minecart minecart, Block block);

    public boolean allowPlace(final Player player, final Block block) {

        return true;
    }

    public boolean allowDestroy(final Player player, final Block block) {

        return true;
    }

}
