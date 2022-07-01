package com.baron.test;

import com.baron.rpc.annotation.RpcScan;
import com.baron.rpc.xserver.NettyServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@RpcScan(basePackage = {"com.baron"})
public class TestServer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestServer.class);
        NettyServer nettyServer = (NettyServer) applicationContext.getBean("nettyServer");
        nettyServer.start();
    }

}
