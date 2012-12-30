
package com.quartercode.minecartrevolution.plugin;

import com.quartercode.qcutil.version.Version;

public class PluginInfo {

    private final String  name;
    private final Version version;

    public PluginInfo(final String name, final Version version) {

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
