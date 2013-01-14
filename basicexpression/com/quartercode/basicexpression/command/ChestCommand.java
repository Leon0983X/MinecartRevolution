
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class ChestCommand extends ExpressionCommand {

    public ChestCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "ch", "chest");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart instanceof StorageMinecart;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        Chest chest = null;

        Location minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }

        if (chest == null) {
            return;
        }

        final StorageMinecart storageMinecart = (StorageMinecart) minecart;
        String data = String.valueOf(parameter);

        if (String.valueOf(parameter).startsWith("+")) {
            data = data.replaceAll("\\+", "").trim();
        } else if (String.valueOf(parameter).startsWith("-")) {
            data = data.replaceAll("-", "").trim();
        } else {
            return;
        }

        final List<String> items = new ArrayList<String>();
        for (final String part : data.split(",")) {
            if (!part.isEmpty()) {
                items.add(part);
            }
        }

        if (String.valueOf(parameter).startsWith("+")) {
            if (items.size() > 0) {
                for (final String item : items) {
                    toMinecart(chest, storageMinecart, item);
                }
            } else {
                toMinecart(chest, storageMinecart, null);
            }
        } else if (String.valueOf(parameter).startsWith("-")) {
            if (items.size() > 0) {
                for (final String item : items) {
                    toChest(chest, storageMinecart, item);
                }
            } else {
                toChest(chest, storageMinecart, null);
            }
        }
    }

    private void toChest(final Chest chest, final StorageMinecart storageMinecart, final String string) {

        for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
            if (chest.getInventory().firstEmpty() < 0) {
                return;
            }

            if (storageMinecart.getInventory().getItem(counter) != null && MaterialAliasConfig.equals(storageMinecart.getInventory().getItem(counter), string)) {
                chest.getInventory().addItem(new ItemStack[] { storageMinecart.getInventory().getItem(counter) });
                storageMinecart.getInventory().setItem(counter, new ItemStack(Material.AIR));
            }
        }
    }

    private void toMinecart(final Chest chest, final StorageMinecart storageMinecart, final String string) {

        for (int counter = 0; counter < chest.getInventory().getSize(); counter++) {
            if (storageMinecart.getInventory().firstEmpty() < 0) {
                return;
            }

            if (chest.getInventory().getItem(counter) != null && MaterialAliasConfig.equals(chest.getInventory().getItem(counter), string)) {
                storageMinecart.getInventory().addItem(new ItemStack[] { chest.getInventory().getItem(counter) });
                chest.getInventory().setItem(counter, new ItemStack(Material.AIR));
            }
        }
    }

}
