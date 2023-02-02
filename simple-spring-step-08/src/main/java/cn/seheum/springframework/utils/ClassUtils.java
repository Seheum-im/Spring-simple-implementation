package cn.seheum.springframework.utils;

public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c1 = null;
        try{
            c1 = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex) {

        }

        if(c1 == null) {
            c1 = ClassUtils.class.getClassLoader();
        }
        return c1;
    }
}
