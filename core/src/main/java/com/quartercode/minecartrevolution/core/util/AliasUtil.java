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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.conf.FileConf;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.quarterbukkit.api.ItemData;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class AliasUtil {

    private static MinecartRevolution    minecartRevolution;
    private static Map<String, ItemData> aliases;

    public static void setMinecartRevolution(MinecartRevolution minecartRevolution) {

        AliasUtil.minecartRevolution = minecartRevolution;
    }

    @SuppressWarnings ("deprecation")
    public static ItemData getItemData(String string) {

        try {
            if (string.contains(":")) {
                return new ItemData(Material.getMaterial(Integer.parseInt(string.split(":")[0])), Byte.parseByte(string.split(":")[1]));
            } else {
                return new ItemData(Material.getMaterial(Integer.parseInt(string)));
            }
        }
        catch (NumberFormatException e) {
            return resolveAlias(string);
        }
    }

    // We have to use magic numbers for aliases until we find a better solution
    @SuppressWarnings ("deprecation")
    private static ItemData resolveAlias(String string) {

        if (aliases == null) {
            aliases = new HashMap<String, ItemData>();

            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(new File(FileConf.DATA, "items.csv")));
                String line = null;

                while ( (line = reader.readLine().trim()) != null) {
                    if (!line.isEmpty() && !line.startsWith("#")) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            aliases.put(parts[0], new ItemData(Material.getMaterial(Integer.parseInt(parts[1])), Byte.parseByte(parts[2])));
                        }
                    }
                }
                reader.close();
            }
            catch (IOException e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Error while reading items.csv file for retrieving aliases"));
            }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (aliases.containsKey(string)) {
            return aliases.get(string);
        } else {
            return null;
        }
    }

}
