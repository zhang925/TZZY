package com.zzy.util.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class Util_DoGet {
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
}
