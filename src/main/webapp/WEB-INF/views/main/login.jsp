<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf"%>
<script type="text/javascript">
$(document).ready(function(){ 
	var message = "${message}";
	if(message!=null && message!=''){
		alert(message);
	}else{
		location.href = "<c:url value='/main'/>";
	}
})
</script>
</head>
</html>