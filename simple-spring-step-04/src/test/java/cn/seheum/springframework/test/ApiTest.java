package cn.seheum.springframework.test;


import cn.seheum.springframework.beans.factory.PropertyValue;
import cn.seheum.springframework.beans.factory.PropertyValues;
import cn.seheum.springframework.beans.factory.config.BeanDefinition;
import cn.seheum.springframework.beans.factory.config.BeanReference;
import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.seheum.springframework.test.bean.UserDao;
import cn.seheum.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {


    @Test
    public void test_BeanFactory9() {

        //1、初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、 UserDao 注册
        beanFactory.registryBeanDefinition("userDao",new BeanDefinition(UserDao.class));


        //3、UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //4、UserService 注入 容器
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        //5、UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();

    }
}
