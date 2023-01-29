package cn.seheum.springframework.beans.factory.config;

import cn.seheum.springframework.beans.BeansException;

public interface BeanPostProcessor {

    /**
     *
     * 在Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;


    /**
     * 在Bean对象执行初始化之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;
}
