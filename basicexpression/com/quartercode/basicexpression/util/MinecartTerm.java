
package com.quartercode.basicexpression.util;

import org.bukkit.entity.Minecart;

public interface MinecartTerm {

    public String[] getLabels();

    public boolean getResult(Minecart minecart, Direction direction, String term);

}
