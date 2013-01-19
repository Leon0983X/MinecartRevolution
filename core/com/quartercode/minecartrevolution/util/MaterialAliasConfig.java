
package com.quartercode.minecartrevolution.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.inventory.ItemStack;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.quarterbukkit.QuarterBukkit;

public class MaterialAliasConfig {

    private static MinecartRevolution minecartRevolution;

    public static void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        MaterialAliasConfig.minecartRevolution = minecartRevolution;
    }

    public static int getId(final String string) {

        try {
            if (string.contains(":")) {
                return ((Double) Double.parseDouble(string.split(":")[0])).intValue();
            } else {
                return ((Double) Double.parseDouble(string)).intValue();
            }
        }
        catch (final Exception e) {
            return getValue(string, 1);
        }
    }

    public static byte getData(final String string) {

        if (string.contains(":")) {
            return ((Double) Double.parseDouble(string.split(":")[1])).byteValue();
        } else {
            try {
                Double.parseDouble(string);
                return 0;
            }
            catch (final NumberFormatException e) {
                return (byte) getValue(string, 2);
            }
        }
    }

    private static int getValue(final String string, final int index) {

        if (!FileConf.MATERIAL_ALIAS_CONF.exists()) {
            return 0;
        }

        int value = 0;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FileConf.MATERIAL_ALIAS_CONF));
            String line = null;

            while ( (line = reader.readLine()) != null) {
                final String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(string)) {
                    value = Integer.parseInt(parts[index]);
                    break;
                }
            }
            reader.close();
        }
        catch (final IOException e) {
            QuarterBukkit.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Failed to parse item string: " + string));
        }

        if (reader != null) {
            try {
                reader.close();
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    public static boolean equals(final ItemStack itemStack, final String string) {

        if (itemStack == null) {
            return false;
        } else if (string == null) {
            return true;
        } else {
            return itemStack.getTypeId() == getId(string) && itemStack.getData().getData() == getData(string);
        }
    }

}
