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

package com.quartercode.minecartrevolution.basicexpressions.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.cart.MinecartUtil;

public class SpeedCommand extends ExpressionCommand {

    public SpeedCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.DOUBLE), "sp", "speed");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        MinecartUtil.setSpeed(minecart, (Double) parameter);
    }

}
