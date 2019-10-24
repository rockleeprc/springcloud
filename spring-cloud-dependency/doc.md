
## maven版本管理
```shell
# 修改parent 版本号
mvn versions:set -DnewVersion=0.0.1-SNAPSHOT
# 更新子module版本号
mvn versions:update-child-modules
# 提交
mvn versions:commit
# 撤销
mvn versions:revert
```

## nacos
访问地址：
`http://node5:8848/nacos`

dateId格式：
`${prefix}-${spring.profile.active}.${file-extension}`
`prefix：spring.application.name、spring.cloud.nacos.config.prefix`
`file-exetension：spring.cloud.nacos.config.file-extension`
TODO 修改profiles后dateId中spring.profile.active不起作用

```shell
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=true"
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=false"
```

## sentinal
```shell
# start命令
java -Dserver.port=8010 -Dcsp.sentinel.dashboard.server=node5:8010 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar &
java -Dserver.port=8010 -Dcsp.sentinel.dashboard.server=localhost:8010 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar &
```
访问地址：
`http://node5:8010/`

## gateway
显示路由信息
`http://node5:9110/actuator/gateway/routes`
无效配置
lowerCaseServiceId: false

## other
获取启动的端口号 `implements ApplicationListener<WebServerInitializedEvent>`
