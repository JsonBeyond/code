package com.alex.solution.httpasyncclient;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName com.alex.solution.httpasyncclient.HttpAsyncClientDemoTest
 * @Description 直接使用apache包的异步线程池 测试类
 * @Author Alex
 * @CreateDate 2019/6/12 10:31
 * @Version 1.0
 */
class HttpAsyncClientDemoTest {

    private HttpAsyncClientDemo httpAsyncClientDemo = new HttpAsyncClientDemo();

    @Test
    @DisplayName("测试doGet")
    void testDoGet() throws Exception {
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(100);
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().setConnectionManager(cm).build();
        httpAsyncClient.start();
//        String[] urisToGet = {
//                "http://www.chinaso.com/",
//                "http://www.so.com/",
//                "http://www.qq.com/",
//        };

        final CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            HttpAsyncClientDemo.doGet("http://www.chinaso.com/", httpAsyncClient, latch);
        }
        latch.await();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    @Test
    @DisplayName("测试batchPost")
    void testBatchPost() throws Exception {
        httpAsyncClientDemo.batchPost();
    }

    @Test
    @DisplayName("测试getConfiCall")
    void testGetConfiCall() {
        String url = "http://220.181.14.110/xxxxx/xxxxx/searchbyappid.do";

        List<BasicNameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("appid", "2"));
        httpAsyncClientDemo.getConfCall(url, urlParams);
    }
}
