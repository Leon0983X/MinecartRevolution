
package com.quartercode.minecartrevolution.get;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.Conf;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionException;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.res.PropertyResourceHandler;
import com.quartercode.qcutil.res.ResourceManager;
import com.quartercode.quarterbukkit.QuarterBukkit;

public class Lang {

    public static final String             STANDARD_LANGUAGE = "english";
    public static final ChatColor          DEFAULT           = ChatColor.GOLD;

    private static MinecartRevolution      minecartRevolution;
    private static ResourceManager         resourceManager   = new ResourceManager(FileConf.LANGUAGES);
    private static PropertyResourceHandler resourceHandler   = new PropertyResourceHandler(resourceManager);

    static {

        resourceManager.getFilePatterns().add("english.lang");
    }

    public static void setMinecartRevolution(final MinecartRevolution minecartRevolution) {

        Lang.minecartRevolution = minecartRevolution;
    }

    public static ResourceManager getResourceManager() {

        return resourceManager;
    }

    public static String getValue(final String key, final String... variables) {

        final String result = readValue(key, variables);

        if (result == null) {
            final String languageName = readValue("name");
            String noLangValue = readValue("noLangValue", "key", key, "language", languageName, "languageFile", getLanguage() + ".lang");

            if (noLangValue == null) {
                noLangValue = ChatColor.RED + "There's no language value for " + key + " in the language " + languageName + " (" + getLanguage() + ".lang)!";
            }

            Bukkit.getConsoleSender().sendMessage(noLangValue);
            return noLangValue;
        } else {
            return result;
        }
    }

    private static void addVariable(final List<String> variableList, final String key, final Object value) {

        variableList.add(key);
        variableList.add(String.valueOf(value));
    }

    private static String readValue(final String key, final String... variables) {

        final List<String> variableList = new ArrayList<String>(Arrays.asList(variables));

        addVariable(variableList, "name", Conf.NAME);
        addVariable(variableList, "nameLc", Conf.NAME_LC);
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

        addVariable(variableList, "d", DEFAULT);

        return resourceHandler.getProperty(key, variableList.toArray(new String[variableList.size()]));
    }

    public static Locale getLocale() {

        return resourceManager.getLocale();
    }

    public static void setLocale(final Locale locale) {

        resourceManager.setLocale(locale);
    }

    public static String getLanguage() {

        return getLocale().getLanguage();
    }

    public static void setLanguage(final String language) {

        setLocale(new Locale(language));
    }

    public static boolean isCurrentLanguageAvaiable() {

        return resourceManager.getResource() != null;
    }

    public static void extractDefaults() {

        final List<String> languageFiles = new ArrayList<String>();
        final CodeSource src = MinecartRevolution.class.getProtectionDomain().getCodeSource();

        if (src != null) {
            try {
                final URL jarUrl = src.getLocation();
                final ZipInputStream zipInputStream = new ZipInputStream(jarUrl.openStream());

                ZipEntry zipEntry = null;
                while ( (zipEntry = zipInputStream.getNextEntry()) != null) {
                    final String entryName = zipEntry.getName();
                    if (entryName.matches("languages/.*\\.lang")) {
                        languageFiles.add(entryName);
                    }
                }
            }
            catch (final IOException e) {
                QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to locate default language files"));
                return;
            }
        } else {
            QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, "Failed to locate default language files"));
            return;
        }

        for (final String languageFile : languageFiles) {
            final File file = new File(FileConf.LANGUAGES + java.io.File.separator + languageFile.replace("languages/", ""));
            if (!file.exists()) {
                extractFromJAR(Lang.class.getResource("/" + languageFile), file);
            }
        }
    }

    private static void extractFromJAR(final URL url, final File destination) {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            destination.getParentFile().mkdirs();

            outputStream = new FileOutputStream(destination);
            outputStream.flush();

            final URLConnection castConnection = url.openConnection();

            if (castConnection instanceof JarURLConnection) {
                final JarURLConnection connection = (JarURLConnection) url.openConnection();
                connection.connect();

                final byte[] tempBuffer = new byte[4096];

                inputStream = connection.getInputStream();
                int counter;
                while ( (counter = inputStream.read(tempBuffer)) > 0) {
                    outputStream.write(tempBuffer, 0, counter);
                    outputStream.flush();
                }
            }
        }
        catch (final IOException e) {
            QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to extract language from jar"));
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (final IOException e) {
                    QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to close input stream"));
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (final IOException e) {
                    QuarterBukkit.exception(new MinecartRevolutionException(minecartRevolution, e, "Failed to close output stream"));
                }
            }
        }
    }

    private Lang() {

    }

}
