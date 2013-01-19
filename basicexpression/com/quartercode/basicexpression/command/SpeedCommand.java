
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class SpeedCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public SpeedCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.DOUBLE), "sp", "speed");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (plugin.getConfiguration().getDouble(BasicExpressionConfig.SPEED_MAX_SPEED) < 0) {
            MinecartUtil.setSpeed(minecart, (Double) parameter);
        } else if ((Double) parameter <= plugin.getConfiguration().getDouble(BasicExpressionConfig.SPEED_MAX_SPEED)) {
            MinecartUtil.setSpeed(minecart, (Double) parameter);
        } else {
            MinecartUtil.setSpeed(minecart, plugin.getConfiguration().getDouble(BasicExpressionConfig.SPEED_MAX_SPEED));
        }
    }

}
