package com.stu.sbt.common.config.annotation;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TargetDataSource {
    String value() default "master";
}
