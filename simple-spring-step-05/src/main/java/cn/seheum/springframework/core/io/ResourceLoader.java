package cn.seheum.springframework.core.io;

/**
 * 资源加载接口定义
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    //获取资源
    Resource getResource(String location);
}
