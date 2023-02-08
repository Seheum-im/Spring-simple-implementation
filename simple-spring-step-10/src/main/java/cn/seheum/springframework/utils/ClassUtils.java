package cn.seheum.springframework.utils;

import cn.seheum.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import cn.seheum.springframework.context.ApplicationListener;

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

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
