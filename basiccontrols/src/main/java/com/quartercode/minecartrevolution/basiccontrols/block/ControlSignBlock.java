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

package com.quartercode.minecartrevolution.basiccontrols.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.control.block.ControlBlock;
import com.quartercode.minecartrevolution.core.control.block.ControlBlockInfo;
import com.quartercode.quarterbukkit.api.ItemData;

public class ControlSignBlock extends ControlBlock {

    public ControlSignBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(MinecartRevolution.getLang().get("basiccontrols.blocks.controlsign.name"), MinecartRevolution.getLang().get("basiccontrols.blocks.controlsign.description"), "controlsign.place", "controlsign.destroy", new ItemData(Material.BRICK));
    }

    @Override
    public void execute(Minecart minecart, Block block) {

        if (hasSontrolSign(block.getLocation())) {
            for (Sign sign : getControlSigns(block.getLocation())) {
                minecartRevolution.getControlSignExecutor().executeControlSign(sign, minecart);
            }
        }
    }

}
