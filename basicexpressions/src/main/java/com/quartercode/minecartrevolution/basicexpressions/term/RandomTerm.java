/*
 * This file is part of MinecartRevolution-Basicexpressions.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basicexpressions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basicexpressions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basicexpressions. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.term;

import java.util.Random;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.core.util.Direction;
import com.quartercode.minecartrevolution.core.util.cart.MinecartTerm;

public class RandomTerm implements MinecartTerm {

    private final Random random;

    public RandomTerm() {

        random = new Random();
    }

    @Override
    public String[] getLabels() {

        return new String[] { "r", "random", "r-.*", "random-.*" };
    }

    @Override
    public boolean getResult(Minecart minecart, Direction direction, String term) {

        double percent = 0.5;

        term = term.replaceAll("%", "").trim();
        if (term.split("-").length == 2) {
            try {
                percent = Double.parseDouble(term.split("-")[1]);
            }
            catch (NumberFormatException e1) {
            }
        }

        return random.nextInt(100) < (int) (percent * 100D);
    }

}
