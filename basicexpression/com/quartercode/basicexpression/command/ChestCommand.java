
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class ChestCommand extends ExpressionCommand {

    public ChestCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "ch", "chest");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart instanceof InventoryHolder;
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

        final InventoryHolder inventoryMinecart = (InventoryHolder) minecart;
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
                    transfer(inventoryMinecart.getInventory(), chest.getInventory(), item);
                }
            } else {
                transfer(inventoryMinecart.getInventory(), chest.getInventory(), null);
            }
        } else if (String.valueOf(parameter).startsWith("-")) {
            if (items.size() > 0) {
                for (final String item : items) {
                    transfer(chest.getInventory(), inventoryMinecart.getInventory(), item);
                }
            } else {
                transfer(chest.getInventory(), inventoryMinecart.getInventory(), null);
            }
        }
    }

    private void transfer(final Inventory fromInventory, final Inventory toInventory, final String string) {

        for (int counter = 0; counter < fromInventory.getSize(); counter++) {
            if (fromInventory.getItem(counter) != null && MaterialAliasConfig.equals(fromInventory.getItem(counter), string)) {
                final List<ItemStack> overplus = new ArrayList<ItemStack>(toInventory.addItem(new ItemStack[] { fromInventory.getItem(counter) }).values());
                fromInventory.setItem(counter, new ItemStack(Material.AIR));

                for (final ItemStack itemStack : overplus) {
                    fromInventory.addItem(itemStack);
                }
            }
        }
    }

}
