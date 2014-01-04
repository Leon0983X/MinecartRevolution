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

package com.quartercode.minecartrevolution.core.util.args;

import java.util.Arrays;

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

        int prime = 31;
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

}
