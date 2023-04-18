package cn.seheum.springframework.aop;

public interface PointcutAdvisor extends Advisor {
    /**
     * Get the Pointcut that drives this advisor.
     */
    PointCut getPointcut();
}
