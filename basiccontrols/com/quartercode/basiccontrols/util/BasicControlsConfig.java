
package com.quartercode.basiccontrols.util;

import java.util.List;
import org.bukkit.Material;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginConfig;
import com.quartercode.quarterbukkit.api.ItemData;

public class BasicControlsConfig extends PluginConfig {

    private static final String CONTROL = "control";
    private static final String BLOCK   = CONTROL + ".block";

    public BasicControlsConfig(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin);
    }

    @Override
    public void setDefaults() {

    }

    public void addBlocks(final List<ControlBlock> controlBlocks) {

        for (final ControlBlock controlBlock : controlBlocks) {
            final String path = BasicControlsConfig.BLOCK + "." + controlBlock.getInfo().getName().toLowerCase().replaceAll(" ", "_") + ".blocks";

            String blocks = "";
            for (final ItemData itemData : controlBlock.getInfo().getItemDatas()) {
                blocks += itemData.getMaterial().getId() + (itemData.getData() == 0 ? "" : ":" + itemData.getData()) + ",";
            }
            blocks = blocks.substring(0, blocks.length() - 1);
            addDefault(path, blocks);

            blocks = String.valueOf(get(path));
            final String[] blockDataStrings = blocks.split(",");
            final ItemData[] itemDatas = new ItemData[blockDataStrings.length];

            for (int counter = 0; counter < blockDataStrings.length; counter++) {
                if (blockDataStrings[counter].contains(":")) {
                    itemDatas[counter] = new ItemData(Material.getMaterial(Integer.parseInt(blockDataStrings[counter].split(":")[0])), Byte.parseByte(blockDataStrings[counter].split(":")[1]));
                } else {
                    itemDatas[counter] = new ItemData(Material.getMaterial(Integer.parseInt(blockDataStrings[counter])));
                }
            }

            controlBlock.getInfo().setItemDatas(itemDatas);
        }
    }

}
