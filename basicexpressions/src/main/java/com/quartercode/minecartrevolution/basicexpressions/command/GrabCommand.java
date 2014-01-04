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

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.basicexpressions.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;

public class GrabCommand extends ExpressionCommand {

    private final BasicExpressionsPlugin plugin;

    public GrabCommand(BasicExpressionsPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.DOUBLE), "g", "grab");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart.getPassenger() == null;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        int radius = (int) plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_DEFAULT_RADIUS);

        if (parameter != null) {
            if (plugin.getConfiguration().getLong(BasicExpressionConfig.GRAB_MAX_RADIUS) < 0 || (Double) parameter <= plugin.getConfiguration().getLong(BasicExpressionConfig.GRAB_MAX_RADIUS)) {
                radius = Integer.parseInt(String.valueOf(parameter));
            }
        }

        for (Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Player) {
                minecart.setPassenger(entity);
                return;
            }
        }
    }

}
