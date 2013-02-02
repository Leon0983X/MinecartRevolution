
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.NPC;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class NPCTerm implements MinecartTerm {

    public NPCTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "npc" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        return minecart.getPassenger() instanceof NPC;
    }

}
