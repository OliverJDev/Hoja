package me.tabbin.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class ReflectionUtil {

    public static Field getField(Class<?> clazz, String name) {
        if (clazz == null) throw new NullPointerException("clazz");
        if (name == null) throw new NullPointerException("name");
        Field ret = null;
        try {
            ret = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        makeAccessible(ret);
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getField(Field field, Object object) {
        try {
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void makeAccessible(Field field) {
        try {
            // Mark as accessible using reflection.
            field.setAccessible(true);


        } catch (Exception e) {
            throw e;
        }
    }

    public static void makeAccessible(Method method) {
        try {
            // Mark as accessible using reflection.
            method.setAccessible(true);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void makeAccessible(Constructor<?> constructor) {
        try {
            // Mark as accessible using reflection.
            constructor.setAccessible(true);
        } catch (Exception e) {
            throw e;
        }
    }
}