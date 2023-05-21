package com.huolieniao;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Slf4j
public class ElasticSearchTest extends BaseTest{

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void test() throws IOException {
        log.info("test测试用例");
        GetRequest getRequest = new GetRequest("address","1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info(getResponse.getSourceAsString());
    }
}
