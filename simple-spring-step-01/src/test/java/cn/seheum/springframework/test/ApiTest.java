package cn.seheum.springframework.test;

import cn.seheum.springframework.BeanDefinition;
import cn.seheum.springframework.BeanFactory;
import cn.seheum.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {


    @Test
    public void test_BeanFactory(){


        //初始化工厂
        BeanFactory beanFactory = new BeanFactory();

        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUseInfo();

    }
}
