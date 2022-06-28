package com.baron.test;

import com.baron.rpc.api.ByeService;
import com.baron.rpc.api.HelloObject;
import com.baron.rpc.api.HelloService;
import com.baron.rpc.serializer.CommonSerializer;
import com.baron.rpc.transport.RpcClient;
import com.baron.rpc.transport.RpcClientProxy;
import com.baron.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty消费者
 *
 * @author ziyang
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }

}