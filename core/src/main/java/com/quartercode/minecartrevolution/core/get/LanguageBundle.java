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

package com.quartercode.minecartrevolution.core.get;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import org.bukkit.ChatColor;

public class LanguageBundle {

    public static final String   DEFAULT_BASE_NAME = "i18n.default";

    public static final String   DEFAULT_FORMAT    = ChatColor.RESET.toString() + ChatColor.GOLD.toString();

    private final ResourceBundle resourceBundle;

    public LanguageBundle(String baseName, Locale locale) {

        resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }

    public String get(String key, String... variables) {

        String value = resourceBundle.getString(key);

        if (value == null) {
            String languageName = get("name");
            String noLangValue = get("noLangValue", "key", key, "language", languageName);

            if (noLangValue == null) {
                noLangValue = ChatColor.RED + "There's no language value for " + key + " in the language " + languageName + "!";
            }

            return noLangValue;
        }

        return format(value, variables);
    }

    private String format(String value, String... variables) {

        Map<String, String> variableMap = new HashMap<String, String>();

        if (variables.length % 2 == 0) {
            // Fill in variables
            for (int counter = 0; counter < variables.length; counter += 2) {
                variableMap.put(variables[counter], variables[counter + 1]);
            }
        }

        // Basic data
        variableMap.put("name", "MinecartRevolution");

        // Color codes
        variableMap.put("aqua", ChatColor.AQUA.toString());
        variableMap.put("black", ChatColor.BLACK.toString());
        variableMap.put("blue", ChatColor.BLUE.toString());
        variableMap.put("darkAqua", ChatColor.DARK_AQUA.toString());
        variableMap.put("darkBlue", ChatColor.DARK_BLUE.toString());
        variableMap.put("darkGray", ChatColor.DARK_GRAY.toString());
        variableMap.put("darkGreen", ChatColor.DARK_GREEN.toString());
        variableMap.put("darkPurple", ChatColor.DARK_PURPLE.toString());
        variableMap.put("darkRed", ChatColor.DARK_RED.toString());
        variableMap.put("gold", ChatColor.GOLD.toString());
        variableMap.put("gray", ChatColor.GRAY.toString());
        variableMap.put("green", ChatColor.GREEN.toString());
        variableMap.put("purple", ChatColor.LIGHT_PURPLE.toString());
        variableMap.put("red", ChatColor.RED.toString());
        variableMap.put("white", ChatColor.WHITE.toString());
        variableMap.put("yellow", ChatColor.YELLOW.toString());

        // Format codes
        variableMap.put("bold", ChatColor.BOLD.toString());
        variableMap.put("italic", ChatColor.ITALIC.toString());
        variableMap.put("magic", ChatColor.MAGIC.toString());
        variableMap.put("random", ChatColor.MAGIC.toString());
        variableMap.put("reset", ChatColor.RESET.toString());
        variableMap.put("strike", ChatColor.STRIKETHROUGH.toString());
        variableMap.put("strikethrough", ChatColor.STRIKETHROUGH.toString());
        variableMap.put("under", ChatColor.UNDERLINE.toString());
        variableMap.put("underline", ChatColor.UNDERLINE.toString());

        // Default color & format
        variableMap.put("d", DEFAULT_FORMAT);

        // Replace placeholders with variables
        for (Entry<String, String> variable : variableMap.entrySet()) {
            value = value.replaceAll("\\$" + variable.getKey() + "\\$", variable.getValue());
        }

        return value;
    }

}
