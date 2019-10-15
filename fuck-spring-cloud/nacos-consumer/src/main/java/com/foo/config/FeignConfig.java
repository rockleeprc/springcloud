package com.foo.config;

import com.foo.controller.api.RemoteClient;
import com.foo.controller.api.RemoteClientFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignConfig {

    @Bean
    public RemoteClient remoteClient() {
        return new RemoteClientFallback();
    }
}
