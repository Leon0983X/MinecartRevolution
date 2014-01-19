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

import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import com.quartercode.minecartrevolution.basicexpressions.util.EffectUtil.ExtendedEffect;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.config.GlobalConfig;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class LockCommand extends ExpressionCommand implements Listener {

    public LockCommand() {

    }

    @Override
    public void enable() {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "l", "lock");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (String.valueOf(parameter).equals("+") && !isLocked(minecart)) {
            setLocked(minecart, true);
        } else if (String.valueOf(parameter).equals("-") && isLocked(minecart)) {
            setLocked(minecart, false);
        }

        if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PLAY_DEFAULT_EFFECTS)) {
            ExtendedEffect.DOOR.play(minecart);
        }
    }

    public boolean isLocked(Minecart minecart) {

        return minecartRevolution.getMetadataStorage().getBoolen(minecart, "locked");
    }

    public void setLocked(Minecart minecart, boolean locked) {

        minecartRevolution.getMetadataStorage().setBoolean(minecart, "locked", locked);
    }

    @EventHandler
    public void onVehicleExit(final VehicleExitEvent event) {

        if (event.getVehicle() instanceof Minecart && isLocked((Minecart) event.getVehicle())) {
            new ScheduleTask(minecartRevolution.getPlugin()) {

                @Override
                public void run() {

                    event.getVehicle().setPassenger(event.getExited());
                }
            }.run(true, 0);
        }
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {

        if (event.getVehicle() instanceof Minecart && isLocked((Minecart) event.getVehicle())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof Minecart && isLocked((Minecart) event.getInventory().getHolder())) {
            event.setCancelled(true);
        }
    }

}
