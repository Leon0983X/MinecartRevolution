
package com.quartercode.basicexpression.term;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.AliasUtil;
import com.quartercode.minecartrevolution.util.ItemData;

public class ItemHoldTerm implements MinecartTerm {

    public ItemHoldTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "ih", "ihold", "itemhold", "ih-.*", "ihold-.*", "itemhold-.*" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        if (minecart.getPassenger() instanceof InventoryHolder && ((InventoryHolder) minecart.getPassenger()).getInventory() instanceof PlayerInventory) {
            final PlayerInventory inventory = ((Player) minecart.getPassenger()).getInventory();

            if (term.split("-").length == 2) {
                for (final String item : term.split("-")[1].split(",")) {
                    if (AliasUtil.equals(new ItemData(inventory.getItemInHand()), item)) {
                        return true;
                    }
                }
            } else {
                if (inventory.getItemInHand() != null && inventory.getItemInHand().getType() != Material.AIR) {
                    return true;
                }
            }
        }

        return false;
    }

}
