<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>店家資料-listOnestore.jsp</title>
</head>

<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有廠商工資料 - listnoestore1_byDAO.jsp</h3> <a href="select_page.jsp">回首頁</a>
			</td>
		</tr>
	</table>


	<table border='1' bordercolor='#CCCCFF' width='1500'>
		<tr>
		<th>廠商編號</th>
		<th>會員帳號</th>
		<th>統一編號</th>
		<th>審核狀態</th>
		<th>廠商住址</th>
		<th>廠商名稱</th>
		<th>廠商介紹</th>
		<th>證件照</th>
		<th>店家照1</th>
		<th>店家照2</th>
		<th>店家照3</th>
		</tr>
		<tr align='center' valign='middle'}>
			<td><%=storeVO.getSTORE_NO()%></td>
			<td><%=storeVO.getMEM_AC()%></td>
			<td><%=storeVO.getTAX_ID_NO()%></td>
			<td width ='80'><%=storeVO.getSTORE_STAT()%></td>
			<td width='300'><%=storeVO.getSTORE_ADD()%></td>
			<td width ='80'><%=storeVO.getSTORE_NAME()%></td>
			<td><%=storeVO.getSTORE_CONT()%></td>
			
			<td><%=storeVO.getWIN_ID_PIC()%></td>
			<td><%=storeVO.getSTORE_PIC1()%></td>
			<td><%=storeVO.getSTORE_PIC2()%></td>
			<td><%=storeVO.getSTORE_PIC3()%></td>
		</tr>

	</table>
</body>
</html>