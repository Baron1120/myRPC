package com.baron.rpc.service.registry;

import java.net.InetSocketAddress;


public interface ServiceDiscovery {

    
    InetSocketAddress lookupService(String serviceName);

}
