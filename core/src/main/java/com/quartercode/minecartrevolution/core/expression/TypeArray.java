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

package com.quartercode.minecartrevolution.core.expression;

public class TypeArray {

    public enum Type {

        NONE (null, false), STRING (String.class, false), DOUBLE (Double.class, true);

        private Class<?> c;
        private boolean  number;

        private Type(Class<?> c, boolean isNumber) {

            this.c = c;
            number = isNumber;
        }

        public Class<?> getTypeClass() {

            return c;
        }

        public boolean isInstance(Object object) {

            return object != null && object.getClass().equals(c) || c == null && object == null;
        }

        public boolean isNumber() {

            return number;
        }

    }

    private final Type[] types;

    public TypeArray(Type... types) {

        this.types = types;
    }

    public Type[] getTypes() {

        return types;
    }

    public boolean hasType(Object object) {

        for (Type type : types) {
            if (type.isInstance(object)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasNumberType() {

        for (Type type : types) {
            if (type.isNumber()) {
                return true;
            }
        }

        return false;
    }

}
