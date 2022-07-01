package com.baron.test;

import com.baron.rpc.annotation.RpcService;
import com.baron.rpc.api.ByeService;


@RpcService(group = "test", version = "version2")
public class ByeServiceImpl2 implements ByeService {

    @Override
    public String bye(String name) {
        return "bye2, " + name;
    }
}
