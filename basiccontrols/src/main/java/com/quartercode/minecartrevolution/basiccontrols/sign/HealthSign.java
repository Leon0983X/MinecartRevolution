/*
 * This file is part of MinecartRevolution-Basiccontrols.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccontrols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccontrols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccontrols. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccontrols.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignInfo;

public class HealthSign extends ControlSign {

    public HealthSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(MinecartRevolution.getLang().get("basiccontrols.signs.health.name"), MinecartRevolution.getLang().get("basiccontrols.signs.health.description"), "health.place", "health.destroy", "health");
    }

    @Override
    public void execute(Minecart minecart, String label, Sign sign) {

        executeExpression(minecart, "health " + sign.getLine(1));
    }

}
