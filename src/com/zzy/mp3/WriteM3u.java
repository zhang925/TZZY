package com.zzy.mp3;
import java.io.File;
import java.io.*;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class WriteM3u {
    public int writeM3u(String url){
        int rtn=1;
        File file=new File(url+"/mp3/list.m3u");
        try {
            if (!file.createNewFile()) {
                rtn = 0;
            }
        } catch (IOException ex) {
            rtn=0;
            System.out.println("创建M3U文件出错："+ex.getMessage());
        }
        return rtn;
    }
    //组合M3u文件的内容
    public String getText(HttpServletRequest request,String path_part){
        ConnDB conn=new ConnDB();
        String ID[]=request.getParameterValues("playid");
        String path="";
        String sql = "";
        if (ID.length > 0) {
            for (int i = 0; i < ID.length; i++) {
                sql = "select * from tb_mp3list where id=" + ID[i];
                ResultSet rs = conn.executeQuery(sql);
                try {
                    if (rs.next()) {
                        path = path + path_part + rs.getString("url") + "\n";
                    }
                } catch (Exception e) {
                    System.out.println("查询时的错误信息：" + e.getMessage());
                }
            }
        }
        return path;
    }
}
