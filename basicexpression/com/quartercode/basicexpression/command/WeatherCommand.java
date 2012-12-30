
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class WeatherCommand implements ExpressionCommand {

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("w", "weather");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (parameter != null) {
            if (String.valueOf(parameter).equalsIgnoreCase("sun") || String.valueOf(parameter).equalsIgnoreCase("s")) {
                minecart.getWorld().setStorm(false);
                minecart.getWorld().setThundering(false);
            } else if (String.valueOf(parameter).equalsIgnoreCase("rain") || String.valueOf(parameter).equalsIgnoreCase("r")) {
                minecart.getWorld().setStorm(true);
            } else if (String.valueOf(parameter).equalsIgnoreCase("thunder") || String.valueOf(parameter).equalsIgnoreCase("t")) {
                minecart.getWorld().setStorm(true);
                minecart.getWorld().setThundering(true);
            }
        }
    }

}
