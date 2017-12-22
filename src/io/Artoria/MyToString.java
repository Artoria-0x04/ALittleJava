package io.Artoria;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artoria on 2017/1/22.
 * Customized toString()
 */
public class MyToString {
    @Override
    public String toString() {
        String s = "";
        Field[] fields = getAllFields(getClass());
        for(int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                s += fields[i].get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i < fields.length - 1) {
                s += ", ";
            } else {
            }
        }
        return "new " + getClass().getSimpleName() + "(" + s + ")";
    }

    public static Field[] getAllFields(Class<?> clazz) {
        List<Field> fields = new LinkedList<>();
        if (clazz == Object.class) {
            return new Field[]{};
        } else {
            fields.addAll(Arrays.asList(getAllFields(clazz.getSuperclass())));
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return fields.toArray(new Field[0]);
    }
}
