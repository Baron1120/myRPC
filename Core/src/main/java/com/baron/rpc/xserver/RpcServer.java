package com.baron.rpc.xserver;

import com.baron.rpc.entity.RpcServiceConfig;
import com.baron.rpc.extension.SPI;
import com.baron.rpc.transport.serializer.CommonSerializer;

@SPI
public interface RpcServer {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    <T> void publishService(RpcServiceConfig registeredService);
}
