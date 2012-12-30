
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class CommandCommand implements ExpressionCommand {

    private final MinecartRevolution minecartRevolution;

    public CommandCommand(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("cmd", "command");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart.getPassenger() != null && minecart.getPassenger() instanceof Player;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (parameter != null) {
            minecartRevolution.getServer().dispatchCommand((Player) minecart.getPassenger(), String.valueOf(parameter));
        }
    }

}
