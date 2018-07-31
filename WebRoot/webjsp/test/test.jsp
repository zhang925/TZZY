<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--引入自定义标签--%>
<%@ taglib prefix="ex" uri="../../../WEB-INF/custom.tld"%>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--<jsp:useBean id="user" scope="page" class="com.sunny.entity.User" />
<jsp:setProperty name="user" property="name"  value="这是姓名"/>
<h3>  <%=user.getName()%>  输出：${user.name} </h3>
--%>


<h2>
    <ex:sunny tip="这是一个placeholder" ></ex:sunny>
    <br/>
     自定义标签：<ex:Hello message="Hello myTag">1</ex:Hello>

</h2>

<div>


</div>
</body>
</html>
