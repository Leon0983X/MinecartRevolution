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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Minecart;
import org.bukkit.material.Lever;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.basicexpressions.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class SensorCommand extends ExpressionCommand {

    private final BasicExpressionsPlugin plugin;

    public SensorCommand(BasicExpressionsPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "se", "sensor");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        for (String sensor : String.valueOf(parameter).split(",")) {
            if (trySensor(minecart, sensor)) {
                power(minecart);
                return;
            }
        }
    }

    private boolean trySensor(Minecart minecart, String sensor) {

        for (String term : sensor.split("&")) {
            if (!tryTerm(minecart, term)) {
                return false;
            }
        }

        return true;
    }

    private boolean tryTerm(Minecart minecart, String term) {

        boolean negate = term.startsWith("!");
        if (negate) {
            term = term.replace("!", "");
        }

        for (MinecartTerm minecartTerm : plugin.getMinecartTerms()) {
            for (String label : minecartTerm.getLabels()) {
                if (term.matches(label) && minecartTerm.getResult(minecart, Direction.valueOf(minecart), term)) {
                    return !negate;
                }
            }
        }

        return negate;
    }

    private void power(Minecart minecart) {

        final Location minecartLocation = minecart.getLocation();

        if (getLever(minecartLocation) != null) {
            setPowered(minecartLocation, true);

            double time = 1;
            if (plugin.getConfiguration().getDouble(BasicExpressionConfig.SENSOR_POWER_TIME) > 0) {
                time = plugin.getConfiguration().getDouble(BasicExpressionConfig.SENSOR_POWER_TIME);
            }

            new ScheduleTask(minecartRevolution.getPlugin()) {

                @Override
                public void run() {

                    setPowered(minecartLocation, false);
                }
            }.run(true, (long) (time * 1000D));
        }
    }

    private void setPowered(Location minecartLocation, boolean powered) {

        BlockState blockState = getLever(minecartLocation);
        Lever lever = (Lever) blockState.getData();
        lever.setPowered(powered);
        blockState.setData(lever);
        blockState.update();
    }

    private BlockState getLever(Location minecartLocation) {

        Block railBlock = minecartLocation.getBlock();
        Block block = null;

        if (railBlock.getWorld().getBlockAt(railBlock.getX() + 1, railBlock.getY(), railBlock.getZ()).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX() + 1, railBlock.getY(), railBlock.getZ());
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX() - 1, railBlock.getY(), railBlock.getZ()).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX() - 1, railBlock.getY(), railBlock.getZ());
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY(), railBlock.getZ() + 1).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY(), railBlock.getZ() + 1);
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY(), railBlock.getZ() - 1).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY(), railBlock.getZ() - 1);
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX() + 1, railBlock.getY() - 1, railBlock.getZ()).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX() + 1, railBlock.getY() - 1, railBlock.getZ());
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX() - 1, railBlock.getY() - 1, railBlock.getZ()).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX() - 1, railBlock.getY() - 1, railBlock.getZ());
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY() - 1, railBlock.getZ() + 1).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY() - 1, railBlock.getZ() + 1);
        } else if (railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY() - 1, railBlock.getZ() - 1).getType() == Material.LEVER) {
            block = railBlock.getWorld().getBlockAt(railBlock.getX(), railBlock.getY() - 1, railBlock.getZ() - 1);
        }

        if (block != null) {
            return block.getState();
        } else {
            return null;
        }
    }

}
