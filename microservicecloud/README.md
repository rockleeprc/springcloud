
## 端口
provider 9XXX
consumer 8xxx
eureka 70xx


## eureka
###  保护机制
自我保护机制的工作机制是如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，Eureka Server自动进入自我保护机制，此时会出现以下几种情况：

1、Eureka Server不再从注册列表中移除因为长时间没收到心跳而应该过期的服务。

2、Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上，保证当前节点依然可用。

3、当网络稳定时，当前Eureka Server新的注册信息会被同步到其它节点中。

因此Eureka Server可以很好的应对因网络故障导致部分节点失联的情况，而不会像ZK那样如果有一半不可用的情况会导致整个集群不可用而变成瘫痪。

# 打开关闭保护机制
eureka.server.enable-self-preservation
# 检查失效服务的时间
eviction-interval-timer-in-ms: 3000
 # 清理间隔（单位毫秒，默认是60*1000）
eureka.server.eviction-interval-timer-in-ms
# 续约更新时间间隔（默认30秒）
eureka.instance.lease-renewal-interval-in-seconds
# 续约到期时间（默认90秒）
eureka.instance.lease-expiration-duration-in-seconds

* 集群配置时不用配置
register-with-eureka: false
fetch-registry: false