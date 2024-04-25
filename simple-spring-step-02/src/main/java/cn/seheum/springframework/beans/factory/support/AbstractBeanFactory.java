package cn.seheum.springframework.beans.factory.support;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.BeanFactory;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;
/**
 * @description: 模板模式实现getBean()，获取bean对象，这里的继承关系是：父类AbstractBeanFactory 继承了BeanDefinition注册，剩下他的子类则不需要再去继承DefaultSingletonBeanRegistry，减少了继承冗余
 * @author：Seheum
 * @date: 2023/10/28
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        // 先去查询单例bean是否存在，若不存在则进行获取
        Object bean = getSingleton(name);
        if(bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);

    }


    /**
     * 获取bean对象的容器封装类
     * @param beanName bean对象名称
     * @return bean对象的容器封装类
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean对象
     * @param beanName bean对象名称
     * @param beanDefinition bean对象的容器封装类
     * @return 对应的bean对象
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
}
