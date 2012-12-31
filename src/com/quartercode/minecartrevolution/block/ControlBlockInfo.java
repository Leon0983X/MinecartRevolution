
package com.quartercode.minecartrevolution.block;

public class ControlBlockInfo {

    private final String name;
    private final String description;
    private final String placePermission;
    private final String destroyPermission;
    private int[]        blockIds;

    public ControlBlockInfo(final String name, final String description, final String placePermission, final String destroyPermission, final int... blockIds) {

        this.name = name;
        this.description = description;
        this.placePermission = "control.block." + placePermission;
        this.destroyPermission = "control.block." + destroyPermission;
        this.blockIds = blockIds;
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

    public int[] getBlockIds() {

        return blockIds;
    }

    public void setBlockIds(final int[] blockIds) {

        this.blockIds = blockIds;
    }

}
