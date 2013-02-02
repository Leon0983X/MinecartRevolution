
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class EastTerm implements MinecartTerm {

    public EastTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "e", "east" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return direction == Direction.EAST;
    }

}
