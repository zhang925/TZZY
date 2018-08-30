package com.zzy.util.excel;

import com.zzy.util.util_Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * excel导入导出工具类
 * @author zzy
 * @version 1.0
 * @time 2018年5月29日16:34:32
 */
public class UtilExcel<T> implements Serializable {

	private static final long serialVersionUID = 551970754610248636L;
	private final static String excel2003 = ".xls";    //2003- 版本的excel
	private final static String excel2007 = ".xlsx";   //2007+ 版本的excel


	private Class<T> clazz;

	public UtilExcel(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 将本地excel表单数据源的数据导入到list
	 * @param sheetName
	 *            工作表的名称
	 * @param input
	 *            java输入流
	 */
	public List<T> getExcelToList(String sheetName, InputStream input) {
		List<T> list = new ArrayList<T>();
		try {
			HSSFWorkbook book = new HSSFWorkbook(input);
			HSSFSheet sheet = null;
			// 如果指定sheet名,则取指定sheet中的内容.
			if (StringUtils.isNotBlank(sheetName)) {
				sheet = book.getSheet(sheetName);
			}
			// 如果传入的sheet名不存在则默认指向第1个sheet.
			if (sheet == null) {
				sheet = book.getSheetAt(0);
			}
			// 得到数据的行数
			int rows = sheet.getLastRowNum();
			// 有数据时才处理
			if (rows > 0) {
				// 得到类的所有field
				Field[] allFields = clazz.getDeclaredFields();
				// 定义一个map用于存放列的序号和field
				Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
				for (int i = 0, index = 0; i < allFields.length; i++) {
					Field field = allFields[i];
					// 将有注解的field存放到map中
					if (field.isAnnotationPresent(ExcelAttribute.class)) {
						// 设置类的私有字段属性可访问
						field.setAccessible(true);
						fieldsMap.put(index, field);
						index++;
					}
				}
				// 从第2行开始取数据,默认第一行是表头
				for (int i = 1, len = rows; i < len; i++) {
					// 得到一行中的所有单元格对象.
					HSSFRow row = sheet.getRow(i);
					Iterator<Cell> cells = row.cellIterator();
					T entity = null;
					int index = 0;
					while (cells.hasNext()) {
						// 单元格中的内容.
						String c = cells.next().getStringCellValue();
						if (!StringUtils.isNotBlank(c)) {
							continue;
						}
						if (c.indexOf("合计：") != -1) {
							continue;
						}
						// 如果不存在实例则新建
						entity = (entity == null ? clazz.newInstance() : entity);
						// 从map中得到对应列的field
						Field field = fieldsMap.get(index);
						if (field == null) {
							continue;
						}
						// 取得类型,并根据对象类型设置值.
						Class<?> fieldType = field.getType();
						if (fieldType == null) {
							continue;
						}
						if (String.class == fieldType) {
							field.set(entity, String.valueOf(c));
						} else if (BigDecimal.class == fieldType) {
							c = c.indexOf("%") != -1 ? c.replace("%", "") : c;
							field.set(entity, BigDecimal.valueOf(Double.valueOf(c)));
						} else if (Date.class == fieldType) {
							//
							field.set(entity, util_Date.strToDate1(c.toString()));
							
						} else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
							field.set(entity, Integer.parseInt(c));
						} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
							field.set(entity, Long.valueOf(c));
						} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
							field.set(entity, Float.valueOf(c));
						} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
							field.set(entity, Short.valueOf(c));
						} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
							field.set(entity, Double.valueOf(c));
						} else if (Character.TYPE == fieldType) {
							if ((c != null) && (c.length() > 0)) {
								field.set(entity, Character.valueOf(c.charAt(0)));
							}
						}
						index++;

					}
					if (entity != null) {
						list.add(entity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 从页面选择文件，导入到List,2003，2007 都可以
	 * 将excel表单数据源的数据导入到list
	 * @param file
	 */
	public List<T> getExcelToList(MultipartFile file) {
		List<T> list = new ArrayList<T>();
		try {
			InputStream ist = file.getInputStream();
			String fielType = file.getOriginalFilename();
			String fileType = fielType.substring(fielType.lastIndexOf("."));
			if(excel2003.equals(fileType)){
				list = getExcelToList2003(file);
			}else if(excel2007.equals(fileType)){
				list =  getExcelToList2007(file);
			}else{
				throw new Exception("解析的文件格式有误！");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 浏览器导入Excel 2003 xls 后缀 内部 使用外部
	 * @param file
	 * @return
	 */
	public List<T> getExcelToList2003(MultipartFile file) {
		List<T> list = new ArrayList<T>();
		try {
			InputStream ist = file.getInputStream();
			//lxs 2003
			HSSFWorkbook book = new HSSFWorkbook(ist);
			// 如果传入的sheet名不存在则默认指向第1个sheet.
			HSSFSheet sheet = book.getSheetAt(0);
			//sheet = book.getSheet(sheetName);
			// 得到数据的行数
			int rows = sheet.getLastRowNum();
			// 有数据时才处理
			if (rows > 0) {
				// 得到类的所有field
				Field[] allFields = clazz.getDeclaredFields();
				// 定义一个map用于存放列的序号和field
				Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
				for (int i = 0, index = 0; i < allFields.length; i++) {
					Field field = allFields[i];
					// 将有注解的field存放到map中
					if (field.isAnnotationPresent(ExcelAttribute.class)) {
						// 设置类的私有字段属性可访问
						field.setAccessible(true);
						fieldsMap.put(index, field);
						index++;
					}
				}
				// 从第2行开始取数据,默认第一行是表头
				for (int i = 1, len = rows; i < len; i++) {
					// 得到一行中的所有单元格对象.
					HSSFRow row = sheet.getRow(i);
					Iterator<Cell> cells = row.cellIterator();
					T entity = null;
					int index = 0;
					while (cells.hasNext()) {
						// 单元格中的内容.
						String c = cells.next().getStringCellValue();
						if (!StringUtils.isNotBlank(c)) {
							continue;
						}
						if (c.indexOf("合计：") != -1) {
							continue;
						}
						// 如果不存在实例则新建
						entity = (entity == null ? clazz.newInstance() : entity);
						// 从map中得到对应列的field
						Field field = fieldsMap.get(index);
						if (field == null) {
							continue;
						}
						// 取得类型,并根据对象类型设置值.
						Class<?> fieldType = field.getType();
						if (fieldType == null) {
							continue;
						}
						if (String.class == fieldType) {
							field.set(entity, String.valueOf(c));
						} else if (BigDecimal.class == fieldType) {
							c = c.indexOf("%") != -1 ? c.replace("%", "") : c;
							field.set(entity, BigDecimal.valueOf(Double.valueOf(c)));
						} else if (Date.class == fieldType) {
							//
							field.set(entity, util_Date.strToDate1(c));

						} else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
							field.set(entity, Integer.parseInt(c));
						} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
							field.set(entity, Long.valueOf(c));
						} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
							field.set(entity, Float.valueOf(c));
						} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
							field.set(entity, Short.valueOf(c));
						} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
							field.set(entity, Double.valueOf(c));
						} else if (Character.TYPE == fieldType) {
							if ((c != null) && (c.length() > 0)) {
								field.set(entity, Character.valueOf(c.charAt(0)));
							}
						}
						index++;

					}
					if (entity != null) {
						list.add(entity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 浏览器导入Excel 2003 xls 后缀 内部 使用外部
	 * @param file
	 * @return
	 */
	public List<T> getExcelToList2007(MultipartFile file) {
		List<T> list = new ArrayList<T>();
		try {
			InputStream ist = file.getInputStream();
			//xlxs 2007
			XSSFWorkbook book = new XSSFWorkbook(ist);
			// 如果传入的sheet名不存在则默认指向第1个sheet.
			XSSFSheet sheet = book.getSheetAt(0);
			//sheet = book.getSheet(sheetName);
			// 得到数据的行数
			int rows = sheet.getLastRowNum();
			// 有数据时才处理
			if (rows > 0) {
				// 得到类的所有field
				Field[] allFields = clazz.getDeclaredFields();
				// 定义一个map用于存放列的序号和field
				Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
				for (int i = 0, index = 0; i < allFields.length; i++) {
					Field field = allFields[i];
					// 将有注解的field存放到map中
					if (field.isAnnotationPresent(ExcelAttribute.class)) {
						// 设置类的私有字段属性可访问
						field.setAccessible(true);
						fieldsMap.put(index, field);
						index++;
					}
				}
				// 从第2行开始取数据,默认第一行是表头
				for (int i = 1, len = rows; i < len; i++) {
					// 得到一行中的所有单元格对象.
					XSSFRow row = sheet.getRow(i);
					Iterator<Cell> cells = row.cellIterator();
					T entity = null;
					int index = 0;
					while (cells.hasNext()) {
						// 单元格中的内容.
						String c = cells.next().getStringCellValue();
						if (!StringUtils.isNotBlank(c)) {
							continue;
						}
						if (c.indexOf("合计：") != -1) {
							continue;
						}
						// 如果不存在实例则新建
						entity = (entity == null ? clazz.newInstance() : entity);
						// 从map中得到对应列的field
						Field field = fieldsMap.get(index);
						if (field == null) {
							continue;
						}
						// 取得类型,并根据对象类型设置值.
						Class<?> fieldType = field.getType();
						if (fieldType == null) {
							continue;
						}
						if (String.class == fieldType) {
							field.set(entity, String.valueOf(c));
						} else if (BigDecimal.class == fieldType) {
							c = c.indexOf("%") != -1 ? c.replace("%", "") : c;
							field.set(entity, BigDecimal.valueOf(Double.valueOf(c)));
						} else if (Date.class == fieldType) {
							//
							field.set(entity, util_Date.strToDate1(c));

						} else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
							field.set(entity, Integer.parseInt(c));
						} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
							field.set(entity, Long.valueOf(c));
						} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
							field.set(entity, Float.valueOf(c));
						} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
							field.set(entity, Short.valueOf(c));
						} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
							field.set(entity, Double.valueOf(c));
						} else if (Character.TYPE == fieldType) {
							if ((c != null) && (c.length() > 0)) {
								field.set(entity, Character.valueOf(c.charAt(0)));
							}
						}
						index++;

					}
					if (entity != null) {
						list.add(entity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	/**
	 * 将list数据源的数据导入到excel表单
	 * 下载到浏览器
	 * @param list
	 *            数据源
	 * @param sheetName
	 *            工作表的名称
	 * @param  response 下载到浏览器
	 *            java输出流
	 */
	public boolean getListToExcel(List<T> list, String sheetName, String fileName, HttpServletResponse response) {
		try {
			// excel中每个sheet中最多有65536行
			int sheetSize = 65536;
			// 得到所有定义字段
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> fields = new ArrayList<Field>();
			// 得到所有field并存放到一个list中
			for (Field field : allFields) {
				if (field.isAnnotationPresent(ExcelAttribute.class)) {
					fields.add(field);
				}
			}
			// 产生工作薄对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 取出一共有多少个sheet
			int listSize = 0;
			if (list != null && list.size() >= 0) {
				listSize = list.size();
			}
			double sheetNo = Math.ceil(listSize / sheetSize);
			for (int index = 0; index <= sheetNo; index++) {
				// 产生工作表对象
				HSSFSheet sheet = workbook.createSheet();
				// 设置工作表的名称.
				workbook.setSheetName(index, sheetName + index);
				HSSFRow row;
				HSSFCell cell;// 产生单元格
				row = sheet.createRow(0);// 产生一行
				/* *********普通列样式********* */
				HSSFFont font = workbook.createFont();
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				font.setFontName("Arail narrow"); // 字体
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
				/* *********标红列样式********* */
				HSSFFont newFont = workbook.createFont();
				HSSFCellStyle newCellStyle = workbook.createCellStyle();
				newFont.setFontName("Arail narrow"); // 字体
				newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
				/* *************创建列头名称*************** */
				for (int i = 0; i < fields.size(); i++) {
					Field field = fields.get(i);
					ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
					int col = i;
					// 根据指定的顺序获得列号
					if (StringUtils.isNotBlank(attr.column())) {
						col = getExcelCol(attr.column());
					}
					// 创建列
					cell = row.createCell(col);
					if (attr.isMark()) {
						newFont.setColor(HSSFFont.COLOR_RED); // 字体颜色
						newCellStyle.setFont(newFont);
						cell.setCellStyle(newCellStyle);
					} else {
						font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
					}
					//设置Excel 的宽度
					int cellWidth = attr.width();
					if(cellWidth<=0){//没有设置
						cellWidth = (attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length); //默认一个
						sheet.setColumnWidth(i, (int) ( cellWidth * 1.5 * 256));
					}else{
						if(cellWidth>=255){//最大只能是255
							cellWidth = 254;
						}
						sheet.setColumnWidth(i, cellWidth);
					}

					//sheet.setColumnWidth(i, (int) ((attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length) * 1.5 * 256));
					// 设置列中写入内容为String类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// 写入列名
					cell.setCellValue(attr.name());
					// 如果设置了提示信息则鼠标放上去提示.
					if (StringUtils.isNotBlank(attr.prompt())) {
						setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);
					}
					// 如果设置了combo属性则本列只能选择不能输入
					if (attr.combo().length > 0) {
						setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);
					}
				}
				/* *************创建内容列*************** */
				font = workbook.createFont();
				cellStyle = workbook.createCellStyle();
				int startNo = index * sheetSize;
				int endNo = Math.min(startNo + sheetSize, listSize);
				// 写入各条记录,每条记录对应excel表中的一行
				for (int i = startNo; i < endNo; i++) {
					row = sheet.createRow(i + 1 - startNo);
					T vo = (T) list.get(i); // 得到导出对象.
					for (int j = 0; j < fields.size(); j++) {
						// 获得field
						Field field = fields.get(j);
						// 设置实体类私有属性可访问
						field.setAccessible(true);
						ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
						int col = j;
						// 根据指定的顺序获得列号
						if (StringUtils.isNotBlank(attr.column())) {
							col = getExcelCol(attr.column());
						}
						// 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
						if (attr.isExport()) {
							// 创建cell
							cell = row.createCell(col);
							if (attr.isMark()) {
								newFont.setColor(HSSFFont.COLOR_RED); // 字体颜色
								newCellStyle.setFont(newFont);
								cell.setCellStyle(newCellStyle);
							} else {
								font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
								cellStyle.setFont(font);
								cell.setCellStyle(cellStyle);
							}
							// 如果数据存在就填入,不存在填入空格
							Class<?> classType = (Class<?>) field.getType();
							String value = null;
							if (field.get(vo) != null && classType.isAssignableFrom(Date.class)) {
								SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
								String thisv = String.valueOf(field.get(vo));
								value = util_Date.dateToStr1(sdf.parse(thisv));
							}
							cell.setCellValue(field.get(vo) == null ? "" : value == null ? String.valueOf(field.get(vo)) : value);
						}
					}
				}
				/* *************创建合计列*************** */
				HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
				for (int i = 0; i < fields.size(); i++) {
					Field field = fields.get(i);
					ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
					if (attr.isSum()) {
						int col = i;
						// 根据指定的顺序获得列号
						if (StringUtils.isNotBlank(attr.column())) {
							col = getExcelCol(attr.column());
						}
						BigDecimal totalNumber = BigDecimal.ZERO;
						for (int j = 1, len = (sheet.getLastRowNum() - 1); j < len; j++) {
							HSSFRow hssfRow = sheet.getRow(j);
							if (hssfRow != null) {
								HSSFCell hssfCell = hssfRow.getCell(col);
								if (hssfCell != null && hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING ) {
									//这个条件先取消 && ValidateUtil.isFloat(hssfCell.getStringCellValue())
									//totalNumber = BigDecimalUtils.getValue(totalNumber, BigDecimal.valueOf(Double.valueOf(hssfCell.getStringCellValue())), CalculateType.Add);
									totalNumber = new BigDecimal("123132");//这里先写成死数据，有空再补全
								}
							}
						}
						HSSFCell sumCell = lastRow.createCell(col);
						sumCell.setCellValue(new HSSFRichTextString("合计：" + totalNumber));
					}
				}
			}

			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(fileName.getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			OutputStream ouputStream  = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 导出到本地
	 * @param list
	 * @param sheetName
	 * @param output
	 * @return
	 */
	public boolean getListToExcel(List<T> list, String sheetName, OutputStream output) {
		try {
			// excel中每个sheet中最多有65536行
			int sheetSize = 65536;
			// 得到所有定义字段
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> fields = new ArrayList<Field>();
			// 得到所有field并存放到一个list中
			for (Field field : allFields) {
				if (field.isAnnotationPresent(ExcelAttribute.class)) {
					fields.add(field);
				}
			}
			// 产生工作薄对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 取出一共有多少个sheet
			int listSize = 0;
			if (list != null && list.size() >= 0) {
				listSize = list.size();
			}
			double sheetNo = Math.ceil(listSize / sheetSize);
			for (int index = 0; index <= sheetNo; index++) {
				// 产生工作表对象
				HSSFSheet sheet = workbook.createSheet();
				// 设置工作表的名称.
				workbook.setSheetName(index, sheetName + index);
				HSSFRow row;
				HSSFCell cell;// 产生单元格
				row = sheet.createRow(0);// 产生一行
				/* *********普通列样式********* */
				HSSFFont font = workbook.createFont();
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				font.setFontName("Arail narrow"); // 字体
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
				/* *********标红列样式********* */
				HSSFFont newFont = workbook.createFont();
				HSSFCellStyle newCellStyle = workbook.createCellStyle();
				newFont.setFontName("Arail narrow"); // 字体
				newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体宽度
				/* *************创建列头名称*************** */
				for (int i = 0; i < fields.size(); i++) {
					Field field = fields.get(i);
					ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
					int col = i;
					// 根据指定的顺序获得列号
					if (StringUtils.isNotBlank(attr.column())) {
						col = getExcelCol(attr.column());
					}
					// 创建列
					cell = row.createCell(col);
					if (attr.isMark()) {
						newFont.setColor(HSSFFont.COLOR_RED); // 字体颜色
						newCellStyle.setFont(newFont);
						cell.setCellStyle(newCellStyle);
					} else {
						font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
					}
					//设置Excel 的宽度
					int cellWidth = attr.width();
					if(cellWidth<=0){//没有设置
						cellWidth = (attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length); //默认一个
					}
					sheet.setColumnWidth(i, (int) ( cellWidth * 1.5 * 256));
					///sheet.setColumnWidth(i, (int) ((attr.name().getBytes().length <= 4 ? 6 : attr.name().getBytes().length) * 1.5 * 256));
					// 设置列中写入内容为String类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// 写入列名
					cell.setCellValue(attr.name());
					// 如果设置了提示信息则鼠标放上去提示.
					if (StringUtils.isNotBlank(attr.prompt())) {
						setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);
					}
					// 如果设置了combo属性则本列只能选择不能输入
					if (attr.combo().length > 0) {
						setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);
					}
				}
				/* *************创建内容列*************** */
				font = workbook.createFont();
				cellStyle = workbook.createCellStyle();
				int startNo = index * sheetSize;
				int endNo = Math.min(startNo + sheetSize, listSize);
				// 写入各条记录,每条记录对应excel表中的一行
				for (int i = startNo; i < endNo; i++) {
					row = sheet.createRow(i + 1 - startNo);
					T vo = (T) list.get(i); // 得到导出对象.
					for (int j = 0; j < fields.size(); j++) {
						// 获得field
						Field field = fields.get(j);
						// 设置实体类私有属性可访问
						field.setAccessible(true);
						ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
						int col = j;
						// 根据指定的顺序获得列号
						if (StringUtils.isNotBlank(attr.column())) {
							col = getExcelCol(attr.column());
						}
						// 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
						if (attr.isExport()) {
							// 创建cell
							cell = row.createCell(col);
							if (attr.isMark()) {
								newFont.setColor(HSSFFont.COLOR_RED); // 字体颜色
								newCellStyle.setFont(newFont);
								cell.setCellStyle(newCellStyle);
							} else {
								font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
								cellStyle.setFont(font);
								cell.setCellStyle(cellStyle);
							}
							// 如果数据存在就填入,不存在填入空格
							Class<?> classType = (Class<?>) field.getType();
							String value = null;
							if (field.get(vo) != null && classType.isAssignableFrom(Date.class)) {
								SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
								String thisv = String.valueOf(field.get(vo));
								value = util_Date.dateToStr1(sdf.parse(thisv));
							}
							cell.setCellValue(field.get(vo) == null ? "" : value == null ? String.valueOf(field.get(vo)) : value);
						}
					}
				}
				/* *************创建合计列*************** */
				HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
				for (int i = 0; i < fields.size(); i++) {
					Field field = fields.get(i);
					ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
					if (attr.isSum()) {
						int col = i;
						// 根据指定的顺序获得列号
						if (StringUtils.isNotBlank(attr.column())) {
							col = getExcelCol(attr.column());
						}
						BigDecimal totalNumber = BigDecimal.ZERO;
						for (int j = 1, len = (sheet.getLastRowNum() - 1); j < len; j++) {
							HSSFRow hssfRow = sheet.getRow(j);
							if (hssfRow != null) {
								HSSFCell hssfCell = hssfRow.getCell(col);
								if (hssfCell != null && hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING ) {
									//这个条件先取消 && ValidateUtil.isFloat(hssfCell.getStringCellValue())
									//totalNumber = BigDecimalUtils.getValue(totalNumber, BigDecimal.valueOf(Double.valueOf(hssfCell.getStringCellValue())), CalculateType.Add);
									totalNumber = new BigDecimal("123132");//这里先写成死数据，有空再补全
								}
							}
						}
						HSSFCell sumCell = lastRow.createCell(col);
						sumCell.setCellValue(new HSSFRichTextString("合计：" + totalNumber));
					}
				}
			}
			output.flush();
			workbook.write(output);
			output.close();
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
	 * 
	 * @param col
	 */
	public static int getExcelCol(String col) {
		col = col.toUpperCase();
		// 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
		int count = -1;
		char[] cs = col.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
		}
		return count;
	}

	/**
	 * 设置单元格上提示
	 * 
	 * @param sheet
	 *            要设置的sheet.
	 * @param promptTitle
	 *            标题
	 * @param promptContent
	 *            内容
	 * @param firstRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param endCol
	 *            结束列
	 * @return 设置好的sheet.
	 */
	public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                                          int firstCol, int endCol) {
		// 构造constraint对象
		DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
		data_validation_view.createPromptBox(promptTitle, promptContent);
		sheet.addValidationData(data_validation_view);
		return sheet;
	}

	/**
	 * 设置某些列的值只能输入预制的数据,显示下拉框.
	 * 
	 * @param sheet
	 *            要设置的sheet.
	 * @param textlist
	 *            下拉框显示的内容
	 * @param firstRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param endCol
	 *            结束列
	 * @return 设置好的sheet.
	 */
	public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
		// 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
		sheet.addValidationData(data_validation_list);
		return sheet;
	}



}
