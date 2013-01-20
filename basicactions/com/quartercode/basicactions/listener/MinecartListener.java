
package com.quartercode.basicactions.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.util.Vector;
import com.quartercode.basicexpression.command.LockCommand;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;

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

}
