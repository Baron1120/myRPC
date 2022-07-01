package com.baron.rpc.xserver;

import com.baron.rpc.entity.RpcServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baron.rpc.service.provider.ServiceProvider;
import com.baron.rpc.service.registry.ServiceRegistry;
import java.net.InetSocketAddress;

public abstract class AbstractRpcServer implements RpcServer {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String host;
    protected int port = 9999;

    protected ServiceRegistry serviceRegistry;
    protected ServiceProvider serviceProvider;

    @Override
    public <T> void publishService(RpcServiceConfig rpcServiceConfig) {
        // 放入provider
        String rpcServiceName = rpcServiceConfig.getRpcServiceName();
        serviceProvider.addServiceProvider(rpcServiceConfig.getService(), rpcServiceName);
        logger.info("Add service: {} and interfaces:{}", rpcServiceName, rpcServiceConfig.getService().getClass().getInterfaces());
        // 注册Nacos
        serviceRegistry.register(rpcServiceName, new InetSocketAddress(host, port));
    }
}
