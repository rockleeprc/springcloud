package com.example;

import com.example.es.ESClient;
import com.example.pojo.Article;
import com.example.pojo.Content;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ArticleTest {

    private static final int DEL = 1;
    private static final int NOT_DEL = 2;

    private static final String indexName = "art";
    private static final String typeName = "art";

    @Test
    public void testSearch() throws IOException {
        List<Article> list = search("内容", "zh", "站点名称6", 1, 10);
        for (Article article : list) {
            System.out.println(article);
        }
    }

    public List<Article> search(String keyword, String nationCode, String siteName, int pageNum, int pageSize) throws IOException {
        QueryBuilder name = QueryBuilders.termQuery("articleNationName", QueryParser.escape(keyword));
        QueryBuilder content = QueryBuilders.termQuery("articleNationContent", QueryParser.escape(keyword));

        BoolQueryBuilder searchKeyWord = QueryBuilders.boolQuery();
        searchKeyWord.should(name).should(content);

        QueryBuilder articleNationDel = QueryBuilders.matchQuery("articleNationDel", NOT_DEL);
        QueryBuilder site = QueryBuilders.matchQuery("siteName", siteName);
        QueryBuilder nation = QueryBuilders.matchQuery("nationCode", nationCode);

        BoolQueryBuilder filter = QueryBuilders.boolQuery();
        filter.must(articleNationDel).must(site).must(nation);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(searchKeyWord).filter(filter);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        searchSourceBuilder.from((pageNum - 1) * pageSize);
        searchSourceBuilder.size(pageSize);
        String query = searchSourceBuilder.toString();

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        SearchResult result = ESClient.getClient().execute(search);
        System.out.println("total page:" + (result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1));

        return result.getSourceAsObjectList(Article.class, false);
    }

    @Test
    public void queryString() throws Exception {
        int pageNum = 1;
        int pageSize = 10;

        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
//        QueryBuilder b = QueryBuilders.queryStringQuery(QueryParser.escape("国人"));
        QueryBuilder articleName = QueryBuilders.termQuery("articleNationName", QueryParser.escape("名称"));
        QueryBuilder categoryDel = QueryBuilders.matchQuery("categoryDel", NOT_DEL);
        QueryBuilder sectionsDel = QueryBuilders.matchQuery("sectionsDel", NOT_DEL);
//        QueryBuilder c = QueryBuilders.matchQuery("d","天安门");
//        QueryBuilder d = QueryBuilders.matchQuery("d", "天安门");
//        MatchQueryBuilder statusMatch = QueryBuilders.matchQuery("status", "X");
        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        filterBuilder.should(categoryDel).should(sectionsDel);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(articleName).filter(filterBuilder);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
//        searchSourceBuilder.from((pageNum - 1) * pageSize);
//        searchSourceBuilder.size(pageSize);
        String query = searchSourceBuilder.toString();
        System.out.println(searchSourceBuilder);

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        SearchResult result = ESClient.getClient().execute(search);
        System.out.println(result.getTotal());
        System.out.println(result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1);

        List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
        System.out.println("Size:" + hits.size());

        for (SearchResult.Hit<Article, Void> hit : hits) {
            Article article = hit.source;
            System.out.println(article.toString());
        }
    }


    @Test
    public void batchInsert() throws IOException {
        AtomicInteger id = new AtomicInteger(1);
        AtomicInteger artileId = new AtomicInteger(1);
        List<Article> articles = Arrays.asList(
                new Article(id.incrementAndGet(), DEL, new Date(), "名称1", "内容1", artileId.incrementAndGet(), 1, "zh", 1, "站点名称1", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new Article(id.incrementAndGet(), NOT_DEL, new Date(), "名称2", "内容2", artileId.incrementAndGet(), 1, "zh", 1, "站点名称2", 1, "分类名称", NOT_DEL, 1, "分区名称", NOT_DEL),
                new Article(id.incrementAndGet(), DEL, new Date(), "名称3", "内容3", artileId.incrementAndGet(), 1, "zh", 1, "站点名称3", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new Article(id.incrementAndGet(), DEL, new Date(), "名称4", "内容4", artileId.incrementAndGet(), 1, "zh", 1, "站点名称4", 1, "分类名称", DEL, 1, "分区名称", NOT_DEL),
                new Article(id.incrementAndGet(), DEL, new Date(), "名称5", "内容5", artileId.incrementAndGet(), 1, "zh", 1, "站点名称5", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new Article(id.incrementAndGet(), NOT_DEL, new Date(), "名称6", "内容6", artileId.incrementAndGet(), 1, "zh", 1, "站点名称6", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new Article(id.incrementAndGet(), DEL, new Date(), "名称7", "内容7", artileId.incrementAndGet(), 1, "zh", 1, "站点名称7", 1, "分类名称", NOT_DEL, 1, "分区名称", DEL)

        );


        Bulk.Builder bulkBuilder = new Bulk.Builder();
        for (Article article : articles) {
            Index index = new Index.Builder(article).index(indexName).type(typeName).build();
            bulkBuilder.addAction(index);
        }
        JestClient client = ESClient.getClient();
        BulkResult result = client.execute(bulkBuilder.build());
        System.out.println(result.isSucceeded());

    }


}
