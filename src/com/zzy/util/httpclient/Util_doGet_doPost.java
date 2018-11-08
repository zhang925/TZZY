package com.zzy.util.httpclient;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Util_doGet_doPost {


    /**apache http请求获取get方法*/
    public  static String DoGET(String uri){
        String json = "";
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
         try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据
                json = EntityUtils.toString(response.getEntity(), "UTF-8");
                //FileUtils.writeStringToFile(new File("E:\\baidu.html"), content, "UTF-8");
                // 服务端返回数据的长度
            }else{
                //获取异常了
                json="";
            }
        }catch (Exception e){
             e.printStackTrace();
         }finally {
          try{
              if (response != null) {
                  response.close();
                  // 相当于关闭浏览器
                  httpclient.close();
              }
          }catch (Exception e){
              e.printStackTrace();
          }
        }

        return  json;
    }

    /**
     *  get 带参数
     * @param url
     * @param param
     * @param value
     * @return
     */
    public static String DoGETParam(String url,String param , String value){
        String json = "";
        CloseableHttpResponse response = null;
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 定义请求的参数

            // 设置2个post参数，一个是scope、一个是q
            //List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            //parameters.add(new BasicNameValuePair("scope", "project"));
            //parameters.add(new BasicNameValuePair("q", "java"));

            URI uri = new URIBuilder(url).setParameter(param, value).build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端，响应的数据
                json = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else{
                //获取异常了
                json="";
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if (response != null) {
                    response.close();
                    httpclient.close(); // 相当于关闭浏览器
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  json;
    }


    /**
     *  post 请求
     * @param uri
     * @return
     */
    public static String DoPOST(String uri) {
        String json = "";
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建http POST请求
            HttpPost httpPost = new HttpPost(uri);
            // 伪装浏览器请求
            httpPost.setHeader( "User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 获取服务端，响应的数据
                    json = EntityUtils.toString(response.getEntity(), "UTF-8");
                }else{
                    //获取异常了
                    json="";
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if (response != null) {
                        response.close();
                        httpclient.close(); // 相当于关闭浏览器
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        return json;
    }

    /**
     *  POST 请求带参数
     * @param uri
     * @param parameters
     * @return
     */
    public static String DoPOSTParam (String uri,List<NameValuePair> parameters){
        String json = "";
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建http POST请求
            HttpPost httpPost = new HttpPost(uri);
            // 设置2个post参数，一个是scope、一个是q
            //List<NameValuePair> parameters = new ArrayList<NameValuePair>();
           // parameters.add(new BasicNameValuePair("scope", "project"));
           // parameters.add(new BasicNameValuePair("q", "java"));
            CloseableHttpResponse response = null;
            try {
                // 构造一个form表单式的实体
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
                // 将请求实体设置到httpPost对象中
                httpPost.setEntity(formEntity);
                // 伪装浏览器请求
                httpPost.setHeader( "User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
                // 执行请求
                response = httpclient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 获取服务端响应的数据
                    json = EntityUtils.toString(response.getEntity(), "UTF-8");
                }else{
                    //获取异常了
                    json="";
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if (response != null) {
                        response.close();
                        httpclient.close(); // 相当于关闭浏览器
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        return json;

    }


    /***
     * HttpClient对于请求的配置管理
     */
    public static  void  RequestConfigDemo() {
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet("http://www.baidu.com/");

            // 构建请求配置信息
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(1000) // 创建连接的最长时间
                    .setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
                    .setSocketTimeout(10 * 1000) // 数据传输的最长时间
                    .setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
                    .build();
            // 设置请求配置信息
            httpGet.setConfig(config);
            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpGet);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(content);
                }
            }catch (Exception e){
                e.printStackTrace();
            }  finally {
                try{
                    if (response != null) {
                        response.close();
                        httpclient.close(); // 相当于关闭浏览器
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


    }


    /**
     * HttpClient对于请求连接池的管理
     * @param   cm
     */
    public static  void  HttpConnectManager(HttpClientConnectionManager cm) {
        // 通过PoolingHttpClientConnectionManager，来获取CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.custom() .setConnectionManager(cm).build();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        }catch (Exception e){
            e.printStackTrace();
        }  finally {
            try{
                if (response != null) {
                    response.close();
                }
                //httpClient.close(); // 相当于关闭浏览器
                // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
                // 因为此处的httpClient是通过PoolingHttpClientConnectionManager获取的
                // 所以这里的httpClient也由PoolingHttpClientConnectionManager的管理
                // httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    //测试 上面的     HttpConnectManager
    public void testHttpConnectManager(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(200);
        // 设置每个主机地址的并发数
        cm.setDefaultMaxPerRoute(20);
        HttpConnectManager(cm);
        HttpConnectManager(cm);
    }



}
