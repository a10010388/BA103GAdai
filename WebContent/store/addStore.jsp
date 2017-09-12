<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<html>
<head>
<title>店家新增 - addStore.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>店家資料新增 - addEmp.jsp</h3>
			</td>
			<td><a href="select_page.jsp">回首頁</a></td>
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

	<FORM METHOD="POST" ACTION="store.do" name="form1"
		enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>統一編號:</td>
				<td><input type="TEXT" name="TAX_ID_NO" size="45"
					value="<%=(storeVO == null) ? "85487596" : storeVO.getTAX_ID_NO()%>" /></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><input type="TEXT" name="STORE_PHONE" size="45"
					value="<%=(storeVO == null) ? "02-12345678" : storeVO.getSTORE_PHONE()%>" /></td>
			</tr>
			<tr>

				<td>店家住址:</td>
				<td><input type="TEXT" name="STORE_ADD" size="45"
					value="<%=(storeVO == null) ? "中壢市中央路" : storeVO.getSTORE_ADD()%>" /></td>
				</td>
			</tr>
			<tr>
				<td>店家名稱:</td>
				<td><input type="TEXT" name="STORE_NAME" size="45"
					value="<%=(storeVO == null) ? "媽媽嘴" : storeVO.getSTORE_NAME()%>" /></td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td><input type="TEXT" name="STORE_CONT" size="45"
					value="<%=(storeVO == null) ? "媽媽嘴殺過人" : storeVO.getSTORE_CONT()%>" /></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="MEM_AC" size="45"
					value="<%=(storeVO == null) ? "BBBBAAA" : storeVO.getMEM_AC()%>" /></td>
			</tr>
			<tr>
				<td>證件照:</td>
				<td><input type="file" name="WIN_ID_PIC"></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="STORE_ADD_LAT" size="45"
					value="<%=(storeVO == null) ? "121.447666" : storeVO.getSTORE_ADD_LAT()%>" /></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="STORE_ADD_LON" size="45"
					value="<%=(storeVO == null) ? "121.447666" : storeVO.getSTORE_ADD_LON()%>" /></td>
			</tr>
			<tr>
				<td>店家照1:</td>
				<td><input type="file" name="STORE_PIC1"></td>
			</tr>
			<tr>
				<td>店家照2:</td>
				<td><input type="file" name="STORE_PIC2"></td>
			</tr>
			<tr>
				<td>店家照3:</td>
				<td><input type="file" name="STORE_PIC3"></td>
			</tr>
			<tr>
				<td>審核狀態:</td>
				<td><input type="TEXT" name="STORE_STAT" size="45"
					value="<%=(storeVO == null) ? "待審核" : storeVO.getSTORE_STAT()%>" /></td>
			</tr>
			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="STORE_FREE_SHIP" size="45"
					value="<%=(storeVO == null) ? "1000" : storeVO.getSTORE_FREE_SHIP()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>雇用日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="STORE_STAT_CDATE"
					value="<%=(storeVO == null) ? date_SQL : storeVO.getSTORE_STAT_CDATE()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','STORE_STAT_CDATE','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>
			</FORM>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

</html>
