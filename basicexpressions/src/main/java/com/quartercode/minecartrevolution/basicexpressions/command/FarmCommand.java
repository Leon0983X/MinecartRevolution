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

import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.basicexpressions.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.quarterbukkit.api.ItemData;

public class FarmCommand extends ExpressionCommand {

    private final BasicExpressionsPlugin plugin;

    public FarmCommand(BasicExpressionsPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "fa", "farm");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        String[] parameters = String.valueOf(parameter).split(" ");
        int radius = (int) plugin.getConfiguration().getLong(BasicExpressionConfig.FARM_DEFAULT_RADIUS);

        try {
            if (parameters.length >= 2 && (plugin.getConfiguration().getLong(BasicExpressionConfig.FARM_MAX_RADIUS) < 0 || Integer.parseInt(parameters[1]) <= plugin.getConfiguration().getLong(BasicExpressionConfig.FARM_MAX_RADIUS))) {
                radius = Integer.parseInt(parameters[1]);
            }
        } catch (NumberFormatException e) {
            radius = 5;
        }

        String type = parameters[0];

        World world = minecart.getWorld();
        Location location = minecart.getLocation().getBlock().getLocation();

        if (type.equalsIgnoreCase("wood") || type.equalsIgnoreCase("wheat")) {
            Material[] destroyTypes = null;
            Material[] collectTypes = null;
            Material plantItemType = null;
            Material plantBlockType = null;
            if (type.equalsIgnoreCase("wood")) {
                destroyTypes = new Material[] { Material.LEAVES, Material.LEAVES_2, Material.LOG, Material.LOG_2 };
                collectTypes = new Material[] { Material.SAPLING, Material.LOG, Material.LOG_2, Material.APPLE };
                plantItemType = Material.SAPLING;
                plantBlockType = Material.SAPLING;
            } else if (type.equalsIgnoreCase("wheat")) {
                destroyTypes = new Material[] { Material.CROPS };
                collectTypes = new Material[] { Material.SEEDS, Material.WHEAT };
                plantItemType = Material.SEEDS;
                plantBlockType = Material.CROPS;
            }

            for (Material destroyType : destroyTypes) {
                for (int y = (int) (location.getY() - radius); y <= radius * 2 + location.getY(); y++) {
                    for (int x = (int) (location.getX() - radius); x <= radius * 2 + location.getX(); x++) {
                        for (int z = (int) (location.getZ() - radius); z <= radius * 2 + location.getZ(); z++) {
                            Block block = world.getBlockAt(x, y, z);
                            if (block != null && block.getType() == destroyType) {
                                // Check for crop growth
                                if (block.getType() == Material.CROPS && ((Crops) block.getState().getData()).getState() != CropState.RIPE) {
                                    continue;
                                }

                                block.breakNaturally();
                                for (Material collectType : collectTypes) {
                                    collectItems((InventoryHolder) minecart, minecart, radius, collectType);
                                }

                                Block blockBelow = world.getBlockAt(x, y - 1, z);
                                if (blockBelow != null && blockBelow.getType() != Material.AIR) {
                                    for (int storageSlot = 0; storageSlot < ((InventoryHolder) minecart).getInventory().getSize(); storageSlot++) {
                                        if ( ((InventoryHolder) minecart).getInventory().getItem(storageSlot) != null && ((InventoryHolder) minecart).getInventory().getItem(storageSlot).getType() == plantItemType) {
                                            ItemStack newItemStack = new ItemStack( ((InventoryHolder) minecart).getInventory().getItem(storageSlot));
                                            newItemStack.setAmount( ((InventoryHolder) minecart).getInventory().getItem(storageSlot).getAmount() - 1);
                                            ((InventoryHolder) minecart).getInventory().setItem(storageSlot, newItemStack);
                                            if ( ((InventoryHolder) minecart).getInventory().getItem(storageSlot).getAmount() <= 0) {
                                                ((InventoryHolder) minecart).getInventory().setItem(storageSlot, null);
                                            }

                                            block.setType(plantBlockType);
                                            // Reset crop growth
                                            if (block.getType() == Material.CROPS) {
                                                BlockState blockState = block.getState();
                                                Crops crops = (Crops) blockState.getData();
                                                crops.setState(CropState.SEEDED);
                                                blockState.setData(crops);
                                                blockState.update();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (type.equalsIgnoreCase("pumpkin") || type.equalsIgnoreCase("melon")) {
            Material destroyType = null;
            Material collectType = null;
            if (type.equalsIgnoreCase("pumpkin")) {
                destroyType = Material.PUMPKIN;
                collectType = Material.PUMPKIN;
            } else if (type.equalsIgnoreCase("melon")) {
                destroyType = Material.MELON_BLOCK;
                collectType = Material.MELON;
            }

            for (int y = (int) (location.getY() - radius); y <= radius * 2 + location.getY(); y++) {
                for (int x = (int) (location.getX() - radius); x <= radius * 2 + location.getX(); x++) {
                    for (int z = (int) (location.getZ() - radius); z <= radius * 2 + location.getZ(); z++) {
                        Block block = world.getBlockAt(x, y, z);
                        if (block != null && block.getType() == destroyType) {
                            block.breakNaturally();
                        }
                    }
                }
            }

            collectItems((InventoryHolder) minecart, minecart, radius, collectType);
        } else if (type.equalsIgnoreCase("sugar") || type.equalsIgnoreCase("sugarcane")) {
            Material destroyType = Material.SUGAR_CANE_BLOCK;
            Material collectType = Material.SUGAR_CANE;

            for (int y = (int) (location.getY() - radius); y <= radius * 2 + location.getY(); y++) {
                for (int x = (int) (location.getX() - radius); x <= radius * 2 + location.getX(); x++) {
                    for (int z = (int) (location.getZ() - radius); z <= radius * 2 + location.getZ(); z++) {
                        Block block = world.getBlockAt(x, y, z);
                        Block blockBelow = world.getBlockAt(x, y - 1, z);
                        if (block != null && block.getType() == destroyType && blockBelow != null && blockBelow.getType() == destroyType) {
                            block.breakNaturally();
                        }
                    }
                }
            }

            collectItems((InventoryHolder) minecart, minecart, radius, collectType);
        }
    }

    private void collectItems(InventoryHolder inventory, Minecart minecart, int radius, Material collectType) {

        if (inventory.getInventory().firstEmpty() < 0) {
            return;
        }

        for (Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                } else if (new ItemData(item.getItemStack()).getMaterial() != collectType) {
                    continue;
                }

                inventory.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

}
