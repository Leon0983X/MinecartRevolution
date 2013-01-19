
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class WeatherCommand extends ExpressionCommand {

    public WeatherCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.STRING), "w", "weather");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

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
