package cn.seheum.springframework.beans.factory;

/**
 * 定义销毁方法接口
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
