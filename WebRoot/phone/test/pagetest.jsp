<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach items="${list }" var="u">

<table class="mxlan" style="width:100%;">
	<tr  style="height:300px;width:100%; background-color: blue;color: white;">
		<td style="width: 30%">${u.uid }</td>
		<td style="width: 40%">${u.name }</td>
		<td style="width: 30%">${u.qq }</td>
	</tr>
</table>

</c:forEach>

