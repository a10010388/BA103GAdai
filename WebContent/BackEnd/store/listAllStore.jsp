<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	StoreService storeSvc = new StoreService();
	List<StoreVO> list = storeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有店家資料-listALLStore.jsp</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>所有店家資料-listALLStore.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/store/select_page.jsp">回首頁</a></td>
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
			<th>店家照1</th>
			<th>店家照1</th>
		</tr>
		<%@ include file="page1.file"%>

		<% 
		for(int i=pageIndex;(i<list.size()) && (i<pageIndex+rowsPerPage-1);i++){
		%>

		<tr align='center' valign='middle'>
			<% 
			byte[] data=list.get(i).getWin_id_pic();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data));
			String win_id_pic = sb.toString();
			
			byte[] data1=list.get(i).getStore_pic1();
			StringBuilder sb1 = new StringBuilder();
			sb1.append("data:image/png;base64,");
			sb1.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data1));
			String store_pic1 = sb1.toString();
			
			byte[] data2=list.get(i).getStore_pic2();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("data:image/png;base64,");
			sb2.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data2));
			String store_pic2 = sb2.toString();
			
			byte[] data3=list.get(i).getStore_pic3();
			StringBuilder sb3 = new StringBuilder();
			sb3.append("data:image/png;base64,");
			sb3.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data3));
			String store_pic3 = sb3.toString();
			%>
			<td><%=list.get(i).getStore_no()%></td>
			<td><%=list.get(i).getMem_ac()%></td>
			<td><%=list.get(i).getTax_id_no()%></td>
			<td width='80'><%=list.get(i).getStore_stat()%></td>
			<td width='300'><%=list.get(i).getStore_add()%></td>
			<td width='80'><%=list.get(i).getStore_name()%></td>
			<td width='500'><%=list.get(i).getStore_cont()%></td>
			<td><img src="<%=win_id_pic %>" width=100></td>
			<td><img src="<%=store_pic1 %>" width=100></td>
			<td><img src="<%=store_pic2 %>" width=100></td>
			<td><img src="<%=store_pic3 %>" width=100></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/store/store.do">
					<input type="submit" value="修改"> <input type="hidden"
						name="store_no" value="<%=list.get(i).getStore_no()%>"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/store/store.do">
					<input type="submit" value="刪除"> <input type="hidden"
						name="store_no" value="<%=list.get(i).getStore_no()%>"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	 <% 	} %>

	</table>


	<%@ include file="page2.file"%>




</body>
</html>