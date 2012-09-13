
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class AnnounceCommand implements ExpressionCommand {

    public AnnounceCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("a", "announce");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return minecart.getPassenger() != null && minecart.getPassenger() instanceof Player;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (parameter != null) {
            if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
                ((Player) minecart.getPassenger()).sendMessage(String.valueOf(parameter));
            }
        }
    }

}
