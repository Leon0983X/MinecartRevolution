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

package com.quartercode.minecartrevolution.core.util;

import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public enum Direction {

    NORTH (BlockFace.NORTH), EAST (BlockFace.EAST), SOUTH (BlockFace.SOUTH), WEST (BlockFace.WEST);

    private BlockFace face;

    private Direction(BlockFace face) {

        this.face = face;
    }

    public BlockFace getFace() {

        return face;
    }

    public Direction nextRight() {

        if (ordinal() == values().length - 1) {
            return values()[0];
        } else {
            return values()[ordinal() + 1];
        }
    }

    public Direction nextLeft() {

        if (ordinal() == 0) {
            return values()[values().length - 1];
        } else {
            return values()[ordinal() - 1];
        }
    }

    public static Direction valueOf(BlockFace face) {

        for (Direction direction : values()) {
            if (direction.getFace() == face) {
                return direction;
            }
        }

        return null;
    }

    public static Direction valueOf(Minecart minecart) {

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

    // TODO: Replace magic value method
    @SuppressWarnings ("deprecation")
    public static Direction valueOf(Sign sign) {

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

}
