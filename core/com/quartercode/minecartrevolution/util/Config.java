
package com.quartercode.minecartrevolution.util;

import java.io.IOException;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.qcutil.io.File;

public abstract class Config {

    protected FileConfiguration configuration;
    protected File              file;

    protected Config(final File file) {

        this.file = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {

        return configuration;
    }

    // public Object get(final String path) {
    //
    // if (configuration.isBoolean(path)) {
    // return configuration.getBoolean(path);
    // } else if (configuration.isDouble(path)) {
    // return configuration.getDouble(path);
    // } else if (configuration.isInt(path)) {
    // return configuration.getInt(path);
    // } else if (configuration.isItemStack(path)) {
    // return configuration.getItemStack(path);
    // } else if (configuration.isList(path)) {
    // return configuration.getList(path);
    // } else if (configuration.isLong(path)) {
    // return configuration.getLong(path);
    // } else if (configuration.isOfflinePlayer(path)) {
    // return configuration.getOfflinePlayer(path);
    // } else if (configuration.isString(path)) {
    // return configuration.getString(path);
    // } else if (configuration.isVector(path)) {
    // return configuration.getVector(path);
    // } else {
    // return configuration.get(path);
    // }
    // }

    public String get(final String path) {

        return configuration.getString(path);
    }

    public boolean getBool(final String path) {

        return configuration.getBoolean(path);
    }

    public long getLong(final String path) {

        if (configuration.isLong(path)) {
            return configuration.getLong(path);
        } else {
            return configuration.getInt(path);
        }
    }

    public double getDouble(final String path) {

        if (configuration.isDouble(path)) {
            return configuration.getDouble(path);
        } else {
            return getLong(path);
        }
    }

    public ItemStack getItemStack(final String path) {

        return configuration.getItemStack(path);
    }

    public List<?> getList(final String path) {

        return configuration.getList(path);
    }

    public OfflinePlayer getOfflinePlayer(final String path) {

        return configuration.getOfflinePlayer(path);
    }

    public Vector getVector(final String path) {

        return configuration.getVector(path);
    }

    public void set(final String path, final Object value) {

        configuration.set(path, value);
    }

    public void save() {

        try {
            configuration.save(file);
        }
        catch (final IOException e) {
            MinecartRevolution.handleThrowable(e);
        }
    }

    protected void addDefault(final String path, final Object value) {

        if (!configuration.contains(path)) {
            configuration.set(path, value);
        }
    }

    public abstract void setDefaults();

}
