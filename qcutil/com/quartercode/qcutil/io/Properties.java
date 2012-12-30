
package com.quartercode.qcutil.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.quartercode.qcutil.utility.ObjectUtil;

public class Properties extends java.util.Properties {

    private static final long  serialVersionUID             = 477995413322824328L;

    protected String           name;
    protected char[]           variableSeperators           = Properties.STANDARD_VARIABLE_SEPERATORS;
    public static final char[] STANDARD_VARIABLE_SEPERATORS = new char[] { '<', '>' };

    public Properties() {

        super();
    }

    public Properties(final Properties properties) {

        super(properties);
    }

    public Properties(final Properties properties, final String name) {

        super(properties);
        setName(name);
    }

    public Properties(final InputStream inputStream) throws IOException {

        super();
        load(inputStream);
    }

    public Properties(final InputStream inputStream, final String name) throws IOException {

        super();
        load(inputStream);
        setName(name);
    }

    public Properties(final Reader reader) throws IOException {

        super();
        load(reader);
    }

    public Properties(final Reader reader, final String name) throws IOException {

        super();
        load(reader);
        setName(name);
    }

    public Properties(final File file) throws FileNotFoundException, IOException {

        super();
        load(file);
    }

    public Properties(final File file, final String name) throws FileNotFoundException, IOException {

        super();
        load(file);
        setName(name);
    }

    public Properties(final String string) throws IOException {

        super();
        load(string);
    }

    public Properties(final String string, final String name) throws IOException {

        super();
        load(string);
        setName(name);
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setVariableSeperators(final char[] variableSeperators) {

        this.variableSeperators = variableSeperators;
    }

    public char[] getVariableSeperators() {

        return variableSeperators;
    }

    protected String getStandardValue(final String value, final String standardValue) {

        if (value == null) {
            return standardValue;
        } else {
            return value;
        }
    }

    @Override
    public String getProperty(final String key) {

        return getProperty(key, variableSeperators);
    }

    @Override
    public String getProperty(final String key, final String standardValue) {

        return getStandardValue(getProperty(key, variableSeperators), standardValue);
    }

    public String getProperty(final String key, final Properties otherSearchProperties) {

        return getProperty(key, variableSeperators, otherSearchProperties);
    }

    public String getProperty(final String key, final String standardValue, final Properties otherSearchProperties) {

        return getStandardValue(getProperty(key, variableSeperators, otherSearchProperties), standardValue);
    }

    public String getProperty(final String key, final char[] variableSeperators, final Properties... otherSearchProperties) {

        String value = super.getProperty(key);
        if (value == null) {
            for (final Properties properties : otherSearchProperties) {
                final String localValue = ((java.util.Properties) properties).getProperty(key);
                if (localValue != null) {
                    value = localValue;
                    break;
                }
            }
        }

        if (value != null) {
            for (int counter = 0; counter < value.length(); counter++) {
                final String nowChar = String.valueOf(value.charAt(counter));

                if (nowChar.equals(String.valueOf(variableSeperators[0]))) {
                    String parseString = "";

                    for (int counter2 = counter + 1; counter2 < value.length(); counter2++) {
                        final String nowChar2 = String.valueOf(value.charAt(counter2));
                        if (nowChar2.equals(String.valueOf(variableSeperators[1]))) {
                            break;
                        } else {
                            parseString += nowChar2;
                        }
                    }

                    final String parsedString = getProperty(parseString);
                    if (parsedString != null) {
                        value = value.replaceAll(String.valueOf(variableSeperators[0]) + parseString + String.valueOf(variableSeperators[1]), parsedString);
                    }
                }
            }

            return value;
        } else {
            return null;
        }
    }

    public String getProperty(final String key, final String standardValue, final char[] variableSeperators, final Properties... otherSearchProperties) {

        return getStandardValue(getProperty(key, variableSeperators, otherSearchProperties), standardValue);
    }

    public void setProperties(final Map<String, String> map) {

        clear();

        for (final Map.Entry<String, String> entry : map.entrySet()) {
            setProperty(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, String> getProperties() {

        final Map<String, String> propertiesMap = new HashMap<String, String>();
        final ArrayList<String> keyList = new ArrayList<String>();
        final ArrayList<String> valueList = new ArrayList<String>();

        for (final Object key : keySet()) {
            keyList.add(String.valueOf(key));
        }
        for (final Object object : values()) {
            valueList.add(String.valueOf(object));
        }

        if (keyList.size() == valueList.size()) {
            for (int counter = 0; counter < keyList.size(); counter++) {
                propertiesMap.put(keyList.get(counter), valueList.get(counter));
            }

            return propertiesMap;
        } else {
            return null;
        }
    }

    public void load(final File file) throws FileNotFoundException, IOException {

        load(new FileReader(file));
    }

    public void load(final String string) throws IOException {

        load(new StringReader(string));
    }

    protected String getComments(final String... defaultComments) {

        if (defaultComments != null && defaultComments.length > 0) {
            return toCommentsString(defaultComments);
        } else {
            String comment = "";
            if (name != null && !name.isEmpty()) {
                comment += "Properties " + name + "\n";
            }
            comment += "Properties saved at";

            return comment;
        }
    }

    protected String toCommentsString(final String... comments) {

        String comment = "";
        for (int counter = 0; counter < comments.length; counter++) {
            comment += comments[counter];

            if (counter < comments.length - 1) {
                comment += ", ";
            }
        }

        return comment;
    }

    public void store(final OutputStream outputStream, final String... comments) throws IOException {

        super.store(outputStream, getComments(comments));
    }

    public void store(final Writer writer, final String... comments) throws IOException {

        super.store(writer, getComments(comments));
    }

    public void store(final File file, final String... comments) throws FileNotFoundException, IOException {

        store(new FileWriter(file), getComments(comments));
    }

    public String storeToString(final String... comments) throws IOException {

        final StringWriter writer = new StringWriter();
        store(writer, comments);
        return writer.getBuffer().toString();
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getProperties() == null ? 0 : getProperties().hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(variableSeperators);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Properties other = (Properties) obj;
        if (getProperties() == null) {
            if (other.getProperties() != null) {
                return false;
            }
        } else if (!getProperties().equals(other.getProperties())) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (!Arrays.equals(variableSeperators, other.variableSeperators)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectString(this, "name", name, "variableSeperators", Arrays.asList(variableSeperators), "elements", super.toString());
    }

}
