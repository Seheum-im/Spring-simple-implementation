package cn.seheum.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author seheum 2023/2/17
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";
}
