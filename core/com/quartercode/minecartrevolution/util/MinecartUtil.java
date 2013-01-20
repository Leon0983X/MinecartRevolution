
package com.quartercode.minecartrevolution.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.util.Vector;
import com.quartercode.basicexpression.util.Direction;

public class MinecartUtil {

    public static double getSpeed(final Minecart minecart) {

        final Vector velocity = minecart.getVelocity();

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

    public static void setSpeed(final Minecart minecart, final double speed) {

        final Vector velocity = minecart.getVelocity();

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

    public static void addSpeed(final Minecart minecart, final double speed) {

        setSpeed(minecart, getSpeed(minecart) + speed);
    }

    public static void subtractSpeed(final Minecart minecart, final double speed) {

        addSpeed(minecart, -speed);
    }

    public static void multiplySpeed(final Minecart minecart, final double factor) {

        final Vector velocity = minecart.getVelocity();

        velocity.setX(velocity.getX() * factor);
        velocity.setZ(velocity.getZ() * factor);

        minecart.setVelocity(velocity);
    }

    public static void divideSpeed(final Minecart minecart, final double factor) {

        final Vector velocity = minecart.getVelocity();

        velocity.setX(velocity.getX() / factor);
        velocity.setZ(velocity.getZ() / factor);

        minecart.setVelocity(velocity);
    }

    public static void driveInDirection(final Minecart minecart, final Direction direction) {

        final Vector velocity = minecart.getVelocity();
        final double speed = 0.3913788423600029;

        if (direction == Direction.SOUTH) {
            final Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() - 1.0D);
            minecart.teleport(newLocation);
            velocity.setZ(-speed);
        } else if (direction == Direction.WEST) {
            final Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() + 1.0D);
            minecart.teleport(newLocation);
            velocity.setX(speed);
        } else if (direction == Direction.NORTH) {
            final Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() + 1.0D);
            minecart.teleport(newLocation);
            velocity.setZ(speed);
        } else if (direction == Direction.EAST) {
            final Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() - 1.0D);
            minecart.teleport(newLocation);
            velocity.setX(-speed);
        }

        minecart.setVelocity(velocity);
    }

    // public static void driveInDirection(final Minecart minecart, Direction direction) {
    //
    // final Vector velocity = minecart.getVelocity();
    // final double speed = 0.3913788423600029;
    //
    // if (sign.getRawData() == 0) {
    // final Location newLocation = minecart.getLocation();
    // newLocation.setZ(minecart.getLocation().getZ() - 1.0D);
    // minecart.teleport(newLocation);
    // velocity.setZ(-speed);
    // } else if (sign.getRawData() == 4) {
    // final Location newLocation = minecart.getLocation();
    // newLocation.setX(minecart.getLocation().getX() + 1.0D);
    // minecart.teleport(newLocation);
    // velocity.setX(speed);
    // } else if (sign.getRawData() == 8) {
    // final Location newLocation = minecart.getLocation();
    // newLocation.setZ(minecart.getLocation().getZ() + 1.0D);
    // minecart.teleport(newLocation);
    // velocity.setZ(speed);
    // } else if (sign.getRawData() == 12) {
    // final Location newLocation = minecart.getLocation();
    // newLocation.setX(minecart.getLocation().getX() - 1.0D);
    // minecart.teleport(newLocation);
    // velocity.setX(-speed);
    // }
    //
    // minecart.setVelocity(velocity);
    // }

    public static boolean remove(final Minecart minecart) {

        final VehicleDestroyEvent event = new VehicleDestroyEvent(minecart, null);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            minecart.remove();
        }

        return event.isCancelled();
    }

    private MinecartUtil() {

    }

}
