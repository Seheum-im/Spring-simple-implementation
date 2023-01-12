package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义实例化策略接口
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;
}
