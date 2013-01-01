
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

    public void setControlSigns(final List<ControlSign> controlSigns) {

        this.controlSigns = controlSigns;
    }

    public void execute(final Minecart minecart) {

        final Location location = minecart.getLocation();

        for (final int[] offsets : controlSignOffsets) {
            final Location signLocation = location.clone();
            signLocation.add(offsets[0], offsets[1], offsets[2]);

            if (signLocation.getBlock().getType() == Material.SIGN || signLocation.getBlock().getType() == Material.SIGN_POST) {
                executeControlSign(signLocation, minecart);
            }
        }
    }

    public void executeControlSign(final Location location, final Minecart minecart) {

        if (location.getBlock().isBlockIndirectlyPowered()) {
            return;
        }

        if (location.getBlock().getState() instanceof Sign) {
            final Sign sign = (Sign) location.getBlock().getState();

            for (final ControlSign controlSign : controlSigns) {
                final ControlSignInfo controlSignInfo = controlSign.getInfo();

                for (final String label : controlSignInfo.getLabels()) {
                    if (ControlSignInfo.getFormattedLabel(label).equalsIgnoreCase(sign.getLine(0))) {
                        controlSign.execute(minecart, location, sign.getLine(0), sign);
                    }
                }
            }
        }
    }

}
