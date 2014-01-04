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

package com.quartercode.minecartrevolution.core.conf;

import java.io.File;

public class FileConf {

    // Global
    public static final String MAIN_CONF_NAME = "config.yml";

    // Plugin folder
    public static final File   PLUGIN_FOLDER  = new File("plugins", "MinecartRevolution");

    // First level
    public static final File   SCRIPTS        = new File(PLUGIN_FOLDER, "scripts");
    public static final File   DATA           = new File(PLUGIN_FOLDER, "data");
    public static final File   PLUGINS        = new File(PLUGIN_FOLDER, "plugins");
    public static final File   MAIN_CONF      = new File(PLUGIN_FOLDER, MAIN_CONF_NAME);

    static {

        PLUGIN_FOLDER.mkdirs();
        SCRIPTS.mkdirs();
    }

    private FileConf() {

    }

}
