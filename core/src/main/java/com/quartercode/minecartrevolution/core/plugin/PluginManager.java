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

package com.quartercode.minecartrevolution.core.plugin;

import java.util.List;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.util.UniqueList;

public class PluginManager {

    private static MinecartRevolution                   minecartRevolution;
    private static final List<MinecartRevolutionPlugin> plugins = new UniqueList<MinecartRevolutionPlugin>();

    public static void registerMinecartRevolution(MinecartRevolution minecartRevolution) {

        PluginManager.minecartRevolution = minecartRevolution;
    }

    public static void registerPlugin(MinecartRevolutionPlugin minecartRevolutionPlugin) {

        if (minecartRevolution == null) {
            throw new IllegalStateException("A plugin can only be registered after MinecartRevolution was loaded [" + minecartRevolutionPlugin.getInfo().getName() + "]");
        }

        minecartRevolution.getLogger().info("Loading plugin '" + minecartRevolutionPlugin.getInfo().getName() + "' ...");
        minecartRevolutionPlugin.setMinecartRevolution(minecartRevolution);
        plugins.add(minecartRevolutionPlugin);

        minecartRevolution.getLogger().info("Enabling plugin '" + minecartRevolutionPlugin.getInfo().getName() + "' ...");
        minecartRevolutionPlugin.getPluginFolder().mkdirs();
        minecartRevolutionPlugin.enable();
        minecartRevolution.getLogger().info("Plugin '" + minecartRevolutionPlugin.getInfo().getName() + "' successfully enabled!");
    }

    public static List<MinecartRevolutionPlugin> getPlugins() {

        return plugins;
    }

    public static MinecartRevolutionPlugin getPlugin(String name) {

        for (MinecartRevolutionPlugin plugin : plugins) {
            if (plugin.getInfo().getName().equals(name)) {
                return plugin;
            }
        }

        return null;
    }

    public static MinecartRevolutionPlugin getPlugin(Class<? extends MinecartRevolutionPlugin> c) {

        for (MinecartRevolutionPlugin plugin : plugins) {
            if (plugin.getClass().equals(c)) {
                return plugin;
            }
        }

        return null;
    }

    private PluginManager() {

    }

}
