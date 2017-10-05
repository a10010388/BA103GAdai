<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.ord_list.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<c:set var="mem_ac" value="${sessionScope.mem_ac}" scope="page"/>

<c:set var="memVO" value="${memSvc.findByPrimaryKeyNoImg(mem_ac)}"/>

	
	
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
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLrfGty4UTt0tx-W-iI8kvMI07T7vgapc"> </script>

<title>Insert title here</title>

</head>
<body>
	<div class="verticnav col-sm-4">
		<ul class="verticnav">
			<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
			<li><img src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=1" width='100'></li>
			<li><img src="" class="shop_photo"></li>
			<li><a href="#">修改店家資料</a></li>
			<li><a href="#">商品管理</a></li>
			<li><a href="#">訂單管理</a></li>
		</ul>
	</div>
	<%-- 錯誤表列 --%>
	
	<div class="shop">
		<div class="shop col-sm-8">
			<table class="table">
    		  <tr class="success" align="center">
       			<td>
       				<form action=""></form>
       				你好${mem_ac}  您尚未成為店家。，如要申請店家請點擊以下連結<br><a href="<%=request.getContextPath()%>/FrontEnd/reg_store/ToStore.jsp">點這裡</a>
       			</td>
     		 </tr>
  			</table>
		</div>
	</div>
	<br>
	<br>
	<br>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<style>
.verticnav {
	list-style: none;
	font-size: 25px;
}
.data{
	list-style: none;
}

caption {
	text-align: center;
}
.table_shop{
 width:750px;
 
}
</style>

</body>
</html>


