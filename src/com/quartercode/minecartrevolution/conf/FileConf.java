
package com.quartercode.minecartrevolution.conf;

import com.quartercode.qcutil.io.File;

public class FileConf {

    // Global
    public static final String MAIN_CONF_NAME = "config.yml";

    // Plugin folder
    public static final File   PLUGIN_FOLDER  = new File("plugins", Conf.NAME);

    // First level
    public static final File   EXPRESSIONS    = new File(PLUGIN_FOLDER, "expressions");
    public static final File   LANGUAGES      = new File(PLUGIN_FOLDER, "languages");
    public static final File   PLUGINS        = new File(PLUGIN_FOLDER, "plugins");
    public static final File   MAIN_CONF      = new File(PLUGIN_FOLDER, MAIN_CONF_NAME);

    static {
        PLUGIN_FOLDER.mkdirs();

        EXPRESSIONS.mkdirs();
    }

    private FileConf() {

    }

}
