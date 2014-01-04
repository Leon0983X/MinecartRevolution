/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.term;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.minecartrevolution.core.util.cart.MinecartType;

public class HopperMinecartTerm implements MinecartTerm {

    public HopperMinecartTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "hop", "hopper" };
    }

    @Override
    public boolean getResult(Minecart minecart, Direction direction, String term) {

        return MinecartType.valueOf(minecart) == MinecartType.HOPPER;
    }

}
