package cn.seheum.springframework.test;

import cn.seheum.springframework.context.support.ClassPathXmlApplicationContext;
import cn.seheum.springframework.test.bean.IUserService;
import org.junit.Test;

/**
 * @author seheum 2023/2/17
 */
public class ApiTest {
    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
