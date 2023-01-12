package cn.seheum.springframework.test;

import cn.seheum.springframework.beans.factory.config.BeanDefinition;
import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.seheum.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        //1、初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        //3、第一次获取bean
       UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println("userService对象在堆中的地址" + userService);
       userService.queryUserInfo();

       //4、第二次获取bean
        UserService userServiceCopy = (UserService) beanFactory.getBean("userService");
        System.out.println("userServiceCopy对象在堆中的地址" + userServiceCopy);
        userServiceCopy.queryUserInfo();
    }
}
