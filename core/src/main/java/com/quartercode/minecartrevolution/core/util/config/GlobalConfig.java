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

package com.quartercode.minecartrevolution.core.util.config;

import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.get.FileConf;

public class GlobalConfig extends Config {

    // ---------- Plugin ----------

    private static final String PLUGIN                          = "plugin";

    public static final String  LANGUAGE                        = PLUGIN + ".language";

    private static final String UPDATE                          = PLUGIN + ".update";
    public static final String  AUTO_UPDATE                     = UPDATE + ".autoUpdate";
    public static final String  CHECK_VERSION_ON_JOIN           = UPDATE + ".checkVersionOnJoin";

    private static final String ERRORS                          = PLUGIN + ".errors";
    public static final String  PRINT_ERROR_MESSAGES            = ERRORS + ".printMessages";
    public static final String  PRINT_ERROR_STACK_TRACES        = ERRORS + ".printStackTraces";
    public static final String  PRINT_SILENT_ERROR_MESSAGES     = ERRORS + ".printSilentMessages";
    public static final String  PRINT_SILENT_ERROR_STACK_TRACES = ERRORS + ".printSilentStackTraces";

    // ---------- Functionality ----------

    private static final String FUNCTIONALITY                   = "functionality";

    private static final String ENTITY                          = FUNCTIONALITY + ".entity";
    public static final String  MAX_SPEED                       = ENTITY + ".maxSpeed";
    public static final String  PLAY_DEFAULT_EFFECTS            = ENTITY + ".playDefaultEffects";

    private static final String ENTITY_METADATA                 = ENTITY + ".metadata";
    public static final String  SAVE_TIME_INTERVAL              = ENTITY_METADATA + ".saveTimeInterval";

    public GlobalConfig(MinecartRevolution minecartRevolution) {

        super(minecartRevolution, FileConf.MAIN_CONF);
    }

    @Override
    public void setDefaults() {

        addDefault(LANGUAGE, "en");

        addDefault(AUTO_UPDATE, false);
        addDefault(CHECK_VERSION_ON_JOIN, true);

        addDefault(PRINT_ERROR_MESSAGES, true);
        addDefault(PRINT_ERROR_STACK_TRACES, true);
        addDefault(PRINT_SILENT_ERROR_MESSAGES, false);
        addDefault(PRINT_SILENT_ERROR_STACK_TRACES, false);

        addDefault(MAX_SPEED, 0.4);
        addDefault(PLAY_DEFAULT_EFFECTS, true);

        addDefault(SAVE_TIME_INTERVAL, 600);
    }

}
