
package com.quartercode.minecartrevolution.util;

public class TypeArray {

    public enum Type {

        NONE (null, false), STRING (String.class, false), DOUBLE (Double.class, true);

        private final Class<?> c;
        private final boolean  isNumber;

        private Type(final Class<?> c, final boolean isNumber) {

            this.c = c;
            this.isNumber = isNumber;
        }

        public Class<?> getTypeClass() {

            return c;
        }

        public boolean isInstance(final Object object) {

            return object != null && object.getClass().equals(c) || c == null && object == null;
        }

        public boolean isNumber() {

            return isNumber;
        }
    }

    private final Type[] types;

    public TypeArray(final Type... types) {

        this.types = types;
    }

    public Type[] getTypes() {

        return types;
    }

    public boolean isInstance(final Object object) {

        for (final Type type : types) {
            if (type.isInstance(object)) {
                return true;
            }
        }

        return false;
    }

    public boolean isOneNumber() {

        for (final Type type : types) {
            if (type.isNumber) {
                return true;
            }
        }

        return false;
    }

}