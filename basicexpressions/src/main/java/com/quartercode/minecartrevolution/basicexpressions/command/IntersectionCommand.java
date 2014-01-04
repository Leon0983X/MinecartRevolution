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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.basicexpressions.BasicExpressionsPlugin;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.core.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.core.expression.TypeArray;
import com.quartercode.minecartrevolution.core.expression.TypeArray.Type;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;

public class IntersectionCommand extends ExpressionCommand {

    private final BasicExpressionsPlugin plugin;

    public IntersectionCommand(BasicExpressionsPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "i", "intersection");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        for (String intersection : String.valueOf(parameter).split(",")) {
            if (tryIntersection(minecart, intersection)) {
                execute(minecart, intersection.split(":")[1]);
                return;
            }
        }

        doIntersection(minecart, Direction.valueOf(minecart), Direction.valueOf(minecart));
    }

    private boolean tryIntersection(Minecart minecart, String intersection) {

        if (intersection.split(":").length == 2) {
            String terms = intersection.split(":")[0];

            for (String term : terms.split("&")) {
                if (!tryTerm(minecart, term)) {
                    return false;
                }
            }

            return true;
        }

        return false;
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

    private void execute(Minecart minecart, String action) {

        Direction from = Direction.valueOf(minecart);

        if (action.equalsIgnoreCase("r") || action.equalsIgnoreCase("right")) {
            doIntersection(minecart, from, from.nextRight());
        } else if (action.equalsIgnoreCase("l") || action.equalsIgnoreCase("left")) {
            doIntersection(minecart, from, from.nextLeft());
        } else if (action.equalsIgnoreCase("m") || action.equalsIgnoreCase("middle")) {
            doIntersection(minecart, from, from);
        } else if (action.equalsIgnoreCase("re") || action.equalsIgnoreCase("reverse")) {
            doIntersection(minecart, from, from.nextRight().nextRight());
        } else if (action.equalsIgnoreCase("n") || action.equalsIgnoreCase("north")) {
            doIntersection(minecart, from, Direction.NORTH);
        } else if (action.equalsIgnoreCase("e") || action.equalsIgnoreCase("east")) {
            doIntersection(minecart, from, Direction.EAST);
        } else if (action.equalsIgnoreCase("s") || action.equalsIgnoreCase("south")) {
            doIntersection(minecart, from, Direction.SOUTH);
        } else if (action.equalsIgnoreCase("w") || action.equalsIgnoreCase("west")) {
            doIntersection(minecart, from, Direction.WEST);
        } else if (action.toLowerCase().startsWith("c-") || action.toLowerCase().startsWith("cmd-") || action.toLowerCase().startsWith("command-")) {
            String command = action.replace("command-", "").replace("cmd-", "").replace("c-", "");
            if (minecart.getPassenger() instanceof CommandSender) {
                Bukkit.dispatchCommand((CommandSender) minecart.getPassenger(), command);
            }
        } else {
            plugin.getExpressionExecutor().execute(minecart, action);
        }
    }

    @SuppressWarnings ("deprecation")
    private void doIntersection(Minecart minecart, Direction from, Direction to) {

        Block rail = minecart.getLocation().getBlock();
        byte data = -1;

        if (from == Direction.NORTH && to == Direction.NORTH) {
            data = 0;
        } else if (from == Direction.NORTH && to == Direction.EAST) {
            data = 8;
        } else if (from == Direction.NORTH && to == Direction.SOUTH) {
            data = 0;
            reverse(minecart);
        } else if (from == Direction.NORTH && to == Direction.WEST) {
            data = 9;
        } else if (from == Direction.EAST && to == Direction.NORTH) {
            data = 6;
        } else if (from == Direction.EAST && to == Direction.EAST) {
            data = 1;
        } else if (from == Direction.EAST && to == Direction.SOUTH) {
            data = 9;
        } else if (from == Direction.EAST && to == Direction.WEST) {
            data = 1;
            reverse(minecart);
        } else if (from == Direction.SOUTH && to == Direction.NORTH) {
            data = 0;
            reverse(minecart);
        } else if (from == Direction.SOUTH && to == Direction.EAST) {
            data = 7;
        } else if (from == Direction.SOUTH && to == Direction.SOUTH) {
            data = 0;
        } else if (from == Direction.SOUTH && to == Direction.WEST) {
            data = 6;
        } else if (from == Direction.WEST && to == Direction.NORTH) {
            data = 7;
        } else if (from == Direction.WEST && to == Direction.EAST) {
            data = 1;
            reverse(minecart);
        } else if (from == Direction.WEST && to == Direction.SOUTH) {
            data = 8;
        } else if (from == Direction.WEST && to == Direction.WEST) {
            data = 1;
        }

        if (data >= 0 && rail.getType() == Material.RAILS) {
            // TODO: Replace with better mechanism
            rail.setData(data);
        }
    }

    private void reverse(Minecart minecart) {

        Vector vector = minecart.getVelocity();
        vector.setX(-vector.getX());
        vector.setZ(-vector.getZ());

        minecart.setVelocity(vector);
    }

}
