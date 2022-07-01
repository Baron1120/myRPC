package com.baron.rpc.spring;

import com.baron.rpc.annotation.RpcReference;
import com.baron.rpc.annotation.RpcService;
import com.baron.rpc.entity.RpcServiceConfig;
import com.baron.rpc.extension.ExtensionLoader;
import com.baron.rpc.factory.SingletonFactory;
import com.baron.rpc.hook.ShutdownHook;
import com.baron.rpc.service.provider.ServiceProvider;
import com.baron.rpc.service.provider.ServiceProviderImpl;
import com.baron.rpc.service.registry.NacosServiceRegistry;
import com.baron.rpc.xclient.NettyClient;
import com.baron.rpc.xclient.RpcClient;
import com.baron.rpc.xclient.RpcClientProxy;
import com.baron.rpc.xserver.NettyServer;
import com.baron.rpc.xserver.RpcServer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(SpringBeanPostProcessor.class);
    private final RpcClient rpcClient;
    private final RpcServer rpcServer;

    public SpringBeanPostProcessor() {
        this.rpcClient = ExtensionLoader.getExtensionLoader(RpcClient.class).getExtension("client");
        this.rpcServer = ExtensionLoader.getExtensionLoader(RpcServer.class).getExtension("server");
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            logger.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());
            // get RpcService annotation
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            // build RpcServiceProperties
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();
            rpcServer.publishService(rpcServiceConfig);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                        .group(rpcReference.group())
                        .version(rpcReference.version()).build();
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceConfig);
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }
}
