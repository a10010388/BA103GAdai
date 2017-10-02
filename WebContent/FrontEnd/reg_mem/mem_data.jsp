<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.prod.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
MemVO memVO=(MemVO) request.getAttribute("memVO");

%>
<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService"/>
<c:set var="prodVOs" value="${prodSvc.getAll()}"/>

<jsp:include page="/FrontEnd/include/head.jsp"/>
<style>
.reg_mem{
	 margin-top: 2cm;
	 height:500px;
}
</style>

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
	
	
<div  class="reg_mem">
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
	
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/mem/mem.do" enctype="multipart/form-data">
		<table border="1">
			<caption>
				您好${memVO.mem_ac}，恭喜你成為會員，請完善您的資料</caption>
			<tr>
				<td>會員姓氏:</td>
				<td><input type="TEXT" name="mem_lname" size="45"
					value="" /></td>
			</tr>
			<tr>
				<td>會員名字:</td>
				<td><input type="TEXT" name="mem_fname" size="45" 
					value="" /></td>
			</tr>
			<tr>
				<td>會員Email:</td>
				<td><input type="TEXT"  name="mem_email" size="45"
					 value="" /></td>
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT"  name="mem_add" size="45"
					 value="" /></td>
			</tr>
			<tr>
				<td>會員圖片:</td>
				<td><input type="file"  name="mem_pic" size="45"
					 value="" /></td>
			</tr>
			
			<tr>
				<td>搜尋偏好設定:國家</td>
				<td>
					<select name="mem_set1">
						<c:forEach var="prodVO"  items="${prodVOs}">
							<option value="${prodVO.bean_contry}">${prodVO.bean_contry}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>搜尋偏好設定:處理法</td>
				<td>
					<input type="radio" name="mem_set2" value="日曬">日曬<br>
					<input type="radio" name="mem_set2" value="半水洗">半水洗<br>
					<input type="radio" name="mem_set2" value="水洗">水洗<br>
					<input type="radio" name="mem_set2" value="蜜處理">蜜處理<br>
				</td>
			</tr>
			<tr>
				<td>搜尋偏好設定:烘焙度</td>
				<td>
					<select name="mem_set3">
						<option value="極淺焙">極淺焙</option>
						<option value="淺焙">淺焙</option>
						<option value="中焙">中焙</option>
						<option value="中深焙">中深焙</option>
						<option value="城市烘焙">城市烘焙</option>
						<option value="深焙">深焙</option>
						<option value="法式烘焙">法式烘焙</option>
						<option value="重焙">重焙</option>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="確定送出"> 
		<input type="hidden" value="${memVO.mem_ac}" name="mem_ac">
		<input type="hidden" name="action" value="update_data">
	</FORM>
	
	
	
	
	
</div>
</body>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>


<jsp:include page="/FrontEnd/include/footer.jsp"/>