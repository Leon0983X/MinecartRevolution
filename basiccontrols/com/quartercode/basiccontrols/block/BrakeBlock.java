
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.basiccontrols.BasicControlsPlugin;
import com.quartercode.basiccontrols.util.BasicControlsConfig;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;

public class BrakeBlock extends ControlBlock {

    private BasicControlsPlugin basicControlsPlugin;

    public BrakeBlock(BasicControlsPlugin basicControlsPlugin) {

        this.basicControlsPlugin = basicControlsPlugin;
    }

    @Override
    public ControlBlockInfo getInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.brake.name"), Lang.getValue("basiccontrols.blocks.brake.description"), "brake.place", "brake.destroy", Material.SOUL_SAND.getId());
    }

    @Override
    public void execute(Minecart minecart, Location blockLocation, int blockId, Block block) {

        executeExpression(minecart, (String) basicControlsPlugin.getConfig().get(BasicControlsConfig.brakeBlockExpression));
    }

}
