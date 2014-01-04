/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.control.block;

import com.quartercode.quarterbukkit.api.ItemData;

public class ControlBlockInfo {

    private final String name;
    private final String description;
    private final String placePermission;
    private final String destroyPermission;
    private ItemData[]   itemDatas;

    public ControlBlockInfo(String name, String description, String placePermission, String destroyPermission, ItemData... itemDatas) {

        this.name = name;
        this.description = description;
        this.placePermission = "control.block." + placePermission;
        this.destroyPermission = "control.block." + destroyPermission;
        this.itemDatas = itemDatas;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getPlacePermission() {

        return placePermission;
    }

    public String getDestroyPermission() {

        return destroyPermission;
    }

    public ItemData[] getItemDatas() {

        return itemDatas;
    }

    public void setItemDatas(ItemData[] itemDatas) {

        this.itemDatas = itemDatas;
    }

}
