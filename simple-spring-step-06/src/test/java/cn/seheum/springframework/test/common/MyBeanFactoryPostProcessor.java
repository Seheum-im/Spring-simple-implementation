package cn.seheum.springframework.test.common;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.PropertyValue;
import cn.seheum.springframework.beans.PropertyValues;
import cn.seheum.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;
import cn.seheum.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company","改为：百度"));
    }
}
