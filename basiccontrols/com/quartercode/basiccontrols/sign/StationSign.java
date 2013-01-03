
package com.quartercode.basiccontrols.sign;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;
import com.quartercode.minecartrevolution.util.MinecartUtil;

public class StationSign extends ControlSign implements Listener {

    private final MinecartRevolution minecartRevolution;

    public StationSign(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
        Bukkit.getPluginManager().registerEvents(this, minecartRevolution);
    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.station.name"), Lang.getValue("basiccontrols.signs.station.description"), "station.place", "station.destroy", "station");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        minecart.setVelocity(new Vector(0, 0, 0));
    }

    @EventHandler
    public void onBlockRedstoneUpdate(final BlockRedstoneEvent event) {

        if (event.getBlock().getState() instanceof Sign) {
            if (event.getBlock().isBlockPowered()) {
                final Sign sign = (Sign) event.getBlock().getState();

                for (final String label : getInfo().getLabels()) {
                    if (sign.getLine(0).equalsIgnoreCase("[" + label + "]")) {
                        for (final Minecart minecart : event.getBlock().getWorld().getEntitiesByClass(Minecart.class)) {
                            for (final Sign sign2 : minecartRevolution.getControlSignExecutor().getSigns(minecart)) {
	if (sign.equals(sign2)) {
	    MinecartUtil.driveInSignDirection(minecart, sign);
	}
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleEnter(final VehicleEnterEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            for (final Sign sign : minecartRevolution.getControlSignExecutor().getSigns((Minecart) event.getVehicle())) {
                for (final String label : getInfo().getLabels()) {
                    if (sign.getLine(0).equalsIgnoreCase("[" + label + "]") && (sign.getLine(1).equalsIgnoreCase("enter") || sign.getLine(2).equalsIgnoreCase("enter"))) {
                        MinecartUtil.driveInSignDirection((Minecart) event.getVehicle(), sign);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleExit(final VehicleExitEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            for (final Sign sign : minecartRevolution.getControlSignExecutor().getSigns((Minecart) event.getVehicle())) {
                for (final String label : getInfo().getLabels()) {
                    if (sign.getLine(0).equalsIgnoreCase("[" + label + "]") && (sign.getLine(1).equalsIgnoreCase("exit") || sign.getLine(2).equalsIgnoreCase("exit"))) {
                        MinecartUtil.driveInSignDirection((Minecart) event.getVehicle(), sign);
                    }
                }
            }
        }
    }

}
