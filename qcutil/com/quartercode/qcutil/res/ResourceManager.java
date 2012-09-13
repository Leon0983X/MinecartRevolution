
package com.quartercode.qcutil.res;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.utility.ObjectUtil;

public class ResourceManager implements Serializable {

    private static final long        serialVersionUID      = 574945674134362025L;

    public static final List<String> DEFAULT_FILE_PATTERNS = getFilePatterns("", ".lang", "_");

    public static List<String> getFilePatterns(String prefix, String suffix, String seperator) {

        List<String> list = new ArrayList<String>();
        list.add(prefix + "$language$_$country$_$variant$".replaceAll("_", seperator) + suffix);
        list.add(prefix + "$language$_$country$".replaceAll("_", seperator) + suffix);
        list.add(prefix + "$language$" + suffix);
        list.add(prefix + "default" + suffix);

        return list;
    }

    protected File         directory;
    protected Locale       locale;
    protected List<String> filePatterns = DEFAULT_FILE_PATTERNS;

    public ResourceManager() {

        setLocaleToDefault();
    }

    public ResourceManager(File directory) {

        setDirectory(directory);
        setLocaleToDefault();
    }

    public ResourceManager(Locale locale) {

        setLocale(locale);
    }

    public ResourceManager(File directory, Locale locale) {

        setDirectory(directory);
        setLocale(locale);
    }

    public File getDirectory() {

        return directory;
    }

    public void setDirectory(File directory) {

        this.directory = directory;
    }

    public Locale getLocale() {

        return locale;
    }

    public void setLocale(Locale locale) {

        this.locale = locale;
    }

    public void setLocaleToDefault() {

        locale = Locale.getDefault();
    }

    public List<String> getFilePatterns() {

        return filePatterns;
    }

    public void setFilePatterns(List<String> filePatterns) {

        this.filePatterns = filePatterns;
    }

    protected String parsePattern(String rawPattern) {

        String parsedPattern = rawPattern;

        parsedPattern = parsedPattern.replaceAll("\\$language\\$", locale.getLanguage());
        parsedPattern = parsedPattern.replaceAll("\\$country\\$", locale.getCountry());
        parsedPattern = parsedPattern.replaceAll("\\$variant\\$", locale.getVariant());

        return parsedPattern;
    }

    public File getResource() {

        for (String pattern : filePatterns) {
            File resource = new File(directory, parsePattern(pattern));
            if (resource.exists()) {
                return resource;
            }
        }

        return null;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (directory == null ? 0 : directory.hashCode());
        result = prime * result + (filePatterns == null ? 0 : filePatterns.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResourceManager other = (ResourceManager) obj;
        if (directory == null) {
            if (other.directory != null) {
                return false;
            }
        } else if (!directory.equals(other.directory)) {
            return false;
        }
        if (filePatterns == null) {
            if (other.filePatterns != null) {
                return false;
            }
        } else if (!filePatterns.equals(other.filePatterns)) {
            return false;
        }
        if (locale == null) {
            if (other.locale != null) {
                return false;
            }
        } else if (!locale.equals(other.locale)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "directory", "locale", "resourceHandler", "filePatterns");
    }

}
