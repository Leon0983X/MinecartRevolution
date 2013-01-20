
package com.quartercode.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;

public class BlockListener implements Listener {

    public BlockListener(final MinecartRevolution minecartRevolution) {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onRedstoneBlockChange(final BlockRedstoneEvent event) {

        final Block block = event.getBlock();
        Location newLocation = block.getLocation();

        newLocation.setX(newLocation.getX() + 1.0D);
        if (newLocation.getBlock().getType() == Material.CHEST || newLocation.getBlock().getType() == Material.SIGN_POST) {
            if (block.getData() >= 9) {
                return;
            }
            tryDispenseMinecart(newLocation.getBlock());
            return;
        }
        newLocation = block.getLocation();
        newLocation.setX(newLocation.getX() - 1.0D);
        if (newLocation.getBlock().getType() == Material.CHEST || newLocation.getBlock().getType() == Material.SIGN_POST) {
            if (block.getData() >= 9) {
                return;
            }
            tryDispenseMinecart(newLocation.getBlock());
            return;
        }
        newLocation = block.getLocation();
        newLocation.setZ(newLocation.getZ() + 1.0D);
        if (newLocation.getBlock().getType() == Material.CHEST || newLocation.getBlock().getType() == Material.SIGN_POST) {
            if (block.getData() >= 9) {
                return;
            }
            tryDispenseMinecart(newLocation.getBlock());
            return;
        }
        newLocation = block.getLocation();
        newLocation.setZ(newLocation.getZ() - 1.0D);
        if (newLocation.getBlock().getType() == Material.CHEST || newLocation.getBlock().getType() == Material.SIGN_POST) {
            if (block.getData() >= 9) {
                return;
            }
            tryDispenseMinecart(newLocation.getBlock());
            return;
        }
    }

    private void tryDispenseMinecart(final Block block) {

        final BlockState state = block.getState();

        String type = "";
        Sign sign = null;
        Chest chest = null;
        if (state.getType() == Material.SIGN_POST) {
            sign = (Sign) state;
            type = "SIGN";
        } else if (state instanceof Chest) {
            chest = (Chest) state;
            type = "CHEST";
        }

        Location newLocation = block.getLocation();
        Location newBlockLocation = block.getLocation();
        newBlockLocation.setY(newBlockLocation.getY() - 2.0D);
        if (newBlockLocation.getBlock().getType() == Material.RAILS) {
            newLocation = newBlockLocation;
        }
        newBlockLocation = block.getLocation();
        newBlockLocation.setY(newBlockLocation.getY() + 2.0D);
        if (newBlockLocation.getBlock().getType() == Material.RAILS) {
            newLocation = newBlockLocation;
        }
        newBlockLocation = block.getLocation();
        newBlockLocation.setY(newBlockLocation.getY() + 3.0D);
        if (newBlockLocation.getBlock().getType() == Material.RAILS) {
            newLocation = newBlockLocation;
        }

        newLocation.setX(newLocation.getX() + 1.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            dispenseMinecart(chest, sign, type, newLocation.getBlock(), new Vector(1, 0, 0));
            return;
        }
        newLocation.setX(newLocation.getX() - 2.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            dispenseMinecart(chest, sign, type, newLocation.getBlock(), new Vector(-1, 0, 0));
            return;
        }
        newLocation.setX(newLocation.getX() + 1.0D);
        newLocation.setZ(newLocation.getZ() + 1.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            dispenseMinecart(chest, sign, type, newLocation.getBlock(), new Vector(0, 0, 1));
            return;
        }
        newLocation.setZ(newLocation.getZ() - 2.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            dispenseMinecart(chest, sign, type, newLocation.getBlock(), new Vector(0, 0, -1));
            return;
        }
    }

    private void dispenseMinecart(final Chest chest, final Sign sign, final String type, final Block railBlock, final Vector velocity) {

        int minecartType = -1;

        if (type.equalsIgnoreCase("SIGN")) {
            if (sign.getLine(1).equalsIgnoreCase("[Spawn]")) {
                if (sign.getLine(2).equalsIgnoreCase("Standard")) {
                    minecartType = 0;
                } else if (sign.getLine(2).equalsIgnoreCase("Storage")) {
                    minecartType = 1;
                } else if (sign.getLine(2).equalsIgnoreCase("Powered")) {
                    minecartType = 2;
                } else {
                    minecartType = 0;
                }
            }
        } else if (type.equalsIgnoreCase("CHEST")) {
            int firstMinecartSlot = chest.getInventory().first(Material.MINECART);
            int firstStorageMinecartSlot = chest.getInventory().first(Material.STORAGE_MINECART);
            int firstPoweredMinecartSlot = chest.getInventory().first(Material.POWERED_MINECART);

            if (firstMinecartSlot < 0) {
                firstMinecartSlot = 100;
            }
            if (firstStorageMinecartSlot < 0) {
                firstStorageMinecartSlot = 100;
            }
            if (firstPoweredMinecartSlot < 0) {
                firstPoweredMinecartSlot = 100;
            }

            if (firstStorageMinecartSlot < firstMinecartSlot && firstStorageMinecartSlot < firstPoweredMinecartSlot) {
                minecartType = 1;
                chest.getInventory().setItem(chest.getInventory().first(Material.STORAGE_MINECART), null);
            } else if (firstPoweredMinecartSlot < firstMinecartSlot && firstPoweredMinecartSlot < firstStorageMinecartSlot) {
                minecartType = 2;
                chest.getInventory().setItem(chest.getInventory().first(Material.POWERED_MINECART), null);
            } else if (firstMinecartSlot < firstStorageMinecartSlot && firstMinecartSlot < firstPoweredMinecartSlot) {
                minecartType = 0;
                chest.getInventory().setItem(chest.getInventory().first(Material.MINECART), null);
            }
        }

        if (minecartType == 0) {
            final Minecart minecart = railBlock.getWorld().spawn(railBlock.getLocation(), Minecart.class);
            minecart.setVelocity(velocity);
        } else if (minecartType == 1) {
            final Minecart minecart = railBlock.getWorld().spawn(railBlock.getLocation(), StorageMinecart.class);
            minecart.setVelocity(velocity);
        } else if (minecartType == 2) {
            final Minecart minecart = railBlock.getWorld().spawn(railBlock.getLocation(), PoweredMinecart.class);
            minecart.setVelocity(velocity);
        }
    }

}
