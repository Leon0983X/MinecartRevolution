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

package com.quartercode.minecartrevolution.basicactions.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignInfo;

public class SpawnSign extends ControlSign {

    public SpawnSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(MinecartRevolution.getLang().get("basicactions.spawn.name"), MinecartRevolution.getLang().get("basicactions.spawn.description"), "spawn.place", "spawn.destroy", "spawn");
    }

    @Override
    public void execute(Minecart minecart, String label, Sign sign) {

    }

}
