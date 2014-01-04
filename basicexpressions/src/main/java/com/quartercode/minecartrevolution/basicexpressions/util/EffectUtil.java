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
/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basicexpressions.util;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectUtil {

    public static enum ExtendedEffect {

        SMOKE (new PreparedEffect(Effect.SMOKE, 1), new PreparedEffect(Effect.EXTINGUISH, 1)), FLAMES (new PreparedEffect(Effect.MOBSPAWNER_FLAMES, 1)), ENDER (new PreparedEffect(Effect.ENDER_SIGNAL, 1)), DOOR (new PreparedEffect(Effect.DOOR_TOGGLE, 1)), POTION (new PreparedEffect(Effect.POTION_BREAK, 1)), DISPENSER (new PreparedEffect(Effect.CLICK1, 1)), PRESSURE (new PreparedEffect(Effect.CLICK2, 1)), BOW (new PreparedEffect(Effect.BOW_FIRE, 1)), GHAST (new PreparedEffect(Effect.GHAST_SHRIEK, 1)), SHOOT (new PreparedEffect(Effect.GHAST_SHOOT, 1)), STEP (new PreparedEffect(Effect.ZOMBIE_DESTROY_DOOR, 1), new PreparedEffect(Effect.EXTINGUISH, 1)), ANIMATION (new PreparedEffect(Effect.SMOKE, 1), new PreparedEffect(Effect.MOBSPAWNER_FLAMES, 1), new PreparedEffect(Effect.ENDER_SIGNAL, 1), new PreparedEffect(Effect.POTION_BREAK, 1), new PreparedEffect(Effect.EXTINGUISH, 1));

        private final PreparedEffect[] effects;

        private ExtendedEffect(PreparedEffect... effects) {

            this.effects = effects;
        }

        public PreparedEffect[] getEffects() {

            return effects;
        }

        public void play(Minecart minecart) {

            for (PreparedEffect effect : effects) {
                minecart.getWorld().playEffect(minecart.getLocation(), effect.getEffect(), effect.getStrength());
            }
        }

        protected static class PreparedEffect {

            private final Effect effect;
            private final int    strength;

            public PreparedEffect(Effect effect, int strength) {

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

    }

    public static void playEffect(Minecart minecart, String name) {

        if (name.equalsIgnoreCase("light") || name.equalsIgnoreCase("lightning") || name.equalsIgnoreCase("thunder")) {
            minecart.getWorld().strikeLightningEffect(minecart.getLocation());
        } else if (name.equalsIgnoreCase("hurt")) {
            if (minecart.getPassenger() != null) {
                minecart.getPassenger().playEffect(EntityEffect.HURT);
            }
        } else if (name.equalsIgnoreCase("conf") || name.equalsIgnoreCase("confusion")) {
            if (minecart.getPassenger() instanceof Player) {
                ((Player) minecart.getPassenger()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 10));
            }
        } else {
            for (ExtendedEffect effect : ExtendedEffect.values()) {
                if (effect.name().equalsIgnoreCase(name)) {
                    effect.play(minecart);
                    return;
                }
            }
        }
    }

}
