package com.baron.test;

import com.baron.rpc.annotation.RpcReference;
import com.baron.rpc.api.ByeService;
import com.baron.rpc.api.HelloObject;
import com.baron.rpc.api.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloController {

    @RpcReference(group = "test", version = "version2")
    private HelloService helloService;

    @RpcReference(group = "test", version = "version2")
    private ByeService byeService;

    public void test() throws InterruptedException {
        System.out.println(helloService.hello(new HelloObject(1, "baron")));
        System.out.println(byeService.bye("baron"));
    }
}
