package cn.seheum.springframework.context;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
