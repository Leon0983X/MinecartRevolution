
package com.quartercode.minecartrevolution.sign;

public class ControlSignInfo {

    public static String getFormattedLabel(final String label) {

        return "[" + label + "]";
    }

    public static String getUnformattedLabel(final String label) {

        if (label.startsWith("[") && label.endsWith("]")) {
            return label.substring(1, label.length() - 1);
        } else {
            return null;
        }
    }

    private final String   name;
    private final String   description;
    private final String   placePermission;
    private final String   destroyPermission;
    private final String[] labels;

    public ControlSignInfo(final String name, final String description, final String placePermission, final String destroyPermission, final String... labels) {

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
