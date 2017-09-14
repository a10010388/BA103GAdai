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
				<h3>所有廠商工資料 - listnoestore1_byDAO.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/store/select_page.jsp">回首頁</a>
			</td>
		</tr>
	</table>

	<% 
			byte[] data=storeVO.getWin_id_pic();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data));
			String win_id_pic = sb.toString();
			
			byte[] data1=storeVO.getStore_pic1();
			StringBuilder sb1 = new StringBuilder();
			sb1.append("data:image/png;base64,");
			sb1.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data1));
			String store_pic1 = sb1.toString();
			
			byte[] data2=storeVO.getStore_pic2();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("data:image/png;base64,");
			sb2.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data2));
			String store_pic2 = sb2.toString();
			
			byte[] data3=storeVO.getStore_pic3();
			StringBuilder sb3 = new StringBuilder();
			sb3.append("data:image/png;base64,");
			sb3.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data3));
			String store_pic3 = sb3.toString();
			%>
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
			<td><%=storeVO.getStore_no()%></td>
			<td><%=storeVO.getMem_ac()%></td>
			<td><%=storeVO.getTax_id_no()%></td>
			<td width ='80'><%=storeVO.getStore_stat()%></td>
			<td width='300'><%=storeVO.getStore_add()%></td>
			<td width ='80'><%=storeVO.getStore_name()%></td>
			<td><%=storeVO.getStore_cont()%></td>
			
			<td><img src="<%=win_id_pic %>" width=100></td>
			<td><img src="<%=store_pic1 %>" width=100></td>
			<td><img src="<%=store_pic2 %>" width=100></td>
			<td><img src="<%=store_pic3 %>" width=100></td>
		</tr>

	</table>
</body>
</html>