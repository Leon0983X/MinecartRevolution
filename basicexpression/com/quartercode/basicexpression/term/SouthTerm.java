
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class SouthTerm implements MinecartTerm {

    public SouthTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "s", "south" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return direction == Direction.SOUTH;
    }

}
