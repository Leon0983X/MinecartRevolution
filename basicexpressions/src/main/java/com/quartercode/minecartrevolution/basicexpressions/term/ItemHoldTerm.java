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
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import com.quartercode.minecartrevolution.core.util.AliasUtil;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.quarterbukkit.api.ItemData;

public class ItemHoldTerm implements MinecartTerm {

    public ItemHoldTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "ih", "ihold", "itemhold", "ih-.*", "ihold-.*", "itemhold-.*" };
    }

    @Override
    public boolean getResult(Minecart minecart, Direction direction, String term) {

        if (minecart.getPassenger() instanceof InventoryHolder && ((InventoryHolder) minecart.getPassenger()).getInventory() instanceof PlayerInventory) {
            PlayerInventory inventory = ((Player) minecart.getPassenger()).getInventory();

            if (term.split("-").length == 2) {
                for (String item : term.split("-")[1].split(",")) {
                    if (new ItemData(inventory.getItemInHand()).equals(AliasUtil.getItemData(item))) {
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
