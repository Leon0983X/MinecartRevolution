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

import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public class EjectCommand extends MRCommandHandler {

    public EjectCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, MinecartRevolution.getLang().get("basiccommands.eject.description"), "minecartrevolution.command.eject", "eject");
    }

    @Override
    public void execute(Command command) {

        if (command.getSender() instanceof Player) {
            Player player = (Player) command.getSender();

            if (player.getVehicle() != null) {
                player.getVehicle().eject();
            }
        }
    }

}
