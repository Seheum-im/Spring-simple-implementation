package cn.seheum.springframework.beans.factory.config;
/**
 * @description: bean对象的容器封装类（bean定义） 
 * @author：Seheum
 * @date: 2023/10/28
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
