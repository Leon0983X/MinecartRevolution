
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.qcutil.version.Version;

public class PluginInfo {

    private String  name;
    private Version version;

    public PluginInfo(String name, Version version) {

        this.name = name;
        this.version = version;
    }

    public String getName() {

        return name;
    }

    public Version getVersion() {

        return version;
    }

}
