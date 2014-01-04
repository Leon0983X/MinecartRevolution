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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.basicexpressions.command.LockCommand;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.get.Perm;
import com.quartercode.minecartrevolution.core.util.cart.MinecartType;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;

public class MinecartListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public MinecartListener(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {

        if (event.getEntered() instanceof CommandSender) {
            ((CommandSender) event.getEntered()).sendMessage(MinecartRevolution.getLang().get("basicactions.punch"));
        }
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {

        if (event.getVehicle() instanceof Minecart && event.getAttacker() instanceof Player) {
            Minecart minecart = (Minecart) event.getVehicle();
            Player player = (Player) event.getAttacker();
            if (Perm.has(player, "action.punch") && player.isInsideVehicle() && minecart.getLocation().getBlock().getType() == Material.RAILS) {
                for (ExpressionCommand expressionCommand : minecartRevolution.getExpressionExecutor().getExpressionCommands()) {
                    if (expressionCommand instanceof LockCommand) {
                        if ( ((LockCommand) expressionCommand).isLocked(minecart)) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                }

                Vector velocity = player.getLocation().getDirection();
                minecart.setVelocity(velocity);
                event.setCancelled(true);
            }
        }
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
