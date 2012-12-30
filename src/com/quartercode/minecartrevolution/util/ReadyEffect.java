
package com.quartercode.minecartrevolution.util;

import org.bukkit.Effect;

public class ReadyEffect {

    private final Effect effect;
    private final int    strength;

    public ReadyEffect(final Effect effect, final int strength) {

        this.effect = effect;
        this.strength = strength;
    }

    public Effect getEffect() {

        return effect;
    }

    public int getStrength() {

        return strength;
    }
}
