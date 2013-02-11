
package com.quartercode.minecartrevolution;

import org.bukkit.plugin.java.JavaPlugin;
import com.quartercode.qcutil.io.File;
import com.quartercode.quarterbukkit.QuarterBukkitIntegration;

public class MinecartRevolutionPlugin extends JavaPlugin {

    private MinecartRevolution minecartRevolution;

    public MinecartRevolutionPlugin() {

    }

    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    @Override
    public File getFile() {

        return (File) super.getFile();
    }

    @Override
    public void onEnable() {

        if (!QuarterBukkitIntegration.integrate(this)) {
            getLogger().severe("Can't enable plugin " + getName() + "! For more information, see above.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        minecartRevolution = new MinecartRevolution(this);
        minecartRevolution.enable();
    }

}
