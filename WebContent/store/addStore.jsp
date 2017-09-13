<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<td><input type="TEXT" name="tax_id_no" size="45"
					value="<%=(storeVO == null) ? "85487596" : storeVO.getTax_id_no()%>" /></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><input type="TEXT" name="store_phone" size="45"
					value="<%=(storeVO == null) ? "02-12345678" : storeVO.getStore_phone()%>" /></td>
			</tr>
			<tr>

				<td>店家住址:</td>
				<td><input type="TEXT" name="store_add" size="45"
					value="<%=(storeVO == null) ? "中壢市中央路" : storeVO.getStore_add()%>" /></td>
				</td>
			</tr>
			<tr>
				<td>店家名稱:</td>
				<td><input type="TEXT" name="store_name" size="45"
					value="<%=(storeVO == null) ? "媽媽嘴" : storeVO.getStore_name()%>" /></td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td><input type="TEXT" name="store_cont" size="45"
					value="<%=(storeVO == null) ? "媽媽嘴殺過人" : storeVO.getStore_cont()%>" /></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="mem_ac" size="45"
					value="<%=(storeVO == null) ? "BA105" : storeVO.getMem_ac()%>" /></td>
			</tr>
			<tr>
				<td>證件照:</td>
				<td><input type="file" name="win_id_pic"></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="store_add_lat" size="45"
					value="<%=(storeVO == null) ? "121.447666" : storeVO.getStore_add_lat()%>" /></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="store_add_lon" size="45"
					value="<%=(storeVO == null) ? "121.447666" : storeVO.getStore_add_lon()%>" /></td>
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
			<tr>
				<td>審核狀態:</td>
				<td><input type="TEXT" name="store_stat" size="45"
					value="<%=(storeVO == null) ? "待審核" : storeVO.getStore_stat()%>" /></td>
			</tr>
			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="store_free_ship" size="45"
					value="<%=(storeVO == null) ? "1000" : storeVO.getStore_free_ship()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>雇用日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="store_stat_cdate"
					value="<%=(storeVO == null) ? date_SQL : storeVO.getStore_stat_cdate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','store_stat_cdate','BTN_date');return false;">
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
