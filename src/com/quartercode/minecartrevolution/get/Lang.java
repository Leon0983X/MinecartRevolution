
package com.quartercode.minecartrevolution.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.bukkit.ChatColor;
import com.quartercode.minecartrevolution.conf.Conf;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.qcutil.res.PropertyResourceHandler;
import com.quartercode.qcutil.res.ResourceManager;

public class Lang {

    protected static ResourceManager         resourceManager = new ResourceManager(FileConf.LANGUAGES);
    protected static PropertyResourceHandler resourceHandler = new PropertyResourceHandler(resourceManager);

    static {
        resourceManager.getFilePatterns().add("english.lang");
    }

    public static ResourceManager getResourceManager() {

        return resourceManager;
    }

    public static String getValue(String key, String... variables) {

        List<String> variableList = new ArrayList<String>(Arrays.asList(variables));

        addVariable(variableList, "name", Conf.NAME);
        addVariable(variableList, "nameLc", Conf.NAME_LC);
        addVariable(variableList, "version", Conf.VERSION.getVersionString());
        addVariable(variableList, "devBuild", Conf.DEV_BUILD);

        addVariable(variableList, "aqua", ChatColor.AQUA);
        addVariable(variableList, "black", ChatColor.BLACK);
        addVariable(variableList, "blue", ChatColor.BLUE);
        addVariable(variableList, "darkAqua", ChatColor.DARK_AQUA);
        addVariable(variableList, "darkBlue", ChatColor.DARK_BLUE);
        addVariable(variableList, "darkGray", ChatColor.DARK_GRAY);
        addVariable(variableList, "darkGreen", ChatColor.DARK_GREEN);
        addVariable(variableList, "darkPurple", ChatColor.DARK_PURPLE);
        addVariable(variableList, "darkRed", ChatColor.DARK_RED);
        addVariable(variableList, "gold", ChatColor.GOLD);
        addVariable(variableList, "gray", ChatColor.GRAY);
        addVariable(variableList, "green", ChatColor.GREEN);
        addVariable(variableList, "purple", ChatColor.LIGHT_PURPLE);
        addVariable(variableList, "red", ChatColor.RED);
        addVariable(variableList, "white", ChatColor.WHITE);
        addVariable(variableList, "yellow", ChatColor.YELLOW);

        addVariable(variableList, "bold", ChatColor.BOLD);
        addVariable(variableList, "italic", ChatColor.ITALIC);
        addVariable(variableList, "magic", ChatColor.MAGIC);
        addVariable(variableList, "random", ChatColor.MAGIC);
        addVariable(variableList, "reset", ChatColor.RESET);
        addVariable(variableList, "strike", ChatColor.STRIKETHROUGH);
        addVariable(variableList, "strikethrough", ChatColor.STRIKETHROUGH);
        addVariable(variableList, "under", ChatColor.UNDERLINE);
        addVariable(variableList, "underline", ChatColor.UNDERLINE);

        // addVariable(variableList, "black", "§0");
        // addVariable(variableList, "darkBlue", "§1");
        // addVariable(variableList, "darkGreen", "§2");
        // addVariable(variableList, "darkAqua", "§3");
        // addVariable(variableList, "darkRed", "§4");
        // addVariable(variableList, "purple", "§5");
        // addVariable(variableList, "gold", "§6");
        // addVariable(variableList, "grey", "§7");
        // addVariable(variableList, "darkGrey", "§8");
        // addVariable(variableList, "indigo", "§9");
        // addVariable(variableList, "brightGreen", "§a");
        // addVariable(variableList, "aqua", "§b");
        // addVariable(variableList, "red", "§c");
        // addVariable(variableList, "pink", "§d");
        // addVariable(variableList, "yellow", "§e");
        // addVariable(variableList, "white", "§f");

        // addVariable(variableList, "random", "§k");
        // addVariable(variableList, "bold", "§l");
        // addVariable(variableList, "strike", "§m");
        // addVariable(variableList, "underline", "§n");
        // addVariable(variableList, "italic", "§o");
        // addVariable(variableList, "reset", "§r");

        return resourceHandler.getProperty(key, variableList.toArray(new String[variableList.size()]));
    }

    private static void addVariable(List<String> variableList, String key, Object value) {

        variableList.add(key);
        variableList.add(String.valueOf(value));
    }

    public static Locale getLocale() {

        return resourceManager.getLocale();
    }

    public static void setLocale(Locale locale) {

        resourceManager.setLocale(locale);
    }

    public static String getLanguage() {

        return getLocale().getLanguage();
    }

    public static void setLanguage(String language) {

        setLocale(new Locale(language));
    }

    private Lang() {

    }

}
