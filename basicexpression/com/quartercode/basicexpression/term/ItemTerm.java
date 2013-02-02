
package com.quartercode.basicexpression.term;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.AliasUtil;
import com.quartercode.minecartrevolution.util.ItemData;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class ItemTerm implements MinecartTerm {

    public ItemTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "i", "item", "items", "i-.*", "item-.*", "items-.*" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        Inventory inventory;
        if (minecart instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart).getInventory();
        } else if (minecart.getPassenger() instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart.getPassenger()).getInventory();
        } else {
            return false;
        }

        if (term.split("-").length == 2) {
            for (final String item : term.split("-")[1].split(",")) {
                for (int slot = 0; slot < inventory.getSize(); slot++) {
                    if (AliasUtil.equals(new ItemData(inventory.getItem(slot)), item)) {
                        return true;
                    }
                }
            }
        } else {
            for (int slot = 0; slot < inventory.getSize(); slot++) {
                if (inventory.getItem(slot) == null || inventory.getItem(slot).getType() == Material.AIR) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

}
