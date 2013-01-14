
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class AnnounceCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public AnnounceCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING, Type.DOUBLE), "a", "announce");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() instanceof Player;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (plugin.getConfiguration().getBool(BasicExpressionConfig.ANNOUNCE_ALLOW_COLOR_CODES)) {
            ((Player) minecart.getPassenger()).sendMessage(String.valueOf(parameter).replaceAll("&", "§"));
        } else {
            ((Player) minecart.getPassenger()).sendMessage(String.valueOf(parameter));
        }
    }

}
