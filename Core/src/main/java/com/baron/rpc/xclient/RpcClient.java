package com.baron.rpc.xclient;

import com.baron.rpc.transport.serializer.CommonSerializer;
import com.baron.rpc.entity.RpcRequest;


public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
