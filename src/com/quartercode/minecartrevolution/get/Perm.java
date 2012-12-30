
package com.quartercode.minecartrevolution.get;

import org.bukkit.entity.Player;

public class Perm {

    public static boolean has(final Player player, final String permission) {

        if (player == null) {
            return false;
        }

        if (permission.startsWith("minecartrevolution.")) {
            return player.hasPermission(permission);
        } else {
            return player.hasPermission("minecartrevolution." + permission);
        }
    }

    private Perm() {

    }

}
