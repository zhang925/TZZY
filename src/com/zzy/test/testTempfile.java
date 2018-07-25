package com.zzy.test;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class testTempfile {

    private static File tempPath;
   
    //初始化
    public void init(){
        tempPath = new File("./temp");
        if(!tempPath.exists() || !tempPath.isDirectory()){
            tempPath.mkdir();  //如果不存在，则创建该文件夹
        }
    }
    //处理事件
    public static void actionPerformed(){
        try{
            //在tempPath路径下创建临时文件"mytempfileXXXX.tmp"
            //XXXX 是系统自动产生的随机数, tempPath对应的路径应事先存在
            File tempFile = File.createTempFile("mytempfile", ".txt", tempPath);
            System.out.println(tempFile.getAbsolutePath());
            FileWriter fout = new FileWriter(tempFile);
            PrintWriter out = new PrintWriter(fout);
            out.println("some info!" );
            out.close(); //注意：如无此关闭语句，文件将不能删除
            //tempFile.delete();
            tempFile.deleteOnExit();
        }catch(IOException e1){
            System.out.println(e1);
        }
    }
    
    public static void main(String args[]){
    	actionPerformed();
    	
    }
    
}
