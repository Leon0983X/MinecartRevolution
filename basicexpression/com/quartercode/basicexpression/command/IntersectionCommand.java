
package com.quartercode.basicexpression.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class IntersectionCommand extends ExpressionCommand {

    public enum Direction {

        NORTH, EAST, SOUTH, WEST;

        public static Direction getDirection(final Minecart minecart) {

            if (minecart.getVelocity().getX() > 0.0D) {
                return WEST;
            } else if (minecart.getVelocity().getX() < 0.0D) {
                return EAST;
            } else if (minecart.getVelocity().getZ() > 0.0D) {
                return NORTH;
            } else if (minecart.getVelocity().getZ() < 0.0D) {
                return SOUTH;
            } else {
                return null;
            }
        }

        public Direction getRight() {

            if (ordinal() == values().length - 1) {
                return values()[0];
            } else {
                return values()[ordinal() + 1];
            }
        }

        public Direction getLeft() {

            if (ordinal() == 0) {
                return values()[values().length - 1];
            } else {
                return values()[ordinal() - 1];
            }
        }
    }

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

        if (String.valueOf(parameter).split(":").length == 2) {
            final String term = String.valueOf(parameter).split(":")[0];
            final String action = String.valueOf(parameter).split(":")[1];

            if ( (term.equalsIgnoreCase("n") || term.equalsIgnoreCase("north")) && Direction.getDirection(minecart) == Direction.NORTH) {
                execute(minecart, action);
            } else if ( (term.equalsIgnoreCase("e") || term.equalsIgnoreCase("east")) && Direction.getDirection(minecart) == Direction.EAST) {
                execute(minecart, action);
            } else if ( (term.equalsIgnoreCase("s") || term.equalsIgnoreCase("south")) && Direction.getDirection(minecart) == Direction.SOUTH) {
                execute(minecart, action);
            } else if ( (term.equalsIgnoreCase("w") || term.equalsIgnoreCase("west")) && Direction.getDirection(minecart) == Direction.WEST) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("storage") && minecart instanceof StorageMinecart) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("powered") && minecart instanceof PoweredMinecart) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("standard") && ! (minecart instanceof StorageMinecart) && ! (minecart instanceof PoweredMinecart)) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("invcart") && minecart instanceof InventoryHolder) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("player") && minecart.getPassenger() instanceof Player) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("mob") && (minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof Animals)) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("empty") && minecart.isEmpty()) {
                execute(minecart, action);
            } else if (term.equalsIgnoreCase("nocargo")) {
                if (minecart.getPassenger() instanceof Player) {
                    final Player player = (Player) minecart.getPassenger();
                    for (int counter = 0; counter < player.getInventory().getSize(); counter++) {
                        if (player.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    execute(minecart, action);
                } else if (minecart instanceof StorageMinecart) {
                    final StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                        if (storageMinecart.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    execute(minecart, action);
                } else {
                    execute(minecart, action);
                }
            } else if (term.contains("p-") && minecart.getPassenger() instanceof Player) {
                final Player player = (Player) minecart.getPassenger();
                final String[] variables = term.split("-");
                if (player.getName().equalsIgnoreCase(variables[1])) {
                    execute(minecart, action);
                }
            } else if (term.contains("invc-")) {
                if (minecart.getPassenger() instanceof InventoryHolder) {
                    final String[] variables = term.split("-");
                    if (contains( ((InventoryHolder) minecart.getPassenger()).getInventory(), variables[1])) {
                        execute(minecart, action);
                    }
                } else if (minecart instanceof InventoryHolder) {
                    final String[] variables = term.split("-");
                    if (contains( ((InventoryHolder) minecart).getInventory(), variables[1])) {
                        execute(minecart, action);
                    }
                }
            } else if (term.contains("ihold-") && minecart.getPassenger() instanceof Player) {
                final Player player = (Player) minecart.getPassenger();
                final String[] variables = term.split("-");
                if (MaterialAliasConfig.equals(player.getItemInHand(), variables[1])) {
                    execute(minecart, action);
                }
            }
        }
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
        } else if (action.equalsIgnoreCase("d") || action.equalsIgnoreCase("delete")) {
            minecart.remove();
        } else if (action.equalsIgnoreCase("ej") || action.equalsIgnoreCase("eject")) {
            minecart.eject();
        } else if (action.toLowerCase().startsWith("c-") || action.toLowerCase().startsWith("cmd-") || action.toLowerCase().startsWith("command-")) {
            final String command = action.replace("command-", "").replace("cmd-", "").replace("c-", "");
            if (minecart.getPassenger() instanceof CommandSender) {
                Bukkit.dispatchCommand((CommandSender) minecart.getPassenger(), command);
            }
        } else if (action.toLowerCase().startsWith("e-") || action.toLowerCase().startsWith("revo-") || action.toLowerCase().startsWith("control-") || action.toLowerCase().startsWith("script-") || action.toLowerCase().startsWith("expression-")) {
            final String command = action.replace("revo-", "").replace("control-", "").replace("script-", "").replace("expression-", "").replace("e-", "");
            try {
                plugin.getExpressionExecutor().execute(minecart, command);
            }
            catch (final Exception e) {
                MinecartRevolution.handleSilenceThrowable(e);
            }
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

    private boolean contains(final Inventory inventory, final String string) {

        for (final ItemStack itemStack : inventory.getContents()) {
            if (MaterialAliasConfig.equals(itemStack, string)) {
                return true;
            }
        }

        return false;
    }

}
