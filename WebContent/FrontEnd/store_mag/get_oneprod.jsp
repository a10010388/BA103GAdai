<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%

	String store_no =request.getParameter("store_no");
	
	String prod_no = request.getParameter("prod_no");
	ProdService proSvc = new ProdService();

	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/FrontEnd/res/css/store_list.css">
<title>Insert title here</title>

</head>
<body>
	<div class="head1 ">
		<div class="verticnav col-sm-4">
			<ul class="verticnav ">
				<li>店家認證狀態：</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
				<li><img src="LOGO.svg" class="shop_photo"></li>
				<li><a href="#">修改店家資料</a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#">訂單管理</a></li>

			</ul>

		</div>
	</div>

	<div class="shop">
		<div class="product col-sm-4">
			<table class=" table-bordered table-responsive pro_one">

				<tr><td>商品名稱</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>豆種</td><td><input type="text" name="bean_type" value="${prodvo.bean_type}"></td></tr>
				<tr><td>生豆等級</td><td><input type="radio" name="BEAN_GRADE" value="${prodvo.bean_grade}"></td></tr>
									   <input type="radio" name="BEAN_GRADE" value="Taipei"> 台北<br>
									   <input type="radio" name="BEAN_GRADE" value="Taoyuan"> 桃園<br>
				<tr><td>生產國</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>地區</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>農場</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>生產者</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>海拔</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>處理法</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>烘焙度</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>風味-酸度</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>風味-香氣</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>風味-醇度</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>風味-餘味</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>風味-平衡度</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>香味</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>標價  $NT</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>重量  lb</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>運費</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>供應數量</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>商品描述</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>商品圖片-1</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>商品圖片-2</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>商品圖片-3</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>上架狀態</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<tr><td>商品描述</td><td><input type="text" name="prod_name" value="${prodvo.prod_name}"></td></tr>
				<td>
					<FORM METHOD="post" mACTION="<%=request.getContextPath()%>/prod/Prod_manag.do">
						<input type="submit" value="修改商品資料" class="btn btn-info"> 
						<input type="hidden" name="prod_no" value="${prodvo.prod_no}">
						
						<input type="hidden" name="action" value="getOne_For_Update">
						
					</FORM>
				</td>
			</table>
			
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>


