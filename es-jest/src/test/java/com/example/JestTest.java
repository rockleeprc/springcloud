package com.example;

import com.example.pojo.Content;
import com.example.pojo.User;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class JestTest {
    private static JestClient client;

    private String url = "http://47.106.214.111:9200";
//    private String url = "http://192.168.56.11:9200";


    @Test
    public void date4() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2018-09-3 14:53:05");


        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDate oldDate = LocalDateTime.ofInstant(instant, zone).toLocalDate();

        LocalDate today = LocalDate.now();
        System.out.println("Today：" + today);

        System.out.println("OldDate：" + oldDate);

        Period p = Period.between(oldDate, today);
        System.out.printf("目标日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());

        Duration duration = java.time.Duration.between(LocalDateTime.ofInstant(instant, zone), LocalDateTime.now());

        System.out.println("hours: "+duration.toHours());
        System.out.println("hours: "+duration.toMinutes());

    }

    @Test
    public void date3() throws ParseException {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

    }

    @Test
    public void date2() {
        LocalDate today = LocalDate.now();
        System.out.println("Today：" + today);
        LocalDate oldDate = LocalDate.parse("2017-07-31 17:16:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("OldDate：" + oldDate);

        Period p = Period.between(oldDate, today);
        System.out.printf("目标日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());
    }


    @Test
    public void date1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2018-08-31 17:16:05");

        Calendar c1 = Calendar.getInstance();   //当前日期
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);   //设置为另一个时间

        //这里只是简单的对两个年份数字进行相减，而没有考虑月份的情况
        System.out.println("year：" + (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)));
        System.out.println("month：" + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH)));
        System.out.println("HOUR: " + (c1.get(Calendar.HOUR) - c2.get(Calendar.HOUR)));
    }

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
        criterias.add(new Field("status", "Y"));
        SearchResult result = index("test", "user", "是否", "name", criterias, client, 1, 2);
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
        User u = new User(1, "zhangsan-update-upd", new Date(), "Y");
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
        User u1 = new User(1, "是否记录上次执行结果, 如果为真,将会把上次执行到的", new Date(), "X");
        User u2 = new User(2, "是否需要记录某个column 的值", new Date(), "Y");
        User u3 = new User(3, "指定文件,来记录上次执行到的 tracking_column 字段的值", new Date(), "Y");
        User u4 = new User(4, "我们只需要在 SQL 语句中 WHERE MY_ID > :last_sql_value 即可", new Date(), "X");
        User u5 = new User(5, "是否清除 last_run_metadata_path 的记录", new Date(), "Y");
        User u6 = new User(6, "투자 유의사항: 높은 가격 변동성 때문에 디지털 자산에 대한 투자 손실이 발생할 수 있습니다. 투자하기 ", new Date(), "Y");
        User u7 = new User(7, "中国人民共和国国歌", new Date(), "Y");
        DocumentResult result1 = client.execute(new Index.Builder(u1).index("test").type("person").refresh(true).build());
        DocumentResult result2 = client.execute(new Index.Builder(u2).index("test").type("person").refresh(true).build());
        DocumentResult result3 = client.execute(new Index.Builder(u3).index("test").type("person").refresh(true).build());
        DocumentResult result4 = client.execute(new Index.Builder(u4).index("test").type("person").refresh(true).build());
        DocumentResult result5 = client.execute(new Index.Builder(u5).index("test").type("person").refresh(true).build());
        DocumentResult result6 = client.execute(new Index.Builder(u6).index("test").type("person").refresh(true).build());
        DocumentResult result7 = client.execute(new Index.Builder(u7).index("test").type("person").refresh(true).build());
//        System.out.println(result.isSucceeded());
    }

    @Test
    public void batchInsert() throws IOException {
        List<User> list = Arrays.asList(new User(1, "是否记录上次执行结果, 如果为真,将会把上次执行到的", new Date(), "X"),
                new User(2, "是否需要记录某个column 的值", new Date(), "Y"),
                new User(3, "指定文件,来记录上次执行到的 tracking_column 字段的值", new Date(), "Y"),
                new User(4, "我们只需要在 SQL 语句中 WHERE MY_ID > :last_sql_value 即可", new Date(), "X"),
                new User(5, "是否清除 last_run_metadata_path 的记录", new Date(), "Y"));

        Bulk.Builder bulkBuilder = new Bulk.Builder();
        //循环构造批量数据
        for (User u : list) {
            Index indexDoc = new Index.Builder(u).index("test").type("user").build();
            bulkBuilder.addAction(indexDoc);
        }
        BulkResult br = client.execute(bulkBuilder.build());
        System.out.println(br.isSucceeded());
    }

    @Test
    public void batchInsertContent() throws IOException {
        List<Content> list = Arrays.asList(
                new Content(1, "我是", "中国人", "关闭自动添加字段", "关闭后索引数据中如果有多余字段不会修改mapping,默认true", new Date(), "X"),
                new Content(2, "我是", "东北人", "禁用_source字段", "_source字段在生成索引过程中存储发送到elasticsear", new Date(), "X"),
                new Content(3, "我是", "上海人", "启用时间戳并设置", "时间戳记录文档索引时间", new Date(), "Y"),
                new Content(4, "我是", "上海人", "定义文档的生命周期,周期结束后文档会自动删除", "指定将name字段作为路由", new Date(), "X"),
                new Content(5, "我是", "非洲人", "且每个文档必须指定name字", "编入索引供搜索、no:不编入索引", new Date(), "Y"),
                new Content(6, "我是", "上海人", "启用时间戳并设置", "天安门", new Date(), "X")
        );


        Bulk.Builder bulkBuilder = new Bulk.Builder();
        //循环构造批量数据
        for (Content u : list) {
            Index indexDoc = new Index.Builder(u).index("content").type("content").build();
            bulkBuilder.addAction(indexDoc);
        }
        BulkResult br = client.execute(bulkBuilder.build());
        System.out.println(br.isSucceeded());
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
    public void deleteType() {

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
}
