
package com.quartercode.basicexpression.term;

import java.util.Random;
import org.bukkit.entity.Minecart;
import com.quartercode.basicexpression.util.Direction;
import com.quartercode.basicexpression.util.MinecartTerm;

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
    public boolean getResult(final Minecart minecart, final Direction direction, String term) {

        double percent = 0.5;

        term = term.replaceAll("%", "").trim();
        if (term.split("-").length == 2) {
            try {
                percent = Double.parseDouble(term.split("-")[1]);
            }
            catch (final NumberFormatException e1) {
            }
        }

        return random.nextInt(100) < (int) (percent * 100D);
    }

}
