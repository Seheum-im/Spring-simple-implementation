package cn.seheum.springframework.beans.factory;

import cn.seheum.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回bean实力
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /*
   返回注册表中的所有Bean名称
     */
    String[] getBeanDefinitionNames();


}
