package cn.seheum.springframework.test;


import cn.seheum.springframework.beans.PropertyValue;
import cn.seheum.springframework.beans.PropertyValues;
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
        // 这里我自己新建的UserDao内含有自己的字段
        PropertyValues uDpvs = new PropertyValues();
        uDpvs.addPropertyValue(new PropertyValue("addr", "Los Angle"));
        BeanDefinition uDBeanDefinition = new BeanDefinition(UserDao.class, uDpvs);
        beanFactory.registryBeanDefinition("uD", uDBeanDefinition);


        //3、UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("uD")));

        //4、UserService 注入 容器
        // Bean对象定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        // Bean对象注册
        beanFactory.registryBeanDefinition("uS",beanDefinition);

        //5、UserService 获取bean
        // 在创建UserService实例的时候，同时也创建了UserService里面的引用类型实例，如UserDao
        UserService userService = (UserService) beanFactory.getBean("uS");
        // 则下面这段被注释掉的getBean将不会再次创建 UserDao，它会去获取单例已经创建好的UserDao
//        UserDao uD = (UserDao) beanFactory.getBean("uD", "Japan");

        // 获取UserDao
        UserDao uD = (UserDao) beanFactory.getBean("uD");
        System.out.println(uD.getAddr());
        userService.queryInfo();

    }
}
