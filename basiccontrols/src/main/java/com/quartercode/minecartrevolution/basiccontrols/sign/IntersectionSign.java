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
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.sign.ControlSign;
import com.quartercode.minecartrevolution.core.control.sign.ControlSignInfo;
import com.quartercode.minecartrevolution.core.get.Perm;

public class IntersectionSign extends ControlSign {

    public IntersectionSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(MinecartRevolution.getLang().get("basiccontrols.signs.intersection.name"), MinecartRevolution.getLang().get("basiccontrols.signs.intersection.description"), "intersection.place", "intersection.destroy", "intersection");
    }

    @Override
    public void execute(Minecart minecart, String label, Sign sign) {

        String expression = "intersection ";

        if (!sign.getLine(1).isEmpty()) {
            expression += sign.getLine(1);
        }
        if (!sign.getLine(2).isEmpty()) {
            if (!sign.getLine(1).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(2);
        }
        if (!sign.getLine(3).isEmpty()) {
            if (!sign.getLine(1).isEmpty() || !sign.getLine(2).isEmpty()) {
                expression += ",";
            }
            expression += sign.getLine(3);
        }

        executeExpression(minecart, expression);
    }

    @Override
    public boolean allowPlace(Player player, String[] lines, Sign sign) {

        for (String line : lines) {
            if (line.split(":").length == 2) {
                String action = line.split(":")[1];
                if (action.toLowerCase().startsWith("c-") || action.toLowerCase().startsWith("cmd-") || action.toLowerCase().startsWith("command-")) {
                    if (!Perm.has(player, "control.sign.intersection.command")) {
                        return false;
                    }
                } else if (!action.equalsIgnoreCase("r") && !action.equalsIgnoreCase("l") && !action.equalsIgnoreCase("m") && !action.equalsIgnoreCase("re") && !action.equalsIgnoreCase("n") && !action.equalsIgnoreCase("e") && !action.equalsIgnoreCase("s") && !action.equalsIgnoreCase("w")) {
                    if (!Perm.has(player, "control.sign.intersection.expression")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
