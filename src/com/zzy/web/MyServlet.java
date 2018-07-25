package com.zzy.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * 供测试 用的servlet 2016年11月25日16:52:03
 * 
 * @author zzy
 * 
 */
public class MyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			InputStream input = req.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 接收请求发来的xml消息体
		String data = recieveData(req);
		// 解析xml获取参数
		String domain = data.substring(data.indexOf("<domain>") + 8,
				data.indexOf("</domain>"));
		// 根据参数进行第三服务接口查询，由第三方返回xml
		String xml = getAccount(domain);
		// 解析xml字符串获取返回码
		String code = xml.substring(xml.indexOf("<RETCODE>") + 9,
				xml.indexOf("</RETCODE>"));
		// 解析xml获取账号
		String account = xml.substring(xml.indexOf("<TELLERNO") + 15,
				xml.indexOf(" />") - 1);
		// 组合xml发送给请求者
		sendCompressDate(response, account, code);

		out.flush();
		out.close();
	}

	/**
	 * 接收请求发来的xml消息体
	 * 
	 * @param request
	 * @return
	 */
	public static String recieveData(HttpServletRequest request) {
		String inputLine = null;
		// 接收到的数据
		StringBuffer recieveData = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			while ((inputLine = in.readLine()) != null) {
				recieveData.append(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return recieveData.toString();
	}

	/**
	 * 根据参数发送第三服务接口查询，由第三方返回xml
	 * 
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public String SendAndGetXml(String domain) throws Exception {
		String rqestXml = "";
		String urls = "";
		try {
			// 服务器ip
			String appserverIp = "10.166.46.182";
			// 端口号
			String appserverPort = "8080";
			// 发送xml消息体
			rqestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><domain>"
					+ domain + "</domain></root>";
			// 访问远程接接口
			urls = "http://" + appserverIp + ":" + appserverPort
					+ "/ICBCCattestationM/validate";
		} catch (Exception e) {
			throw new Exception();
		}
		// 使用HttpURLConnection发送http请求
		byte[] xmlbyte = rqestXml.getBytes("UTF-8");
		URL url = new URL(urls);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false);// 不使用Cache
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length",
				String.valueOf(xmlbyte.length));
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		DataOutputStream outStream = new DataOutputStream(
				conn.getOutputStream());
		outStream.write(xmlbyte);// 发送xml数据
		outStream.flush();
		outStream.close();
		// 解析返回来的xml消息体
		byte[] msgBody = null;
		if (conn.getResponseCode() != 200)
			throw new RuntimeException("请求url失败");
		InputStream is = conn.getInputStream();// 获取返回数据
		byte[] temp = new byte[1024];
		int n = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((n = is.read(temp)) != -1) {
			bos.write(temp, 0, n);
		}
		msgBody = bos.toByteArray();
		bos.close();
		is.close();
		String returnXml = new String(msgBody, "UTF-8").trim();
		conn.disconnect();
		// 以下下游解析XML
		System.out.println(returnXml);
		return returnXml;
	}

	/** 组合xml返回给请求者 */
	public static void sendCompressDate(HttpServletResponse response,
			String account, String retCode) {
		BufferedWriter out = null;
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		sb.append("<result>");
		sb.append("<retCode>" + retCode + "</retCode>");
		if (!"ValidateServlet".equals(account)) {
			sb.append("<ucAccount>" + account + "</ucAccount>");
		}
		sb.append("</result>");
		try {
			System.out.println("Message,data[" + sb.toString() + "]");
			out = new BufferedWriter(new OutputStreamWriter(
					response.getOutputStream(), "UTF-8"));
			out.write(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				out = null;
			}
		}
	}

	/**
	 * 根据参数进行第三服务接口查询，由第三方返回xml
	 * 
	 * @param domain
	 * @return
	 */
	public String getAccount(String domain) {
		String url = "http://10.166.106.107:8080/authServer/servlet/QueryUniformTellerInfo";
		URL sendUrl = null;
		HttpURLConnection con = null;
		StringBuffer retStr = new StringBuffer();
		InputStream in = null;
		BufferedReader bufferedReader = null;
		String enc = "gbk";
		String info = domain;
		String flag = "1";
		try {
			url += "?queryEnc=" + enc + "&queryInfo=" + URLEncoder.encode(info)
					+ "&queryFlag=" + flag;
			sendUrl = new URL(url);
			con = (HttpURLConnection) sendUrl.openConnection();
			if (con.getResponseCode() == 200) {
				in = con.getInputStream();
				bufferedReader = new BufferedReader(new InputStreamReader(in));
				String temp = bufferedReader.readLine();
				while (temp != null) {
					retStr.append(temp);
					temp = bufferedReader.readLine();
				}
				bufferedReader.close();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 异常处理
		} finally {
			try {
				bufferedReader.close();
				in.close();
			} catch (Exception e2) {
				// 异常处理
				e2.printStackTrace();
			}
			con.disconnect();
		}
		String xmlStr = retStr.toString();
		// 以下下游解析XML
		return xmlStr;
	}

}
