package org.invokebar;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class InvokeBarApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvokeBarApplication.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(fallbackClass = ExceptionUtil.class, fallback = "handleException", blockHandlerClass = ExceptionUtil.class, blockHandler = "fallException")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    static class ExceptionUtil {
        /**
         * 熔断降级方法
         */
        public static ClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
            return new SentinelClientHttpResponse("blockhandler method");
        }


        /**
         * 异常降级方法
         */
        public static ClientHttpResponse fallException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
            return new SentinelClientHttpResponse("fallback method");
        }
    }
}
