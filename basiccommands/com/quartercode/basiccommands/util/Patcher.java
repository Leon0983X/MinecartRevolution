
package com.quartercode.basiccommands.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;

public abstract class Patcher {

    private static List<Patcher> patchers = new ArrayList<Patcher>();

    public static List<Patcher> getPatchers() {

        return Collections.unmodifiableList(patchers);
    }

    public static void addPatcher(final Patcher patcher) {

        patchers.add(patcher);
    }

    public static void removePatcher(final Patcher patcher) {

        patchers.remove(patcher);
    }

    protected MinecartRevolution minecartRevolution;

    protected Patcher(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    public abstract String getName();

    public int execute(final CommandSender causer, final List<World> worlds, final int radius) {

        return execute(causer, worlds.toArray(new World[worlds.size()]), radius);
    }

    public int execute(final CommandSender causer, final World[] worlds, final int radius) {

        int signs = 0;

        for (final World world : worlds) {
            for (final Chunk chunk : world.getLoadedChunks()) {
                for (final BlockState blockState : chunk.getTileEntities()) {
                    if (radius < 1 || causer instanceof CommandSender && blockState.getLocation().distance(new Location(blockState.getWorld(), 0, 0, 0)) <= radius || causer instanceof Player && blockState.getLocation().distance( ((Player) causer).getLocation()) <= radius) {
                        if (blockState instanceof Sign) {
                            final Sign sign = (Sign) blockState;
                            if (patch(causer, sign)) {
	signs++;
                            }
                        }
                    }
                }
            }
        }

        return signs;
    }

    protected abstract boolean patch(CommandSender causer, Sign sign);

    protected String formatItems(final List<String> items) {

        return formatItems(items.toArray(new String[items.size()]));
    }

    protected String formatItems(final String[] items) {

        String itemString = "";
        for (final String item : items) {
            itemString += item + ",";
        }
        itemString = itemString.substring(0, itemString.length() - 1);

        return itemString;
    }

    protected void setSignContent(final Sign sign, final String... lines) {

        for (int counter = 0; counter < 4; counter++) {
            sign.setLine(counter, "");
        }
        for (int counter = 0; counter < lines.length && counter < 4; counter++) {
            sign.setLine(counter, lines[counter]);
        }

        sign.update();
    }

    protected void setBlockType(final Block block, final Class<? extends ControlBlock> c) {

        minecartRevolution.getControlBlockExecutor().getControlBlock(c).getInfo().getItemDatas()[0].apply(block);
    }

}