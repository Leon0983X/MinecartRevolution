/*
 * This file is part of MinecartRevolution-Basicactions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicactions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicactions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicactions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartType;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;
import com.quartercode.quarterbukkit.api.event.RedstoneToggleEvent;

public class BlockListener implements Listener {

    public BlockListener(MinecartRevolution minecartRevolution) {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onRedstoneToggleOnChest(RedstoneToggleEvent event) {

        if (event.getBlock().getState() instanceof Chest && event.isPowered()) {
            Chest chest = (Chest) event.getBlock().getState();
            Location rail = getRail(chest.getBlock());
            Direction direction = getDirection(chest.getBlock());
            if (rail == null || direction == null) {
                return;
            }

            MinecartType minecartType = null;
            for (int slot = 0; slot < chest.getInventory().getSize(); slot++) {
                if (chest.getInventory().getItem(slot) != null && chest.getInventory().getItem(slot).getType() != Material.AIR) {
                    Material type = chest.getInventory().getItem(slot).getType();

                    if (type == Material.MINECART) {
                        minecartType = MinecartType.RIDEABLE;
                    } else if (type == Material.STORAGE_MINECART) {
                        minecartType = MinecartType.STORAGE;
                    } else if (type == Material.POWERED_MINECART) {
                        minecartType = MinecartType.POWERED;
                    } else if (type == Material.HOPPER_MINECART) {
                        minecartType = MinecartType.HOPPER;
                    } else if (type == Material.EXPLOSIVE_MINECART) {
                        minecartType = MinecartType.TNT;
                    }

                    if (minecartType != null) {
                        chest.getInventory().setItem(slot, new ItemStack(Material.AIR));
                        break;
                    }
                }
            }

            if (minecartType != null) {
                dispense(minecartType, rail, direction);
            }
        }
    }

    @EventHandler
    public void onRedstoneToggleOnSign(RedstoneToggleEvent event) {

        if (event.getBlock().getState() instanceof Sign && event.isPowered()) {
            Sign sign = (Sign) event.getBlock().getState();

            if (sign.getLine(0).equalsIgnoreCase("[spawn]")) {
                Location rail = getRail(sign.getBlock());
                Direction direction = getDirection(sign.getBlock());
                if (rail == null || direction == null) {
                    return;
                }

                MinecartType minecartType = null;
                for (MinecartType testMinecartType : MinecartType.values()) {
                    for (String name : testMinecartType.getNames()) {
                        if (name.equalsIgnoreCase(sign.getLine(1))) {
                            minecartType = testMinecartType;
                        }
                    }
                }

                if (minecartType != null) {
                    dispense(minecartType, rail, direction);
                }
            }
        }
    }

    private Location getRail(Block block) {

        if (block.getLocation().add(1, 0, 0).getBlock().getType() == Material.RAILS || block.getLocation().add(1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            return block.getLocation().add(1, 0, 0);
        } else if (block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.RAILS || block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            return block.getLocation().add(-1, 0, 0);
        } else if (block.getLocation().add(0, 0, 1).getBlock().getType() == Material.RAILS || block.getLocation().add(0, 0, 1).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(0, 0, 1).getBlock().getType() == Material.DETECTOR_RAIL) {
            return block.getLocation().add(0, 0, 1);
        } else if (block.getLocation().add(0, 0, -1).getBlock().getType() == Material.RAILS || block.getLocation().add(0, 0, -1).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(0, 0, -1).getBlock().getType() == Material.DETECTOR_RAIL) {
            return block.getLocation().add(0, 0, -1);
        }

        return null;
    }

    private Direction getDirection(Block block) {

        if (block.getLocation().add(1, 0, 0).getBlock().getType() == Material.RAILS || block.getLocation().add(1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            return Direction.WEST;
        } else if (block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.RAILS || block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(-1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            return Direction.EAST;
        } else if (block.getLocation().add(0, 0, 1).getBlock().getType() == Material.RAILS || block.getLocation().add(0, 0, 1).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(0, 0, 1).getBlock().getType() == Material.DETECTOR_RAIL) {
            return Direction.NORTH;
        } else if (block.getLocation().add(0, 0, -1).getBlock().getType() == Material.RAILS || block.getLocation().add(0, 0, -1).getBlock().getType() == Material.POWERED_RAIL || block.getLocation().add(0, 0, -1).getBlock().getType() == Material.DETECTOR_RAIL) {
            return Direction.SOUTH;
        }

        return null;
    }

    private void dispense(MinecartType minecartType, Location rail, Direction direction) {

        Minecart minecart = rail.getWorld().spawn(rail, minecartType.getCartClass());
        MinecartUtil.driveInDirection(minecart, direction);
    }

}
