/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands;

import com.quartercode.minecartrevolution.basiccommands.command.EjectCommand;
import com.quartercode.minecartrevolution.basiccommands.command.GetVersionsCommand;
import com.quartercode.minecartrevolution.basiccommands.command.HelpCommand;
import com.quartercode.minecartrevolution.basiccommands.command.InfoCommand;
import com.quartercode.minecartrevolution.basiccommands.command.PatchCommand;
import com.quartercode.minecartrevolution.basiccommands.command.RemovecartsCommand;
import com.quartercode.minecartrevolution.basiccommands.command.StopcartsCommand;
import com.quartercode.minecartrevolution.basiccommands.command.UpdateCommand;
import com.quartercode.minecartrevolution.basiccommands.command.VersioncheckCommand;
import com.quartercode.minecartrevolution.basiccommands.listener.PlayerListener;
import com.quartercode.minecartrevolution.basiccommands.util.MinecartRevolutionRecodePatcher;
import com.quartercode.minecartrevolution.basiccommands.util.Patcher;
import com.quartercode.minecartrevolution.core.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginInfo;

public class BasicCommandsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicCommandsPlugin() {

        super();
    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicCommands");
    }

    @Override
    public void enable() {

        new PlayerListener(getMinecartRevolution());

        addCommandHandler(new HelpCommand());
        addCommandHandler(new InfoCommand());
        addCommandHandler(new GetVersionsCommand());
        addCommandHandler(new VersioncheckCommand());
        addCommandHandler(new UpdateCommand());
        addCommandHandler(new RemovecartsCommand());
        addCommandHandler(new StopcartsCommand());
        addCommandHandler(new EjectCommand());
        addCommandHandler(new PatchCommand());

        Patcher.addPatcher(new MinecartRevolutionRecodePatcher(getMinecartRevolution()));
    }

}
