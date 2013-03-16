
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Minecart;
import org.bukkit.material.Lever;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class SensorCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public SensorCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "se", "sensor");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    public void execute2(final Minecart minecart, final Object parameter) {

        String term = String.valueOf(parameter);

        final boolean negate = term.startsWith("!");
        if (negate) {
            term = term.replace("!", "");
        }

        for (final MinecartTerm minecartTerm : plugin.getMinecartTerms()) {
            for (final String label : minecartTerm.getLabels()) {
                if (term.matches(label) && minecartTerm.getResult(minecart, Direction.getDirection(minecart), term)) {
                    if (!negate) {
                        power(minecart);
                    }
                    return;
                }
            }
        }

        if (negate) {
            power(minecart);
        }
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        for (final String sensor : String.valueOf(parameter).split(",")) {
            if (trySensor(minecart, sensor)) {
                power(minecart);
                return;
            }
        }
    }

    private boolean trySensor(final Minecart minecart, final String sensor) {

        for (final String term : sensor.split("&")) {
            if (!tryTerm(minecart, term)) {
                return false;
            }
        }

        return true;
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

    private void power(final Minecart minecart) {

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

    private void setPowered(final Location minecartLocation, final boolean powered) {

        final BlockState blockState = getLever(minecartLocation);
        final Lever lever = (Lever) blockState.getData();
        lever.setPowered(powered);
        blockState.setData(lever);
        blockState.update();
    }

    private BlockState getLever(final Location minecartLocation) {

        final Block railBlock = minecartLocation.getBlock();
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
