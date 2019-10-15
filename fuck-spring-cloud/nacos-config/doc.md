
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=true"

curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-config.properties&group=DEFAULT_GROUP&content=useLocalCache=false"
