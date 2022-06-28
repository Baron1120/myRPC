package com.baron.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baron.rpc.annotation.Service;
import com.baron.rpc.api.HelloObject;
import com.baron.rpc.api.HelloService;


@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到消息：{}", object.getMessage());
        return "这是Impl1方法";
    }

}
