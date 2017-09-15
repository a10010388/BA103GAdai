<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>後端首頁0902</title>

</head>

<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有廠商工資料 - listnoestore1_byDAO.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/store/listAllStore.jsp">回首頁</a>
			</td>
		</tr>
	</table>

	
	<table border='1' bordercolor='#CCCCFF' width='1500'>
		<tr>
		<th>廠商編號</th>
		<th>會員帳號</th>
		<th>統一編號</th>
		<th>廠商住址</th>
		<th>廠商名稱</th>
		<th>證件照</th>
		<th>店家照1</th>
		<th>店家照2</th>
		<th>店家照3</th>
		<th>審核狀態</th>
		</tr>
		<tr align='center' valign='middle'}>
			<td>${storeVO.store_no}</td>
			<td>${storeVO.mem_ac}</td>
			<td>${storeVO.tax_id_no}</td>
			<td>${storeVO.store_add}</td>
			<td>${storeVO.store_name}</td>
			<td><img width="100px" src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=0" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=1" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=2" ></td>
			<td><img width="100px" src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=3" ></td>
			<td>${storeVO.store_stat}</td>
		</tr>

	</table>

	
</body>
</html>