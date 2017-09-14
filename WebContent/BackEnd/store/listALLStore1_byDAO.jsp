<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	StoreJDBCDAO dao = new StoreJDBCDAO();
	List<StoreVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有店家資料 - listAllEmp1_byDAO.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廠商工資料 - listAllEmp1_byDAO.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>廠商編號</th>
		<th>會員帳號</th>
		<th>統編</th>
		<th>審核狀態</th>
		<th>廠商住址</th>
		<th>廠商名稱</th>
		<th>廠商介紹</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="StoreVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'}>
			<td>${StoreVO.STORE_NO}</td>
			<td>${StoreVO.MEM_AC}</td>
			<td>${StoreVO.TAX_ID_NO}</td>
			<td>${StoreVO.STORE_STAT}</td>
			<td>${StoreVO.STORE_ADD}</td>
			<td>${StoreVO.STORE_NAME}</td>
			<td>${StoreVO.STORE_CONT}</td>
		</tr>
	
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>
