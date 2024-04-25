package cn.seheum.springframework.context.support;

import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.seheum.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 实现Bean对象的资源解析，定义、属性填充和注册Bean对象
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
