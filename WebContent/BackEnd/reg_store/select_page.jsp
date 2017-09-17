<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>後端首頁0902</title>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/BackEnd/res/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/css/store.css">
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
					<div class="fa fa-futbol-o"></div>
					<a class="h3" href="act.html"> 活動審核</a>
				</a><a class="h3 title" href="#check" aria-expanded="false"
					aria-controls="check" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-check-circle"></div>
					<span class="h3">檢舉管理</span>
					<ul class="collapse" id="check">
						<a>評論檢舉</a>
						<a>商品檢舉</a>
						<a>討論區檢舉</a>
					</ul>
				</a><a class="h3 title" href="#mem" aria-expanded="false"
					aria-controls="mem" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-address-card-o"></div>
					<span class="h3">會員管理</span>
					<ul class="collapse" id="mem">
						<a>會員資料管理</a>
						<a>廠商店家授權</a>
						<a>積分管理</a>
					</ul>
				</a><a class="h3 title" href="#admin" aria-expanded="false"
					aria-controls="admin" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-user-o"></div>
					<span class="h3">管理員管理</span>
					<ul class="collapse" id="admin">
						<a>管理帳戶授權</a>
						<a>帳戶管理</a>
					</ul>
				</a><a class="h3 title" href="#gift" aria-expanded="false"
					aria-controls="gift" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-gift"></div>
					<span class="h3">平台業務管理</span>
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
				
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>beanlife:home</h3>
				<font color=red>( MVC )</font></td>
		</tr>
	</table>
	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<ul>
		<li><a href='listAllStore.jsp'>List</a> all Emps.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>輸入店家編號 (如S1000000001):</b> <input type="text" name="store_no">
				<input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="storeSvc" scope="page"
			class="com.store.model.StoreService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>選擇店家編號:</b> <select size="1" name=store_no>
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.store_no}">${StoreVO.store_no}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
				<b>選擇店家姓名:</b> <select size="1" name="store_no">
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.store_no}">${StoreVO.store_name}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>

	<h3>店家管理</h3>

	<ul>
		<li><a href='addStore.jsp'>Add</a> a new Store.</li>
	</ul>
	</div>

			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/BackEnd/res/js/index.js"></script>
</body>
</html>
</body>
</html>