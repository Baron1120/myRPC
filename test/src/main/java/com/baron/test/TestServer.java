package com.baron.test;

import com.baron.rpc.annotation.ServiceScan;
import com.baron.rpc.serializer.CommonSerializer;
import com.baron.rpc.transport.RpcServer;
import com.baron.rpc.transport.netty.server.NettyServer;


@ServiceScan
public class TestServer {

    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}