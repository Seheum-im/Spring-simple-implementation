package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

/**
 * @author seheum
 * @date 2023/4/18
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}

