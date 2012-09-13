
package com.quartercode.minecartrevolution.sign;

public class ControlSignInfo {

    public static String getFormattedLabel(String label) {

        return "[" + label + "]";
    }

    public static String getUnformattedLabel(String label) {

        if (label.startsWith("[") && label.endsWith("]")) {
            return label.substring(1, label.length() - 1);
        } else {
            return null;
        }
    }

    private String   name;
    private String   description;
    private String   placePermission;
    private String   destroyPermission;
    private String[] labels;

    public ControlSignInfo(String name, String description, String placePermission, String destroyPermission, String... labels) {

        this.name = name;
        this.description = description;
        this.placePermission = "control.sign." + placePermission;
        this.destroyPermission = "control.sign." + destroyPermission;
        this.labels = labels;
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

    public String[] getLabels() {

        return labels;
    }

}
