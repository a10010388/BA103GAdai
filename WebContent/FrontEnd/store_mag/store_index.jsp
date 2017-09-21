<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	pageContext.setAttribute("STORE_NO", "S1000000002");
	String STORE_NO = (String) pageContext.getAttribute("STORE_NO");
	StoreService storeSvc = new StoreService();
	Set<ProdVO> set;
	set = storeSvc.getProdsByStore(STORE_NO);
	pageContext.setAttribute("set", set);
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
	href="<%=request.getContextPath()%>/FrontEnd/res/css/store_index.css">
<title>Insert title here</title>

</head>
<body>
	<div class="head1 ">
		<div class="verticnav col-sm-4">
			<ul class="verticnav ">
				<li>店家認證狀態：</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功</li>
				<li><img src="LOGO.svg" class="shop_photo"></li>
				<li><a href="#">修改店家資料</a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#">訂單管理</a></li>

			</ul>

		</div>
	</div>
	<div class="shop">
		<div class="shop col-sm-4">
			<table class="table_order">
				<caption>
					<big>最新訂單</big><a href="#"><small>更多</small></a>
				</caption>
				<tr>
					<th>下單日期</th>
					<th>買家名稱</th>
					<th>商品</th>
					<th>總金額</th>
				</tr>
				<tr>
					<td>下單日期</td>
					<td>買家名稱</td>
					<td>商品</td>
					<td>總金額</td>
				</tr>
				<tr>
					<td>下單日期</td>
					<td>買家名稱</td>
					<td>商品</td>
					<td>總金額</td>
				</tr>
				<tr>
					<td>下單日期</td>
					<td>買家名稱</td>
					<td>商品</td>
					<td>總金額</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="shop">
		<div class="product col-sm-4">
			<table class="table-bordered table-responsive">
				<caption>
					<big>我的商品</big><a href="<%=request.getContextPath()%>/FrontEnd/store_mag/listAllpro_bystore.jsp"><small>更多</small></a>
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>商品圖片</th>
					<th>商品</th>
					<th>烘焙度</th>
				</tr>
				<c:forEach var="ProdVO" items="${set}" begin="0" end="4">
					<tr>
						<td>${ProdVO.prod_name}</td>
						<td><img src="<%=request.getContextPath()%>/prod/prodImg.do?prod_no=${ProdVO.prod_no}&index=1" class="product_photo"></td>
						<td>${ProdVO.prod_price}</td>
						<td>${ProdVO.roast}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	


	
















	<div class="shop">
		<div class="product col-sm-4 col-sm-offset-4">
			<table class="table_product">
				<caption>
					<big>最新商品評論</big><a href="#"><small>更多</small></a>
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>商品圖片</th>
					<th>商品評分</th>
					<th>商品心得</th>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="shop">
		<div class="product col-sm-4">
			<table class="table_product">
				<caption>
					<big>最新詢問</big><a href="#"><small>更多</small></a>
				</caption>
				<tr>
					<th>發問時間</th>
					<th>會員帳號</th>
					<th>問題敘述</th>
					<th>商品連結</th>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
			</table>
		</div>
	</div>

</body>


</html>