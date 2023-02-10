package cn.seheum.springframework.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String name);

    void registerSingleton(String beanName, Object singletonObject);
}
