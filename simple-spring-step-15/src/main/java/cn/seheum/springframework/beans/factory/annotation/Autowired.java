package cn.seheum.springframework.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author seheum 2023/2/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
public @interface Autowired {
}

