<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	
	String mem_ac =  (String) request.getAttribute("mem_ac");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>會員申請店家 - addStore.jsp</h3>
			</td>
			
		</tr>
	</table>

	<h3>資料店家:</h3>
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

	<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/store/ToStore.do" name="form1"
		enctype="multipart/form-data">
		<table border="0">
					<caption>您好：<%=mem_ac %></caption>
			<tr>
				<td>店家名稱:</td>
				<td><input type="TEXT" name="store_name" size="45"
					value="我家咖啡館" /></td>
			</tr>
			<tr>
				<td>統一編號:</td>
				<td><input type="TEXT" name="tax_id_no" size="45"
					value="00000000" /></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><input type="TEXT" name="store_phone" size="45"
					value="02-12534875" /></td>
			</tr>
			<tr>
				<td>店家住址:</td>
				<td><input type="TEXT" name="store_add" size="45"
					value="中壢市中大路300號" /></td>
				</td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td><textarea rows="4" cols="50" name="store_cont" value="輸入店家介紹"></textarea></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="store_add_lat" size="45"
					value="緯度" /></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="store_add_lon" size="45"
					value="經度" /></td>
			</tr>
			
			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="store_free_ship" size="45"
					value="1000" /></td>
			</tr>
			
			<tr>
				<td>證件照:</td>
				<td><input type="file" name="win_id_pic"></td>
			</tr>
			<tr>
				<td>店家照1:</td>
				<td><input type="file" name="store_pic1"></td>
			</tr>
			<tr>
				<td>店家照2:</td>
				<td><input type="file" name="store_pic2"></td>
			</tr>
			<tr>
				<td>店家照3:</td>
				<td><input type="file" name="store_pic3"></td>
			</tr>
			</FORM>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> 
			 <input type="hidden" name="mem_ac" value="mamabeak">
			 <input type="submit" value="送出新增">
	</FORM>
</body>

<script>

</script>


</html>