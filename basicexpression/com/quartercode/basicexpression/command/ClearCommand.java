
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class ClearCommand implements ExpressionCommand {

    private MinecartRevolution minecartRevolution;

    public ClearCommand(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("c", "clear");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder || minecart.getPassenger() != null && minecart.getPassenger() instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

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
            List<String> itemList = new ArrayList<String>();
            for (String string : String.valueOf(parameter).split(",")) {
                itemList.add(string);
            }

            for (int counter = 0; counter < itemList.size(); counter++) {
                inventory.remove(minecartRevolution.getAliasConfig().getId(itemList.get(counter)));
            }
        }
    }

}