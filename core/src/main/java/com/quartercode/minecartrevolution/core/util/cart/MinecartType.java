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

import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

public enum MinecartType {

    RIDEABLE (RideableMinecart.class, "rideable", "minecart", "standard", "default"), STORAGE (StorageMinecart.class, "storage", "chest"), POWERED (PoweredMinecart.class, "powered", "fuel", "furnace"), HOPPER (HopperMinecart.class, "hopper"), TNT (ExplosiveMinecart.class, "tnt", "explosive"), SPAWNER (SpawnerMinecart.class, "spawner"), COMMAND (CommandMinecart.class, "com", "command");

    public static MinecartType valueOf(Minecart minecart) {

        for (MinecartType minecartType : values()) {
            if (minecartType.getCartClass().isAssignableFrom(minecart.getClass())) {
                return minecartType;
            }
        }

        return null;
    }

    private Class<? extends Minecart> c;
    private String[]                  names;

    private MinecartType(Class<? extends Minecart> c, String... names) {

        this.c = c;
        this.names = names;
    }

    public Class<? extends Minecart> getCartClass() {

        return c;
    }

    public String[] getNames() {

        return names;
    }

}
