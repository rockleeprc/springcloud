
dateId格式：
${prefix}-${spring.profile.active}.${file-extension}
prefix：spring.application.name、spring.cloud.nacos.config.prefix
file-exetension：spring.cloud.nacos.config.file-extension
TODO 修改profiles后dateId中spring.profile.active不起作用


curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=true"
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=false"
