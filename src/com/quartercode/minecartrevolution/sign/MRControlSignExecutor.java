
package com.quartercode.minecartrevolution.sign;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public class MRControlSignExecutor {

    private static final int[][] controlSignOffsets = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 0, -2, 0 }, { 0, 2, 0 }, { 0, 3, 0 } };

    private List<ControlSign>    controlSigns;

    public MRControlSignExecutor() {

        controlSigns = new ArrayList<ControlSign>();
    }

    public List<ControlSign> getControlSigns() {

        return controlSigns;
    }

    public void setControlSigns(List<ControlSign> controlSigns) {

        this.controlSigns = controlSigns;
    }

    public void execute(Minecart minecart) {

        Location location = minecart.getLocation();

        for (int[] offsets : controlSignOffsets) {
            Location signLocation = location.clone();
            signLocation.add(offsets[0], offsets[1], offsets[2]);

            if (signLocation.getBlock().getType() == Material.SIGN || signLocation.getBlock().getType() == Material.SIGN_POST) {
                executeControlSign(signLocation, minecart);
            }
        }
    }

    public boolean executeControlSign(Location location, Minecart minecart) {

        boolean executed = false;

        if (location.getBlock().getState() instanceof Sign) {
            Sign sign = (Sign) location.getBlock().getState();

            for (ControlSign controlSign : controlSigns) {
                ControlSignInfo controlSignInfo = controlSign.getInfo();

                for (String label : controlSignInfo.getLabels()) {
                    if (ControlSignInfo.getFormattedLabel(label).equalsIgnoreCase(sign.getLine(0))) {
                        controlSign.execute(minecart, location, sign.getLine(0), sign);
                        executed = true;
                    }
                }
            }
        }

        return executed;
    }

}
