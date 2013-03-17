
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.MinecartType;

public class TNTMinecartTerm implements MinecartTerm {

    public TNTMinecartTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "tnt" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return MinecartType.getType(minecart) == MinecartType.TNT;
    }

}
