
package com.quartercode.minecartrevolution.block;

import com.quartercode.minecartrevolution.util.ItemData;

public class ControlBlockInfo {

    private final String name;
    private final String description;
    private final String placePermission;
    private final String destroyPermission;
    private ItemData[]   itemDatas;

    public ControlBlockInfo(final String name, final String description, final String placePermission, final String destroyPermission, final ItemData... itemDatas) {

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

    public void setItemDatas(final ItemData[] itemDatas) {

        this.itemDatas = itemDatas;
    }

}
