package com.baron.test;

import com.baron.rpc.annotation.RpcService;
import com.baron.rpc.api.ByeService;


@RpcService(group = "test", version = "version1")
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye1, " + name;
    }
}
