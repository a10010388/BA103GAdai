<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	pageContext.setAttribute("store_no", "S1000000002");
	String store_no = (String) pageContext.getAttribute("store_no");
	StoreService storeSvc = new StoreService();
	Set<ProdVO> set;
	set = storeSvc.getProdsByStore(store_no);
	pageContext.setAttribute("set", set);
	StoreVO storeVO=storeSvc.getonestore(store_no);
	pageContext.setAttribute("storeVO", storeVO); 
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
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/FrontEnd/res/css/store_list.css">

<title>Insert title here</title>

</head>
<body>
	<div class="head1 ">
		<div class="verticnav col-sm-4">
			<ul class="verticnav">
				<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
				<li><img src="LOGO.svg" class="shop_photo"></li>
				<li><a href="#">修改店家資料</a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#">訂單管理</a></li>

			</ul>

		</div>
	</div>
	
	<div class="shop">
		<div class="product col-sm-8">
			<table class="table-bordered table-responsive pro_all">
				<caption >
					<font size="20">我的商品</font>
					<ul class="verticnav">
						<li><small><a href="<%=request.getContextPath()%>/FrontEnd/prod/addprod.jsp">新增商品</a></small></li>
					</ul>	
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>商品圖片</th>
					<th>豆種</th>
					<th>價格</th>
					<th>烘培度</th>
					<th>狀態</th>
					<th></th>
					
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="ProdVO" items="${set}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${ProdVO.prod_name}</td>
						<td><img src="<%=request.getContextPath()%>/prod/prodImg.do?prod_no=${ProdVO.prod_no}&index=1"></td>
						<td>${ProdVO.bean_type}</td>
						<td>${ProdVO.prod_price}</td>
						<td>${ProdVO.roast}</td>
						<td>${ProdVO.prod_stat}</td>
						<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/prod/Prod_manag.do">
										<input type="submit" value="修改商品資料" class="btn btn-info"> 
										<input type="hidden" name="prod_no" value="${ProdVO.prod_no}">
										<input type="hidden" name="prod_no" value="${ProdVO.prod_no}">
										<input type="hidden" name="store_no"	value="<%=store_no%>">  
										<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
				<%@ include file="page2.file"%>
		</div>
	</div>

<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/BackEnd/res/js/sorttable.js"></script>
<script>
$(".pro_all img:first-child").css(
	    {"width":"70px"}
	)
</script>
</body>
</html>


