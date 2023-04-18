package cn.seheum.springframework.aop.framework.autoproxy;

import cn.seheum.springframework.aop.*;
import cn.seheum.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.seheum.springframework.aop.framework.ProxyFactory;
import cn.seheum.springframework.beans.BeansException;
import cn.seheum.springframework.beans.PropertyValues;
import cn.seheum.springframework.beans.factory.BeanFactory;
import cn.seheum.springframework.beans.factory.BeanFactoryAware;
import cn.seheum.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.seheum.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {


    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)|| PointCut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(isInfrastructureClass(bean.getClass())) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();


            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            //以下注释掉的是出bug的代码
//            TargetSource targetSource = null;
//
//
//            try {
//                targetSource = new TargetSource(bean.getClass().getDeclaredConstructor().newInstance());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            advisedSupport.setTargetSource(targetSource);
//            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
//            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
//            advisedSupport.setProxyTargetClass(false);

            //返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }
}
