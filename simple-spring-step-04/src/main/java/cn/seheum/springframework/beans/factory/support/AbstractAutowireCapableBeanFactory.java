package cn.seheum.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.PropertyValue;
import cn.seheum.springframework.beans.factory.PropertyValues;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;
import cn.seheum.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    /**
     * Bean 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition) {

        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference) {
                    //A 依赖B，获取B的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                //属性填充
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }

    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);

            //给Bean 填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
        } catch (Exception  e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructor = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructor) {
            if(null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
