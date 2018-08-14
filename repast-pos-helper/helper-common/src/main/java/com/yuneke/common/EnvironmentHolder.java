package com.yuneke.common;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class EnvironmentHolder implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static EmbeddedServletContainerInitializedEvent event;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        EnvironmentHolder.event = event;
    }

    public static int getPort() {
        Assert.notNull(event,"EmbeddedServletContainerInitializedEvent is null");
        int port = event.getEmbeddedServletContainer().getPort();
        Assert.state(port != -1, "端口号获取失败");
        return port;
    }

    public static String getAddr(){
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return host;
    }
}