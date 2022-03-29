package top.baron.rpc.transport;

import top.baron.rpc.entity.RpcRequest;
import top.baron.rpc.serializer.CommonSerializer;

/**
 * 客户端类通用接口
 *
 * 
 */
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
