package top.baron.test;

import top.baron.rpc.annotation.Service;
import top.baron.rpc.api.ByeService;


@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
