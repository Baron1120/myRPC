package com.baron.rpc.registry;

import java.net.InetSocketAddress;


public interface ServiceDiscovery {

    
    InetSocketAddress lookupService(String serviceName);

}
