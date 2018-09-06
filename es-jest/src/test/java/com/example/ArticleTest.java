package com.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.es.ESClient;
import com.example.pojo.ArticleSearch;
import com.example.pojo.ArticleSearchVo;
import com.example.pojo.Content;
import com.example.pojo.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import io.searchbox.core.search.sort.Sort;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ArticleTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleTest.class);

    private static final int DEL = 1;
    private static final int NOT_DEL = 2;

    private static final String indexName = "article";
    private static final String typeName = "article";


    @Test
    public void t() throws IOException {

        //        List<Article> list = ESClient.search("中国", "zh", "sohu", 10, 10);
//        List<Article> list = ESClient.search("同意", "zh", "sohu", 10, 10);
//        List<Article> list = ESClient.search("元首", "zh", "sohu", 10, 10);
//        List<Article> list = ESClient.search("习近平", "zh", "sohu", 10, 10);
//        List<Article> list = ESClient.search("周恩来", "zh", "sohu", 10, 10);
        ArticleSearchVo vo = ESClient.search("B", "cn", "http://127.0.0.1:8080", 1, 10);
//        List<ArticleSearch> list = search("彩虹", "cn", "http://127.0.0.1:8080", 1, 10);
        System.out.println(JSONObject.toJSON(vo));
    }

    @Test
    public void searchPresure() throws IOException, InterruptedException {


        String[] criterias = new String[]{"中国", "同意", "元首", "习近平", "周恩来", "中非合作论坛", "共产党宣言", "小伙子"};
        Random random = new Random(System.currentTimeMillis());
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        int loop = 200;
        CountDownLatch latch = new CountDownLatch(loop);

        for (int i = 0; i < loop; i++) {
            latch.countDown();
            threadPool.submit(new Task(i, criterias[random.nextInt(criterias.length)], "jp", "baidu", 1, 20));
        }
        latch.await();
        System.out.println("finish");
        System.in.read();

    }

    private class Task implements Runnable {

        private int index;
        private String keyword;
        private String nationCode;
        private String siteName;
        private int pageNum;
        private int pageSize;

        public Task(int index, String keyword, String nationCode, String siteName, int pageNum, int pageSize) {
            this.index = index;
            this.keyword = keyword;
            this.nationCode = nationCode;
            this.siteName = siteName;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }

        public void run() {
            long start = System.currentTimeMillis();
            ArticleSearchVo vo  = null;
            try {
                vo = ESClient.search(keyword, nationCode, siteName, pageNum, pageSize);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                long cast = (System.currentTimeMillis() - start) / 1000;
//                System.err.println("index=" + index + " cast=" + cast + " list.size=" + (list == null ? "time out" : list.size()));
            }
        }
    }


    @Test
    public void testSearch() throws IOException {
        long start = System.currentTimeMillis();
        List<ArticleSearch> list = search("中国", "zh", "sohu", 10, 10);
        System.out.println((System.currentTimeMillis() - start));
        System.out.println((System.currentTimeMillis() - start) / 1000);
        System.out.println(list.size());
//        for (Article article : list) {
//            System.out.println(article);
//        }
    }

    @Test
    public void testSearchDoamin() throws IOException {
        String domain = "http://127.0.0.1:8080";
        QueryBuilder site = QueryBuilders.termQuery("domain", domain);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(site);
        String query = searchSourceBuilder.toString();

        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        JestClient client = ESClient.getClient();
        SearchResult result;
        result = client.execute(search);
        System.out.println(result.getTotal());

        List<ArticleSearch> list = result.getSourceAsObjectList(ArticleSearch.class, false);

        System.out.println(Arrays.toString(list.toArray()));
    }


    /**
     * 标题/内容检索
     *
     * @param keyword    检索关键字
     * @param nationCode 国家代码
     * @param domain     站点名称
     * @param pageNum    页码
     * @param pageSize   页记录数
     * @return
     * @throws IOException
     */
    public static List<ArticleSearch> search(String keyword, String nationCode, String domain, int pageNum, int pageSize) throws IOException {
        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(nationCode) || StringUtils.isEmpty(domain)) {
            return Lists.newArrayList();
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
        filter.must(articleNationDel).must(nation).must(site);

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
        System.out.println(result.getTotal());
        System.out.println("total page:" + (result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1));
        return result.getSourceAsObjectList(ArticleSearch.class, false);

    }


    @Test
    public void queryString() throws Exception {
        int pageNum = 1;
        int pageSize = 10;

        //文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
//        QueryBuilder b = QueryBuilders.queryStringQuery(QueryParser.escape("国人"));
        QueryBuilder articleName = QueryBuilders.termQuery("articleNationName", QueryParser.escape("名称"));
//        QueryBuilder categoryDel = QueryBuilders.matchQuery("categoryDel", NOT_DEL);
        QueryBuilder sectionsDel = QueryBuilders.matchQuery("sectionsDel", NOT_DEL);
        QueryBuilder c = QueryBuilders.matchQuery("d", "天安门");
//        QueryBuilder d = QueryBuilders.matchQuery("d", "天安门");
//        MatchQueryBuilder statusMatch = QueryBuilders.matchQuery("status", "X");
        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        filterBuilder.should(articleName);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(articleName).filter(filterBuilder);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
//        searchSourceBuilder.from((pageNum - 1) * pageSize);
//        searchSourceBuilder.size(pageSize);
        String query = searchSourceBuilder.toString();
        System.out.println(searchSourceBuilder);

        Sort sort = new Sort("articleNationId");
        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).addSort(sort).build();
        SearchResult result = ESClient.getClient().execute(search);
        System.out.println(result.getTotal());
        System.out.println(result.getTotal() % pageSize == 0 ? result.getTotal() / pageSize : result.getTotal() / pageSize + 1);

        List<SearchResult.Hit<ArticleSearch, Void>> hits = result.getHits(ArticleSearch.class);
        System.out.println("Size:" + hits.size());

        for (SearchResult.Hit<ArticleSearch, Void> hit : hits) {
            ArticleSearch article = hit.source;
            System.out.println(article.toString());
        }
    }

    @Test
    public void largeInsert() throws IOException {
        logger.info("init articles start...");

        AtomicInteger id = new AtomicInteger(0);
        AtomicInteger otherId = new AtomicInteger(0);
        AtomicInteger artileId = new AtomicInteger(0);
        String[] names = new String[]{"习近平同科特迪瓦总统瓦塔拉举行会谈", "两国元首一致同意", "推动中科关系迈向更高水平"};
        String[] contents = contents();
        String[] nations = new String[]{"zh", "jp", "ka", "usa"};
        String[] sites = new String[]{"baidu", "sohu", "sina", "alibaba"};
        String[] categories = new String[]{"动作", "文艺", "言情", "恐怖"};
        String[] sections = new String[]{"头版", "八卦", "娱乐", "要闻"};
        int[] dels = new int[]{DEL, NOT_DEL};

        Random random = new Random(System.currentTimeMillis());
        List<ArticleSearch> articles = Lists.newArrayList();
        int loop = 10000 * 100;
        for (int i = 0; i <= loop; i++) {
            ArticleSearch article = new ArticleSearch(id.incrementAndGet(),
                    dels[random.nextInt(dels.length)],
                    new Date(),
                    names[random.nextInt(names.length)],
                    contents[random.nextInt(contents.length)],
                    artileId.incrementAndGet(),
                    artileId.incrementAndGet(),
                    nations[random.nextInt(nations.length)],
                    artileId.incrementAndGet(),
                    sites[random.nextInt(sites.length)],
                    artileId.incrementAndGet(),
                    categories[random.nextInt(categories.length)],
                    dels[random.nextInt(dels.length)],
                    artileId.incrementAndGet(),
                    sections[random.nextInt(sections.length)],
                    dels[random.nextInt(dels.length)]);
            articles.add(article);
        }
        logger.info("init articles finish");

        System.out.println(articles.size());

        Bulk.Builder bulkBuilder = new Bulk.Builder();
        for (int i = 0; i < articles.size(); i++) {
            ArticleSearch article = articles.get(i);
            Index index = new Index.Builder(article).index(indexName).type(typeName).build();
            bulkBuilder.addAction(index);
            if (i == 0)
                continue;

            if (i % 20000 == 0 || i == articles.size()) {
                JestClient client = ESClient.getClient();
                BulkResult result = client.execute(bulkBuilder.build());
                bulkBuilder = new Bulk.Builder();
                logger.info("articles save index " + i);
                System.out.println(result.isSucceeded());
            }
        }


        logger.info("save articles finish");
    }

    private String[] contents() {

        String article1 = "习近平欢迎瓦塔拉来华出席中非合作论坛北京峰会并对中国进行国事访问。习近平指出，这次论坛峰会致力于构建更加紧密的中非命运共同体，对推动中非全面战略合作伙伴关系深入发展、加强发展中国家团结合作具有重要意义。\n" +
                "\n" +
                "　　习近平指出，中科建交35年来，两国各领域合作不断扩大，成效显著。中方赞赏科方坚定奉行一个中国政策，坚定支持科方维护主权、安全、发展权益，支持科特迪瓦走适合本国国情的发展道路。双方要在涉及彼此核心利益和重大关切问题上更加坚定地相互支持。经贸合作是中科关系的“推进器”，要以共建“一带一路”为契机，加强发展战略对接，促进两国高质量、可持续的共同发展。要有效应对安全挑战，为促进西非地区和非洲大陆和平稳定发挥建设性作用。要在联合国安理会加强协调，共同维护包括非洲国家在内广大发展中国家的合法权益。\n" +
                "\n" +
                "　　瓦塔拉表示，中国是科特迪瓦伟大的朋友，科特迪瓦人民珍视同中国人民的传统友谊。科特迪瓦钦佩并祝贺中国的改革和发展成就，视中国为成功的榜样。科特迪瓦完全支持习近平主席提出的“一带一路”重要倡议，愿积极参与、并愿推动西非经济货币联盟国家共同参与共建“一带一路”合作。科方高度评价中方在多边事务中努力维护发展中国家权益，感谢中方一直支持非洲，积极参与联合国在非洲的维和行动。我相信，中非合作论坛北京峰会一定会取得成功，成为中非合作的一次历史性盛会。\n" +
                "\n" +
                "　　会谈后，两国元首还共同见证了有关双边合作文件的签署。\n" +
                "\n" +
                "　　会谈前，习近平在人民大会堂北大厅为瓦塔拉举行欢迎仪式。彭丽媛、丁薛祥、杨洁篪、王东明、王毅、张庆黎、何立峰等参加。\n";
        String article2 = "吃粽子蘸墨汁？这样的奇怪搭配是什么样的味道？习近平告诉你答案：\n" +
                "\n" +
                "　　一天，一个小伙子在家里奋笔疾书，妈妈在外面喊着说：“你吃粽子要加红糖水，吃了吗？”他说：“吃了吃了，甜极了。”老太太进门一看，这个小伙子埋头写书，嘴上全是黑墨水。结果吃错了，他旁边一碗红糖水没喝，却把那个墨水给喝了，而他浑然不觉，还说，“可甜了可甜了”。这人是谁呢，就是陈望道，他当时在浙江义乌的家里，就是写这本书。于是由此就说了一句话：真理的味道非常甜。\n" +
                "\n" +
                "　　这就是陈望道翻译《共产党宣言》的故事，习近平多次提及。\n" +
                "\n" +
                "　　1920年8月，由陈望道翻译的第一本中文版《共产党宣言》在上海正式问世。\n" +
                "\n" +
                "　　于中国而言，这本书意义非凡。如闪电，如路标，如烛光，为黑暗中探索的先驱们带来了温暖与希望……\n" +
                "\n" +
                "　　《共产党宣言》直接催生了中国共产党的成立，滋养了一代又一代中国共产党人。\n" +
                "\n" +
                "　　毛泽东同志曾说过，“《共产党宣言》，我看了不下100遍”，是《共产党宣言》等经典著作建立起自己对马克思主义的信仰。邓小平同志也说过：“我的入门老师是《共产党宣言》和《共产主义ABC》。”江泽民同志、胡锦涛同志都十分重视对《共产党宣言》等马克思主义经典著作的学习。\n" +
                "\n" +
                "　　习近平总书记多次强调，党员领导干部要原原本本地阅读马克思主义原著，把马克思主义作为看家本领，特别要求领导干部精读《马克思恩格斯文集》《列宁专题文集》中的代表性篇目，其中首篇就是《共产党宣言》。\n" +
                "\n" +
                "　　墨汁的黑，如同近现代中国那段黑暗历史，底色沉沉，路在何方？真理的甜，赋予了一代代共产党人勇气和力量，劈开黑色世界，让光明洒满大地，让中国从苦难走向辉煌。\n" +
                "\n" +
                "　　前赴后继，开天辟地，“马克思主义不仅深刻改变了世界，也深刻改变了中国”。";
        String article3 = "在山东东营市博物馆，有一本书被列为国家一级文物，这就是陈望道翻译的《共产党宣言》中文首译本中的一本。这本书系平装本，长18厘米，宽12厘米，书面印有水红色马克思半身像，书名从右至左排列，只是“共产党宣言”错排成“共党产宣言”。\n" +
                "\n" +
                "　　这样一本书，如何能历经战火，穿越时空，得以保存？这背后是一个个值得铭记的名字。\n" +
                "\n" +
                "　　1926年正月，共产党员刘雨辉回到家乡山东广饶县刘集村省亲，临别前将一本《共产党宣言》首译本赠与族中的共产党员刘良才。她神情凝重地说：“党员都应该学一学，它会让我们明白革命的目的，知道今后走的路。”\n" +
                "\n" +
                "　　大革命失败，“白色恐怖”笼罩中国。国民党罗列的“禁书名单”中，《共产党宣言》名列榜首。刘良才冒着生命危险，在住宅墙角外挖了一个隐蔽地窖，把书藏起来，躲过敌人无数次搜查。\n" +
                "\n" +
                "　　1931年，刘良才赴潍县任县委书记。临行前，他将《共产党宣言》首译本托付给刘集村党支部委员刘考文。\n" +
                "\n" +
                "　　1932年8月，博兴暴动失败，广饶县党组织遭到严重破坏。刘考文意识到，自己随时都可能被捕。为了保护这本珍贵的书籍，他将之郑重交到了为人忠厚低调的共产党员刘世厚手中。\n" +
                "\n" +
                "　　没过多久，刘考文被捕。1933年，刘良才也在潍县英勇就义。\n" +
                "\n" +
                "　　血雨腥风中，这本书，曾藏在炕洞中、躲在粮囤下、掖在墙眼里。刘世厚想尽办法，妥善保存着它。\n" +
                "\n" +
                "　　即便是1945年1月，暴虐的日寇一次性烧毁刘集村500余间房屋时，它也得以幸免。已逃出村的刘世厚冒着危险，返回村里，将塞在屋山墙中的首译本安全带出，使之免于被焚毁。";
        String article4 = "1975年1月，第四届全国人大召开期间，已重病在身的周恩来见到时任复旦大学校长的陈望道，紧握着他的手问道：“《共产党宣言》最早的译本找到没有？那是马列老祖宗在我们中国的第一本经典著作，找不到它，是中国共产党人的心病啊！”陈望道看着总理期待的目光，遗憾地摇了摇头。\n" +
                "\n" +
                "　　1975年5月，刘世厚捐出了珍藏40多年的《共产党宣言》首译本。今天，我们有幸看到了这份珍贵的文献。\n" +
                "\n" +
                "　　这样一份幸运的背后，是血与火，是真理的力量！\n" +
                "\n" +
                "　　经世流年，精神长青。";
        String article5 = "习近平在中央政治局4月23日的第五次集体学习中，以6个“深刻阐述”和3个“经典著作”，肯定了《共产党宣言》的经典地位。他强调，“《共产党宣言》是一个内容丰富的理论宝库，值得我们反复学习、深入研究，不断从中汲取思想营养”。\n" +
                "\n" +
                "　　作为“《共产党宣言》精神的忠实传人”的中国共产党，在中国革命、建设和改革中，把马克思主义基本原理和中国实际相结合，书写了辉煌的篇章。新时代，我国社会主要矛盾发生变化、国际环境发生重大改变，以习近平同志为核心的党中央紧密结合新的历史条件，不断进行新探索新实践，提出一系列新理念新思想新战略，又以属于我们这个时代的“新语言”书写着科学社会主义的“新版本”。\n" +
                "\n" +
                "　　《共产党宣言》旗帜鲜明地提出，无产阶级在建立政权后应当“尽可能快地增加生产力的总量”，而发展生产力的关键是“把农业和工业结合起来，促使城乡对立逐步消灭”。\n" +
                "\n" +
                "　　党的十一届三中全会作出以经济建设为中心、实行改革开放的历史性决策。特别是党的十八大以来，以习近平同志为核心的党中央以科学的理论指引发展的实践，强调向经济建设这个中心聚焦发力，作出经济发展进入新常态的重大判断，牢固树立新发展理念，深入推进供给侧结构性改革，经济建设再上新台阶。";
        return new String[]{article1, article2, article3, article4, article5};
    }


    @Test
    public void batchInsert() throws IOException {
        AtomicInteger id = new AtomicInteger(0);
        AtomicInteger artileId = new AtomicInteger(0);
        List<ArticleSearch> articles = Arrays.asList(
                new ArticleSearch(id.incrementAndGet(), DEL, new Date(), "名称1", "内容1", artileId.incrementAndGet(), 1, "zh-cn", 1, "sohu", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new ArticleSearch(id.incrementAndGet(), NOT_DEL, new Date(), "名称2", "内容2", artileId.incrementAndGet(), 1, "ah-cn", 1, "sohu", 1, "分类名称", NOT_DEL, 1, "分区名称", NOT_DEL),
                new ArticleSearch(id.incrementAndGet(), DEL, new Date(), "名称3", "内容3", artileId.incrementAndGet(), 1, "zh-cn", 1, "sina", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new ArticleSearch(id.incrementAndGet(), DEL, new Date(), "名称4", "内容4", artileId.incrementAndGet(), 1, "bh-cn", 1, "sina", 1, "分类名称", DEL, 1, "分区名称", NOT_DEL),
                new ArticleSearch(id.incrementAndGet(), DEL, new Date(), "名称5", "内容5", artileId.incrementAndGet(), 1, "zh", 1, "baidu", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new ArticleSearch(id.incrementAndGet(), NOT_DEL, new Date(), "名称6", "内容6", artileId.incrementAndGet(), 1, "zh", 1, "baidu", 1, "分类名称", DEL, 1, "分区名称", DEL),
                new ArticleSearch(id.incrementAndGet(), NOT_DEL, new Date(), "名称7", "内容7", artileId.incrementAndGet(), 1, "zh", 1, "souhu", 1, "分类名称", NOT_DEL, 1, "分区名称", DEL)

        );


        Bulk.Builder bulkBuilder = new Bulk.Builder();
        for (ArticleSearch article : articles) {
            Index index = new Index.Builder(article).index(indexName).type(typeName).build();
            bulkBuilder.addAction(index);
        }
        JestClient client = ESClient.getClient();
        BulkResult result = client.execute(bulkBuilder.build());
        System.out.println(result.isSucceeded());

    }


}
