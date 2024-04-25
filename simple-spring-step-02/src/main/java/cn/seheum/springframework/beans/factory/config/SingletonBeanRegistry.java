package cn.seheum.springframework.beans.factory.config;
/**
 * @description: 获取单例模式的bean对象
 * @author：Seheum
 * @date: 2023/10/28
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
