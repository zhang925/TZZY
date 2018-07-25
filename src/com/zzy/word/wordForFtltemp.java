  package com.zzy.word;  
      
    import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

      
import com.zzy.util.file.util_down;

    import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
      
    public class wordForFtltemp {  
          
        private Configuration configuration = null;  
          
        public wordForFtltemp(){  
            configuration = new Configuration();  
            configuration.setDefaultEncoding("UTF-8");  
        }  
          
        public static void main(String[] args) {  
        	createword();
        }  
          
        public void createWord(){ 
        	
        	//HttpServletResponse response,HttpServletRequest request
        	
        	/*
        	String realPath = request.getSession().getServletContext().getRealPath("/");
    		mbpath = realPath + "resources/word/wordftl/" + filename01;
    		*/
    		
            Map<String,Object> dataMap=new HashMap<String,Object>();  
            getData(dataMap);  
            
            configuration.setClassForTemplateLoading(this.getClass(), "/");  //FTL文件所存在的位置  
            Template t=null;  
            try {  
                t = configuration.getTemplate("ceshi.ftl"); //文件名  
            } catch (IOException e) {  
                e.printStackTrace();  
            } 
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
  		  	String desktopPath = desktopDir.getAbsolutePath();
  		  
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
  		 	
          //File outFile = new File("f:/"+Math.random()*10000+".doc"); 
 		  File outFile = new File(desktopPath+"/2222222222.doc"); 
//  		  System.out.println(desktopPath+"/测试frameMaker.doc");
            Writer out = null;  
            try {  
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
            } catch (FileNotFoundException e1) {  
                e1.printStackTrace();  
            }  
               
            try {  
                t.process(dataMap, out);  
            } catch (TemplateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } 
  		  	
        }  
      
        private void getData(Map<String, Object> dataMap) {  
            dataMap.put("name", "德玛西亚");  
            dataMap.put("year", "2012");  
            dataMap.put("month", "2");  
            dataMap.put("day", "13");  
            
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
            for (int i = 0; i < 10; i++) {  
                Map<String,Object> map = new HashMap<String,Object>();  
                map.put("number", i);  
                map.put("content", "内容"+i);  
                list.add(map);  
            }  
              
            dataMap.put("listtest", list); 
        }  
        
        
        
        public static void createword(){
        	  wordForFtltemp test = new wordForFtltemp();  
              test.createWord();  
        }
      
    }  