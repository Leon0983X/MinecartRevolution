
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;
import com.quartercode.minecartrevolution.util.MinecartType;

public class StorageMinecartTerm implements MinecartTerm {

    public StorageMinecartTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "sto", "storage" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return MinecartType.getType(minecart) == MinecartType.STORAGE;
    }

}
