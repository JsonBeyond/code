package com.alex.solution.httpasyncclient.httppool;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PagodaHttpClientService
 * @Description http client 业务逻辑处理类(当前只提供异步业务)
 * @Author Alex
 * @CreateDate 2019/5/14 9:48
 * @Version 1.0
 */
public class PagodaHttpClientService {

    private static Logger logger = LoggerFactory
            .getLogger(PagodaHttpClientService.class);

    public void exeAsyncReq(CloseableHttpAsyncClient hc, String baseUrl, boolean isPost,
                            List<BasicNameValuePair> urlParams,
                            String jsonParam, Map<String, String> headers, FutureCallback callback)
            throws Exception {

        if (baseUrl == null) {
            logger.warn("we don't have base url, check config");
            throw new Exception("missing base url");
        }

        HttpRequestBase httpMethod;

            hc.start();

            HttpClientContext localContext = HttpClientContext.create();
            BasicCookieStore cookieStore = new BasicCookieStore();

            if (isPost) {
                httpMethod = new HttpPost(baseUrl);

                if (StringUtils.isNotEmpty(jsonParam)) {
                    logger.debug("exeAsyncReq post postBody={}", jsonParam);
                    // 解决中文乱码问题
                    StringEntity entity = new StringEntity(jsonParam, StandardCharsets.UTF_8);
                    entity.setContentEncoding("UTF-8");
                    entity.setContentType("application/json");
                    ((HttpPost) httpMethod).setEntity(entity);
                }

            } else {

                httpMethod = new HttpGet(baseUrl);

            }

            if (null != headers) {

                for (String name : headers.keySet()) {
                    httpMethod.setHeader(name, headers.get(name));
                }

            }

            if (null != urlParams) {

                String getUrl = EntityUtils
                        .toString(new UrlEncodedFormEntity(urlParams));

                httpMethod.setURI(new URI(httpMethod.getURI().toString()
                        + "?" + getUrl));
            }

            System.out.println("exeAsyncReq getparams:" + httpMethod.getURI());

            localContext.setAttribute(HttpClientContext.COOKIE_STORE,
                    cookieStore);

            hc.execute(httpMethod, localContext, callback);

    }

    public String getHttpContent(HttpResponse response) {

        HttpEntity entity = response.getEntity();
        String body = null;

        if (null == entity) {
            return null;
        }

        try {

            body = EntityUtils.toString(entity, StandardCharsets.UTF_8);

        } catch (ParseException e) {

            logger.warn("the response's content format is incorrect", e);
        } catch (IOException e) {

            logger.warn("the response's content inputstream is corrupt", e);
        }
        return body;
    }
}
