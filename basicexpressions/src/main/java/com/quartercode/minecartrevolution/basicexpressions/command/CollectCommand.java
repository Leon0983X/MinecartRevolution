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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.basicexpressions.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.AliasUtil;
import com.quartercode.quarterbukkit.api.ItemData;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class CollectCommand extends ExpressionCommand {

    private final BasicExpressionsPlugin plugin;

    public CollectCommand(BasicExpressionsPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.STRING, Type.DOUBLE), "co", "collect");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart instanceof InventoryHolder;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        int radius = (int) plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_DEFAULT_RADIUS);
        List<String> items = new ArrayList<String>();

        if (parameter != null) {
            for (String part : String.valueOf(parameter).split(",")) {
                try {
                    if (part.startsWith("r")) {
                        if (plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_MAX_RADIUS) < 0 || Integer.parseInt(part.replaceAll("r", "")) <= plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_MAX_RADIUS)) {
                            radius = Integer.parseInt(part.replaceAll("r", ""));
                        }
                    } else {
                        items.add(part);
                    }
                } catch (NumberFormatException e) {
                    ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Failed to parse collect radius"));
                }
            }
        }

        if (items.size() > 0) {
            for (String item : items) {
                collectItems((InventoryHolder) minecart, minecart, radius, item);
            }
        } else {
            collectItems((InventoryHolder) minecart, minecart, radius, null);
        }
    }

    private void collectItems(InventoryHolder inventoryMinecart, Minecart minecart, int radius, String string) {

        if (inventoryMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                } else if (string != null && !new ItemData(item.getItemStack()).equals(AliasUtil.getItemData(string))) {
                    continue;
                }

                inventoryMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

}
