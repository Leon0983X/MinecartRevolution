
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
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

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        String term = String.valueOf(parameter);

        if ( (term.equalsIgnoreCase("n") || term.equalsIgnoreCase("north")) && Direction.getDirection(minecart) == Direction.NORTH) {
            power(minecart);
        } else if ( (term.equalsIgnoreCase("e") || term.equalsIgnoreCase("east")) && Direction.getDirection(minecart) == Direction.EAST) {
            power(minecart);
        } else if ( (term.equalsIgnoreCase("s") || term.equalsIgnoreCase("south")) && Direction.getDirection(minecart) == Direction.SOUTH) {
            power(minecart);
        } else if ( (term.equalsIgnoreCase("w") || term.equalsIgnoreCase("west")) && Direction.getDirection(minecart) == Direction.WEST) {
            power(minecart);
        } else if (term.equalsIgnoreCase("storage") && minecart instanceof StorageMinecart) {
            power(minecart);
        } else if (term.equalsIgnoreCase("powered") && minecart instanceof PoweredMinecart) {
            power(minecart);
        } else if (term.equalsIgnoreCase("standard") && ! (minecart instanceof StorageMinecart) && ! (minecart instanceof PoweredMinecart)) {
            power(minecart);
        } else if (term.equalsIgnoreCase("invcart") && minecart instanceof InventoryHolder) {
            power(minecart);
        } else if (term.equalsIgnoreCase("player") && minecart.getPassenger() instanceof Player) {
            power(minecart);
        } else if (term.equalsIgnoreCase("mob") && (minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof Animals)) {
            power(minecart);
        } else if (term.equalsIgnoreCase("empty") && minecart.isEmpty()) {
            power(minecart);
        } else if (term.equalsIgnoreCase("nocargo")) {
            if (minecart.getPassenger() instanceof Player) {
                final Player player = (Player) minecart.getPassenger();
                for (int counter = 0; counter < player.getInventory().getSize(); counter++) {
                    if (player.getInventory().getItem(counter) != null) {
                        return;
                    }
                }
                power(minecart);
            } else if (minecart instanceof StorageMinecart) {
                final StorageMinecart storageMinecart = (StorageMinecart) minecart;
                for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                    if (storageMinecart.getInventory().getItem(counter) != null) {
                        return;
                    }
                }
                power(minecart);
            } else {
                power(minecart);
            }
        } else if (term.contains("p-") && minecart.getPassenger() instanceof Player) {
            final Player player = (Player) minecart.getPassenger();
            final String[] variables = term.split("-");
            if (player.getName().equalsIgnoreCase(variables[1])) {
                power(minecart);
            }
        } else if (term.contains("invc-")) {
            if (minecart.getPassenger() instanceof InventoryHolder) {
                final String[] variables = term.split("-");
                if (contains( ((InventoryHolder) minecart.getPassenger()).getInventory(), variables[1])) {
                    power(minecart);
                }
            } else if (minecart instanceof InventoryHolder) {
                final String[] variables = term.split("-");
                if (contains( ((InventoryHolder) minecart).getInventory(), variables[1])) {
                    power(minecart);
                }
            }
        } else if (term.contains("ihold-") && minecart.getPassenger() instanceof Player) {
            final Player player = (Player) minecart.getPassenger();
            final String[] variables = term.split("-");
            if (MaterialAliasConfig.equals(player.getItemInHand(), variables[1])) {
                power(minecart);
            }
        } else {
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
    }

    private boolean contains(final Inventory inventory, final String string) {

        for (final ItemStack itemStack : inventory.getContents()) {
            if (MaterialAliasConfig.equals(itemStack, string)) {
                return true;
            }
        }

        return false;
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
            }.run((long) (time * 1000D));
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
