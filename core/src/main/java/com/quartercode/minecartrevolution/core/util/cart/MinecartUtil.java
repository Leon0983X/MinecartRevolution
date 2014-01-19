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

package com.quartercode.minecartrevolution.core.util.cart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.core.util.Direction;

public class MinecartUtil {

    public static double getSpeed(Minecart minecart) {

        Vector velocity = minecart.getVelocity();

        if (velocity.getX() > 0) {
            return velocity.getX();
        } else if (velocity.getX() < 0) {
            return -velocity.getX();
        } else if (velocity.getZ() > 0) {
            return velocity.getZ();
        } else if (velocity.getZ() < 0) {
            return -velocity.getZ();
        } else {
            return 0;
        }
    }

    public static void setSpeed(Minecart minecart, double speed) {

        Vector velocity = minecart.getVelocity();

        if (velocity.getX() > 0) {
            velocity.setX(speed);
        } else if (velocity.getX() < 0) {
            velocity.setX(-speed);
        } else if (velocity.getZ() > 0) {
            velocity.setZ(speed);
        } else if (velocity.getZ() < 0) {
            velocity.setZ(-speed);
        }

        minecart.setVelocity(velocity);
    }

    public static void addSpeed(Minecart minecart, double speed) {

        setSpeed(minecart, getSpeed(minecart) + speed);
    }

    public static void subtractSpeed(Minecart minecart, double speed) {

        addSpeed(minecart, -speed);
    }

    public static void multiplySpeed(Minecart minecart, double factor) {

        Vector velocity = minecart.getVelocity();

        velocity.setX(velocity.getX() * factor);
        velocity.setZ(velocity.getZ() * factor);

        minecart.setVelocity(velocity);
    }

    public static void divideSpeed(Minecart minecart, double factor) {

        Vector velocity = minecart.getVelocity();

        velocity.setX(velocity.getX() / factor);
        velocity.setZ(velocity.getZ() / factor);

        minecart.setVelocity(velocity);
    }

    public static void driveInDirection(Minecart minecart, Direction direction) {

        Vector velocity = minecart.getVelocity();
        double speed = 0.3913788423600029;

        if (direction == Direction.SOUTH) {
            Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() - 1.0D);
            minecart.teleport(newLocation);
            velocity.setZ(-speed);
        } else if (direction == Direction.WEST) {
            Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() + 1.0D);
            minecart.teleport(newLocation);
            velocity.setX(speed);
        } else if (direction == Direction.NORTH) {
            Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() + 1.0D);
            minecart.teleport(newLocation);
            velocity.setZ(speed);
        } else if (direction == Direction.EAST) {
            Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() - 1.0D);
            minecart.teleport(newLocation);
            velocity.setX(-speed);
        }

        minecart.setVelocity(velocity);
    }

    public static boolean remove(Minecart minecart) {

        VehicleDestroyEvent event = new VehicleDestroyEvent(minecart, null);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            minecart.remove();
        }

        return event.isCancelled();
    }

    private MinecartUtil() {

    }

}
