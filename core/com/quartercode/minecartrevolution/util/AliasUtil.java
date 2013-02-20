
package com.quartercode.minecartrevolution.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.Material;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.quarterbukkit.api.ItemData;
import com.quartercode.quarterbukkit.api.exception.ExceptionManager;

public class AliasUtil {

    private static MinecartRevolution minecartRevolution;

    public static void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        AliasUtil.minecartRevolution = minecartRevolution;
    }

    public static ItemData getItemData(final String string) {

        return new ItemData(Material.getMaterial(getId(string)), getData(string));
    }

    private static int getId(final String string) {

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

    private static byte getData(final String string) {

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
            ExceptionManager.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Failed to parse item string: " + string));
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

    public static boolean equals(final ItemData itemData, final String string) {

        if (itemData == null) {
            return false;
        } else if (string == null) {
            return true;
        } else {
            return itemData.equals(getItemData(string));
        }
    }

}
