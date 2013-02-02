
package com.quartercode.basicexpression.command;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class EjectCommand extends ExpressionCommand {

    public EjectCommand() {

    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.STRING), "ej", "eject");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() instanceof Entity;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        final Entity passenger = minecart.getPassenger();
        minecart.eject();

        if (Type.STRING.isInstance(parameter) && ((String) parameter).split(",").length == 3) {
            final String[] coordinates = ((String) parameter).split(",");
            final Location location = new Location(minecart.getWorld(), Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2]));
            passenger.teleport(location);
        }
    }

}
