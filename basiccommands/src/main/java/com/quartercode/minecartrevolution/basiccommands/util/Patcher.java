/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.util;

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
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;

public abstract class Patcher {

    private static final List<Patcher> patchers = new ArrayList<Patcher>();

    public static List<Patcher> getPatchers() {

        return Collections.unmodifiableList(patchers);
    }

    public static void addPatcher(Patcher patcher) {

        patchers.add(patcher);
    }

    public static void removePatcher(Patcher patcher) {

        patchers.remove(patcher);
    }

    protected MinecartRevolution minecartRevolution;

    protected Patcher(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    public abstract String getName();

    public int execute(CommandSender causer, List<World> worlds, int radius) {

        return execute(causer, worlds.toArray(new World[worlds.size()]), radius);
    }

    public int execute(CommandSender causer, World[] worlds, int radius) {

        int signs = 0;

        for (World world : worlds) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (BlockState blockState : chunk.getTileEntities()) {
                    if (radius < 1 || causer instanceof CommandSender && blockState.getLocation().distance(new Location(blockState.getWorld(), 0, 0, 0)) <= radius || causer instanceof Player && blockState.getLocation().distance( ((Player) causer).getLocation()) <= radius) {
                        if (blockState instanceof Sign) {
                            Sign sign = (Sign) blockState;
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

    protected String formatItems(List<String> items) {

        return formatItems(items.toArray(new String[items.size()]));
    }

    protected String formatItems(String[] items) {

        String itemString = "";
        for (String item : items) {
            itemString += item + ",";
        }
        itemString = itemString.substring(0, itemString.length() - 1);

        return itemString;
    }

    protected void setSignContent(Sign sign, String... lines) {

        for (int counter = 0; counter < 4; counter++) {
            sign.setLine(counter, "");
        }
        for (int counter = 0; counter < lines.length && counter < 4; counter++) {
            sign.setLine(counter, lines[counter]);
        }

        sign.update();
    }

    protected void setBlockType(Block block, Class<? extends ControlBlock> c) {

        minecartRevolution.getControlBlockExecutor().getControlBlock(c).getInfo().getItemDatas()[0].apply(block);
    }

}
