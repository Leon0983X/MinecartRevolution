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
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class HoldCommand extends ExpressionCommand {

    public HoldCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "ho", "hold");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, Object parameter) {

        double time = 10;
        if (Type.DOUBLE.isInstance(parameter)) {
            time = (Double) parameter;
        }

        if (time > 0) {
            final Vector oldVelocity = minecart.getVelocity();
            minecart.setVelocity(new Vector(0, 0, 0));

            new ScheduleTask(minecartRevolution.getPlugin()) {

                @Override
                public void run() {

                    minecart.setVelocity(oldVelocity);
                }
            }.run(true, (long) (time * 1000D));
        }
    }

}
