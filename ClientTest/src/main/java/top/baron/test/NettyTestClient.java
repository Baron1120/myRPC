package top.baron.test;

import top.baron.rpc.api.ByeService;
import top.baron.rpc.api.HelloObject;
import top.baron.rpc.api.HelloService;
import top.baron.rpc.serializer.CommonSerializer;
import top.baron.rpc.transport.RpcClient;
import top.baron.rpc.transport.RpcClientProxy;
import top.baron.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty消费者
 *
 * 
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
