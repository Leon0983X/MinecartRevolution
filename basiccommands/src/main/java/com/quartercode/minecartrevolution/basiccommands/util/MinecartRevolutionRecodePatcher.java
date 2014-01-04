/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.util;

import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.core.MinecartRevolution;

public class MinecartRevolutionRecodePatcher extends Patcher {

    public MinecartRevolutionRecodePatcher(MinecartRevolution minecartRevolution) {

        super(minecartRevolution);
    }

    @Override
    public String getName() {

        return "MinecartRevolution Recode";
    }

    @Override
    protected boolean patch(CommandSender causer, Sign sign) {

        if (sign.getLine(0).equalsIgnoreCase("[Announce]")) {
            setSignContent(sign, "[Announce]", sign.getLine(1), sign.getLine(2), sign.getLine(3));
        } else if (sign.getLine(0).equalsIgnoreCase("[Chest]")) {
            setSignContent(sign, "[Chest]", sign.getLine(1).replaceAll("allitems", "").trim(), sign.getLine(2).replaceAll("allitems", "").trim(), sign.getLine(3).replaceAll("allitems", "").trim());
        } else if (sign.getLine(0).equalsIgnoreCase("[Collect]")) {
            String radius = sign.getLine(2);
            String items = sign.getLine(1).replaceAll("allitems", "").trim();
            setSignContent(sign, "[Collect]", radius, items);
        }
        // else if (sign.getLine(0).equalsIgnoreCase("[Craft]")) {}
        else if (sign.getLine(1).equalsIgnoreCase("[Smelt]")) {
            setSignContent(sign, "[Furnace]", "+ " + sign.getLine(2), "-");
        }
        // else if (sign.getLine(0).equalsIgnoreCase("[Farm]")) {}
        else if (sign.getLine(1).equalsIgnoreCase("[Lock]")) {
            setSignContent(sign, "[Lock]", sign.getLine(2).toLowerCase().replaceAll("add", "+").replaceAll("remove", "-"));
        }
        // else if (sign.getLine(1).equalsIgnoreCase("[MaxSpeed]")) {}
        else if (sign.getLine(1).equalsIgnoreCase("[Time]")) {
            setSignContent(sign, "[Time]", sign.getLine(2));
        } else if (sign.getLine(1).equalsIgnoreCase("[Weather]")) {
            setSignContent(sign, "[Weather]", sign.getLine(2));
        } else if (sign.getLine(0).equalsIgnoreCase("[Sensor]")) {
            if (sign.getLine(1).equalsIgnoreCase("direction")) {
                setSignContent(sign, "[Sensor]", sign.getLine(2));
            } else if (sign.getLine(1).equalsIgnoreCase("minecart")) {
                if (sign.getLine(2).equalsIgnoreCase("standard")) {
                    setSignContent(sign, "[Sensor]", "Minecart");
                } else {
                    setSignContent(sign, "[Sensor]", sign.getLine(2));
                }
            } else if (sign.getLine(1).equalsIgnoreCase("player")) {
                if (sign.getLine(2).isEmpty()) {
                    setSignContent(sign, "[Sensor]", "player");
                } else {
                    setSignContent(sign, "[Sensor]", "pl-" + sign.getLine(2));
                }
            } else if (sign.getLine(1).equalsIgnoreCase("mob")) {
                setSignContent(sign, "[Sensor]", "mob");
            } else if (sign.getLine(1).equalsIgnoreCase("empty")) {
                setSignContent(sign, "[Sensor]", "!passenger");
            } else if (sign.getLine(1).equalsIgnoreCase("nocargo")) {
                setSignContent(sign, "[Sensor]", "!item");
            } else if (sign.getLine(1).equalsIgnoreCase("item")) {
                if (sign.getLine(2).equalsIgnoreCase("inventory")) {
                    setSignContent(sign, "[Sensor]", "i-" + sign.getLine(3));
                } else if (sign.getLine(2).equalsIgnoreCase("hand")) {
                    setSignContent(sign, "[Sensor]", "ih-" + sign.getLine(3));
                }
            }
        } else if (sign.getLine(0).equalsIgnoreCase("[Command]")) {
            setSignContent(sign, "[Command]", sign.getLine(1).replaceAll("\\%player\\%", "\\$p"), sign.getLine(2).replaceAll("\\%player\\%", "\\$p"));
        }
        // else if (sign.getLine(1).equalsIgnoreCase("[Descent]")) {}
        // else if (sign.getLine(1).equalsIgnoreCase("[Destination]")) {}
        else if (sign.getLine(1).equalsIgnoreCase("[Effect]")) {
            String modifier = "";
            if (sign.getLine(3).equalsIgnoreCase("add")) {
                modifier = "+ ";
            } else if (sign.getLine(3).equalsIgnoreCase("remove")) {
                modifier = "- ";
            } else if (sign.getLine(2).toLowerCase().contains("remove")) {
                setSignContent(sign, "[Effect]", "-");
                return true;
            }

            String effect = sign.getLine(2).trim();
            if (effect.equalsIgnoreCase("flame")) {
                effect = "flames";
            }

            setSignContent(sign, "[Effect]", modifier + effect);
        } else if (sign.getLine(0).equalsIgnoreCase("[Intersection]")) {
            String[] lines = sign.getLines();
            lines[0] = "[Intersection]";

            for (int line = 1; line < 4; line++) {
                if (lines[line].split(":").length < 2) {
                    continue;
                }

                String term = lines[line].split(":")[0];
                String action = lines[line].split(":")[1];

                if (sign.getLine(line).equalsIgnoreCase("direction")) {
                    setSignContent(sign, "[Sensor]", sign.getLine(2));
                } else if (sign.getLine(1).equalsIgnoreCase("minecart")) {
                    if (sign.getLine(2).equalsIgnoreCase("standard")) {
                        setSignContent(sign, "[Sensor]", "Minecart");
                    } else {
                        setSignContent(sign, "[Sensor]", sign.getLine(2));
                    }
                } else if (sign.getLine(1).equalsIgnoreCase("player")) {
                    if (sign.getLine(2).isEmpty()) {
                        setSignContent(sign, "[Sensor]", "pl");
                    } else {
                        setSignContent(sign, "[Sensor]", "pl-" + sign.getLine(2));
                    }
                } else if (sign.getLine(1).equalsIgnoreCase("mob")) {
                    setSignContent(sign, "[Sensor]", "mob");
                } else if (sign.getLine(1).equalsIgnoreCase("empty")) {
                    setSignContent(sign, "[Sensor]", "!pa");
                } else if (sign.getLine(1).equalsIgnoreCase("nocargo")) {
                    setSignContent(sign, "[Sensor]", "!i");
                } else if (sign.getLine(1).equalsIgnoreCase("item")) {
                    if (sign.getLine(2).equalsIgnoreCase("inventory")) {
                        setSignContent(sign, "[Sensor]", "i-" + sign.getLine(3));
                    } else if (sign.getLine(2).equalsIgnoreCase("hand")) {
                        setSignContent(sign, "[Sensor]", "ih-" + sign.getLine(3));
                    }
                }

                if (term.equalsIgnoreCase("standard")) {
                    term = "Minecart";
                } else if (term.equalsIgnoreCase("player")) {
                    term = "pl";
                } else if (term.equalsIgnoreCase("empty")) {
                    term = "!pa";
                } else if (term.equalsIgnoreCase("nocargo")) {
                    term = "!i";
                } else if (term.toLowerCase().startsWith("p-")) {
                    term = term.replaceAll("p-", "pl-");
                } else if (term.toLowerCase().startsWith("invc-")) {
                    term = term.replaceAll("invc-", "i-");
                } else if (term.toLowerCase().startsWith("ihold-")) {
                    term = term.replaceAll("ihold-", "ih-");
                }

                if (action.equalsIgnoreCase("d")) {
                    action = "k";
                } else if (action.equalsIgnoreCase("ej")) {
                    action = "ej";
                }

                lines[line] = term + ":" + action;
            }

            setSignContent(sign, lines[0], lines[1], lines[2], lines[3]);
        } else {
            return false;
        }

        return true;
    }

}
