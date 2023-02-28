package cn.seheum.springframework.context.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.seheum.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.seheum.springframework.beans.factory.config.BeanPostProcessor;
import cn.seheum.springframework.context.ConfigurableApplicationContext;
import cn.seheum.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 经典的设计模式，将需要使用到的ResourceLoader让最上层的Context来继承，避免子类重复实现ResourceLoader造成冗余
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        //1、创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3、在Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4、BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5、提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }


    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //这里是对BeanFactoryProcessor 创建实例Bean
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap  = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //这里是对BeanPostProcessor创建实例Bean
        Map<String, BeanPostProcessor> beanPostProcessorMap  = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor  : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
            
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
