package com.example.tx.invalid.app.service;

import com.example.tx.invalid.app.util.LoggerUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
public class Service6 {

//    @Autowired
//    private Service6 proxy;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void foo() throws FileNotFoundException {
        LoggerUtils.get().debug("foo");
//        System.out.println(proxy.getClass());
//        proxy.bar();
        ((Service6) AopContext.currentProxy()).bar();
//        bar();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void bar() throws FileNotFoundException {
        LoggerUtils.get().debug("bar");
    }
}
