<%@ page contentType="text/html; charset=gb2312" language="java" import="java.io.*,java.sql.*" %>
<jsp:useBean id="conn" scope="page" class="com.zzy.mp3.ConnDB"/>
<jsp:useBean id="writeM3u" scope="page" class="com.zzy.mp3.WriteM3u"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>在线播放MP3歌曲列表</title>
</head>

<body>
<%
String url=pageContext.getServletContext().getRealPath("");   //获取系统路径
writeM3u.writeM3u(url);   //创建m3u文件
//获取MP3文件夹所在的系统路径
int at=request.getRequestURL().lastIndexOf("/");
String path_part=request.getRequestURL().substring(0,at+1);
String filepath=pageContext.getServletContext().getRealPath("mp3/list.m3u");
FileWriter fw=new FileWriter(filepath);
String path=writeM3u.getText(request,path_part);  //获取M3u文件的内容
fw.write(path);   //将数据写入m3u文件
fw.close();   //关闭I/O流

response.sendRedirect("mp3/list.m3u");   //播放m3u文件列表
%>
</body>
</html>
