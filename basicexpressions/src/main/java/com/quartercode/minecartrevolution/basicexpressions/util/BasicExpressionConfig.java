/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.util;

import com.quartercode.minecartrevolution.core.plugin.MinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.core.plugin.PluginConfig;

public class BasicExpressionConfig extends PluginConfig {

    private static final String ANNOUNCE                   = "announce";
    public static final String  ANNOUNCE_ALLOW_COLOR_CODES = ANNOUNCE + ".allowColorCodes";

    private static final String COLLECT                    = "collect";
    public static final String  COLLECT_DEFAULT_RADIUS     = COLLECT + ".defaultRadius";
    public static final String  COLLECT_MAX_RADIUS         = COLLECT + ".maxRadius";

    private static final String FARM                       = "farm";
    public static final String  FARM_DEFAULT_RADIUS        = FARM + ".defaultRadius";
    public static final String  FARM_MAX_RADIUS            = FARM + ".maxRadius";

    private static final String GRAB                       = "grab";
    public static final String  GRAB_DEFAULT_RADIUS        = GRAB + ".defaultRadius";
    public static final String  GRAB_MAX_RADIUS            = GRAB + ".maxRadius";

    private static final String SENSOR                     = "sensor";
    public static final String  SENSOR_POWER_TIME          = SENSOR + ".powerTime";

    private static final String SPEED                      = "speed";
    public static final String  SPEED_MAX_SPEED            = SPEED + ".maxSpeed";

    public BasicExpressionConfig(MinecartRevolutionPlugin minecartRevolutionPlugin) {

        super(minecartRevolutionPlugin);
    }

    @Override
    public void setDefaults() {

        addDefault(ANNOUNCE_ALLOW_COLOR_CODES, true);

        addDefault(COLLECT_DEFAULT_RADIUS, 5);
        addDefault(COLLECT_MAX_RADIUS, 20);

        addDefault(FARM_DEFAULT_RADIUS, 5);
        addDefault(FARM_MAX_RADIUS, 20);

        addDefault(GRAB_DEFAULT_RADIUS, 5);
        addDefault(GRAB_MAX_RADIUS, 20);

        addDefault(SENSOR_POWER_TIME, 1);

        addDefault(SPEED_MAX_SPEED, 100);
    }

}
