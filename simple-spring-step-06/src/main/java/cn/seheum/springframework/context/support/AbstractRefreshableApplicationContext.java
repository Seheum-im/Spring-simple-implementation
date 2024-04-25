package cn.seheum.springframework.context.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 实现BeanFactory 的创建，同时在refreshBeanFactory()中制定了模板，让子类去实现Bean对象的资源解析，定义、属性填充和注册Bean对象
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
