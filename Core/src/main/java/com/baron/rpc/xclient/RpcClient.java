package com.baron.rpc.xclient;

import com.baron.rpc.extension.SPI;
import com.baron.rpc.transport.serializer.CommonSerializer;
import com.baron.rpc.entity.RpcRequest;

@SPI
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
