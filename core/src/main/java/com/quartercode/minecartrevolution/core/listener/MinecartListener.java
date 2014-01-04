/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.listener;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import com.quartercode.minecartrevolution.core.MinecartRevolution;

public class MinecartListener implements Listener {

    private final MinecartRevolution      minecartRevolution;
    private final Map<Minecart, Location> minecartMap = new HashMap<Minecart, Location>();

    public MinecartListener(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onVehicleUpdate(VehicleUpdateEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) event.getVehicle();
            if (minecartRevolution.getExpressionExecutor().getMinecartExpressions().containsKey(minecart)) {
                for (String expression : minecartRevolution.getExpressionExecutor().getMinecartExpressions().get(minecart)) {
                    minecartRevolution.getExpressionExecutor().execute(minecart, expression);
                }
            }
        }
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            executeControls((Minecart) event.getVehicle());
        }
    }

    private void executeControls(Minecart minecart) {

        Location location = minecart.getLocation();

        if (minecartMap.containsKey(minecart)) {
            Location storedLocation = minecartMap.get(minecart);
            if (storedLocation.getBlockX() == location.getBlockX() && storedLocation.getBlockY() == location.getBlockY() && storedLocation.getBlockZ() == location.getBlockZ()) {
                return;
            } else {
                minecartMap.remove(minecart);
            }
        }

        minecartMap.put(minecart, minecart.getLocation().getBlock().getLocation());

        minecartRevolution.getControlBlockExecutor().execute(minecart);
        minecartRevolution.getControlSignExecutor().execute(minecart);
    }

}
