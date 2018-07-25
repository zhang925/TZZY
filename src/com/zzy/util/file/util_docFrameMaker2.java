package com.zzy.util.file;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.zzy.util.DataMapUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
public class util_docFrameMaker2 {
	/**
	 * 根据下载类型获取需要传递的Map参数
	 * @param oid 对象Id
	 * @param downloadType 下载类型
	 */
	private Map<String, Object> getDataMap(String oid,String downloadType){
	    Map<String, Object> dataMap = new HashMap<String, Object>();
	    if("Parameter1".equals(downloadType)){
	        dataMap = DataMapUtil.setObjToMap("");
	    }else{
	        dataMap = DataMapUtil.setObjToMap("");
	    }
	    return dataMap;
	}
}