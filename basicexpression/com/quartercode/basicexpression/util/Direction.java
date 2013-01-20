
package com.quartercode.basicexpression.util;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public static Direction getDirection(final Minecart minecart) {

        if (minecart.getVelocity().getX() > 0.0D) {
            return WEST;
        } else if (minecart.getVelocity().getX() < 0.0D) {
            return EAST;
        } else if (minecart.getVelocity().getZ() > 0.0D) {
            return NORTH;
        } else if (minecart.getVelocity().getZ() < 0.0D) {
            return SOUTH;
        } else {
            return null;
        }
    }

    public static Direction getDirection(final Sign sign) {

        if (sign.getRawData() == 0) {
            return SOUTH;
        } else if (sign.getRawData() == 4) {
            return WEST;
        } else if (sign.getRawData() == 8) {
            return NORTH;
        } else if (sign.getRawData() == 12) {
            return EAST;
        } else {
            return null;
        }
    }

    public Direction getRight() {

        if (ordinal() == values().length - 1) {
            return values()[0];
        } else {
            return values()[ordinal() + 1];
        }
    }

    public Direction getLeft() {

        if (ordinal() == 0) {
            return values()[values().length - 1];
        } else {
            return values()[ordinal() - 1];
        }
    }

}
