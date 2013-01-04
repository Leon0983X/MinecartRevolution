
package com.quartercode.minecartrevolution.conf;

import com.quartercode.qcutil.io.File;

public class FileConf {

    // Global
    public static final String MAIN_CONF_NAME      = "config.yml";

    // Plugin folder
    public static final File   PLUGIN_FOLDER       = new File("plugins", Conf.NAME);

    // First level
    public static final File   SCRIPTS             = new File(PLUGIN_FOLDER, "scripts");
    public static final File   LANGUAGES           = new File(PLUGIN_FOLDER, "languages");
    public static final File   PLUGINS             = new File(PLUGIN_FOLDER, "plugins");
    public static final File   MAIN_CONF           = new File(PLUGIN_FOLDER, MAIN_CONF_NAME);
    public static final File   MATERIAL_ALIAS_CONF = new File("plugins" + File.separator + "Essentials", "items.csv");

    static {

        PLUGIN_FOLDER.mkdirs();
        SCRIPTS.mkdirs();
    }

    private FileConf() {

    }

}
