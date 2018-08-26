package com.example;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class JestTest {
    private static JestClient jestClient;
    private static String indexName = "article";
    private static String typeName = "content";


    @Before
    public void before() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://192.168.56.11:9200")
                .multiThreaded(true)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create())
                .connTimeout(1500)
                .readTimeout(3000)
                .multiThreaded(true)
                .build());
        jestClient = factory.getObject();
    }

    @After
    public void after() {
        if (jestClient != null) {
            try {
                jestClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        CreateIndex index = new CreateIndex.Builder(indexName).build();
        JestResult jestResult = jestClient.execute(index);
        System.out.println(jestResult.isSucceeded());
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        try {
            JestResult jestResult = jestClient.execute(new DeleteIndex.Builder(indexName).build());
            System.out.println(jestResult.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void t(){
        System.out.println(jestClient);
    }


}
