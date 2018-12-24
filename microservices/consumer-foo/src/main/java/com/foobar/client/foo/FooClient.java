package com.foobar.client.foo;

import com.foobar.client.foo.fallback.FooClientFallback;
import com.foobar.service.foo.FooApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "PROVIDER-FOO", fallback = FooClientFallback.class)
public interface FooClient extends FooApi {
}
