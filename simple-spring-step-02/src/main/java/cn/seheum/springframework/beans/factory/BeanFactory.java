package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
