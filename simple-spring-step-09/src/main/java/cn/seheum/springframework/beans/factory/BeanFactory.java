package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

/**
 * 新增getBean接口
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
