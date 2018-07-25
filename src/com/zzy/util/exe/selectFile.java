package com.zzy.util.exe;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

public class selectFile {
	
	/**
	 * 选中文件
	 */
	public static void seeFile(String filename){
		filename = "1.png";
		 Runtime run = Runtime.getRuntime(); 
	     
		 String[] arg = new String[3]; 
	      arg[0] = "cmd"; 
	      arg[1] = "/c"; 
	      
	      
	      File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		 String desktopPath = desktopDir.getAbsolutePath();
	      //arg[2] = "Explorer.exe /n , /select,"+desktopPath+"\\1.png";
		  arg[2] = "Explorer.exe /n , /select,"+desktopPath+"\\"+filename;
		  //arg[2] = "Explorer.exe /n , /select,\\"+filename;
	      
		  
		  try {
			run.exec(arg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	}
}
