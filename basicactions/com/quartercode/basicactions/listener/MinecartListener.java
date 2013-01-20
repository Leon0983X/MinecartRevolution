
package com.quartercode.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.quartercode.basicexpression.command.LockCommand;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.minecartrevolution.util.MinecartType;
import com.quartercode.minecartrevolution.util.MinecartUtil;

public class MinecartListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public MinecartListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onVehicleEnter(final VehicleEnterEvent event) {

        if (event.getEntered() instanceof CommandSender) {
            ((CommandSender) event.getEntered()).sendMessage(Lang.getValue("basicactions.punch"));
        }
    }

    @EventHandler
    public void onVehicleDamage(final VehicleDamageEvent event) {

        if (event.getVehicle() instanceof Minecart && event.getAttacker() instanceof Player) {
            final Minecart minecart = (Minecart) event.getVehicle();
            final Player player = (Player) event.getAttacker();
            if (Perm.has(player, "action.punch") && player.isInsideVehicle() && minecart.getLocation().getBlock().getType() == Material.RAILS) {
                for (final ExpressionCommand expressionCommand : minecartRevolution.getExpressionExecutor().getExpressionCommands()) {
                    if (expressionCommand instanceof LockCommand) {
                        if ( ((LockCommand) expressionCommand).isLocked(minecart)) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                }

                final Vector velocity = player.getLocation().getDirection();
                minecart.setVelocity(velocity);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void VehicleBlockCollisionEvent(final VehicleBlockCollisionEvent event) {

        if (event.getVehicle() instanceof Minecart && event.getBlock().getType() == Material.CHEST) {
            final Minecart minecart = (Minecart) event.getVehicle();
            final MinecartType type = MinecartType.getType(minecart);
            final Chest chest = (Chest) event.getBlock().getState();

            if (chest.getInventory().firstEmpty() < 0) {
                return;
            }

            if (type == MinecartType.MINECART) {
                chest.getInventory().addItem(new ItemStack(Material.MINECART));
            } else if (type == MinecartType.STORAGE) {
                chest.getInventory().addItem(new ItemStack(Material.STORAGE_MINECART));
            } else if (type == MinecartType.POWERED) {
                chest.getInventory().addItem(new ItemStack(Material.POWERED_MINECART));
            } else {
                return;
            }

            MinecartUtil.remove(minecart);
        }
    }

}
