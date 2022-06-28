package com.baron.test;

import com.baron.rpc.annotation.Service;
import com.baron.rpc.api.ByeService;


@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
