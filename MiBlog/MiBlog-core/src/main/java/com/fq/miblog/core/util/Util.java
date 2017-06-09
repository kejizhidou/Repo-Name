package com.fq.miblog.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author jifang
 * @since 16/7/7 下午4:38.
 */
public class Util {

    private static final Set<Class> simpleClazz = new HashSet<>();
    private static final Set<String> ignoreField = new HashSet<>();

    static {
        simpleClazz.add(Boolean.class);
        simpleClazz.add(Byte.class);
        simpleClazz.add(Integer.class);
        simpleClazz.add(Long.class);
        simpleClazz.add(Float.class);
        simpleClazz.add(Double.class);
        simpleClazz.add(Short.class);

        ignoreField.add("password");
    }

    private static Object simpleClassInstance(Class clazz, String str) {
        try {
            Method valueOf = clazz.getDeclaredMethod("valueOf", String.class);
            valueOf.setAccessible(true);
            return valueOf.invoke(null, str);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T mapToSimpleObject(Map<String, String> map, Class<T> clazz) {
        try {
            Object o = clazz.newInstance();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                if (!ignoreField.contains(fieldName)) {
                    try {
                        Field field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Class fieldClazz = field.getType();
                        if (simpleClazz.contains(fieldClazz)) {
                            field.set(o, simpleClassInstance(fieldClazz, entry.getValue()));
                        } else if (fieldClazz == String.class) {
                            field.set(o, entry.getValue());
                        } else if (fieldClazz == Character.class) {
                            field.setChar(o, entry.getValue().toCharArray()[0]);
                        }
                    } catch (NoSuchFieldException ignored) {
                    }
                }
            }
            return (T) o;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
