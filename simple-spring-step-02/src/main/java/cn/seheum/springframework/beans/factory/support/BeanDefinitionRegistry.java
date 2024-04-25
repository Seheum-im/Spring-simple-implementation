package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
/**
 * @description: 实现注册bean对象的容器封装类（bean的定义）
 * @author：Seheum
 * @date: 2023/10/28
 */
public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
