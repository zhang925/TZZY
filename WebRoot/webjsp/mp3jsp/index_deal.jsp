<%@ page contentType="text/html; charset=gb2312" language="java" import="java.io.*,java.sql.*" %>
<jsp:useBean id="conn" scope="page" class="com.zzy.mp3.ConnDB"/>
<jsp:useBean id="writeM3u" scope="page" class="com.zzy.mp3.WriteM3u"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���߲���MP3�����б�</title>
</head>

<body>
<%
String url=pageContext.getServletContext().getRealPath("");   //��ȡϵͳ·��
writeM3u.writeM3u(url);   //����m3u�ļ�
//��ȡMP3�ļ������ڵ�ϵͳ·��
int at=request.getRequestURL().lastIndexOf("/");
String path_part=request.getRequestURL().substring(0,at+1);
String filepath=pageContext.getServletContext().getRealPath("mp3/list.m3u");
FileWriter fw=new FileWriter(filepath);
String path=writeM3u.getText(request,path_part);  //��ȡM3u�ļ�������
fw.write(path);   //������д��m3u�ļ�
fw.close();   //�ر�I/O��

response.sendRedirect("mp3/list.m3u");   //����m3u�ļ��б�
%>
</body>
</html>
