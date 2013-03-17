
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

public enum MinecartType {

    RIDEABLE (Minecart.class), STORAGE (StorageMinecart.class), POWERED (PoweredMinecart.class), HOPPER (HopperMinecart.class), TNT (ExplosiveMinecart.class), SPAWNER (SpawnerMinecart.class);

    public static MinecartType getType(final Minecart minecart) {

        for (final MinecartType minecartType : values()) {
            if (minecartType.getCartClass().equals(minecart.getClass())) {
                return minecartType;
            }
        }

        return null;
    }

    private Class<? extends Minecart> c;

    private MinecartType(final Class<? extends Minecart> c) {

        this.c = c;
    }

    public Class<? extends Minecart> getCartClass() {

        return c;
    }

}
