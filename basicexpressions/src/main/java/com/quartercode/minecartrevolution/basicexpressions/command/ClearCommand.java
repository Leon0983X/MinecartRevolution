/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.command;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.AliasUtil;
import com.quartercode.quarterbukkit.api.ItemData;

public class ClearCommand extends ExpressionCommand {

    public ClearCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.STRING, Type.DOUBLE), "cl", "clear");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder || minecart.getPassenger() instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        Inventory inventory;

        if (minecart instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart).getInventory();
        } else if (minecart.getPassenger() instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart.getPassenger()).getInventory();
        } else {
            return;
        }

        if (parameter == null) {
            inventory.clear();
        } else {
            for (String item : String.valueOf(parameter).split(",")) {
                for (int slot = 0; slot < inventory.getSize(); slot++) {
                    if (new ItemData(inventory.getItem(slot)).equals(AliasUtil.getItemData(item))) {
                        inventory.setItem(slot, new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

}
