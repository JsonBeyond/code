package com.alex.httpasyncclient.httppool;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.IOException;

/**
 * @ClassName HttpAsyncClient
 * @Description httpclient 工厂类
 * @Author Alex
 * @CreateDate 2019/5/14 9:35
 * @Version 1.0
 */
public class HttpClientFactory {

    private static HttpAsyncClient httpAsyncClient = new HttpAsyncClient();

    private static HttpSyncClient httpSyncClient = new HttpSyncClient();

    private HttpClientFactory() {
    }

    private static HttpClientFactory httpClientFactory = new HttpClientFactory();

    public static HttpClientFactory getInstance() {

        return httpClientFactory;

    }

    public HttpAsyncClient getHttpAsyncClientPool() {
        return null == httpAsyncClient ? new HttpAsyncClient() : httpAsyncClient;
    }

    public HttpSyncClient getHttpSyncClientPool() {
        return httpSyncClient;
    }

    public void closeHttpAsyncClientPool(CloseableHttpAsyncClient asyncHttpClient) {
        try {
            asyncHttpClient.close();
            httpAsyncClient = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

