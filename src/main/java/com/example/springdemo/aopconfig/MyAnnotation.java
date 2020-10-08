package com.example.springdemo.aopconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author miaomiaole
 * @date 2020/10/8 19:14
 * @DESCRIBE 定义切面
 */
@Aspect
@Component
public class MyAnnotation {
    @Pointcut("@annotation(com.example.springdemo.AnnotationConfig.MyAnnotation)")
    public void annotiationMethod() {

    }


    /**
     *  JoinPoint
     *
     * @param joinPoint
     */
    @Before(value = "annotiationMethod()")
    public void before(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();
        Object target = joinPoint.getTarget();
        Annotation[] annotations = target.getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            for (Field field : annotation.getClass().getFields()) {
                System.out.println(field.getName());
                String o = field.get(field.getName()).toString();
                System.out.println(o);
            }
        }
        System.out.println("执行注解方法");
    }
}
