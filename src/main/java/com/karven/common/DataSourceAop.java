package com.karven.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class DataSourceAop {

    /*@Pointcut("!@annotation(Master) " +
            "&& (execution(* com.karven.example.service..*.select*(..)) " +
            "|| execution(* com.karven.example.service..*.get*(..)))")*/
    public void readPointcut() {

    }

    /*@Pointcut("@annotation(Master) " +
            "|| execution(* com.karven.example.service..*.insert*(..)) " +
            "|| execution(* com.karven.example.service..*.add*(..)) " +
            "|| execution(* com.karven.example.service..*.update*(..)) " +
            "|| execution(* com.karven.example.service..*.edit*(..)) " +
            "|| execution(* com.karven.example.service..*.delete*(..)) " +
            "|| execution(* com.karven.example.service..*.remove*(..))")*/
    public void writePointcut() {

    }

    /*@Before("readPointcut()")*/
    public void read() {
        DBContextHolder.slave();
    }

    /*@Before("writePointcut()")*/
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.karven.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}