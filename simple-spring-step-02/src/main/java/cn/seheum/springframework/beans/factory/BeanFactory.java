package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

/**
 * @description: 定义获取bean对象的接口
 * @author：Seheum
 * @date: 2023/10/28
 */
public interface BeanFactory {
    /**
     * 根据传输的beanName获取对应的bean对象
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;
}
