package com.zzy.util.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class Util_DoPost {

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
}
