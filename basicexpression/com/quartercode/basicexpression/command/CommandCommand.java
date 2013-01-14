
package com.quartercode.basicexpression.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class CommandCommand extends ExpressionCommand {

    public CommandCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING, Type.DOUBLE), "cmd", "command");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() instanceof CommandSender;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        Bukkit.dispatchCommand((CommandSender) minecart.getPassenger(), String.valueOf(parameter));
    }

}
