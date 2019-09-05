package com.alex.solution.httpasyncclient;

import com.alex.solution.httpasyncclient.httppool.HttpClientService;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName HttpAsyncClientDemo
 * @Description apache包的异步线程池使用demo
 * @Author Alex
 * @CreateDate 2019/5/13 10:46
 * @Version 1.0
 */
public class HttpAsyncClientDemo extends HttpClientService {

    /**
     * 直接调用，调用方写循环逻辑
     *
     * @param uri
     * @param httpAsyncClient
     * @param latch
     * @author Alex
     * @date 2019/6/12 14:49
     */
    public static void doGet(String uri, CloseableHttpAsyncClient httpAsyncClient, final CountDownLatch latch) {
        final HttpGet httpget = new HttpGet(uri);
        httpAsyncClient.execute(httpget, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(final HttpResponse response) {
                latch.countDown();
                //System.out.println(httpget.getRequestLine() + "->" + response.getStatusLine());
            }

            @Override
            public void failed(final Exception ex) {
                latch.countDown();
                //System.out.println(httpget.getRequestLine() + "->" + ex);
            }

            @Override
            public void cancelled() {
                latch.countDown();
                //System.out.println(httpget.getRequestLine() + " cancelled");
            }

        });
    }

    /**
     * 设置线程池参数，批量调用
     *
     * @return java.util.List<org.apache.http.HttpResponse>
     * @author Alex
     * @date 2019/6/12 14:50
     */
    public List<HttpResponse> batchPost() throws IOException {
//        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
//        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
//        // 设置默认工厂类
//        System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");
//        // 设置日志打印类
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//        //设置默认日志级别
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.simplelog.defaultlog", "error");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setSocketTimeout(50000)
                .setConnectionRequestTimeout(300000)
                .build();

        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
                setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        //设置连接池大小
        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        assert ioReactor != null;
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(300);
        connManager.setDefaultMaxPerRoute(150);

        try (CloseableHttpAsyncClient client = HttpAsyncClients.custom().
                setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build()) {
            //构造请求
            String url = "http://localhost:22101/content/v1/activity/fruitCard/list";
            List<HttpPost> list = new ArrayList<>();
            String a = "{\"pageNum\": 1\n,\"pageSize\":9}";
            for (int i = 0; i < 100000; i++) {
                HttpPost httpPost = new HttpPost(url);
                StringEntity entity = null;
                try {
                    //                String a = "{ \"index\": { \"_index\": \"test\", \"_type\": \"test\"} }\n {\"name\": \"上海\",\"age\":33}\n";
                    entity = new StringEntity(a);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httpPost.addHeader("Content-Type", "application/json");
                httpPost.setEntity(entity);
                list.add(httpPost);
            }

            final CountDownLatch latch = new CountDownLatch(100000);

            long start = System.currentTimeMillis();

            client.start();

            for (HttpPost httpPost : list) {
                client.execute(httpPost, new FutureCallback<HttpResponse>() {

                    //无论完成还是失败都调用countDown()
                    @Override
                    public void completed(HttpResponse httpResponse) {
                        latch.countDown();
//                        System.out.println("cost is:" + (System.currentTimeMillis() - start) + ":" + EntityUtils.toString(httpResponse.getEntity()));
                    }

                    @Override
                    public void failed(Exception e) {
                        latch.countDown();
                        System.out.println("失败：" + httpPost.getRequestLine() + "->" + e);
                    }

                    @Override
                    public void cancelled() {
                        latch.countDown();
                    }
                });
            }
            latch.await();
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 封装线程池实例、发送细节
     *
     * @param url
     * @param urlParams
     * @return void
     * @author Alex
     * @date 2019/6/12 14:54
     */
    public void getConfCall(String url, List<BasicNameValuePair> urlParams) {

        exeHttpReq(url, false, urlParams, null, new GetConfCall());
    }

    private void exeHttpReq(String baseUrl, boolean isPost,
                           List<BasicNameValuePair> urlParams,
                           List<BasicNameValuePair> postBody,
                           FutureCallback<HttpResponse> callback) {

        try {
            System.out.println("enter exeAsyncReq");
            super.exeAsyncReq(baseUrl, isPost, urlParams, postBody, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @ClassName GetConfCall
     * @Description 被回调的对象，给异步的httpclient使用
     * @Author Alex
     * @CreateDate 2019/5/13 10:46
     * @Version 1.0
     */
    private class GetConfCall implements FutureCallback<HttpResponse> {

        /**
         * 请求完成后调用该函数
         */
        @Override
        public void completed(HttpResponse response) {
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(getHttpContent(response));
            HttpClientUtils.closeQuietly(response);
        }

        /**
         * 请求取消后调用该函数
         */
        @Override
        public void cancelled() {

        }

        /**
         * 请求失败后调用该函数
         */
        @Override
        public void failed(Exception e) {

        }

    }
}
