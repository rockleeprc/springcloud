package com.example.es;

import com.example.pojo.ArticleSearch;
import com.example.pojo.ArticleSearchVo;
import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.sort.Sort;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ESClient {

    public static Logger logger = LoggerFactory.getLogger(ESClient.class);

    private static final List<String> URLS = Arrays.asList("http://47.106.214.111:9200");
    private static final int DEL = 1;
    private static final int NOT_DEL = 2;
    private static final String indexName = "article";
    private static final String typeName = "article";

    public static JestClient getClient() {
        logger.info("ESClient Init Start...");
        try {
            JestClientFactory factory = new JestClientFactory();
            factory.setHttpClientConfig(new HttpClientConfig
                    .Builder(URLS)
                    .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
//                .defaultMaxTotalConnectionPerRoute(2)
//                .maxTotalConnection(2)
                    .connTimeout(3000)
                    .readTimeout(5000)
                    .multiThreaded(true)
                    .build());
            logger.info(String.format("ESClient init success,ip:[%s]", URLS));
            return factory.getObject();
        } catch (Exception e) {
            logger.error("ESClient init fail", e);
        }
        return null;
    }

    /**
     * 标题/内容检索
     *
     * @param keyword    检索关键字
     * @param nationCode 国家代码
     * @param domain   站点名称
     * @param pageNum    页码
     * @param pageSize   页记录数
     * @return
     * @throws IOException
     */
    public static ArticleSearchVo search(String keyword, String nationCode, String domain, int pageNum, int pageSize) throws IOException {
        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(nationCode) || StringUtils.isEmpty(domain)) {
            return new ArticleSearchVo();
        }
        pageNum = pageNum <= 0 ? 1 : pageNum;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        // 检索条件，文章标题 or 文章内容
        QueryBuilder name = QueryBuilders.matchQuery("articleNationName", QueryParser.escape(keyword));
        QueryBuilder content = QueryBuilders.matchQuery("articleNationContent", QueryParser.escape(keyword));
        BoolQueryBuilder searchKeyWord = QueryBuilders.boolQuery();
        searchKeyWord.should(name).should(content);
        //MultiMatchQueryBuilder searchKeyWord = QueryBuilders.multiMatchQuery(keyword, "articleNationName", "articleNationContent");

        // 过滤条件，文章删除状态 and 站点 and 国家检索
        QueryBuilder articleNationDel = QueryBuilders.termQuery("articleNationDel", NOT_DEL);
        QueryBuilder site = QueryBuilders.termQuery("domain", domain);
        QueryBuilder nation = QueryBuilders.termQuery("nationCode", nationCode);
        BoolQueryBuilder filter = QueryBuilders.boolQuery();
        filter.must(articleNationDel).must(site).must(nation);

        // 检索数据后过滤
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(searchKeyWord).filter(filter);

        //高亮字段
        /*
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.preTags("<em>");
        hBuilder.postTags("</em>");
        hBuilder.field("articleNationName").field("articleNationContent");
        */

        // 查询设置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        searchSourceBuilder.from((pageNum - 1) * pageSize);
        searchSourceBuilder.size(pageSize);
        //searchSourceBuilder.highlighter(hBuilder);
        String query = searchSourceBuilder.toString();

        // 排序
        Sort sort = new Sort("articleNationId");

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).addSort(sort).build();
        JestClient client = ESClient.getClient();
        SearchResult result;
        try {
            result = client.execute(search);
        } finally {
            if (client != null) {
                client.close();
            }
        }

        List<ArticleSearch> articles = new ArrayList<>();
        List<SearchResult.Hit<ArticleSearch, Void>> hits = result.getHits(ArticleSearch.class);
        for (SearchResult.Hit<ArticleSearch, Void> hit : hits) {
            ArticleSearch article = hit.source;
            articles.add(article);
        }
        ArticleSearchVo vo = new ArticleSearchVo();
        vo.setArticles(articles);
        vo.setTotal(result.getTotal());

        return vo;



        /*
        List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
        for (SearchResult.Hit<Article, Void> hit : hits) {
            Article article = hit.source;
            Map<String, List<String>> highlight = hit.highlight;
            Set<Map.Entry<String, List<String>>> entries = highlight.entrySet();
            for (Iterator<Map.Entry<String, List<String>>> iterator = entries.iterator(); iterator.hasNext(); ) {
                Map.Entry<String, List<String>> entry = iterator.next();
                String key = entry.getKey();
                if ("articleNationName".equals(key)) {
                    article.setArticleNationName(entry.getValue().get(0));
                }
                if ("articleNationContent".equals(key)) {
                    article.setArticleNationContent(entry.getValue().get(0));
                }
            }
            System.out.println(hit.source);
        }
        */

        //System.out.println("total page:" + (result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1));
//        return result.getSourceAsObjectList(ArticleSearch.class, false);
    }
}
