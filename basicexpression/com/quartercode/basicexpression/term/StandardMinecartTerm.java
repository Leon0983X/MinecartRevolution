
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.MinecartType;

public class StandardMinecartTerm implements MinecartTerm {

    public StandardMinecartTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "m", "minecart" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return MinecartType.getType(minecart) == MinecartType.MINECART;
    }

}
