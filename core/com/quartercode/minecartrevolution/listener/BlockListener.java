
package com.quartercode.minecartrevolution.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.get.Perm;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;
import com.quartercode.minecartrevolution.util.ItemData;

public class BlockListener implements Listener {

    private final MinecartRevolution minecartRevolution;

    public BlockListener(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;

        minecartRevolution.getServer().getPluginManager().registerEvents(this, minecartRevolution);
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {

        if (event.getBlock().getType() == Material.RAILS || event.getBlock().getType() == Material.POWERED_RAIL || event.getBlock().getType() == Material.DETECTOR_RAIL) {
            for (final Block block : minecartRevolution.getControlBlockExecutor().getBlocks(event.getBlock().getLocation())) {
                for (final ControlBlock controlBlock : minecartRevolution.getControlBlockExecutor().getControlBlocks()) {
                    final ControlBlockInfo info = controlBlock.getInfo();

                    for (final ItemData itemData : info.getItemDatas()) {
                        if (itemData.equals(block)) {
                            if (!Perm.has(event.getPlayer(), info.getPlacePermission())) {
	event.setCancelled(true);
	event.getPlayer().sendMessage(Lang.getValue("control.place.noPermission"));
                            } else {
	event.getPlayer().sendMessage(Lang.getValue("control.place", "type", info.getName()));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {

        if (event.getBlock().getType() == Material.RAILS || event.getBlock().getType() == Material.POWERED_RAIL || event.getBlock().getType() == Material.DETECTOR_RAIL) {
            for (final Block block : minecartRevolution.getControlBlockExecutor().getBlocks(event.getBlock().getLocation())) {
                for (final ControlBlock controlBlock : minecartRevolution.getControlBlockExecutor().getControlBlocks()) {
                    final ControlBlockInfo info = controlBlock.getInfo();

                    for (final ItemData itemData : info.getItemDatas()) {
                        if (itemData.equals(block)) {
                            if (!Perm.has(event.getPlayer(), info.getDestroyPermission())) {
	event.setCancelled(true);
	event.getPlayer().sendMessage(Lang.getValue("control.destroy.noPermission"));
                            } else {
	event.getPlayer().sendMessage(Lang.getValue("control.destroy", "type", info.getName()));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSignChange(final SignChangeEvent event) {

        String label = event.getLine(0);
        label = ControlSignInfo.getUnformattedLabel(label);

        for (final ControlSign controlSign : minecartRevolution.getControlSignExecutor().getControlSigns()) {
            final ControlSignInfo info = controlSign.getInfo();

            for (final String signLabel : info.getLabels()) {
                if (signLabel.equalsIgnoreCase(label)) {
                    if (!Perm.has(event.getPlayer(), info.getPlacePermission())) {
                        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                            event.getBlock().setTypeId(0);
                        } else {
                            event.getBlock().breakNaturally();
                        }

                        event.getPlayer().sendMessage(Lang.getValue("control.place.noPermission"));
                    } else {
                        event.getPlayer().sendMessage(Lang.getValue("control.place", "type", info.getName()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSignBreak(final BlockBreakEvent event) {

        if (event.getBlock().getType() == Material.SIGN || event.getBlock().getType() == Material.SIGN_POST || event.getBlock().getType() == Material.WALL_SIGN) {
            final Sign sign = (Sign) event.getBlock().getState();
            String label = sign.getLine(0);
            label = ControlSignInfo.getUnformattedLabel(label);

            for (final ControlSign controlSign : minecartRevolution.getControlSignExecutor().getControlSigns()) {
                final ControlSignInfo info = controlSign.getInfo();

                for (final String signLabel : info.getLabels()) {
                    if (signLabel.equalsIgnoreCase(label)) {
                        if (!Perm.has(event.getPlayer(), info.getDestroyPermission())) {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(Lang.getValue("control.destroy.noPermission"));
                        } else {
                            event.getPlayer().sendMessage(Lang.getValue("control.destroy", "type", info.getName()));
                        }
                    }
                }
            }
        }
    }

}
