package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
