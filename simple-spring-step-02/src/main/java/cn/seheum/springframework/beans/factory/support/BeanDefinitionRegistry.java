package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;

public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
