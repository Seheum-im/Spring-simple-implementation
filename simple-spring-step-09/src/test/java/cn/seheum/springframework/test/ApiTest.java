package cn.seheum.springframework.test;


import cn.seheum.springframework.context.support.ClassPathXmlApplicationContext;
import cn.seheum.springframework.test.bean.UserService;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class ApiTest {

    @Test
   public void test_prototype() {
        //1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        //2.获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }
    @Test
    public void test_factory_bean() {
//         1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }


    @Test
    public void test_dynamic_proxy() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if(method.getName().equals("lookAtYou")) {
                    System.out.println("Hello,sweet " + args[0]);
                }
                return null;
            }
        };

        SeeYouAgain proxyInstance = (SeeYouAgain) Proxy.newProxyInstance(SeeYouAgain.class.getClassLoader(), new Class[]{SeeYouAgain.class}, handler);
        proxyInstance.lookAtYou("whip your ass");
    }

    interface SeeYouAgain{
        void lookAtYou(String act);
    }
}
