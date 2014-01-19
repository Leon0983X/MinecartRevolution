/*
 * This file is part of MinecartRevolution-Basiccontrols.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccontrols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccontrols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccontrols. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccontrols.sign;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignInfo;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;
import com.quartercode.quarterbukkit.api.event.RedstoneToggleEvent;

public class StationSign extends ControlSign implements Listener {

    public StationSign() {

    }

    @Override
    public void enable() {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(MinecartRevolution.getLang().get("basiccontrols.signs.station.name"), MinecartRevolution.getLang().get("basiccontrols.signs.station.description"), "station.place", "station.destroy", "station");
    }

    @Override
    public void execute(Minecart minecart, String label, Sign sign) {

        minecart.setVelocity(new Vector(0, 0, 0));
    }

    @EventHandler
    public void onRedstoneToggle(RedstoneToggleEvent event) {

        if (event.getBlock().getState() instanceof Sign && event.isPowered()) {
            Sign sign = (Sign) event.getBlock().getState();

            for (String label : getInfo().getLabels()) {
                if (sign.getLine(0).equalsIgnoreCase("[" + label + "]")) {
                    for (Minecart minecart : event.getBlock().getWorld().getEntitiesByClass(Minecart.class)) {
                        for (Sign sign2 : minecartRevolution.getControlSignExecutor().getSigns(minecart)) {
                            if (sign.equals(sign2)) {
                                MinecartUtil.driveInDirection(minecart, Direction.valueOf(sign));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            for (Sign sign : minecartRevolution.getControlSignExecutor().getSigns((Minecart) event.getVehicle())) {
                for (String label : getInfo().getLabels()) {
                    if (sign.getLine(0).equalsIgnoreCase("[" + label + "]") && (sign.getLine(1).equalsIgnoreCase("enter") || sign.getLine(2).equalsIgnoreCase("enter"))) {
                        MinecartUtil.driveInDirection((Minecart) event.getVehicle(), Direction.valueOf(sign));
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            for (Sign sign : minecartRevolution.getControlSignExecutor().getSigns((Minecart) event.getVehicle())) {
                for (String label : getInfo().getLabels()) {
                    if (sign.getLine(0).equalsIgnoreCase("[" + label + "]") && (sign.getLine(1).equalsIgnoreCase("exit") || sign.getLine(2).equalsIgnoreCase("exit"))) {
                        MinecartUtil.driveInDirection((Minecart) event.getVehicle(), Direction.valueOf(sign));
                        return;
                    }
                }
            }
        }
    }

}
