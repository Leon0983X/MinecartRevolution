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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.bukkit.entity.Vehicle;

public class VehicleMetdataStorage {

    private final Map<String, Map<String, String>> metadata = new HashMap<String, Map<String, String>>();

    public VehicleMetdataStorage() {

    }

    private String getVehicleKey(Vehicle vehicle) {

        return vehicle.getUniqueId().toString();
    }

    public String get(Vehicle vehicle, String key) {

        if (metadata.containsKey(getVehicleKey(vehicle))) {
            return metadata.get(getVehicleKey(vehicle)).get(key);
        } else {
            return null;
        }
    }

    public boolean getBoolen(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return false;
        } else {
            return Boolean.parseBoolean(get(vehicle, key));
        }
    }

    public byte getByte(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Byte.parseByte(get(vehicle, key));
        }
    }

    public short getShort(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Short.parseShort(get(vehicle, key));
        }
    }

    public int getInteger(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Integer.parseInt(get(vehicle, key));
        }
    }

    public long getLong(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Long.parseLong(get(vehicle, key));
        }
    }

    public float getFloat(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Float.parseFloat(get(vehicle, key));
        }
    }

    public double getDouble(Vehicle vehicle, String key) {

        if (get(vehicle, key) == null) {
            return 0;
        } else {
            return Double.parseDouble(get(vehicle, key));
        }
    }

    public void set(Vehicle vehicle, String key, String value) {

        if (!metadata.containsKey(getVehicleKey(vehicle))) {
            metadata.put(getVehicleKey(vehicle), new HashMap<String, String>());
        }

        if (value == null) {
            metadata.get(getVehicleKey(vehicle)).remove(key);
        } else {
            metadata.get(getVehicleKey(vehicle)).put(key, value);
        }

        if (metadata.get(getVehicleKey(vehicle)).isEmpty()) {
            metadata.remove(getVehicleKey(vehicle));
        }
    }

    public void setBoolean(Vehicle vehicle, String key, boolean value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setByte(Vehicle vehicle, String key, byte value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setShort(Vehicle vehicle, String key, short value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setInteger(Vehicle vehicle, String key, int value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setLong(Vehicle vehicle, String key, long value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setFloat(Vehicle vehicle, String key, float value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void setDouble(Vehicle vehicle, String key, double value) {

        set(vehicle, key, String.valueOf(value));
    }

    public void clear(Vehicle vehicle) {

        metadata.remove(getVehicleKey(vehicle));
    }

    public Properties serialize() {

        Properties properties = new Properties();
        for (Entry<String, Map<String, String>> vehicleMetdata : metadata.entrySet()) {
            for (Entry<String, String> pair : vehicleMetdata.getValue().entrySet()) {
                properties.put(vehicleMetdata.getKey() + "." + pair.getKey(), pair.getValue());
            }
        }

        return properties;
    }

    public void deserialize(Properties properties) {

        metadata.clear();

        for (Entry<Object, Object> entry : properties.entrySet()) {
            String entryKey = String.valueOf(entry.getKey());
            String[] entryKeyParts = entryKey.split("\\.");
            if (entryKeyParts.length == 2) {
                String vehicle = entryKeyParts[0];
                String key = entryKeyParts[1];
                String value = String.valueOf(entry.getValue());

                if (!metadata.containsKey(vehicle)) {
                    metadata.put(vehicle, new HashMap<String, String>());
                }
                metadata.get(vehicle).put(key, value);
            }
        }
    }

}
