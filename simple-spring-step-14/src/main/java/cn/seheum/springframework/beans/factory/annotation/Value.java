package cn.seheum.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author seheum 2023/2/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface Value {

    String value();
}
