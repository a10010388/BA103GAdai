<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
pageContext.setAttribute("mem_ac", "mrbrown");
String mem = (String) pageContext.getAttribute("mem_ac");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>

</head>
<body>
	<div class ="head1 " > 
		<div class="verticnav col-sm-4">
			<ul class ="verticnav ">
				<li>店家認證狀態：</li>
				<li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功</li>
				<li><img src="LOGO.svg" class="shop_photo"></li>
				<li><a href="#">"檢視/修改店家資料</a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#">訂單管理</a></li>
			</ul>
		</div>
	</div>
	你好<%=mem%>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/store/ToStore.do">
		<input type="submit" value="檢視/修改店家資料"> 
		<input type="hidden" name="mem_ac" value="mrbrown">
		<input type="hidden" name="action" value="getOne_For_Update">
	</FORM>
1111111111111111
</body>


</html>