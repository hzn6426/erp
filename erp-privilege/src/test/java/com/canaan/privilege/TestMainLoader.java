package com.canaan.privilege;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 直接启动dubbo服务，用于启动调试
 */
public class TestMainLoader {
//    public static void main(String[] args) throws IOException {
////        com.alibaba.dubbo.container.Main.main(args);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/configs/spring-*.xml");
//        context.start();
//        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");
//        while (true) {
//        }
////        System.in.read();
//    }

    @Test
    public void testStartDubbo() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "configs/spring-*.xml"
        );
        context.start();
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");
        while (true) {
        }
    }
}