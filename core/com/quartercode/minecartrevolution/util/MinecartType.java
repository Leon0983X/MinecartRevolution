
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

public enum MinecartType {

    RIDEABLE (Minecart.class, "rideable", "minecart", "standard", "default"), STORAGE (StorageMinecart.class, "storage", "chest"), POWERED (PoweredMinecart.class, "powered", "fuel", "furnace"), HOPPER (HopperMinecart.class, "hopper"), TNT (ExplosiveMinecart.class, "tnt", "explosive"), SPAWNER (SpawnerMinecart.class, "spawner");

    public static MinecartType getType(final Minecart minecart) {

        for (final MinecartType minecartType : values()) {
            if (minecartType.getCartClass().equals(minecart.getClass())) {
                return minecartType;
            }
        }

        return null;
    }

    private final Class<? extends Minecart> c;
    private final String[]                  names;

    private MinecartType(final Class<? extends Minecart> c, final String... names) {

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
