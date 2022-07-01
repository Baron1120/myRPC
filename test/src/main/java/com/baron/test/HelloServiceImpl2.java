package com.baron.test;

import com.baron.rpc.annotation.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baron.rpc.api.HelloObject;
import com.baron.rpc.api.HelloService;


@RpcService(group = "test", version = "version2")
public class HelloServiceImpl2 implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("hello2 收到消息：{}", object.getMessage());
        return "hello2, " + object.getMessage();
    }

}
