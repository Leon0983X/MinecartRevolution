
package com.quartercode.basiccontrols.util;

import java.util.List;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginConfig;

public class BasicControlsConfig extends PluginConfig {

    public static final String CONTROL = "control";
    public static final String BLOCK   = CONTROL + ".block";

    public BasicControlsConfig(final MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin);
    }

    @Override
    public void setDefaults() {

    }

    public void addBlocks(final List<ControlBlock> controlBlocks) {

        for (final ControlBlock controlBlock : controlBlocks) {
            final String path = BasicControlsConfig.BLOCK + "." + controlBlock.getInfo().getName().toLowerCase().replaceAll(" ", "_") + ".ids";

            String blocks = "";
            for (final int blockId : controlBlock.getInfo().getBlockIds()) {
                blocks += blockId + ",";
            }
            blocks = blocks.substring(0, blocks.length() - 1);
            addDefault(path, blocks);

            blocks = String.valueOf(get(path));
            final String[] blockIdStrings = blocks.split(",");
            final int[] blockIds = new int[blockIdStrings.length];

            for (int counter = 0; counter < blockIdStrings.length; counter++) {
                blockIds[counter] = Integer.parseInt(blockIdStrings[counter]);
            }

            controlBlock.getInfo().setBlockIds(blockIds);
        }
    }

}
