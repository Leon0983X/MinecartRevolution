
package com.quartercode.basicexpression.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class IntersectionCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public IntersectionCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "i", "intersection");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        for (final String intersection : String.valueOf(parameter).split(",")) {
            if (tryIntersection(minecart, intersection)) {
                execute(minecart, intersection.split(":")[1]);
                return;
            }
        }

        doIntersection(minecart, Direction.getDirection(minecart), Direction.getDirection(minecart));
    }

    private boolean tryIntersection(final Minecart minecart, final String intersection) {

        if (intersection.split(":").length == 2) {
            final String terms = intersection.split(":")[0];

            for (final String term : terms.split("&")) {
                if (!tryTerm(minecart, term)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    private boolean tryTerm(final Minecart minecart, String term) {

        final boolean negate = term.startsWith("!");
        if (negate) {
            term = term.replace("!", "");
        }

        for (final MinecartTerm minecartTerm : plugin.getMinecartTerms()) {
            for (final String label : minecartTerm.getLabels()) {
                if (term.matches(label) && minecartTerm.getResult(minecart, Direction.getDirection(minecart), term)) {
                    return !negate;
                }
            }
        }

        return negate;
    }

    private void execute(final Minecart minecart, final String action) {

        final Direction from = Direction.getDirection(minecart);

        if (action.equalsIgnoreCase("r") || action.equalsIgnoreCase("right")) {
            doIntersection(minecart, from, from.getRight());
        } else if (action.equalsIgnoreCase("l") || action.equalsIgnoreCase("left")) {
            doIntersection(minecart, from, from.getLeft());
        } else if (action.equalsIgnoreCase("m") || action.equalsIgnoreCase("middle")) {
            doIntersection(minecart, from, from);
        } else if (action.equalsIgnoreCase("re") || action.equalsIgnoreCase("reverse")) {
            doIntersection(minecart, from, from.getRight().getRight());
        } else if (action.equalsIgnoreCase("n") || action.equalsIgnoreCase("north")) {
            doIntersection(minecart, from, Direction.NORTH);
        } else if (action.equalsIgnoreCase("e") || action.equalsIgnoreCase("east")) {
            doIntersection(minecart, from, Direction.EAST);
        } else if (action.equalsIgnoreCase("s") || action.equalsIgnoreCase("south")) {
            doIntersection(minecart, from, Direction.SOUTH);
        } else if (action.equalsIgnoreCase("w") || action.equalsIgnoreCase("west")) {
            doIntersection(minecart, from, Direction.WEST);
        } else if (action.toLowerCase().startsWith("c-") || action.toLowerCase().startsWith("cmd-") || action.toLowerCase().startsWith("command-")) {
            final String command = action.replace("command-", "").replace("cmd-", "").replace("c-", "");
            if (minecart.getPassenger() instanceof CommandSender) {
                Bukkit.dispatchCommand((CommandSender) minecart.getPassenger(), command);
            }
        } else {
            plugin.getExpressionExecutor().execute(minecart, action);
        }
    }

    private void doIntersection(final Minecart minecart, final Direction from, final Direction to) {

        final Block rail = minecart.getLocation().getBlock();
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
            rail.setData(data);
        }
    }

    private void reverse(final Minecart minecart) {

        final Vector vector = minecart.getVelocity();
        vector.setX(-vector.getX());
        vector.setZ(-vector.getZ());

        minecart.setVelocity(vector);
    }

}
