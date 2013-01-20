
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.quarterbukkit.api.scheduler.ScheduleTask;

public class LockCommand extends ExpressionCommand implements Listener {

    private final List<Minecart> lockedMinecarts = new ArrayList<Minecart>();

    public LockCommand() {

    }

    @Override
    public void enable() {

        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "l", "lock");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (String.valueOf(parameter).equals("+") && !lockedMinecarts.contains(minecart)) {
            lockedMinecarts.add(minecart);
        } else if (String.valueOf(parameter).equals("-") && lockedMinecarts.contains(minecart)) {
            lockedMinecarts.remove(minecart);
        }
    }

    public List<Minecart> getLockedMinecarts() {

        return lockedMinecarts;
    }

    public boolean isLocked(final Minecart minecart) {

        for (final Minecart testMinecart : lockedMinecarts) {
            if (minecart.getEntityId() == testMinecart.getEntityId()) {
                return true;
            }
        }

        return false;
    }

    @EventHandler
    public void onVehicleExit(final VehicleExitEvent event) {

        if (event.getVehicle() instanceof Minecart && isLocked((Minecart) event.getVehicle())) {
            new ScheduleTask(minecartRevolution.getPlugin()) {

                @Override
                public void run() {

                    event.getVehicle().setPassenger(event.getExited());
                }
            }.run(0);
        }
    }

    @EventHandler
    public void onVehicleDamage(final VehicleDamageEvent event) {

        if (event.getVehicle() instanceof Minecart && isLocked((Minecart) event.getVehicle())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof Minecart && isLocked((Minecart) event.getInventory().getHolder())) {
            event.setCancelled(true);
        }
    }

}
