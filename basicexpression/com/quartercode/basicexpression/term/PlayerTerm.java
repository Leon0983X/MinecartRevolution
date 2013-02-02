
package com.quartercode.basicexpression.term;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.minecartrevolution.util.MinecartTerm;

public class PlayerTerm implements MinecartTerm {

    public PlayerTerm() {

    }

    @Override
    public String[] getLabels() {

        return new String[] { "pl", "player", "pl-.*", "player-.*" };
    }

    @Override
    public boolean getResult(final Minecart minecart, final Direction direction, final String term) {

        if (minecart.getPassenger() instanceof Player) {
            if (term.split("-").length == 2) {
                if ( ((Player) minecart.getPassenger()).getName().equalsIgnoreCase(term.split("-")[1])) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }

}
