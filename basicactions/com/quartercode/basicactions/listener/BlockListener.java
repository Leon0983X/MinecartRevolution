
package com.quartercode.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.MinecartType;
import com.quartercode.minecartrevolution.util.MinecartUtil;

public class BlockListener implements Listener {

    public BlockListener(final MinecartRevolution minecartRevolution) {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onRedstoneBlockChangeOnChest(final BlockRedstoneEvent event) {

        if (event.getBlock().getData() >= 9) {
            return;
        }

        Chest chest;
        if (event.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
            chest = (Chest) event.getBlock().getLocation().add(1, 0, 0).getBlock().getState();
        } else if (event.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
            chest = (Chest) event.getBlock().getLocation().add(-1, 0, 0).getBlock().getState();
        } else if (event.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
            chest = (Chest) event.getBlock().getLocation().add(0, 0, 1).getBlock().getState();
        } else if (event.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
            chest = (Chest) event.getBlock().getLocation().add(0, 0, -1).getBlock().getState();
        } else {
            return;
        }

        final Location rail = getRail(chest.getBlock());
        final Direction direction = getDirection(chest.getBlock());
        if (rail == null || direction == null) {
            return;
        }

        MinecartType minecartType = null;
        for (int slot = 0; slot < chest.getInventory().getSize(); slot++) {
            if (chest.getInventory().getItem(slot) != null && chest.getInventory().getItem(slot).getType() != Material.AIR) {
                final Material type = chest.getInventory().getItem(slot).getType();

                if (type == Material.MINECART) {
                    minecartType = MinecartType.MINECART;
                    chest.getInventory().setItem(slot, new ItemStack(Material.AIR));
                    break;
                } else if (type == Material.STORAGE_MINECART) {
                    minecartType = MinecartType.STORAGE;
                    chest.getInventory().setItem(slot, new ItemStack(Material.AIR));
                    break;
                } else if (type == Material.POWERED_MINECART) {
                    minecartType = MinecartType.POWERED;
                    chest.getInventory().setItem(slot, new ItemStack(Material.AIR));
                    break;
                }
            }
        }

        if (minecartType != null) {
            dispense(minecartType, rail, direction);
        }
    }

    @EventHandler
    public void onRedstoneBlockChangeOnSign(final BlockRedstoneEvent event) {

        if (event.getBlock().getData() >= 9) {
            return;
        }

        Sign sign;
        if (event.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.SIGN || event.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.SIGN_POST || event.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
            sign = (Sign) event.getBlock().getLocation().add(1, 0, 0).getBlock().getState();
        } else if (event.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.SIGN || event.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.SIGN_POST || event.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
            sign = (Sign) event.getBlock().getLocation().add(-1, 0, 0).getBlock().getState();
        } else if (event.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.SIGN || event.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.SIGN_POST || event.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.WALL_SIGN) {
            sign = (Sign) event.getBlock().getLocation().add(0, 0, 1).getBlock().getState();
        } else if (event.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.SIGN || event.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.SIGN_POST || event.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.WALL_SIGN) {
            sign = (Sign) event.getBlock().getLocation().add(0, 0, -1).getBlock().getState();
        } else {
            return;
        }

        if (sign.getLine(0).equalsIgnoreCase("[spawn]")) {
            final Location rail = getRail(sign.getBlock());
            final Direction direction = getDirection(sign.getBlock());
            if (rail == null || direction == null) {
                return;
            }

            MinecartType minecartType;
            if (sign.getLine(1).equalsIgnoreCase("minecart")) {
                minecartType = MinecartType.MINECART;
            } else if (sign.getLine(1).equalsIgnoreCase("storage")) {
                minecartType = MinecartType.STORAGE;
            } else if (sign.getLine(1).equalsIgnoreCase("powered")) {
                minecartType = MinecartType.POWERED;
            } else {
                return;
            }

            dispense(minecartType, rail, direction);
        }
    }

    private Location getRail(final Block block) {

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

    private Direction getDirection(final Block block) {

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

    private void dispense(final MinecartType minecartType, final Location rail, final Direction direction) {

        final Minecart minecart = rail.getWorld().spawn(rail, minecartType.getCartClass());
        MinecartUtil.driveInDirection(minecart, direction);
    }

}
