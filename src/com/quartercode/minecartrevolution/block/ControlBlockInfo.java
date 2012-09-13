
package com.quartercode.minecartrevolution.block;

public class ControlBlockInfo {

    private String name;
    private String description;
    private String placePermission;
    private String destroyPermission;
    private int[]  blockIds;

    public ControlBlockInfo(String name, String description, String placePermission, String destroyPermission, int... blockIds) {

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

}
