package com.foo.consumer.config;

import com.foo.consumer.remote.RemoteClient;
import com.foo.consumer.remote.fallback.RemoteClientFallback;

//@Component
@Deprecated
public class FeignConfig {

    //@Bean
    public RemoteClient remoteClient() {
        return new RemoteClientFallback();
    }
}
