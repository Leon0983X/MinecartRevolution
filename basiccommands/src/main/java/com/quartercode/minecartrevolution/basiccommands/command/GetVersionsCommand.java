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

package com.quartercode.minecartrevolution.basiccommands.command;

import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.quarterbukkit.api.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class GetVersionsCommand extends MRCommandHandler {

    public GetVersionsCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, MinecartRevolution.getLang().get("basiccommands.getversions.description"), "minecartrevolution.command.versions.get", "getversions", "getv", "versions");
    }

    @Override
    public void execute(Command command) {

        for (Updater updater : minecartRevolution.getUpdaters()) {
            command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.getversions.return", "plugin", updater.getUpdatePlugin().getName(), "pluginVersion", updater.getUpdatePlugin().getDescription().getVersion()));
        }
    }

}
