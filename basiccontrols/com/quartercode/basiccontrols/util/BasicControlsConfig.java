
package com.quartercode.basiccontrols.util;

import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginConfig;

public class BasicControlsConfig extends PluginConfig {

    public static final String control                = "control";
    public static final String block                  = control + ".block";
    public static final String sign                   = control + ".sign";

    public static final String boosterBlock           = block + ".booster";
    public static final String boosterBlockExpression = boosterBlock + ".expression";

    public static final String brakeBlock             = block + ".brake";
    public static final String brakeBlockExpression   = brakeBlock + ".expression";

    public BasicControlsConfig(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin);
    }

    @Override
    public void setDefaults() {

        addDefault(boosterBlockExpression, "speed $speed * 5;");
        addDefault(brakeBlockExpression, "speed $speed / 5;");
    }

}
