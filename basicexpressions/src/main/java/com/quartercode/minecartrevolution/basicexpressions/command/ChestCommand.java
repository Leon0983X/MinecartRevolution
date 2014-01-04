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

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
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

public class ChestCommand extends ExpressionCommand {

    public ChestCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "ch", "chest");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        List<Chest> chests = new ArrayList<Chest>();

        Location minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chests.add((Chest) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chests.add((Chest) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chests.add((Chest) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chests.add((Chest) minecartLocation.getBlock().getState());
        }

        InventoryHolder inventoryMinecart = (InventoryHolder) minecart;
        String data = String.valueOf(parameter);

        if (String.valueOf(parameter).startsWith("+")) {
            data = data.replaceAll("\\+", "").trim();
        } else if (String.valueOf(parameter).startsWith("-")) {
            data = data.replaceAll("-", "").trim();
        } else {
            return;
        }

        List<String> items = new ArrayList<String>();
        for (String part : data.split(",")) {
            if (!part.isEmpty()) {
                items.add(part);
            }
        }

        for (Chest chest : chests) {
            if (String.valueOf(parameter).startsWith("+")) {
                if (items.size() > 0) {
                    for (String item : items) {
                        transfer(inventoryMinecart.getInventory(), chest.getInventory(), item);
                    }
                } else {
                    transfer(inventoryMinecart.getInventory(), chest.getInventory(), null);
                }
            } else if (String.valueOf(parameter).startsWith("-")) {
                if (items.size() > 0) {
                    for (String item : items) {
                        transfer(chest.getInventory(), inventoryMinecart.getInventory(), item);
                    }
                } else {
                    transfer(chest.getInventory(), inventoryMinecart.getInventory(), null);
                }
            }
        }
    }

    private void transfer(Inventory fromInventory, Inventory toInventory, String string) {

        for (int counter = 0; counter < fromInventory.getSize(); counter++) {
            if (fromInventory.getItem(counter) != null && new ItemData(fromInventory.getItem(counter)).equals(AliasUtil.getItemData(string))) {
                List<ItemStack> overplus = new ArrayList<ItemStack>(toInventory.addItem(new ItemStack[] { fromInventory.getItem(counter) }).values());
                fromInventory.setItem(counter, new ItemStack(Material.AIR));

                for (ItemStack itemStack : overplus) {
                    fromInventory.addItem(itemStack);
                }
            }
        }
    }

}
