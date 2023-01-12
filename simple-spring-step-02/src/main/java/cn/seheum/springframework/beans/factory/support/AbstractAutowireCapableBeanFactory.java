package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
