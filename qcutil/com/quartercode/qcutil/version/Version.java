
package com.quartercode.qcutil.version;

import java.io.Serializable;
import java.util.ArrayList;
import com.quartercode.qcutil.utility.ObjectUtil;

public class Version implements Serializable, Comparable<Version> {

    private static final long serialVersionUID = 1284800724016741891L;

    public static Version getJavaVersion() {

        return new Version(System.getProperty("java.version"));
    }

    protected String versionString;

    public Version() {

        super();
    }

    public Version(final String versionString) {

        super();

        setVersionString(versionString);
    }

    public String getVersionString() {

        return versionString;
    }

    public void setVersionString(final String versionString) {

        this.versionString = versionString;
    }

    public String getVersionPrefix() {

        if (versionString.split(" ").length > 1) {
            return versionString.split(" ")[0];
        } else {
            return "";
        }
    }

    public String getVersionNumbers() {

        if (versionString.split(" ").length > 1) {
            return versionString.split(" ")[1];
        } else {
            return versionString.split(" ")[0];
        }
    }

    public boolean isSupported() {

        try {
            splitVersionNumbers(getVersionNumbers());
            return true;
        }
        catch (final NumberFormatException e) {
            return false;
        }
    }

    protected int[] splitVersionNumbers(final String versionNumbers) throws NumberFormatException {

        final ArrayList<Integer> versionNumbersList = new ArrayList<Integer>();

        final String[] versionParts = versionNumbers.split("\\.");
        for (int counter = 0; counter < versionParts.length; counter++) {
            if (!versionParts[counter].contains("_")) {
                versionNumbersList.add(Integer.parseInt(versionParts[counter]));
            } else {
                final String[] versionPartParts = versionParts[counter].split("_");
                if (counter == versionParts.length - 1) {
                    for (final String versionPartPart : versionPartParts) {
                        versionNumbersList.add(Integer.parseInt(versionPartPart));
                    }
                } else {
                    versionNumbersList.add(Integer.parseInt(versionPartParts[0]));
                }
            }
        }

        final int[] versionNumbersArray = new int[versionNumbersList.size()];
        for (int counter = 0; counter < versionNumbersList.size(); counter++) {
            versionNumbersArray[counter] = versionNumbersList.get(counter);
        }
        return versionNumbersArray;
    }

    public boolean bigger(final Version otherVersion) throws NumberFormatException {

        if (equals(otherVersion)) {
            return false;
        } else {
            final String[] prefixOrder = { "Pre", "Alpha", "Beta", "" };

            int thisVersionPrefix = prefixOrder.length - 1;
            for (int counter = 0; counter < prefixOrder.length; counter++) {
                if (getVersionPrefix().equals(prefixOrder[counter])) {
                    thisVersionPrefix = counter;
                }
            }
            int otherVersionPrefix = prefixOrder.length - 1;
            for (int counter = 0; counter < prefixOrder.length; counter++) {
                if (otherVersion.getVersionPrefix().equals(prefixOrder[counter])) {
                    otherVersionPrefix = counter;
                }
            }

            if (thisVersionPrefix > otherVersionPrefix) {
                return true;
            } else if (thisVersionPrefix < otherVersionPrefix) {
                return false;
            } else {
                try {
                    final int[] thisVersionParts = splitVersionNumbers(getVersionNumbers());
                    final int[] otherVersionParts = splitVersionNumbers(otherVersion.getVersionNumbers());

                    for (int counter = 0; counter < thisVersionParts.length; counter++) {
                        if (thisVersionParts.length > counter && otherVersionParts.length > counter) {
                            if (thisVersionParts[counter] > otherVersionParts[counter]) {
	return true;
                            } else if (thisVersionParts[counter] < otherVersionParts[counter]) {
	return false;
                            }
                        } else if (thisVersionParts.length <= counter && ! (otherVersionParts.length <= counter)) {
                            return false;
                        } else if (! (thisVersionParts.length <= counter) && otherVersionParts.length <= counter) {
                            return true;
                        }
                    }
                }
                catch (final NumberFormatException e) {
                    return false;
                }
            }

            return false;
        }
    }

    public boolean lesser(final Version otherVersion) {

        if (equals(otherVersion)) {
            return false;
        } else {
            return !bigger(otherVersion);
        }
    }

    @Override
    public int compareTo(final Version o) {

        if (bigger(o)) {
            return 1;
        } else if (lesser(o)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (versionString == null ? 0 : versionString.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Version other = (Version) obj;
        if (versionString == null) {
            if (other.versionString != null) {
                return false;
            }
        } else if (!versionString.equals(other.versionString)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "versionString");
    }

}
