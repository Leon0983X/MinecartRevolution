
package com.quartercode.minecartrevolution.listener;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import com.quartercode.minecartrevolution.MinecartRevolution;

public class MinecartListener implements Listener {

    private MinecartRevolution      minecartRevolution;
    private Map<Minecart, Location> minecartMap = new HashMap<Minecart, Location>();

    public MinecartListener(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        minecartRevolution.getServer().getPluginManager().registerEvents(this, minecartRevolution);
    }

    @EventHandler
    public void onVehicleUpdate(VehicleUpdateEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) event.getVehicle();
            if (minecartRevolution.getExpressionExecutor().getMinecartExpressions().containsKey(minecart)) {
                for (String expression : minecartRevolution.getExpressionExecutor().getMinecartExpressions().get(minecart)) {
                    try {
                        minecartRevolution.getExpressionExecutor().execute(minecart, expression);
                    }
                    catch (Exception e) {
                        MinecartRevolution.handleSilenceThrowable(e);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            executeControls((Minecart) event.getVehicle());
        }
    }

    private void executeControls(Minecart minecart) {

        Location location = minecart.getLocation();

        if (minecartMap.containsKey(minecart)) {
            Location storedLocation = minecartMap.get(minecart);
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
