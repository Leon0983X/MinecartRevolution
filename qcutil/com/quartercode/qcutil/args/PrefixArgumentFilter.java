
package com.quartercode.qcutil.args;

import java.util.Arrays;
import com.quartercode.qcutil.utility.ObjectUtil;

public class PrefixArgumentFilter implements ArgumentFilter {

    protected String[] prefixes;

    public PrefixArgumentFilter(String... prefixes) {

        this.prefixes = prefixes;
    }

    @Override
    public boolean isMark(String mark) {

        for (String prefix : prefixes) {
            if (mark.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equalsWithMarkString(String argument, String markString, boolean ignoreCase) {

        for (String prefix : prefixes) {
            if (ignoreCase) {
                if (argument.equalsIgnoreCase(prefix + markString)) {
                    return true;
                }
            } else {
                if (argument.equals(prefix + markString)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(prefixes);
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
        PrefixArgumentFilter other = (PrefixArgumentFilter) obj;
        if (!Arrays.equals(prefixes, other.prefixes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectString(this, "prefixes", Arrays.asList(prefixes));
    }

}
