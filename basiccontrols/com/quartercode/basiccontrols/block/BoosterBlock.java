
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

public class BoosterBlock extends ControlBlock {

    private final BasicControlsPlugin basicControlsPlugin;

    public BoosterBlock(final BasicControlsPlugin basicControlsPlugin) {

        this.basicControlsPlugin = basicControlsPlugin;
    }

    @Override
    public ControlBlockInfo getInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.booster.name"), Lang.getValue("basiccontrols.blocks.booster.description"), "booster.place", "booster.destroy", Material.GOLD_BLOCK.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        executeExpression(minecart, (String) basicControlsPlugin.getConfig().get(BasicControlsConfig.boosterBlockExpression));
    }

}
