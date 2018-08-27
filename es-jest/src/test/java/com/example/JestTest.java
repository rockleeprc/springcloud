package com.example;

import com.example.pojo.User;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class JestTest {
    private static JestClient client;

    private String url = "http://47.106.214.111:9200";
//    private String url = "http://192.168.56.11:9200";

    /**
     * 创建JestClient
     */
    @Before
    public void before() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(url)
                .multiThreaded(true)
//                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create())
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


    @Test
    public void deleteCriter() throws IOException {
        List<Field> criterias = new ArrayList<Field>();
        criterias.add(new Field("id", 1));
        criterias.add(new Field("id", 2));
        String cri = buildSearch(criterias).toString();
        System.out.println(cri);
        JestResult result = client.execute(new DeleteByQuery.Builder(cri)
                .addIndex("test")
                .addType("user")
                .build());
        System.out.println(result.getJsonObject().get("deleted").getAsInt());
    }

    @Test
    public void search1() {
        List<Field> criterias = new ArrayList<Field>();
        criterias.add(new Field("id", "1"));
        SearchResult result = index("test", "user", "liyan", "name", criterias, client, 1, 2);
        List<User> list = result.getSourceAsObjectList(User.class, false);
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void search() throws IOException {
        List<Field> criterias = new ArrayList<Field>();
        criterias.add(new Field("name", "li*"));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (criterias != null && !criterias.isEmpty()) {
            //构建查询条件必须嵌入filter中！
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (Field c : criterias) {
                boolQueryBuilder.filter(QueryBuilders.termQuery(c.getFieldName(), c.getFieldValue()));
            }

            searchSourceBuilder.query(boolQueryBuilder);
        }

        SearchResult result = client.execute(new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("test")
                .addType("user")
                .build());
        List<User> list = result.getSourceAsObjectList(User.class, false);
        System.out.println(Arrays.toString(list.toArray()));


    }

    private SearchSourceBuilder buildSearch(List<Field> criterias) {
        //指定查询的库表
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        if (criterias != null && !criterias.isEmpty()) {
            //构建查询条件必须嵌入filter中！
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (Field c : criterias) {
                boolQueryBuilder.filter(QueryBuilders.termQuery(c.getFieldName(), c.getFieldValue()));
            }

            searchSourceBuilder.query(boolQueryBuilder);
        }
        return searchSourceBuilder;
    }


    @Test
    public void searchById() throws IOException {
        DocumentResult result = client.execute(new Get.Builder("test", "3")
                .type("user")
                .build());
        System.out.println(result.getSourceAsObject(User.class));
    }

    /**
     * 根据id删除
     *
     * @throws IOException
     */
    @Test
    public void delete() throws IOException {
        DocumentResult result = client.execute(new Delete.Builder(String.valueOf(1))
                .index("test")
                .type("user")
                .build());
        System.out.println(result.isSucceeded());
    }

    /**
     * doc更新
     * 第一次更新时会创建一条数据
     *
     * @throws IOException
     */
    @Test
    public void update() throws IOException {
        User u = new User(1, "zhangsan-update-upd", new Date());
        DocumentResult result = client.execute(new Update.Builder(u)
                .index("test")
                .type("user")
                .refresh(true)
                .build());
        System.out.println(result.isSucceeded());
    }

    /**
     * 创建document
     *
     * @throws IOException
     */
    @Test
    public void insert() throws IOException {
        User u = new User(2, "ligui", new Date());
        DocumentResult result = client.execute(new Index.Builder(u)
                .index("test")
                .type("user")
                .refresh(true)
                .build());
        System.out.println(result.isSucceeded());
    }

    @Test
    public void usingBuilder() throws IOException {
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards", 5);
        settingsBuilder.put("number_of_replicas", 1);

        JestResult jestResult = client.execute(new CreateIndex.Builder("articles").settings(settingsBuilder.build().getAsMap()).build());
        System.out.println(jestResult.isSucceeded());
    }

    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        String indexName = "article";
        CreateIndex index = new CreateIndex.Builder(indexName).build();
        JestResult jestResult = client.execute(index);
        System.out.println(jestResult.isSucceeded());
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        String indexName = "test";
        try {
            JestResult jestResult = client.execute(new DeleteIndex.Builder(indexName).build());
            System.out.println(jestResult.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasicUriGeneration() {
        PutMapping putMapping = new PutMapping.Builder("twitter", "tweet", "source").build();
        System.out.println(putMapping.getRestMethodName());
        System.out.println(putMapping.getURI());
    }

    /**
     * 带条件检索
     * 实现类似  select * from table where filed = queryString AND (... or条件字段 ...)
     *
     * @param indexName   索引名
     * @param typeName    类型名
     * @param queryString 查询内容
     * @param field       字段对象，包括查询字段及查询字段的值
     * @param fieldForOrs 查询条件 or区分
     * @param client
     * @param pageNumber  页码
     * @param pageSize    页数
     * @return
     */
    public SearchResult index(String indexName, String typeName, String queryString, String field, List<Field> fieldForOrs, JestClient client, int pageNumber, int pageSize) {
        //声明一个SearchSourceBuilder对象，构造检索请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //构造查询哪个字段
        if (StringUtils.isEmpty(field)) {
            //没有检索条件，则全字段查询
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.queryStringQuery(queryString));
        } else {
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.matchQuery(field, queryString));
        }

        BoolQueryBuilder innerQueryBuilder = QueryBuilders.boolQuery();
        for (Field fieldValue : fieldForOrs) {
            innerQueryBuilder = innerQueryBuilder.should(QueryBuilders.termQuery(fieldValue.getFieldName(), fieldValue.getFieldValue()));
        }
        boolQueryBuilder = boolQueryBuilder.filter(innerQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);

        //设置高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(field);
        highlightBuilder.preTags("<em>").postTags("</em>");
        highlightBuilder.fragmentSize(200);
        searchSourceBuilder.highlighter(highlightBuilder);

        //设置分页
        searchSourceBuilder.from((pageNumber - 1) * pageSize);
        searchSourceBuilder.size(pageSize);

        //构建Search对象
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(indexName)
                .addType(typeName)
                .build();

        SearchResult searchResult = null;
        try {
            searchResult = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    private static class Field {
        private String fieldName;
        private Object fieldValue;

        public Field(String fieldName, Object fieldValue) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Object getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(Object fieldValue) {
            this.fieldValue = fieldValue;
        }

    }

    @Test
    public void t() {
        System.out.println(client);
    }


}
