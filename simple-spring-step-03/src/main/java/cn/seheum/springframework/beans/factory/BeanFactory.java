package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

/**
 * 新增getBean接口
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    /**
     * 获取实例化后构造函数含参的Bean对象
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name,Object... args) throws BeansException;
}
