package com.baron.rpc.xserver;

import com.baron.rpc.transport.serializer.CommonSerializer;


public interface RpcServer {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    <T> void publishService(T service, String serviceName);

}
