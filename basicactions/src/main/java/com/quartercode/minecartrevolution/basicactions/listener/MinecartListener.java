/*
 * This file is part of MinecartRevolution-Basicactions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicactions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicactions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicactions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.util.cart.MinecartType;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;

public class MinecartListener implements Listener {

    public MinecartListener(MinecartRevolution minecartRevolution) {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void VehicleBlockCollisionEvent(VehicleBlockCollisionEvent event) {

        if (event.getVehicle() instanceof Minecart && event.getBlock().getType() == Material.CHEST) {
            Minecart minecart = (Minecart) event.getVehicle();
            MinecartType type = MinecartType.valueOf(minecart);
            Chest chest = (Chest) event.getBlock().getState();

            if (chest.getInventory().firstEmpty() < 0) {
                return;
            }

            if (type == MinecartType.RIDEABLE) {
                chest.getInventory().addItem(new ItemStack(Material.MINECART));
            } else if (type == MinecartType.STORAGE) {
                chest.getInventory().addItem(new ItemStack(Material.STORAGE_MINECART));
            } else if (type == MinecartType.POWERED) {
                chest.getInventory().addItem(new ItemStack(Material.POWERED_MINECART));
            } else if (type == MinecartType.HOPPER) {
                chest.getInventory().addItem(new ItemStack(Material.HOPPER_MINECART));
            } else if (type == MinecartType.TNT) {
                chest.getInventory().addItem(new ItemStack(Material.EXPLOSIVE_MINECART));
            } else {
                return;
            }

            MinecartUtil.remove(minecart);
        }
    }

}
