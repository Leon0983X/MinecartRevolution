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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import com.quartercode.quarterbukkit.api.ItemData;

public class ItemDataResolver {

    @SuppressWarnings ("deprecation")
    public static ItemData resolve(String string) {

        if (string == null) {
            return null;
        }

        try {
            if (string.contains(":")) {
                return new ItemData(Material.getMaterial(Integer.parseInt(string.split(":")[0])), Byte.parseByte(string.split(":")[1]));
            } else {
                return new ItemData(Material.getMaterial(Integer.parseInt(string)));
            }
        } catch (NumberFormatException e) {
            // No item id provided
            if (Bukkit.getPluginManager().isPluginEnabled("OddItem")) {
                return AliasResolver.resolve(string);
            } else {
                // Can't resolve
                return null;
            }
        }
    }

    private ItemDataResolver() {

    }

}
