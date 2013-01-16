
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
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
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class IntersectionCommand extends ExpressionCommand {

    public IntersectionCommand() {

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

            if (term.equalsIgnoreCase("n") || term.equalsIgnoreCase("e") || term.equalsIgnoreCase("s") || term.equalsIgnoreCase("w")) {
                if (term.equalsIgnoreCase("n") && minecart.getVelocity().getZ() > 0.0D) {
                    execute(minecart, action);
                } else if (term.equalsIgnoreCase("e") && minecart.getVelocity().getX() < 0.0D) {
                    execute(minecart, action);
                } else if (term.equalsIgnoreCase("s") && minecart.getVelocity().getZ() < 0.0D) {
                    execute(minecart, action);
                } else if (term.equalsIgnoreCase("w") && minecart.getVelocity().getX() > 0.0D) {
                    execute(minecart, action);
                }
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

        final Vector speed = new Vector();
        final double speedNumber = MinecartUtil.getSpeed(minecart);
        final Location newLocation = minecart.getLocation();
        if (action.equalsIgnoreCase("r") || action.equalsIgnoreCase("l") || action.equalsIgnoreCase("m")) {
            if (minecart.getVelocity().getX() > 0.0D) {
                if (action.equalsIgnoreCase("r")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (action.equalsIgnoreCase("l")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (action.equalsIgnoreCase("m")) {
                    speed.setX(speedNumber);
                }
            } else if (minecart.getVelocity().getX() < 0.0D) {
                if (action.equalsIgnoreCase("r")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (action.equalsIgnoreCase("l")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (action.equalsIgnoreCase("m")) {
                    speed.setX(-speedNumber);
                }
            } else if (minecart.getVelocity().getZ() > 0.0D) {
                if (action.equalsIgnoreCase("r")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (action.equalsIgnoreCase("l")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (action.equalsIgnoreCase("m")) {
                    speed.setZ(speedNumber);
                }
            } else if (minecart.getVelocity().getZ() < 0.0D) {
                if (action.equalsIgnoreCase("r")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (action.equalsIgnoreCase("l")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (action.equalsIgnoreCase("m")) {
                    speed.setZ(-speedNumber);
                }
            }
        } else if (action.equalsIgnoreCase("n")) {
            speed.setZ(speedNumber);
            newLocation.setZ(newLocation.getZ() + 1.0D);
        } else if (action.equalsIgnoreCase("e")) {
            speed.setX(-speedNumber);
            newLocation.setX(newLocation.getX() - 1.0D);
        } else if (action.equalsIgnoreCase("s")) {
            speed.setZ(-speedNumber);
            newLocation.setZ(newLocation.getZ() - 1.0D);
        } else if (action.equalsIgnoreCase("w")) {
            speed.setX(speedNumber);
            newLocation.setX(newLocation.getX() + 1.0D);
        } else if (action.equalsIgnoreCase("d")) {
            minecart.remove();
        } else if (action.equalsIgnoreCase("ej")) {
            minecart.eject();
        }

        minecart.setVelocity(speed);
        minecart.teleport(newLocation);
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
