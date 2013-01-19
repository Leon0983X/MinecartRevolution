
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class PassengerTerm implements MinecartTerm {

    public PassengerTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "pa", "passenger" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart.getPassenger() != null;
    }

}
