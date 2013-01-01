
package com.quartercode.basicexpression.command;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class ClearCommand extends ExpressionCommand {

    public ClearCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo("cl", "clear");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart instanceof InventoryHolder || minecart.getPassenger() != null && minecart.getPassenger() instanceof InventoryHolder;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        Inventory inventory;

        if (minecart instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart).getInventory();
        } else if (minecart.getPassenger() != null && minecart.getPassenger() instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart.getPassenger()).getInventory();
        } else {
            return;
        }

        if (parameter == null) {
            inventory.clear();
        } else {
            for (final String item : String.valueOf(parameter).split(",")) {
                for (int slot = 0; slot < inventory.getSize(); slot++) {
                    if (MaterialAliasConfig.equals(inventory.getItem(slot), item)) {
                        inventory.setItem(slot, new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

}
