package com.zzy.util.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

//使用连接池管理http连接 7、HttpClient对于请求连接池的管理
public class HttpConnectManager {

    public static void main(String[] args) throws Exception {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(200);
        // 设置每个主机地址的并发数
        cm.setDefaultMaxPerRoute(20);

        doGet(cm);
        doGet(cm);
    }

    public static void doGet(HttpClientConnectionManager cm) throws Exception {
        // 通过PoolingHttpClientConnectionManager，来获取CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm).build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(),
                        "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
            // 因为此处的httpClient是通过PoolingHttpClientConnectionManager获取的
            // 所以这里的httpClient也由PoolingHttpClientConnectionManager的管理
            // httpClient.close();
        }
    }

}