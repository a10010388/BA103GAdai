<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
String mem_ac = (String) session.getAttribute("mem_ac");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="container ">
	<div class="row">
		<div class="table-responsive">
			<table class="table">
    		  <tr class="success" align="center">
       			<td>
       				<form action=""></form>
       				你好<%=mem_ac%>  您已申請成為店家，請靜待管理員審核。<br><a href="<%=request.getContextPath()%>/FrontEnd/update_store/store_index.jsp">回首頁</a>
       			</td>
     		 </tr>
  			</table>
		</div>
	</div>
</div>


</body>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>