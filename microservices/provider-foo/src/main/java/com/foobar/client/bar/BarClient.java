package com.foobar.client.bar;

import com.foobar.client.bar.fallback.BarClientFallback;
import com.foobar.service.bar.BarApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "PROVIDER-BAR", fallback = BarClientFallback.class)
public interface BarClient extends BarApi {
}
