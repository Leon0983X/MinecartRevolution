
package com.quartercode.minecartrevolution.util;

import com.quartercode.minecartrevolution.conf.FileConf;

public class GlobalConfig extends Config {

    public static final String language            = "language";

    public static final String update              = "update";
    public static final String autoUpdate          = update + ".autoUpdate";
    public static final String checkVersionOnStart = update + ".checkVersionOnStart";
    public static final String checkVersionOnJoin  = update + ".checkVersionOnJoin";

    public static final String error               = "error";
    public static final String printSilenceErrors  = error + ".printSilenceErrors";

    public GlobalConfig() {

        super(FileConf.MAIN_CONF);
    }

    @Override
    public void setDefaults() {

        addDefault(language, "english");
        addDefault(autoUpdate, false);
        addDefault(checkVersionOnStart, true);
        addDefault(checkVersionOnJoin, true);
        addDefault(printSilenceErrors, false);
    }

}
