
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class InventoryTerm implements MinecartTerm {

    public InventoryTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "inv", "inventory" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart instanceof InventoryHolder || minecart.getPassenger() instanceof InventoryHolder;
    }

}
