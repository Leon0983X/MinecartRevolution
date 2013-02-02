
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.MinecartType;

public class PoweredMinecartTerm implements MinecartTerm {

    public PoweredMinecartTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "pow", "powered" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return MinecartType.getType(minecart) == MinecartType.POWERED;
    }

}
