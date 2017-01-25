import java.lang.reflect.Field;

/**
 * Created by Artoria on 2017/1/22.
 */
public class Foo {
    public String toString() {
        String s = "";
        Field[] fields = getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
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
        return "new " + getClass().getName() + "(" + s + ")";
    }
}
