
package com.quartercode.minecartrevolution.util;

import java.io.IOException;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.qcutil.io.File;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public abstract class Config {

    protected MinecartRevolution minecartRevolution;
    protected FileConfiguration  configuration;
    protected File               file;

    protected Config(final MinecartRevolution minecartRevolution, final File file) {

        this.minecartRevolution = minecartRevolution;
        this.file = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {

        return configuration;
    }

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
            ExceptionHandler.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to save configuration to: " + file.getPath()));
        }
    }

    protected void addDefault(final String path, final Object value) {

        if (!configuration.contains(path)) {
            configuration.set(path, value);
        }
    }

    public abstract void setDefaults();

}
