
package com.quartercode.basicactions;

import com.quartercode.basicactions.listener.BlockListener;
import com.quartercode.basicactions.listener.MinecartListener;
import com.quartercode.basicactions.sign.SpawnSign;
import com.quartercode.minecartrevolution.plugin.JavaMinecartRevolutionPlugin;
import com.quartercode.minecartrevolution.plugin.PluginInfo;
import com.quartercode.qcutil.version.Version;

public class BasicActionsPlugin extends JavaMinecartRevolutionPlugin {

    public BasicActionsPlugin() {

    }

    @Override
    public PluginInfo getInfo() {

        return new PluginInfo("BasicActions", new Version("1.0"));
    }

    @Override
    public void enable() {

        new MinecartListener(getMinecartRevolution());
        new BlockListener(getMinecartRevolution());

        addControlSign(new SpawnSign());
    }

}
