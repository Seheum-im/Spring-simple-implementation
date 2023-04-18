package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.DisposableBean;
import cn.seheum.springframework.beans.factory.ObjectFactory;
import cn.seheum.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();


    // 一级缓存，存放初始化对象
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    //三级缓存
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    //二级缓存
    protected final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();


    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if(null == singletonObject) {
            //一级缓存中没有对象，则去二级缓存中获取
            singletonObject = earlySingletonObjects.get(beanName);
            if(null == singletonObject) {
                //二级缓存都没有，则去三级缓存中获取
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if(singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    //把三级缓存中的对象存放到二级缓存，然后删除三级缓存中的这个对象
                    earlySingletonObjects.put(beanName,singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName,ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
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
