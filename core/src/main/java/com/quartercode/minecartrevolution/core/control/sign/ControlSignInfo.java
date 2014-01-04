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

package com.quartercode.minecartrevolution.core.control.sign;

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

    private final String   name;
    private final String   description;
    private final String   placePermission;
    private final String   destroyPermission;
    private final String[] labels;

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
