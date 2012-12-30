
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class IntersectionCommand implements ExpressionCommand {

    private final MinecartRevolution minecartRevolution;

    public IntersectionCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("i", "intersection");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (parameter != null && String.valueOf(parameter).split(":").length == 2) {
            final String type = String.valueOf(parameter).split(":")[0];
            final String direction = String.valueOf(parameter).split(":")[1];
            String newDirection = null;

            if (type.equalsIgnoreCase("n") || type.equalsIgnoreCase("e") || type.equalsIgnoreCase("s") || type.equalsIgnoreCase("w")) {
                if (type.equalsIgnoreCase("n") && minecart.getVelocity().getZ() > 0.0D) {
                    newDirection = direction;
                } else if (type.equalsIgnoreCase("e") && minecart.getVelocity().getX() < 0.0D) {
                    newDirection = direction;
                } else if (type.equalsIgnoreCase("s") && minecart.getVelocity().getZ() < 0.0D) {
                    newDirection = direction;
                } else if (type.equalsIgnoreCase("w") && minecart.getVelocity().getX() > 0.0D) {
                    newDirection = direction;
                }
            } else if (type.equalsIgnoreCase("storage") && minecart instanceof StorageMinecart) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("powered") && minecart instanceof PoweredMinecart) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("standard") && minecart instanceof Minecart && ! (minecart instanceof StorageMinecart) && ! (minecart instanceof PoweredMinecart)) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("player") && minecart.getPassenger() instanceof Player) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("mob") && (minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof Animals)) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("empty") && minecart.isEmpty()) {
                newDirection = direction;
            } else if (type.equalsIgnoreCase("nocargo")) {
                if (minecart.getPassenger() instanceof Player) {
                    final Player player = (Player) minecart.getPassenger();
                    for (int counter = 0; counter < player.getInventory().getSize(); counter++) {
                        if (player.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    newDirection = direction;
                } else if (minecart instanceof StorageMinecart) {
                    final StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                        if (storageMinecart.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    newDirection = direction;
                } else {
                    newDirection = direction;
                }
            } else if (type.contains("p-") && minecart.getPassenger() instanceof Player) {
                final Player player = (Player) minecart.getPassenger();
                final String[] variables = type.split("-");
                if (player.getName().equalsIgnoreCase(variables[1])) {
                    newDirection = direction;
                }
            } else if (type.contains("invc-")) {
                if (minecart.getPassenger() instanceof Player) {
                    final Player player = (Player) minecart.getPassenger();
                    final String[] variables = type.split("-");
                    if (player.getInventory().contains(minecartRevolution.getAliasConfig().getId(variables[1]))) {
                        newDirection = direction;
                    }
                } else if (minecart instanceof StorageMinecart) {
                    final StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    final String[] variables = type.split("-");
                    if (storageMinecart.getInventory().contains(minecartRevolution.getAliasConfig().getId(variables[1]))) {
                        newDirection = direction;
                    }
                }
            } else if (type.contains("ihold-") && minecart.getPassenger() instanceof Player) {
                final Player player = (Player) minecart.getPassenger();
                final String[] variables = type.split("-");
                if (player.getInventory().getItemInHand().getTypeId() == minecartRevolution.getAliasConfig().getId(variables[1])) {
                    newDirection = direction;
                }
            }

            if (newDirection != null) {
                driveMinecart(minecart, newDirection);
            }
        }
    }

    private void driveMinecart(final Minecart minecart, final String newDriveDirection) {

        final Vector speed = new Vector();
        final double speedNumber = MinecartUtil.getSpeed(minecart);
        final Location newLocation = minecart.getLocation();
        if (newDriveDirection.equalsIgnoreCase("r") || newDriveDirection.equalsIgnoreCase("l") || newDriveDirection.equalsIgnoreCase("m")) {
            if (minecart.getVelocity().getX() > 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("r")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("l")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("m")) {
                    speed.setX(speedNumber);
                }
            } else if (minecart.getVelocity().getX() < 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("r")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("l")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("m")) {
                    speed.setX(-speedNumber);
                }
            } else if (minecart.getVelocity().getZ() > 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("r")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("l")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("m")) {
                    speed.setZ(speedNumber);
                }
            } else if (minecart.getVelocity().getZ() < 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("r")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("l")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("m")) {
                    speed.setZ(-speedNumber);
                }
            }
        } else if (newDriveDirection.equalsIgnoreCase("n")) {
            speed.setZ(speedNumber);
            newLocation.setZ(newLocation.getZ() + 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("e")) {
            speed.setX(-speedNumber);
            newLocation.setX(newLocation.getX() - 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("s")) {
            speed.setZ(-speedNumber);
            newLocation.setZ(newLocation.getZ() - 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("w")) {
            speed.setX(speedNumber);
            newLocation.setX(newLocation.getX() + 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("d")) {
            minecart.remove();
        } else if (newDriveDirection.equalsIgnoreCase("ej")) {
            minecart.eject();
        }

        minecart.setVelocity(speed);
        minecart.teleport(newLocation);
    }

}
