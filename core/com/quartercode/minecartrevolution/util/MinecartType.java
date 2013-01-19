
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;

public enum MinecartType {

    MINECART, STORAGE, POWERED;

    public static MinecartType getType(final Minecart minecart) {

        if (minecart instanceof StorageMinecart) {
            return STORAGE;
        } else if (minecart instanceof PoweredMinecart) {
            return POWERED;
        } else {
            return MINECART;
        }
    }

}
