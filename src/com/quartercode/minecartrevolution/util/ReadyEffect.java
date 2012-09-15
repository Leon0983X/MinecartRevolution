
package com.quartercode.minecartrevolution.util;

import org.bukkit.Effect;

public class ReadyEffect {

    private Effect effect;
    private int    strength;

    public ReadyEffect(Effect effect, int strength) {

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
