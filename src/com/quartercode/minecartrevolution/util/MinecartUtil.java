
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

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

    private MinecartUtil() {

    }

}
