
package com.quartercode.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
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
    public void onRedstoneBlockChange(final BlockRedstoneEvent event) {

        if (event.getBlock().getData() >= 9) {
            return;
        }

        Chest chest;
        Location location = event.getBlock().getLocation();
        if (location.add(1, 0, 0).getBlock().getType() == Material.CHEST) {
            chest = (Chest) location.getBlock().getState();
        } else if (location.add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
            chest = (Chest) location.getBlock().getState();
        } else if (location.add(0, 0, 1).getBlock().getType() == Material.CHEST) {
            chest = (Chest) location.getBlock().getState();
        } else if (location.add(0, 0, -1).getBlock().getType() == Material.CHEST) {
            chest = (Chest) location.getBlock().getState();
        } else {
            return;
        }

        Location rails;
        Direction direction;
        location = chest.getLocation();
        if (location.add(1, 0, 0).getBlock().getType() == Material.RAILS || location.add(1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || location.add(1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            rails = location;
            direction = Direction.WEST;
        } else if (location.add(-1, 0, 0).getBlock().getType() == Material.RAILS || location.add(-1, 0, 0).getBlock().getType() == Material.POWERED_RAIL || location.add(-1, 0, 0).getBlock().getType() == Material.DETECTOR_RAIL) {
            rails = location;
            direction = Direction.EAST;
        } else if (location.add(0, 0, 1).getBlock().getType() == Material.RAILS || location.add(0, 0, 1).getBlock().getType() == Material.POWERED_RAIL || location.add(0, 0, 1).getBlock().getType() == Material.DETECTOR_RAIL) {
            rails = location;
            direction = Direction.NORTH;
        } else if (location.add(0, 0, -1).getBlock().getType() == Material.RAILS || location.add(0, 0, -1).getBlock().getType() == Material.POWERED_RAIL || location.add(0, 0, -1).getBlock().getType() == Material.DETECTOR_RAIL) {
            rails = location;
            direction = Direction.SOUTH;
        } else {
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
            final Minecart minecart = location.getWorld().spawn(rails, minecartType.getCartClass());
            MinecartUtil.driveInDirection(minecart, direction);
        }
    }

}
