package com.foobar.client;

import com.foobar.service.api.FooApi;
import com.foobar.client.fallback.FooClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value="PROVIDER-FOO",fallback = FooClientFallback.class)
public interface FooClient extends FooApi {
}
