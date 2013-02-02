
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;

public interface MinecartTerm {

    public String[] getLabels();

    public boolean getResult(Minecart minecart, Direction direction, String term);

}
