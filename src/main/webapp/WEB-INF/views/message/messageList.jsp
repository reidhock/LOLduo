<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
<%@ include file="/WEB-INF/include/nav.jsp"%>
<meta charset="utf-8">
<title>메세지함</title>
<style type="text/css">
#modalStyle {
		max-width: 800px;
		margin: 1.75rem auto
	}
</style>
</head>
<body>
<div style=width:60%;margin:auto;>
<div style=text-align:center;font-size:30px;>메세지함</div>
<table class="table table-hover" >
  <tbody class="table-hover">
    <c:forEach items="${list}" var="list">
		<tr class="table-light" <%-- onclick="fnOpen(${list.MESSAGE_NUM})" --%>>
			<td>${list.RNUM}</td>
			<td>${list.MESSAGE_CONTENT}</td>
			<td>${list.MESSAGE_SENDER}</td>
			<td>${list.SEND_DATE }</td>
		</tr>
	</c:forEach>
  </tbody>
</table>
</div>
</body>
</html>