package com.zzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.zzy.model.Worklog;
import com.zzy.util.util_Empty;
 
/**
 * 导出Excel、导入 Excel
 * 
 * @author zzy
 *
 */

public class Export {
	
	   
		
		/**
		 * 导出EXCEL
		 */
		public static void export(String excelname,String sheetname,Class pojoClass,List list,HttpServletResponse  response){
			String temppath="";
	    	Object object=null;
			try {
				object = pojoClass.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			  try {                                 
		            response.reset();   //清空输出流                                                                   
		            String fileName = new String(excelname.getBytes("gb2312"), "ISO8859-1") +".xls";   
		            response.setContentType("application/vnd.ms-excel; charset=utf-8");
		            response.setHeader("Content-disposition", "attachment; filename="+ fileName); //设定输出文件头 
		            response.setHeader("Connection", "close");
		            //判断模板是否存在
		            File file  = new File(temppath);
		            HSSFWorkbook workbook;
					HSSFSheet sheet;
		            if(file.exists()){
		                 workbook = new HSSFWorkbook(new FileInputStream(temppath));//读取excel模板    
		                 sheet = workbook.getSheetAt(0);   //读取第一个工作簿     
		            }else{
		            	 workbook  =new HSSFWorkbook();
		            	  sheet = workbook.createSheet(sheetname);//"sheet1"
		            }
		            if(object instanceof Worklog)  {
		            	//不同的接口 调用不同的方法
		                   exportWorklog( list, sheet);
		            }
		             // 创建文件输出流，准备输出电子表格
	        		OutputStream out = response.getOutputStream();
	        		workbook.write(out);
	        		out.close();
	                                                                                  
		        } catch (IOException e) {                                                                              
		            e.printStackTrace();                                                                               
		        }  
	    	
	    }
	    	
	    
	    
		public static void exportWorklog(List list,HSSFSheet sheet){
	    	//设置第一行
	    	String head01[] = {"ID","创建人","标题","内容","创建时间","修改时间"}; 
	    	HSSFRow row01 = sheet.createRow(0);
	    	for(int i=0;i<head01.length;i++){
	    		HSSFCell cell = row01.createCell(i);//循环创建每一个单元格
	    		cell.setCellValue(head01[i]);
	    	}
	    	
	    	//创建每一行(从第二行开始)
	    	for(int i=0;i<list.size();i++){
	    		Worklog temp = new Worklog();
	    		temp = (Worklog)list.get(i);
	    		if(temp!=null){
	    			//ID
	    			HSSFRow row02 = sheet.createRow(i+1);//循环创建下面的行数
	    			if(temp.getId()!=null && temp.getId()>0 ){
	    				HSSFCell cell = row02.createCell(0);
	    				cell.setCellValue(temp.getId());
	    			}else{
	    				//这里认为没有ID为垃圾数据不要了
	    				continue;
	    			}
    				//创建人
	    			if(util_Empty.strEmpty(temp.getCreateusername())){
	    				HSSFCell cell = row02.createCell(1);
	    				cell.setCellValue(temp.getCreateusername());
	    			}
	    			//标题
	    			if(util_Empty.strEmpty(temp.getTitle())){
	    				HSSFCell cell = row02.createCell(2);
	    				cell.setCellValue(temp.getTitle());
	    			}
	    			//内容
	    			if(util_Empty.strEmpty(temp.getContent())){
	    				HSSFCell cell = row02.createCell(3);
	    				cell.setCellValue(temp.getContent());
	    			}
	    			//创建时间
	    			if(util_Empty.strEmpty(temp.getCreatetime())){
	    				HSSFCell cell = row02.createCell(4);
	    				cell.setCellValue(temp.getCreatetime());
	    			}
	    			//修改时间
	    			if(util_Empty.strEmpty(temp.getUptime())){
	    				HSSFCell cell = row02.createCell(5);
	    				cell.setCellValue(temp.getUptime());
	    			}
    			}else{
    				continue;
    			}
	    	}//循环结束
	    }
	    	
	    	
	    	
	    /**
	     * 导入Excel	
	     */
		@SuppressWarnings("static-access")
		public static void daoruexcel() {
			POIFSFileSystem fs;
			HSSFWorkbook wb = null;
			HSSFSheet sheet;
			InputStream is = null;
			try {
				is = new FileInputStream("F:\\test.xls");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				fs = new POIFSFileSystem(is);
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = wb.getSheetAt(0);
			// 得到总行数
			int rowNum = sheet.getLastRowNum();
			// 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = 0; i <= rowNum; i++) {
				
				HSSFCell cell00 = sheet.getRow(i).getCell(0);
				cell00.setCellType(cell00.CELL_TYPE_STRING);
				String s00 = cell00.getStringCellValue();
				if(s00!=null && !"".equals(s00)){
					System.out.println(s00);
				}else{
					
				}
				
				
				HSSFCell cell01 = sheet.getRow(i).getCell(1);
				cell01.setCellType(cell01.CELL_TYPE_STRING);
				String s01 = cell01.getStringCellValue();
				if(s01!=null && !"".equals(s01)){
					System.out.println(s01);
				}else{
					
				}
				
				
				

			}

		}
	    	
	    	
	    	
	    	
	    	
	    	
	    	public static void main(String args[]){
	    		daoruexcel();
	    	}
	    	
	    	
	    	
	    	
	        
	
	
}
