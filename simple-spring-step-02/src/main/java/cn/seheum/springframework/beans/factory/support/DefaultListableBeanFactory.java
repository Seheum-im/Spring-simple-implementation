package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
/**
 * @description: 实现获取bean对象的容器封装类（也就是BeanDefinition）
 * @author：Seheum
 * @date: 2023/10/28
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null ) {
            throw new BeansException("No bean named " + beanName + " is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
