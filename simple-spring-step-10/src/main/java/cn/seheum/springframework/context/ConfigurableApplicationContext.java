package cn.seheum.springframework.context;

import cn.seheum.springframework.beans.BeansException;


public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;


    void registerShutdownHook();

    void close();
}
