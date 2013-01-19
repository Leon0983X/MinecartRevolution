
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class WestTerm implements MinecartTerm {

    public WestTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "w", "west" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return direction == Direction.WEST;
    }

}
