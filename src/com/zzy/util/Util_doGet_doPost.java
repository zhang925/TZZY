package com.zzy.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Util_doGet_doPost {

    public class DoGET {

            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=java");
            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpGet);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 获取服务端返回的数据
                    String content = EntityUtils.toString(response.getEntity(),
                            "UTF-8");
                    FileUtils.writeStringToFile(new File("E:\\baidu.html"),
                            content, "UTF-8");
                    // 服务端返回数据的长度
                    System.out.println("内容长度：" + content.length());
                }
            } finally {
                if (response != null) {
                    response.close();
                }
                // 相当于关闭浏览器
                httpclient.close();
            }



    }

}
