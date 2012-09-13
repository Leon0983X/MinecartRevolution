
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.minecartrevolution.util.Config;

public abstract class PluginConfig extends Config {

    public PluginConfig(MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin.getConfigFile());
    }

}
