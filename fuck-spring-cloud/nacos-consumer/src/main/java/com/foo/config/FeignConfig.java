package com.foo.config;

import com.foo.remote.RemoteClient;
import com.foo.remote.fallback.RemoteClientFallback;

//@Component
@Deprecated
public class FeignConfig {

    //@Bean
    public RemoteClient remoteClient() {
        return new RemoteClientFallback();
    }
}
