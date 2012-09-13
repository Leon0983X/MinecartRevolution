
package com.quartercode.qcutil.utility;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.quartercode.qcutil.QcUtil;
import com.quartercode.qcutil.ds.OnceList;

public class ObjectUtil {

    public static Field[] getAllFields(Class<?> c) {

        List<Field> fields = new OnceList<Field>();

        fields.addAll(Arrays.asList(c.getDeclaredFields()));
        if (c.getSuperclass() != null) {
            fields.addAll(Arrays.asList(getAllFields(c.getSuperclass())));
        }

        Field[] fieldArray = new Field[fields.size()];
        for (int counter = 0; counter < fields.size(); counter++) {
            fieldArray[counter] = fields.get(counter);
        }

        return fieldArray;
    }

    public static Field searchField(Class<?> c, String name) {

        try {
            return c.getDeclaredField(name);
        }
        catch (NoSuchFieldException e) {
            Field superFiled = searchField(c.getSuperclass(), name);
            return superFiled;
        }
    }

    public static String generateObjectStringWithNames(Object object, String... fieldNames) {

        Field[] fields = new Field[fieldNames.length];
        for (int counter = 0; counter < fieldNames.length; counter++) {
            fields[counter] = searchField(object.getClass(), fieldNames[counter]);
        }

        return generateObjectStringWithFields(object, fields);
    }

    public static String generateObjectStringWithFields(Object object, Field... fields) {

        AccessibleObject.setAccessible(fields, true);
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();

        for (int counter = 0; counter < fields.length; counter++) {
            Field field = fields[counter];
            if (!fieldValues.containsKey(fields[counter].getName())) {

                try {
                    if (field.get(object) != null) {
                        fieldValues.put(fields[counter].getName(), field.get(object).toString());
                    } else {
                        fieldValues.put(fields[counter].getName(), "null");
                    }
                }
                catch (Throwable t) {
                    QcUtil.handleThrowable(t);
                }
            }
        }

        Object[] fieldDeclarations = new Object[fieldValues.size() * 2];
        int counter = 0;
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            fieldDeclarations[counter] = entry.getKey();
            fieldDeclarations[counter + 1] = entry.getValue();
            counter += 2;
        }

        return generateObjectString(object, fieldDeclarations);
    }

    public static String generateObjectString(Object object, Object... fields) {

        if (fields.length <= 0) {
            return generateObjectStringWithFields(object, getAllFields(object.getClass()));
        } else {
            String toString = "";
            toString += object.getClass().getName();

            toString += "[";
            for (int counter = 0; counter < fields.length; counter += 2) {
                toString += fields[counter] + "=" + fields[counter + 1] + ", ";
            }
            toString = toString.substring(0, toString.length() - 2);

            int objectHashCode = object.hashCode();
            int objectIdentyHashCode = System.identityHashCode(object);
            if (objectHashCode != objectIdentyHashCode) {
                toString += ", hashCode=" + object.hashCode();
            }

            toString += "] @ ";
            toString += Integer.toHexString(objectIdentyHashCode);

            return toString;
        }
    }

    private ObjectUtil() {

    }

}
