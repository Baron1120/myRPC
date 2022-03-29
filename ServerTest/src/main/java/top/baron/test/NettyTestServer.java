package top.baron.test;

import top.baron.rpc.annotation.ServiceScan;
import top.baron.rpc.serializer.CommonSerializer;
import top.baron.rpc.transport.RpcServer;
import top.baron.rpc.transport.netty.server.NettyServer;

/**
 * 测试用Netty服务提供者（服务端）
 *
 * 
 */
@ServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}
