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

package com.quartercode.minecartrevolution.core.control.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.core.control.Control;

public abstract class ControlSign extends Control {

    public static final int[][] CONTROL_SIGN_OFFSETS = {
                                                     // Next to the rail
                                                     { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 },
                                                     // Attached to the block the rail is placed on
                                                     { 1, -1, 0 }, { -1, -1, 0 }, { 0, -1, 1 }, { 0, -1, -1 },
                                                     // Above or below the rail
                                                     { 0, -2, 0 }, { 0, 2, 0 }, { 0, 3, 0 } };

    private ControlSignInfo     info;

    public ControlSignInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    protected abstract ControlSignInfo createInfo();

    public abstract void execute(Minecart minecart, String label, Sign sign);

    public boolean allowPlace(Player player, String[] lines, Sign sign) {

        return true;
    }

    public boolean allowDestroy(Player player, String[] lines, Sign sign) {

        return true;
    }

}
