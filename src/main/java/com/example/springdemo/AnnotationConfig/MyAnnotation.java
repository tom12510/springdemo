package com.example.springdemo.AnnotationConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author miaomiaole
 * @date 2020/10/8 18:58
 * @DESCRIBE
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String name() default "miaomiaole";

    String value();

    String[] names();

    boolean isExits();

    int age();

}
