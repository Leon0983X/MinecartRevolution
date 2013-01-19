
package com.quartercode.minecartrevolution.command;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.quarterbukkit.api.command.CommandHandler;
import com.quartercode.quarterbukkit.api.command.CommandInfo;

public abstract class MRCommandHandler implements CommandHandler {

    protected MinecartRevolution minecartRevolution;
    private CommandInfo          info;

    public MRCommandHandler() {

    }

    public void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public final CommandInfo getInfo() {

        if (info == null) {
            info = createInfo();
        }

        return info;
    }

    public void enable() {

    }

    public abstract CommandInfo createInfo();

}
