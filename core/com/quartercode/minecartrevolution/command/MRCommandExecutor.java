
package com.quartercode.minecartrevolution.command;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.quarterbukkit.api.command.CommandExecutor;
import com.quartercode.quarterbukkit.api.command.CommandHandler;

public class MRCommandExecutor extends CommandExecutor {

    private final MinecartRevolution minecartRevolution;

    public MRCommandExecutor(final MinecartRevolution minecartRevolution, final String... commands) {

        super(minecartRevolution.getPlugin(), commands);
        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public void addCommandHandler(final CommandHandler commandHandler) {

        if (commandHandler instanceof MRCommandHandler) {
            ((MRCommandHandler) commandHandler).setMinecartRevolution(minecartRevolution);
        }

        super.addCommandHandler(commandHandler);

        if (commandHandler instanceof MRCommandHandler) {
            ((MRCommandHandler) commandHandler).enable();
        }
    }

}
