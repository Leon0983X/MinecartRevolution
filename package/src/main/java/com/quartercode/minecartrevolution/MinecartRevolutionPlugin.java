/*
 * This file is part of MinecartRevolution-Package.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Package is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Package is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Package. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.quartercode.minecartrevolution.basicactions.BasicActionsPlugin;
import com.quartercode.minecartrevolution.basiccommands.BasicCommandsPlugin;
import com.quartercode.minecartrevolution.basiccontrols.BasicControlsPlugin;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.plugin.PluginManager;
import com.quartercode.quarterbukkit.QuarterBukkitIntegration;

public class MinecartRevolutionPlugin extends JavaPlugin {

    private MinecartRevolution minecartRevolution;

    public MinecartRevolutionPlugin() {

    }

    public MinecartRevolution getMinecartRevolution() {

        return minecartRevolution;
    }

    @Override
    public void onEnable() {

        // QuarterBukkit
        if (!QuarterBukkitIntegration.integrate(this)) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Core
        minecartRevolution = new MinecartRevolution(this);
        minecartRevolution.enable();

        // Default Plugins
        PluginManager.registerPlugin(new BasicCommandsPlugin());
        PluginManager.registerPlugin(new BasicControlsPlugin());
        PluginManager.registerPlugin(new BasicExpressionsPlugin());
        PluginManager.registerPlugin(new BasicActionsPlugin());
    }

    @Override
    public void onDisable() {

        if (minecartRevolution != null) {
            minecartRevolution.disable();
        }
    }

}
