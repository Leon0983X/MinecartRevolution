
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class MonsterTerm implements MinecartTerm {

    public MonsterTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "mon", "monster" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart.getPassenger() instanceof Monster;
    }

}
