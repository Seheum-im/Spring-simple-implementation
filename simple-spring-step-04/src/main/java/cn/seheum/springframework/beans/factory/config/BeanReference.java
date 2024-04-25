package cn.seheum.springframework.beans.factory.config;

/**
 * Bean引用，即Bean对象中的引用类型字段添加入PropertyValues时传入该引用类型的beanName去获取对应的BeanDefinition
 * BeanReference的作用是在具体的实例化操作时进行递归创建和填充
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }


}
