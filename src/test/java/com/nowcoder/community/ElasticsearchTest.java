package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.entity.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticsearchTest {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testInsert() {
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(241));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(242));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(243));
    }

    @Test
    public void testInsertList() {
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(101, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(102, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(103, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(111, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(112, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(131, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(132, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(133, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(134, 0, 100, 0));
    }

    @Test
    public void testUpdate() {
        DiscussPost discussPost = discussPostMapper.selectDiscussPostById(241);
        discussPost.setContent("???????????????????????????");
        discussPostRepository.save(discussPost);
    }

    @Test
    public void testDelete() {
        // ????????????
        discussPostRepository.deleteById(241);
        // ????????????
//        discussPostRepository.deleteAll();
    }

    @Test
    public void testSearchByRepository(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("???????????????", "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        SearchHits<DiscussPost> searchHits = elasticsearchRestTemplate.search(searchQuery, DiscussPost.class);
        // ??????????????????????????????????????????
        List<DiscussPost> discussPosts = new ArrayList<>();
        // ?????????????????????????????????
        for (SearchHit<DiscussPost> searchHit : searchHits) {
            // ???????????????
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            // ???????????????????????????content???
            searchHit.getContent().setTitle(highlightFields.get("title") == null ?
                    searchHit.getContent().getTitle() : highlightFields.get("title").get(0));
            searchHit.getContent().setTitle(highlightFields.get("content") == null ?
                    searchHit.getContent().getContent() : highlightFields.get("content").get(0));
            // ??????????????????
            discussPosts.add(searchHit.getContent());
        }
        // ????????????
        System.out.println(discussPosts.size());
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }


    }

}
