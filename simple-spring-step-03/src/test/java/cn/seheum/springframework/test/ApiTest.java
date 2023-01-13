package cn.seheum.springframework.test;

import cn.seheum.springframework.beans.factory.config.BeanDefinition;
import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.seheum.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        //1、初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、Bean注册
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        //3、获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "菲奥娜");
        userService.queryUserInfo();
    }
}
