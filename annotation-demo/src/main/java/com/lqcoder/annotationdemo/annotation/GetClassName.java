package com.lqcoder.annotationdemo.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GetClassName {
    String value() default "自定义注解";
}
