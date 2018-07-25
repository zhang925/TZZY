package com.zzy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * md5加密
 * @author long
 *
 */
public class util_Md5 {
	/**
	 * Str 转成MD5(字母大写)
	 * @param s
	 * @return  MD5 str
	 */
	 public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	        try {
	            byte[] btInput = s.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
	 
	 
	    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
	            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    protected static MessageDigest messagedigest = null;
	    static {
	        try {
	            messagedigest = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("MD5FileUtil messagedigest初始化失败");
	        }
	    }
	    
	    /**
	     * 获取文件的md5值(文件应小于2G)
	     * @param file
	     * @return
	     * @throws IOException
	     */
	    public static String getFileMD5String(File file) throws IOException {
	        FileInputStream in = new FileInputStream(file);
	        FileChannel ch = in.getChannel();
	        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
	                file.length());
	        messagedigest.update(byteBuffer);
	        return bufferToHex(messagedigest.digest());
	    }
	    
	    /**
	     * 将String进行md5加密(字母小写)
	     * @param s
	     * @return
	     */
	    public static String getMD5String(String s) {
	        return getMD5String(s.getBytes());
	    }
	    
	    /**
	     * 将byte数组进行md5加密
	     * @param bytes
	     * @return
	     */
	    public static String getMD5String(byte[] bytes) {
	        messagedigest.update(bytes);
	        return bufferToHex(messagedigest.digest());
	    }
	    
	    /**
	     * 转换为16进制表示
	     * @param bytes
	     * @return
	     */
	    private static String bufferToHex(byte bytes[]) {
	        return bufferToHex(bytes, 0, bytes.length);
	    }
	    
	    /**
	     * 转换为16进制表示
	     * @param bytes
	     * @param m
	     * @param n
	     * @return
	     */
	    private static String bufferToHex(byte bytes[], int m, int n) {
	        StringBuffer stringbuffer = new StringBuffer(2 * n);
	        int k = m + n;
	        for (int l = m; l < k; l++) {
	            appendHexPair(bytes[l], stringbuffer);
	        }
	        return stringbuffer.toString();
	    }
	    
	    /**
	     * 16进制追加
	     * @param bt-追加内容
	     * @param stringbuffer
	     */
	    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	        char c0 = hexDigits[(bt & 0xf0) >> 4];
	        char c1 = hexDigits[bt & 0xf];
	        stringbuffer.append(c0);
	        stringbuffer.append(c1);
	    }
	    
	    /**
	     * 判断明文与密文是否对应
	     * @param password-明文
	     * @param md5PwdStr-密文
	     * @return
	     */
	    public static boolean checkPassword(String password, String md5PwdStr) {
	        String s = getMD5String(password);
	        return s.equals(md5PwdStr);
	    }
	 
}
