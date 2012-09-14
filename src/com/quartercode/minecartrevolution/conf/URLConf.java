
package com.quartercode.minecartrevolution.conf;

public class URLConf {

    // BukkitDev
    public static final String BUKKIT_DEV_URL      = "http://dev.bukkit.org/server-mods/" + Conf.NAME_LC;
    public static final String BUKKIT_DEV_FILEFEED = BUKKIT_DEV_URL + "/files.rss";

    // Website
    public static final String HOST                = "localhost/MinecartRevolution";
    public static final String CONNECT_URL         = "http://" + HOST + "/connect";

    // First level
    public static final String LANGUAGES           = CONNECT_URL + "/languages";
    public static final String PLUGINS             = CONNECT_URL + "/plugins";

    // Languages

    // Plugins

    private URLConf() {

    }

}
