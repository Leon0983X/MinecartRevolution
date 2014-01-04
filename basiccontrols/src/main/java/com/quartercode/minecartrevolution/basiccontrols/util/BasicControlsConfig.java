/*
 * This file is part of MinecartRevolution-Basiccontrols.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccontrols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccontrols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccontrols. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccontrols.util;

import java.util.List;
import org.bukkit.Material;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;
import com.quartercode.minecartrevolution.core.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginConfig;
import com.quartercode.quarterbukkit.api.ItemData;

public class BasicControlsConfig extends PluginConfig {

    private static final String CONTROL = "control";
    private static final String BLOCK   = CONTROL + ".block";

    public BasicControlsConfig(MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin);
    }

    @Override
    public void setDefaults() {

    }

    @SuppressWarnings ("deprecation")
    public void addBlocks(List<ControlBlock> controlBlocks) {

        for (ControlBlock controlBlock : controlBlocks) {
            String path = BasicControlsConfig.BLOCK + "." + controlBlock.getInfo().getName().toLowerCase().replaceAll(" ", "_") + ".blocks";

            String blocks = "";
            for (ItemData itemData : controlBlock.getInfo().getItemDatas()) {
                blocks += itemData.getMaterial().getId() + (itemData.getData() == 0 ? "" : ":" + itemData.getData()) + ",";
            }
            blocks = blocks.substring(0, blocks.length() - 1);
            addDefault(path, blocks);

            blocks = String.valueOf(get(path));
            String[] blockDataStrings = blocks.split(",");
            ItemData[] itemDatas = new ItemData[blockDataStrings.length];

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
