package com.baron.rpc.xclient;

import com.baron.rpc.entity.RpcServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baron.rpc.entity.RpcRequest;
import com.baron.rpc.entity.RpcResponse;
import com.baron.rpc.util.RpcMessageChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;

    private final RpcServiceConfig rpcServiceConfig;

    public RpcClientProxy(RpcClient client, RpcServiceConfig rpcServiceConfig) {
        this.client = client;
        this.rpcServiceConfig = rpcServiceConfig;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(UUID.randomUUID().toString(), method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes(), rpcServiceConfig.getGroup(), rpcServiceConfig.getVersion(), false);
        RpcResponse rpcResponse = null;
        try {
            CompletableFuture<RpcResponse> completableFuture = (CompletableFuture<RpcResponse>) client.sendRequest(rpcRequest);
            rpcResponse = completableFuture.get();
        } catch (Exception e) {
            logger.error("方法调用请求发送失败", e);
            return null;
        }
        RpcMessageChecker.check(rpcRequest, rpcResponse);
        return rpcResponse.getData();
    }
}
