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
			<td><h3>所有店家資料-listALLStore.jsp</h3> <a href="select_page.jsp">回首頁</a></td>
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

		<c:forEach var="StoreVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr align='center' valign='middle'}>
				<td>${StoreVO.store_no}</td>
				<td>${StoreVO.mem_ac}</td>
				<td>${StoreVO.tax_id_no}</td>
				<td width='80'>${StoreVO.store_stat}</td>
				<td width='300'>${StoreVO.store_add}</td>
				<td width='80'>${StoreVO.store_name}</td>
				<td width='500'>${StoreVO.store_cont}</td>
				<td>${StoreVO.win_id_pic}></td>
				<td>${StoreVO.store_pic1}></td>
				<td>${StoreVO.store_pic2}></td>
				<td>${StoreVO.store_pic3}></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/store.do">
						<input type="submit" value="修改"> <input type="hidden"
							name="store_no" value="${StoreVO.store_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/store.do">
						<input type="submit" value="刪除"> <input type="hidden"
							name="store_no" value="${StoreVO.store_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>

		</c:forEach>
	</table>
	
	
	<%@ include file="page2.file"%>




</body>
</html>