
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.minecartrevolution.util.Config;

public abstract class PluginConfig extends Config {

    public PluginConfig(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin.getConfigFile());
    }

}
