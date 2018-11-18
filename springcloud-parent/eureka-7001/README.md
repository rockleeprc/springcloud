### pom
* spring-cloud-starter-eureka-server：eureka服务端
* spring-boot-actuator：导入后`/info`才能使用

### yml
* eureka.client.service-url.defaultZone：注册地址

### 注解
* @EnableEurekaServer：开启Eureka服务器端

### 配置类
* EurekaServerConfigBean：服务端配置
* EurekaClientConfigBean：客户端配置
* EurekaInstanceConfigBean：实例元数据配置（描述自身服务的数据信息）
    * InstanceInfo：包装EurekaInstanceConfigBean，发送给服务端