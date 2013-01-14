
package com.quartercode.minecartrevolution.util;

public class TypeArray {

    public enum Type {

        NONE (null, false), STRING (String.class, false), DOUBLE (Double.class, true);

        private final Class<?> c;
        private final boolean  isNumber;

        private Type(Class<?> c, boolean isNumber) {

            this.c = c;
            this.isNumber = isNumber;
        }

        public Class<?> getTypeClass() {

            return c;
        }

        public boolean isInstance(Object object) {

            return (object != null && object.getClass().equals(c)) || (c == null && object == null);
        }

        public boolean isNumber() {

            return isNumber;
        }
    }

    private final Type[] types;

    public TypeArray(Type... types) {

        this.types = types;
    }

    public Type[] getTypes() {

        return types;
    }

    public boolean isInstance(Object object) {

        for (Type type : types) {
            if (type.isInstance(object)) {
                return true;
            }
        }

        return false;
    }

    public boolean isOneNumber() {

        for (Type type : types) {
            if (type.isNumber) {
                return true;
            }
        }

        return false;
    }

}