package com.baron.test;

import com.baron.rpc.annotation.RpcScan;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;

@RpcScan(basePackage = {"com.baron"})
public class TestClient {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestClient.class);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestClient.class);
        HelloController helloController = (HelloController) applicationContext.getBean("helloController");
        try {
            helloController.test();
        } catch (Exception e) {
            logger.info("调用HelloController出错");
        }
    }
}
