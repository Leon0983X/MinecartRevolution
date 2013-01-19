
package com.quartercode.minecartrevolution.util;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.get.Lang;

public class GlobalConfig extends Config {

    public static final String  LANGUAGE               = "language";

    private static final String UPDATE                 = "update";
    public static final String  AUTO_UPDATE            = UPDATE + ".autoUpdate";
    public static final String  CHECK_VERSION_ON_START = UPDATE + ".checkVersionOnStart";
    public static final String  CHECK_VERSION_ON_JOIN  = UPDATE + ".checkVersionOnJoin";

    private static final String ERROR                  = "error";
    public static final String  PRINT_SILENCE_ERRORS   = ERROR + ".printSilenceErrors";

    private static final String MINECART               = "minecart";
    public static final String  PLAY_EFFECTS           = MINECART + ".playEffects";

    public GlobalConfig(final MinecartRevolution minecartRevolution) {

        super(minecartRevolution, FileConf.MAIN_CONF);
    }

    @Override
    public void setDefaults() {

        addDefault(LANGUAGE, Lang.STANDARD_LANGUAGE);

        addDefault(AUTO_UPDATE, false);
        addDefault(CHECK_VERSION_ON_START, true);
        addDefault(CHECK_VERSION_ON_JOIN, true);

        addDefault(PRINT_SILENCE_ERRORS, false);

        addDefault(PLAY_EFFECTS, true);
    }

}
