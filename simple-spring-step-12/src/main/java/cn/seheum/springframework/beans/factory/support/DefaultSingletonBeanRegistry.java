package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.DisposableBean;
import cn.seheum.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();


    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }

    protected void addSingleton(String beanName,Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean) {
        disposableBeans.put(beanName,bean);
    }

    /**
     * 这个destroySingletons（）接口是 ConfigurableBeanFactory 定义的
     * 这里是让 （ConfigurableBeanFactory的子类） ‘AbstractBeanFactory’  继承的父类DefaultSingletonBeanRegistry来实现这个接口
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
       for (int i = disposableBeanNames.length - 1;i >= 0;i--) {
           Object beanName = disposableBeanNames[i];
           DisposableBean disposableBean = disposableBeans.remove(beanName);
           try {
               disposableBean.destroy();
           } catch (Exception e) {
               throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
           }
       }




    }

}
