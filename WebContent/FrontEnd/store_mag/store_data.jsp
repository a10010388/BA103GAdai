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
<%
	request.setAttribute("mem_ac", "mrbrown");
	request.setAttribute("store_no", "S1000000002");
	String store_no = (String) request.getAttribute("store_no");
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<c:set var="memVO" value="${memSvc.findByPrimaryKeyNoImg(mem_ac)}"/>
<c:set var="storeVO" value="${storeSvc.getonestore(store_no)}"/>
<jsp:useBean id="prodSvc" scope="page"
	class="com.prod.model.ProdService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<c:set var="ord_listVOs"
	value="${ordSvc.getOrd_listByOrd(ordVO.ord_no)}" />
	
	
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

<title>Insert title here</title>

</head>
<body>
	<div class="verticnav col-sm-4">
		<ul class="verticnav">
			<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
			<li><img src="" class="shop_photo"></li>
			<li><a href="#">修改店家資料</a></li>
			<li><a href="#">商品管理</a></li>
			<li><a href="#">訂單管理</a></li>
		</ul>
	</div>
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
	<div class="shop">
		<div class="shop col-sm-8">
			<table class="table_shop">
				<caption>
					<big><b>修改店家資料</b></big><br>
					<ul class="data">
						<li>這是您的會員資料，請確認下列資料是否正確。</li>
						<li>星號 * 為必填欄位。</li>
					</ul>
				</caption>
				<tr>
					<th>設定帳戶</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>會員帳號</td>
					<td>${mem_ac}</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>公司審核狀態：</th>
					<th>${storeVO.store_stat}</th>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<td>公司名稱</td>
					<td><input type="text" name="store_name" value="${storeVO.store_name}"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>負責人姓名</td>
					<td>${memVO.mem_lname}${memVO.mem_fname}</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>公司統一編號</td>
					<td><input type="text" name="tax_id_no" value="${storeVO.tax_id_no}"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>免運費金額</td>
					<td><input type="text" name="store_free_ship" value="${storeVO.store_free_ship}"></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>公司電話</td>
					<td><input type="text" name=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>公司地址</td>
					<td><input type="text" name=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>公司介紹</td>
					<td><textarea rows="4" cols="50">
At w3schools.com you will learn how to make a website. We offer free tutorials in all web development technologies. 
											</textarea></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>證件照片</td>
					<td><img src="LOGO.svg" class="shop_photo"></td>
					<td><input type="button" value="上傳"></td>
					<td></td>
				</tr>
				<tr>
					<td>店家照片1</td>
					<td><img src="LOGO.svg" class="shop_photo"></td>
					<td><input type="button" value="上傳"></td>
					<td></td>
				</tr>
				<tr>
					<td>店家照片2</td>
					<td><img src="LOGO.svg" class="shop_photo"></td>
					<td><input type="button" value="上傳"></td>
					<td></td>
				</tr>
				<tr>
					<td>店家照片3</td>
					<td><img src="LOGO.svg" class="shop_photo"></td>
					<td><input type="button" value="上傳"></td>
					<td></td>
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
	<script>
		
	</script>
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
</style>
</body>
</html>


