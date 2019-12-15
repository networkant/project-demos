package com.lqcoder.annotationdemo.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputLog {

    boolean value() default true;
}
