package com.fast.summary.cyclic.dependence;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

public class App60_2 {

    static class A {
        private static final Logger log = LoggerFactory.getLogger("A");
        private B b;

        public A() {
            log.debug("A()");
        }

        @Autowired
        public void setB(B b) {
            log.debug("set B({})", b);
            this.b = b;
        }

        public void foo() {
            System.out.println("foo");
        }

        @PostConstruct
        public void init() {
            log.debug("init()");
        }
    }

    @Aspect
    static class MyAspect{
        @Before("execution(* foo())")
        public void before()  {
            System.out.println("before ...");
        }
    }

    static class B {
        private static final Logger log = LoggerFactory.getLogger("B");
        private A a;
        public B() {
            log.debug("B()");
        }
        @Autowired
        public void setA(A a) {
            log.debug("setA({})", a.getClass());
            this.a = a;
        }

        @PostConstruct
        public void init() {
            log.debug("init()");
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("a", A.class);
        context.registerBean("b", B.class);
        context.registerBean(MyAspect.class);
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.refresh();
        context.getBean(A.class).foo();
        System.out.println();
    }
}

