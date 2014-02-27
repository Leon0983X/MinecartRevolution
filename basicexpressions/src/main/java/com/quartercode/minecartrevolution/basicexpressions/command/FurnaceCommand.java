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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.ItemDataResolver;
import com.quartercode.quarterbukkit.api.ItemData;

public class FurnaceCommand extends ExpressionCommand {

    private static final Set<ItemData> FUELS = new HashSet<ItemData>();

    static {

        FUELS.add(new ItemData(Material.COAL, (byte) 0));
        FUELS.add(new ItemData(Material.COAL, (byte) 1));
        FUELS.add(new ItemData(Material.WOOD));
        FUELS.add(new ItemData(Material.WOOD_STEP));
        FUELS.add(new ItemData(Material.SAPLING));
        FUELS.add(new ItemData(Material.WOOD_AXE));
        FUELS.add(new ItemData(Material.WOOD_HOE));
        FUELS.add(new ItemData(Material.WOOD_PICKAXE));
        FUELS.add(new ItemData(Material.WOOD_SPADE));
        FUELS.add(new ItemData(Material.WOOD_SWORD));
        FUELS.add(new ItemData(Material.WOOD_PLATE));
        FUELS.add(new ItemData(Material.WOOD_BUTTON));
        FUELS.add(new ItemData(Material.STICK));
        FUELS.add(new ItemData(Material.FENCE));
        FUELS.add(new ItemData(Material.FENCE_GATE));
        FUELS.add(new ItemData(Material.WOOD_STAIRS));
        FUELS.add(new ItemData(Material.TRAP_DOOR));
        FUELS.add(new ItemData(Material.WORKBENCH));
        FUELS.add(new ItemData(Material.BOOKSHELF));
        FUELS.add(new ItemData(Material.CHEST));
        FUELS.add(new ItemData(Material.JUKEBOX));
        FUELS.add(new ItemData(Material.NOTE_BLOCK));
        FUELS.add(new ItemData(Material.HUGE_MUSHROOM_1));
        FUELS.add(new ItemData(Material.HUGE_MUSHROOM_2));
        FUELS.add(new ItemData(Material.BLAZE_ROD));
        FUELS.add(new ItemData(Material.LAVA_BUCKET));

    }

    public static boolean isFuel(ItemData itemData) {

        for (ItemData fuel : FUELS) {
            if (fuel.equals(itemData)) {
                return true;
            }
        }

        return false;
    }

    public FurnaceCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "fu", "furnace");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        List<Furnace> furnaces = new ArrayList<Furnace>();

        Location minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
            furnaces.add((Furnace) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
            furnaces.add((Furnace) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
            furnaces.add((Furnace) minecartLocation.getBlock().getState());
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
            furnaces.add((Furnace) minecartLocation.getBlock().getState());
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

        for (Furnace furnace : furnaces) {
            if (String.valueOf(parameter).startsWith("+")) {
                if (items.size() > 0) {
                    for (String item : items) {
                        transferToFurnace(inventoryMinecart.getInventory(), furnace.getInventory(), item);
                    }
                } else {
                    transferToFurnace(inventoryMinecart.getInventory(), furnace.getInventory(), null);
                }
            } else if (String.valueOf(parameter).startsWith("-")) {
                if (items.size() > 0) {
                    for (String item : items) {
                        transferToInventory(furnace.getInventory(), inventoryMinecart.getInventory(), item);
                    }
                } else {
                    transferToInventory(furnace.getInventory(), inventoryMinecart.getInventory(), null);
                }
            }
        }
    }

    private void transferToFurnace(Inventory fromInventory, FurnaceInventory toInventory, String string) {

        ItemData itemData = null;
        if (!string.equalsIgnoreCase("fuel") && !string.equalsIgnoreCase("item")) {
            itemData = ItemDataResolver.resolve(string);
        }

        for (int inventorySlot = 0; inventorySlot < fromInventory.getSize(); inventorySlot++) {
            if (fromInventory.getItem(inventorySlot) != null) {
                int furnaceSlot;
                if (isFuel(new ItemData(fromInventory.getItem(inventorySlot))) && (itemData == null || string.equalsIgnoreCase("fuel") || new ItemData(fromInventory.getItem(inventorySlot)).equals(itemData))) {
                    furnaceSlot = 1;
                } else if (itemData == null || string.equalsIgnoreCase("item") || new ItemData(fromInventory.getItem(inventorySlot)).equals(itemData)) {
                    furnaceSlot = 0;
                } else {
                    continue;
                }

                if (toInventory.getItem(furnaceSlot) == null || ItemData.equals(fromInventory.getItem(inventorySlot), toInventory.getItem(furnaceSlot))) {
                    int overplus = fromInventory.getItem(inventorySlot).getAmount() + (toInventory.getItem(furnaceSlot) == null ? 0 : toInventory.getItem(furnaceSlot).getAmount()) - fromInventory.getItem(inventorySlot).getMaxStackSize();
                    ItemStack fuelItemStack = new ItemStack(fromInventory.getItem(inventorySlot));
                    if (overplus > 0) {
                        fuelItemStack.setAmount(fuelItemStack.getMaxStackSize());
                        ItemStack itemStack = new ItemStack(fromInventory.getItem(inventorySlot));
                        itemStack.setAmount(overplus);
                        fromInventory.setItem(inventorySlot, itemStack);
                    } else {
                        fromInventory.setItem(inventorySlot, new ItemStack(Material.AIR));
                    }
                    toInventory.setItem(furnaceSlot, fuelItemStack);
                }
            }
        }
    }

    private void transferToInventory(FurnaceInventory fromInventory, Inventory toInventory, String string) {

        ItemData itemData = ItemDataResolver.resolve(string);

        if (fromInventory.getFuel() != null && fromInventory.getFuel().getType() == Material.BUCKET) {
            List<ItemStack> overplus = new ArrayList<ItemStack>(toInventory.addItem(new ItemStack[] { fromInventory.getFuel() }).values());
            fromInventory.setFuel(new ItemStack(Material.AIR));

            for (ItemStack itemStack : overplus) {
                fromInventory.setFuel(itemStack);
            }
        }

        if (fromInventory.getResult() != null && (itemData == null || new ItemData(fromInventory.getResult()).equals(itemData))) {
            List<ItemStack> overplus = new ArrayList<ItemStack>(toInventory.addItem(new ItemStack[] { fromInventory.getResult() }).values());
            fromInventory.setResult(new ItemStack(Material.AIR));

            for (ItemStack itemStack : overplus) {
                fromInventory.setResult(itemStack);
            }
        }
    }

}
