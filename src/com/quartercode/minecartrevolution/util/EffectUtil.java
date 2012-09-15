
package com.quartercode.minecartrevolution.util;

import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;

public class EffectUtil {

    public static enum DEffect {

        SMOKE (new ReadyEffect(Effect.SMOKE, 1), new ReadyEffect(Effect.EXTINGUISH, 1)), FLAMES (new ReadyEffect(Effect.MOBSPAWNER_FLAMES, 1)), ENDER (new ReadyEffect(Effect.ENDER_SIGNAL, 1)), DOOR (new ReadyEffect(Effect.DOOR_TOGGLE, 1)), POTION (new ReadyEffect(Effect.POTION_BREAK, 1)), DISPENSER (new ReadyEffect(Effect.CLICK1, 1)), PRESSURE (new ReadyEffect(Effect.CLICK2, 1)), BOW (new ReadyEffect(Effect.BOW_FIRE, 1)), GHAST (new ReadyEffect(Effect.GHAST_SHRIEK, 1)), SHOOT (new ReadyEffect(Effect.GHAST_SHOOT, 1)), STEP (new ReadyEffect(Effect.ZOMBIE_DESTROY_DOOR, 1), new ReadyEffect(Effect.EXTINGUISH, 1));

        private ReadyEffect[] effects;

        private DEffect(ReadyEffect... effects) {

            this.effects = effects;
        }

        public ReadyEffect[] getEffects() {

            return effects;
        }

        public void play(Minecart minecart) {

            for (ReadyEffect effect : effects) {
                playEffect(minecart, effect.getEffect(), effect.getStrength());
            }
        }
    }

    public static DEffect getEffect(String name) {

        for (DEffect effect : DEffect.values()) {
            if (effect.name().equalsIgnoreCase(name)) {
                return effect;
            }
        }

        return null;
    }

    public static void playEffect(Minecart minecart, String name) {

        if (name.equalsIgnoreCase("explo") || name.equalsIgnoreCase("explosion")) {
            playExplosion(minecart);
        } else if (name.equalsIgnoreCase("light") || name.equalsIgnoreCase("lightning") || name.equalsIgnoreCase("thunder")) {
            playLightning(minecart);
        } else {
            DEffect effect = getEffect(name);
            if (effect != null) {
                effect.play(minecart);
            }
        }
    }

    public static void playEffect(Minecart minecart, Effect effect) {

        playEffect(minecart, effect, 1);
    }

    public static void playEffect(Minecart minecart, Effect effect, int strength) {

        minecart.getWorld().playEffect(minecart.getLocation(), effect, strength);
    }

    public static void playExplosion(Minecart minecart) {

        minecart.getWorld().createExplosion(minecart.getLocation(), 0);
        for (Entity entity : minecart.getNearbyEntities(2, 2, 2)) {
            if (! (entity instanceof Player) && ! (entity instanceof Vehicle)) {
                entity.remove();
            }
        }
    }

    public static void playLightning(Minecart minecart) {

        minecart.getWorld().strikeLightning(minecart.getLocation().add(0, 6, 0));
    }

}
