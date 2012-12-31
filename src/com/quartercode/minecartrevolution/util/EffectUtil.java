
package com.quartercode.minecartrevolution.util;

import org.bukkit.Effect;
import org.bukkit.entity.Minecart;

public class EffectUtil {

    public static enum DEffect {

        SMOKE (new ReadyEffect(Effect.SMOKE, 1), new ReadyEffect(Effect.EXTINGUISH, 1)), FLAMES (new ReadyEffect(Effect.MOBSPAWNER_FLAMES, 1)), ENDER (new ReadyEffect(Effect.ENDER_SIGNAL, 1)), DOOR (new ReadyEffect(Effect.DOOR_TOGGLE, 1)), POTION (new ReadyEffect(Effect.POTION_BREAK, 1)), DISPENSER (new ReadyEffect(Effect.CLICK1, 1)), PRESSURE (new ReadyEffect(Effect.CLICK2, 1)), BOW (new ReadyEffect(Effect.BOW_FIRE, 1)), GHAST (new ReadyEffect(Effect.GHAST_SHRIEK, 1)), SHOOT (new ReadyEffect(Effect.GHAST_SHOOT, 1)), STEP (new ReadyEffect(Effect.ZOMBIE_DESTROY_DOOR, 1), new ReadyEffect(Effect.EXTINGUISH, 1));

        private final ReadyEffect[] effects;

        private DEffect(final ReadyEffect... effects) {

            this.effects = effects;
        }

        public ReadyEffect[] getEffects() {

            return effects;
        }

        public void play(final Minecart minecart) {

            for (final ReadyEffect effect : effects) {
                playEffect(minecart, effect.getEffect(), effect.getStrength());
            }
        }
    }

    public static DEffect getEffect(final String name) {

        for (final DEffect effect : DEffect.values()) {
            if (effect.name().equalsIgnoreCase(name)) {
                return effect;
            }
        }

        return null;
    }

    public static void playEffect(final Minecart minecart, final String name) {

        if (name.equalsIgnoreCase("light") || name.equalsIgnoreCase("lightning") || name.equalsIgnoreCase("thunder")) {
            playLightning(minecart);
        } else {
            final DEffect effect = getEffect(name);
            if (effect != null) {
                effect.play(minecart);
            }
        }
    }

    public static void playEffect(final Minecart minecart, final Effect effect) {

        playEffect(minecart, effect, 1);
    }

    public static void playEffect(final Minecart minecart, final Effect effect, final int strength) {

        minecart.getWorld().playEffect(minecart.getLocation(), effect, strength);
    }

    public static void playLightning(final Minecart minecart) {

        minecart.getWorld().strikeLightningEffect(minecart.getLocation());
    }

}
