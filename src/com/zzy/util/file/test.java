package com.zzy.util.file;

import java.io.File;

import com.zzy.util.exe.selectFile;

public class test {

	public static void main(String[] args) {
		util_txtfile.writeTxtFile("@echo off \n ipconfig \n pause",new File("F:/seeip.bat"));
		
		
	}

}
