package com.baron.test;

import com.baron.rpc.annotation.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baron.rpc.api.HelloObject;
import com.baron.rpc.api.HelloService;


@RpcService(group = "test", version = "version1")
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("hello1 收到消息：{}", object.getMessage());
        return "hello1, " + object.getMessage();
    }

}
