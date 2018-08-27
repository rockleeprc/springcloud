package com.example;

import com.example.pojo.User;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Exmaple {

    private static JestClient client;

    private String url = "http://47.106.214.111:9200";
    private String indexName = "test";
    private String typeName = "user";

    public SearchResult search(String indexName, String typeName, String query) throws Exception {
        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        return client.execute(search);
    }

    @Test
    public void get() throws IOException {
        Get get = new Get.Builder(indexName, "1").type(typeName).build();
        DocumentResult result = client.execute(get);
        System.out.println(result.isSucceeded());
        User user = result.getSourceAsObject(User.class);
        System.out.println(user);
    }

    @Test
    public void queryString() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(QueryParser.escape("li"));
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).build();
        JestResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = ((SearchResult) result).getHits(User.class);
        System.out.println("Size:" + hits.size());

        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }


    /**
     * 范围查询
     *
     * @throws IOException
     */
    @Test
    public void rangeQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("birth")
                .gte("2018-08-27 11:52:17")
                .lte("2018-08-27 13:13:52")
                .includeLower(true)
                .includeUpper(true);//区间查询;
        searchSourceBuilder.query(queryBuilder).from(0).size(10);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).build();
        JestResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = ((SearchResult) result).getHits(User.class);
        System.out.println(hits.size());
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user);
        }
    }

    /**
     * 前缀查询
     *
     * @throws IOException
     */
    @Test
    public void prefixQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.prefixQuery("name", "zh");
        searchSourceBuilder.query(queryBuilder).size(10).from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).build();
        SearchResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println(hits.size());
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user);
        }

    }

    /**
     * 查询field，多值完全匹配
     *
     * @throws IOException
     */
    @Test
    public void termsQuery() throws IOException {
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.termsQuery("name", new String[]{"liyan", "ligui"});//多值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        SearchResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println(hits.size());

        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user);
        }

    }

    /**
     * 查询field，单值完全匹配
     *
     * @throws IOException
     */
    @Test
    public void termQuery() throws IOException {
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.termQuery("name", "liyan");//单值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        //执行查询
        Search search = new Search.Builder(query).addIndex("test")
                .addType("user").build();
        SearchResult result = client.execute(search);

        //查询结果
        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }

    }

    @Test
    public void wildcardQuery() throws Exception {
        //构建查询
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "li*");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(5);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        //执行查询
        Search search = new Search.Builder(query).addIndex("test")
                .addType("user").build();
        SearchResult result = client.execute(search);

        //查询结果
        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    /**
     * 获取映射
     *
     * @throws IOException
     */
    @Test
    public void getIndexMapping() throws IOException {
        GetMapping mapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult result = client.execute(mapping);
        System.out.println(result.getJsonString());
    }

    /**
     * 未找到失败原因
     * Root mapping definition has unsupported parameters
     *
     * @throws IOException
     */
    @Test
    public void putIndexMapping() throws IOException {
        String source = "{\"aaa\":{\"mappings\":{\"user\":{\"properties\":{\"birth\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"id\":{\"type\":\"long\"},\"name\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}}}}}";
        PutMapping putMapping = new PutMapping.Builder("aaa", "user", source).build();
        JestResult result = client.execute(putMapping);
        System.out.println(result.isSucceeded());
    }

    /**
     * 创建索引，索引名称必须小写
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndex index = new CreateIndex.Builder("aaa").build();
        JestResult result = client.execute(index);
        System.out.println(result.isSucceeded());
    }

    @Test
    public void buildJson() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying  out  Elasticsearch")
                .endObject();
        System.out.println(builder.string());
    }


    @Before
    public void before() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(url)
                .multiThreaded(true)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
                .defaultMaxTotalConnectionPerRoute(2)
                .maxTotalConnection(2)
                .connTimeout(1500)
                .readTimeout(3000)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
    }

    @After
    public void after() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
