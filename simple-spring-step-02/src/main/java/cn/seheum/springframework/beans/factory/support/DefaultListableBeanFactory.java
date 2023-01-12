package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null ) throw new BeansException("No bean named " + beanName + " is defined");
        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
