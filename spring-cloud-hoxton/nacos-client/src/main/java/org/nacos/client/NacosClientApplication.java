package org.nacos.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class);
    }
}
/*

Ribbon负载均衡策略：
 // 随机选择一个server
 new RandomRule();
 // 逐个考察server，如果server断路器打开，则忽略，再选择其中并发链接最低的server
 new BestAvailableRule();
 // 轮询选择，轮询index，选择index对应位置的server
 new RoundRobinRule();
 // 根据server的响应时间分配权重，响应时间越长，权重越低，被选择到的概率也就越低。
 // 响应时间越短，权重越高，被选中的概率越高，这个策略很贴切，综合了各种因素，比如：网络，磁盘，io等，都直接影响响应时间
 new WeightedResponseTimeRule();
 // 综合判断server所在区域的性能，和server的可用性，轮询选择server并且判断一个AWS Zone的运行性能是否可用，剔除不可用的Zone中的所有server
 new ZoneAvoidanceRule();
 */