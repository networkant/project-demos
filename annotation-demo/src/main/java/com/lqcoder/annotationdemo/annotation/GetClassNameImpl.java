package com.lqcoder.annotationdemo.annotation;


import com.lqcoder.annotationdemo.apt.Student;

/**
 * @Author ：luomengsun.
 * @Date ：Created in 11:40 2019/12/15
 * @Description：
 */
public class GetClassNameImpl {
    public static void main(String[] args) {
        Student student = new Student();
        Class tClass = student.getClass();
        GetClassName msg = (GetClassName) tClass.getAnnotation(GetClassName.class);
        System.out.println(msg.value());
    }
}
