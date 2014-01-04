/*
 * This file is part of MinecartRevolution-Basicactions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicactions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicactions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicactions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicactions;

import com.quartercode.minecartrevolution.basicactions.listener.BlockListener;
import com.quartercode.minecartrevolution.basicactions.listener.MinecartListener;
import com.quartercode.minecartrevolution.basicactions.sign.SpawnSign;
import com.quartercode.minecartrevolution.core.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginInfo;

public class BasicActionsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicActionsPlugin() {

    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicActions");
    }

    @Override
    public void enable() {

        new MinecartListener(getMinecartRevolution());
        new BlockListener(getMinecartRevolution());

        addControlSign(new SpawnSign());
    }

}
