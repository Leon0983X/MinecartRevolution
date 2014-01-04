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

package com.quartercode.minecartrevolution.core.command;

import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.quarterbukkit.api.command.CommandExecutor;
import com.quartercode.quarterbukkit.api.command.CommandHandler;

public class MRCommandExecutor extends CommandExecutor {

    private final MinecartRevolution minecartRevolution;

    public MRCommandExecutor(MinecartRevolution minecartRevolution, String... commands) {

        super(minecartRevolution.getPlugin(), commands);
        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public void addCommandHandler(CommandHandler commandHandler) {

        if (commandHandler instanceof MRCommandHandler) {
            ((MRCommandHandler) commandHandler).setMinecartRevolution(minecartRevolution);
        }

        super.addCommandHandler(commandHandler);

        if (commandHandler instanceof MRCommandHandler) {
            ((MRCommandHandler) commandHandler).enable();
        }
    }

}
