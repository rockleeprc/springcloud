
## nacos
访问地址：
http://node5:8848/nacos

dateId格式：
${prefix}-${spring.profile.active}.${file-extension}
prefix：spring.application.name、spring.cloud.nacos.config.prefix
file-exetension：spring.cloud.nacos.config.file-extension
TODO 修改profiles后dateId中spring.profile.active不起作用


curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=true"
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=false"


## sentinal
start命令
java -Dserver.port=8010 -Dcsp.sentinel.dashboard.server=node5:8010 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar &
java -Dserver.port=8010 -Dcsp.sentinel.dashboard.server=localhost:8010 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.6.3.jar &

访问地址：
http://node5:8010/


## other
获取启动的端口号 implements ApplicationListener<WebServerInitializedEvent>
