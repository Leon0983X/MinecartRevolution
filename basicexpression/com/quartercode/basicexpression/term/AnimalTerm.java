
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class AnimalTerm implements MinecartTerm {

    public AnimalTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "ani", "animal" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart.getPassenger() instanceof Animals;
    }

}
