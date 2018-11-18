
### 配置类
* RibbonEurekaAutoConfiguration：ribbon自动配置类

### 负载均衡器
* ILoadBalancer：负载均衡接口
    * AbstractLoadBalancer：实现ILoadBalancer抽象类
        * BaseLoadBalancer：基础实现类
            * DynamicServerListLoadBalancer：扩展BaseLoadBalancer，实现清单在运行期动态更新的能力，对清单进行过滤

### 负载均衡策略
* RandomRule：从服务实例清单中随机选择一个服务实例
* RoundRobinRule：线性轮询方式依次选择
* RetryRule：默认使用RoundRobinRule，但具备重试机制
* WeightedResponseTimeRule：对RoundRobinRule扩展，根据实例的运行情况计算权重，根据权重挑选实例