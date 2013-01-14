
package com.quartercode.minecartrevolution.listener;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import com.quartercode.minecartrevolution.MinecartRevolution;

public class MinecartListener implements Listener {

    private final MinecartRevolution      minecartRevolution;
    private final Map<Minecart, Location> minecartMap = new HashMap<Minecart, Location>();

    public MinecartListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution.getPlugin());
    }

    @EventHandler
    public void onVehicleUpdate(final VehicleUpdateEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            final Minecart minecart = (Minecart) event.getVehicle();
            if (minecartRevolution.getExpressionExecutor().getMinecartExpressions().containsKey(minecart)) {
                for (final String expression : minecartRevolution.getExpressionExecutor().getMinecartExpressions().get(minecart)) {
                    try {
                        minecartRevolution.getExpressionExecutor().execute(minecart, expression);
                    }
                    catch (final Exception e) {
                        MinecartRevolution.handleSilenceThrowable(e);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleMove(final VehicleMoveEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            executeControls((Minecart) event.getVehicle());
        }
    }

    private void executeControls(final Minecart minecart) {

        final Location location = minecart.getLocation();

        if (minecartMap.containsKey(minecart)) {
            final Location storedLocation = minecartMap.get(minecart);
            if (storedLocation.getBlockX() == location.getBlockX() && storedLocation.getBlockY() == location.getBlockY() && storedLocation.getBlockZ() == location.getBlockZ()) {
                return;
            } else {
                minecartMap.remove(minecart);
            }
        }

        minecartMap.put(minecart, minecart.getLocation().getBlock().getLocation());

        minecartRevolution.getControlBlockExecutor().execute(minecart);
        minecartRevolution.getControlSignExecutor().execute(minecart);
    }

}
