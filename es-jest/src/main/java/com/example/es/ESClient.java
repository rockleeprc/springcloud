package com.example.es;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

public class ESClient {

    private static final String url = "http://47.106.214.111:9200";

    public static JestClient getClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(url)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
//                .defaultMaxTotalConnectionPerRoute(2)
//                .maxTotalConnection(2)
                .connTimeout(1500)
                .readTimeout(3000)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}
