
package com.quartercode.minecartrevolution.plugin;

import java.util.List;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.qcutil.ds.OnceList;

public class PluginManager {

    private static MinecartRevolution             minecartRevolution;
    private static List<MinecartRevolutionPlugin> plugins = new OnceList<MinecartRevolutionPlugin>();

    public static void registerMinecartRevolution(final MinecartRevolution minecartRevolution) {

        PluginManager.minecartRevolution = minecartRevolution;
    }

    public static MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    public static void registerPlugin(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        if (minecartRevolution == null) {
            throw new IllegalStateException("A plugin can only be registered after MinecartRevolution was loaded [" + minecartRevolutionPlugin.getInfo().getName() + "]");
        } else {
            minecartRevolutionPlugin.setMinecartRevolution(minecartRevolution);
            plugins.add(minecartRevolutionPlugin);
        }
    }

    public static void enablePlugin(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        minecartRevolution.getLogger().info("Enabling plugin '" + minecartRevolutionPlugin.getInfo().getName() + "' ...");
        minecartRevolutionPlugin.onEnable();
        minecartRevolution.getLogger().info("Plugin '" + minecartRevolutionPlugin.getInfo().getName() + "' successfully enabled!");
    }

    public static List<MinecartRevolutionPlugin> getPlugins() {

        return plugins;
    }

    private PluginManager() {

    }

}
