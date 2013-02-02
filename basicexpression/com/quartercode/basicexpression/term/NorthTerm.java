
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class NorthTerm implements MinecartTerm {

    public NorthTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "n", "north" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return direction == Direction.NORTH;
    }

}
