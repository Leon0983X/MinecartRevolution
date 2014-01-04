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

package com.quartercode.minecartrevolution.basicexpressions.term;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import com.quartercode.minecartrevolution.core.util.AliasUtil;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.quarterbukkit.api.ItemData;

public class ItemTerm implements MinecartTerm {

    public ItemTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "i", "item", "items", "i-.*", "item-.*", "items-.*" };
    }

    @Override
    public boolean getResult(Minecart minecart, Direction direction, String term) {

        Inventory inventory;
        if (minecart instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart).getInventory();
        } else if (minecart.getPassenger() instanceof InventoryHolder) {
            inventory = ((InventoryHolder) minecart.getPassenger()).getInventory();
        } else {
            return false;
        }

        if (term.split("-").length == 2) {
            for (String item : term.split("-")[1].split(",")) {
                for (int slot = 0; slot < inventory.getSize(); slot++) {
                    if (new ItemData(inventory.getItem(slot)).equals(AliasUtil.getItemData(item))) {
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
