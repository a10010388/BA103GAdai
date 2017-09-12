<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>店家資料修改 - update_emp_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>店家資料修改 - update_store_input.jsp</h3> <a href="select_page.jsp">回首頁</a>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>
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

	<FORM METHOD="post" ACTION="store.do" name="form1">
		<table border="0">
			<tr>
				<td>店家編號:<font color=red><b>*</b></font></td>
				<td><%=storeVO.getSTORE_NO()%></td>
			</tr>
			<tr>
				<td>店家名稱::<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="STORE_NAME" size="45"
					value="<%=storeVO.getSTORE_NAME()%>" /></td>
			</tr>
			<tr>
				<td>統一編號::<font color=red><b>*</b></font></td>
				<td><%=storeVO.getTAX_ID_NO()%></td>
			</tr>
			<tr>
				<td>審核狀態:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="TAX_ID_NO" size="45"
					value="<%=storeVO.getSTORE_STAT()%>" /></td>
			</tr>
			<tr>
				<td>店家電話:</td>
				<td><input type="TEXT" name="STORE_PHONE" size="45"
					value="<%=storeVO.getSTORE_PHONE()%>" /></td>
			</tr>
			<tr>
				<td>店家住址:</td>
				<td><input type="TEXT" name="STORE_ADD" size="45"
					value="<%=storeVO.getSTORE_ADD()%>" /></td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td><input type="TEXT" name="STORE_CONT" size="45"
					value="<%=storeVO.getSTORE_CONT()%>" /></td>
			</tr>
			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="STORE_FREE_SHIP" size="45"
					value="<%=storeVO.getSTORE_FREE_SHIP()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="STORE_NO" value="<%=storeVO.getSTORE_NO()%>">
		<input type="submit" value="送出修改">
	</FORM>

</body>
</html>
