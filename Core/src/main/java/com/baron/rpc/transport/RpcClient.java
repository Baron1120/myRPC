package com.baron.rpc.transport;

import com.baron.rpc.serializer.CommonSerializer;
import com.baron.rpc.entity.RpcRequest;

/**
 * 客户端类通用接口
 *
 * @author ziyang
 */
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
