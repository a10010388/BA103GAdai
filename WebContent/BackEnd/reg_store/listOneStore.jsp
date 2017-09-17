<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>後端首頁0902</title>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/BackEnd/res/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res//css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/css/store.css">
<script src="sorttable.js"></script>
</head>
<body>
	<div class="container_fluid titlebar">
		<a class="form-inline titlebarForm" href="main.html"><img
			class="icon" src="<%=request.getContextPath()%>/BackEnd/res/images/BeanLifeLogo2.png">
			<h1>Bean-Life</h1></a>
	</div>
	<!-- .left是左邊導覽列的部分-->
	<!-- .left選擔部分0910修正選單內容-->
	<!-- 0910 玲當部份新增下拉選單-->
	<div class="container card">
		<div class="row">
			<div class="col-xs-2 left">
				<a class="h3 title act" href="#action" aria-expanded="false"
					aria-controls="action" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-futbol-o"></div> <a class="h3" href="act.html">
						活動審核</a>
				</a><a class="h3 title" href="#check" aria-expanded="false"
					aria-controls="check" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-check-circle"></div> <span class="h3">檢舉管理</span>
					<ul class="collapse" id="check">
						<a>評論檢舉</a>
						<a>商品檢舉</a>
						<a>討論區檢舉</a>
					</ul>
				</a><a class="h3 title" href="#mem" aria-expanded="false"
					aria-controls="mem" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-address-card-o"></div> <span class="h3">會員管理</span>
					<ul class="collapse" id="mem">
						<a>會員資料管理</a>
						<a>廠商店家授權</a>
						<a>積分管理</a>
					</ul>
				</a><a class="h3 title" href="#admin" aria-expanded="false"
					aria-controls="admin" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-user-o"></div> <span class="h3">管理員管理</span>
					<ul class="collapse" id="admin">
						<a>管理帳戶授權</a>
						<a>帳戶管理</a>
					</ul>
				</a><a class="h3 title" href="#gift" aria-expanded="false"
					aria-controls="gift" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-gift"></div> <span class="h3">平台業務管理</span>
					<ul class="collapse" id="gift">
						<a>廣告管理</a>
						<a>兌換贈品管理</a>
						<a>兌換贈品業務管理</a>
					</ul>
				</a>
			</div>
			<div class="right col-xs-10">
				<div class="col-xs-12 right_top">

					<h2>你好</h2>
					<a class="fa fa-bell dropdown-toggle" href="#"
						data-toggle="dropdown"></a>
					<ul class="dropdown-menu">
						<li><a>10項檢舉未處理</a></li>
						<li><a>10項活動未審核</a></li>
						<li><a>10項廠商會員申請未審核</a></li>
						<li><a>10項兌換贈品申請</a></li>
					</ul>
				</div>

				<div class="col-xs-12 right_bottom">

<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有廠商工資料 - listnoestore1_byDAO.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/reg_store/listAllStore.jsp">回首頁</a>
			</td>
		</tr>
	</table>

	
	<table class="table-bordered onestr">
		<tr>
		<th>廠商編號</th>
		<th>會員帳號</th>
		<th>統一編號</th>
		<th>廠商住址</th>
		<th>廠商名稱</th>
		<th>證件照</th>
		<th>店家照1</th>
		<th>店家照2</th>
		<th>店家照3</th>
		<th>審核狀態</th>
		<th>不通過理由</th>
		</tr>
		<tr align='center' valign='middle'}>
			<td>${storeVO.store_no}</td>
			<td>${storeVO.mem_ac}</td>
			<td>${storeVO.tax_id_no}</td>
			<td>${storeVO.store_add}</td>
			<td>${storeVO.store_name}</td>
			<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=0" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=1" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=2" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=3" ></td>
			<td>${storeVO.store_stat}</td>
			<td>${storeVO.store_stat_cont}</td>
		</tr>

	</table>
</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/BackEnd/res/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/BackEnd/res/js/sorttable.js"></script>
</body>
</html>