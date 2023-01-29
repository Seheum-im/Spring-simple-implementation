package cn.seheum.springframework.test.common;

import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.factory.config.BeanPostProcessor;
import cn.seheum.springframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：广州");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
