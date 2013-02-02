
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.NPC;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

public class MobTerm implements MinecartTerm {

    public MobTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "mob" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart.getPassenger() instanceof Animals || minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof NPC;
    }

}
