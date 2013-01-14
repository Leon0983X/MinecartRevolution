
package com.quartercode.minecartrevolution;

import org.bukkit.plugin.java.JavaPlugin;
import com.quartercode.qcutil.io.File;

public class MinecartRevolutionPlugin extends JavaPlugin {

    private final MinecartRevolution minecartRevolution;

    public MinecartRevolutionPlugin() {

        minecartRevolution = new MinecartRevolution(this);
    }

    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    @Override
    public File getFile() {

        return (File) super.getFile();
    }

    @Override
    public void onLoad() {

        minecartRevolution.load();
    }

    @Override
    public void onEnable() {

        minecartRevolution.enable();
    }

}
