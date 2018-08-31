package com.example;

import com.example.pojo.Content;
import com.example.pojo.User;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Exmaple {

    private static JestClient client;

    private String url = "http://47.106.214.111:9200";
    private String indexName = "test";
    private String typeName = "user";


    /**
     * 查看集群健康信息
     * @throws Exception
     */
    @Test
    public  void health() throws Exception {
        Health health = new Health.Builder().build();
        JestResult result = client.execute(health);
        System.out.println(result.getJsonString());
    }


    @Test
    public void t2() throws IOException {
        //指定查询的库表
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //构建查询条件必须嵌入filter中！
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.filter(QueryBuilders.termQuery("name", "是否"));
        boolQueryBuilder.filter(QueryBuilders.termQuery("status", "Y"));
        searchSourceBuilder.query(boolQueryBuilder);

        SearchResult result = client.execute(new Search.Builder(searchSourceBuilder.toString())
                .addIndex("test")
                .addType("user")
                .build());
        List<User> list = result.getSourceAsObjectList(User.class, false);
        System.out.println(list.size());
        for (User u : list) {
            System.out.println(u);
        }
    }

    public SearchResult search(String indexName, String typeName, String query) throws Exception {
        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        return client.execute(search);
    }


    @Test
    public void t() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("是否"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");//高亮field
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(200);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("test").addType("user").build();
        SearchResult result = null;
        List<SearchResult.Hit<User, Void>> hits = null;
        try {
            result = client.execute(search);
            System.out.println("本次查询共查到：" + result.getTotal() + "个结果！");

            hits = result.getHits(User.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user);

            Map<String, List<String>> highlight = hit.highlight;
            System.out.println(highlight);
        }
    }

    @Test
    public void highlightBuilder() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "liyan"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");//高亮title
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        SearchResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        for (SearchResult.Hit<User, Void> hit : hits) {
            User user = hit.source;
            //获取高亮后的内容
            Map<String, List<String>> highlight = hit.highlight;
            List<String> names = highlight.get("name");//高亮后的title
            if (names != null) {
                user.setName((names.get(0)));
            }
            System.out.println(user);
        }

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
    public void query() throws IOException {
        // 匹配单个字段
//        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "是否");

        // 多个字段匹配某一个值
//        MultiMatchQueryBuilder matchQuery = QueryBuilders.multiMatchQuery("如果", "name", "status");

        // ?匹配单个字符，*匹配多个字符，中文时只能匹配一个汉字
//        WildcardQueryBuilder matchQuery1 = QueryBuilders.wildcardQuery("name", "*需*");
//        WildcardQueryBuilder matchQuery2 = QueryBuilders.wildcardQuery("name", "*的*");
        //复合条件查询
//        BoolQueryBuilder matchQuery = QueryBuilders.boolQuery();
        // 多个must=and
//        matchQuery.must(matchQuery1);
//        matchQuery.must(matchQuery2);

        // 多个should=or
//        matchQuery.should(matchQuery1);
//        matchQuery.should(matchQuery2);

        // 匹配+条件过滤 name字段中包含“是否” and (id=2 or id=5的数据)
        BoolQueryBuilder matchQuery = QueryBuilders.boolQuery();
        MatchQueryBuilder matchField = QueryBuilders.matchQuery("b", "非洲");
        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        filterBuilder.should(QueryBuilders.termQuery("id", "2")).should(QueryBuilders.termQuery("id", "5"));
        matchQuery.must(matchField).filter(filterBuilder);


        SearchSourceBuilder query = new SearchSourceBuilder();
        query.query(matchQuery);

        Search search = new Search.Builder(query.toString()).addIndex(indexName).addType(typeName).build();
        SearchResult result = client.execute(search);
        List<User> list = result.getSourceAsObjectList(User.class, false);
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void matchAll() throws IOException {

        int pageNum = 1;
        int pageSize = 2;

        MatchAllQueryBuilder matchQuery = QueryBuilders.matchAllQuery();
        SearchSourceBuilder query = new SearchSourceBuilder();
        query.query(matchQuery);
        query.size(pageSize);
        query.from((pageNum - 1) * pageSize);

        Search search = new Search.Builder(query.toString()).addIndex(indexName).addType(typeName).build();
        SearchResult result = client.execute(search);
        List<User> users = result.getSourceAsObjectList(User.class, false);
        System.out.println(Arrays.toString(users.toArray()));


    }

    @Test
    public void queryContent() {
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("a", "是");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(matchQuery);


    }

    @Test
    public void queryString1() throws Exception {
        int pageNum = 1;
        int pageSize = 10;

        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
//        QueryBuilder b = QueryBuilders.queryStringQuery(QueryParser.escape("国人"));
        QueryBuilder a = QueryBuilders.termQuery("a","是");
        QueryBuilder b = QueryBuilders.matchQuery("b","上海人");
//        QueryBuilder c = QueryBuilders.matchQuery("d","天安门");
        QueryBuilder d = QueryBuilders.matchQuery("d","天安门");
        MatchQueryBuilder statusMatch = QueryBuilders.matchQuery("status", "X");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(a).must(b).should(d).must(statusMatch);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
//        searchSourceBuilder.from((pageNum - 1) * pageSize);
//        searchSourceBuilder.size(pageSize);
        String query = searchSourceBuilder.toString();
        System.out.println(searchSourceBuilder);

        Search search = new Search.Builder(query).addIndex("content")
                .addType("content").build();
        SearchResult result = client.execute(search);
        System.out.println(result.getTotal());
        System.out.println(result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1);

        List<SearchResult.Hit<Content, Void>> hits = result.getHits(Content.class);
        System.out.println("Size:" + hits.size());

        for (SearchResult.Hit<Content, Void> hit : hits) {
            Content content = hit.source;
            System.out.println(content.toString());
        }
    }

    @Test
    public void queryStringE() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(QueryParser.escape("国家"));
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);//(pageNumber - 1) * pageSize
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).addIndex("index")
                .addType("test1").build();
        SearchResult result = client.execute(search);

        String json = result.getJsonString();
        System.out.println(json);
    }

    @Test
    public void queryString() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(QueryParser.escape("教练"));
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);//(pageNumber - 1) * pageSize
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query).addIndex("index")
                .addType("test1").build();
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
        SearchResult result = client.execute(search);

        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
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
        QueryBuilder queryBuilder = QueryBuilders.prefixQuery("name", "是否");
        searchSourceBuilder.query(queryBuilder).size(10).from(0);
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
        QueryBuilder queryBuilder = QueryBuilders.termQuery("status", "Y");//单值完全匹配查询
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
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("status", "Y*");
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
    public void putIndexMapping1() throws IOException {
        XContentBuilder mapping = null;
        mapping = XContentFactory.jsonBuilder()
                .startObject()
                //关闭TTL
                .startObject("_ttl")
                .field("enabled", false)
                .endObject()
                .startObject("properties")
                .startObject("id").field("type", "long").endObject()
                .startObject("title").field("type", "text")
                .field("store", "yes")
//                //指定index analyzer 为 ik
//                .field("analyzer", "ik_syno")
//                //指定search_analyzer 为ik_syno
//                .field("searchAnalyzer", "ik_syno")
                .endObject()
                .startObject("description").field("type", "text").field("index", "not_analyzed").endObject()
                .startObject("url").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("type").field("type", "integer").endObject()
                .endObject()
                .endObject();
        String source = mapping.string();
        System.out.println(source);
        PutMapping putMapping = new PutMapping.Builder("shop_v1", "goods", source).build();
        JestResult result = client.execute(putMapping);
        System.out.println(result.isSucceeded());
    }


    public XContentBuilder getMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    //关闭TTL
                    .startObject("_ttl")
                    .field("enabled", false)
                    .endObject()
                    .startObject("properties")
                    .startObject("id").field("type", "long").endObject()
                    .startObject("title")
                    .field("type", "string")
                    .field("store", "yes")
                    //指定index analyzer 为 ik
                    .field("analyzer", "ik")
                    //指定search_analyzer 为ik_syno
                    .field("searchAnalyzer", "ik_syno")
                    .endObject()
                    .startObject("description").field("type", "string").field("index", "not_analyzed").endObject()
                    .startObject("url").field("type", "string").field("index", "not_analyzed").endObject()
                    .startObject("type").field("type", "integer").endObject()
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }

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
        CreateIndex index = new CreateIndex.Builder("shop_v1").build();
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
