
package com.quartercode.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.util.ItemData;

public class ControlSignBlock extends ControlBlock {

    private final MinecartRevolution minecartRevolution;

    public ControlSignBlock(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.controlsign.name"), Lang.getValue("basiccontrols.blocks.controlsign.description"), "controlsign.place", "controlsign.destroy", new ItemData(Material.BRICK));
    }

    @Override
    public void execute(final Minecart minecart, final Block block) {

        if (hasSontrolSign(block.getLocation())) {
            for (final Sign sign : getControlSigns(block.getLocation())) {
                minecartRevolution.getControlSignExecutor().executeControlSign(sign, minecart);
            }
        }
    }

}
