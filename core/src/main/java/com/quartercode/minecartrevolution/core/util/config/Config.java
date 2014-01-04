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

package com.quartercode.minecartrevolution.core.util.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.exception.MinecartRevolutionException;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public abstract class Config {

    protected MinecartRevolution minecartRevolution;
    protected FileConfiguration  configuration;
    protected File               file;

    protected Config(MinecartRevolution minecartRevolution, File file) {

        this.minecartRevolution = minecartRevolution;
        this.file = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {

        return configuration;
    }

    public String get(String path) {

        return configuration.getString(path);
    }

    public boolean getBool(String path) {

        return configuration.getBoolean(path);
    }

    public long getLong(String path) {

        if (configuration.isLong(path)) {
            return configuration.getLong(path);
        } else {
            return configuration.getInt(path);
        }
    }

    public double getDouble(String path) {

        if (configuration.isDouble(path)) {
            return configuration.getDouble(path);
        } else {
            return getLong(path);
        }
    }

    public ItemStack getItemStack(String path) {

        return configuration.getItemStack(path);
    }

    public List<?> getList(String path) {

        return configuration.getList(path);
    }

    public OfflinePlayer getOfflinePlayer(String path) {

        return configuration.getOfflinePlayer(path);
    }

    public Vector getVector(String path) {

        return configuration.getVector(path);
    }

    public void set(String path, Object value) {

        configuration.set(path, value);
    }

    public void save() {

        try {
            configuration.save(file);
        }
        catch (IOException e) {
            ExceptionHandler.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to save configuration to: " + file.getPath()));
        }
    }

    protected void addDefault(String path, Object value) {

        if (!configuration.contains(path)) {
            configuration.set(path, value);
        }
    }

    public abstract void setDefaults();

}
