package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

/**
 * 新增getBean接口
 */
public interface BeanFactory {
    /**
     * 根据传输的beanName获取对应的bean对象
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取构造函数含参 实例化后的Bean对象
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;
}
