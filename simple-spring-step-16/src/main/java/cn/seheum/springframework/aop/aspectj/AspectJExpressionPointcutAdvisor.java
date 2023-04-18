package cn.seheum.springframework.aop.aspectj;

import cn.seheum.springframework.aop.PointCut;
import cn.seheum.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    //切面
    private AspectJExpressionPointcut pointcut;

    //具体拦截方法
    private Advice advice;

    //表达式
    private String expression;

    @Override
    public PointCut getPointcut() {
        if(null == pointcut) {
            return new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
