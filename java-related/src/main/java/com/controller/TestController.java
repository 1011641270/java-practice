/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have a message on the http://tian-dd.top.
 */
package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.annotation.MyAnnotation;

/**
 * @author hztianduoduo
 *
 */
@Controller
public class TestController {
    
    @RequestMapping({ "/test.do" })
    @MyAnnotation(value = "hello") //实现拦截自定义注解
    public void test(HttpServletRequest request,
            HttpServletResponse response) {
        
        System.out.println("test Aspect");
        
    }

}
