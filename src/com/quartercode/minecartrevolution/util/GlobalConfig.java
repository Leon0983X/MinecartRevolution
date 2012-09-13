
package com.quartercode.minecartrevolution.util;

import com.quartercode.minecartrevolution.conf.FileConf;

public class GlobalConfig extends Config {

    public static final String language           = "language";

    public static final String checkVersion       = "checkversion";
    public static final String checkVersionOnJoin = checkVersion + ".onJoin";

    public static final String error              = "error";
    public static final String printSilenceErrors = error + ".printSilenceErrors";

    public GlobalConfig() {

        super(FileConf.MAIN_CONF);
    }

    @Override
    public void setDefaults() {

        addDefault(language, "english");
        addDefault(checkVersionOnJoin, true);
        addDefault(printSilenceErrors, false);
    }

}
