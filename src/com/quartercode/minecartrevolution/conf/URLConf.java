
package com.quartercode.minecartrevolution.conf;

public class URLConf {

    public static final String HOST                 = "localhost/MinecartRevolution";
    public static final String CONNECT_URL          = "http://" + HOST + "/connect";

    // First level
    public static final String BUILD                = CONNECT_URL + "/build";
    public static final String LANGUAGES            = CONNECT_URL + "/languages";
    public static final String PLUGINS              = CONNECT_URL + "/plugins";
    public static final String DOWNLOAD_COUNTER     = CONNECT_URL + "/download-counter";

    // Build
    public static final String BUILD_VERSION        = BUILD + "/version.txt";
    public static final String BUILD_FILE           = BUILD + "/plugin.jar";

    // Languages

    // Plugins

    // Download-Counter
    public static final String DOWNLOAD_COUNTER_GET = DOWNLOAD_COUNTER + "/get.php";

    private URLConf() {

    }

}
