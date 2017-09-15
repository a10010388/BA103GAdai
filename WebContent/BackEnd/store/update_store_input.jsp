<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	
%>

<html>
<head>
<title>後端首頁0902</title>


	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr  align='center' valign='middle' height='20'>
			<td>
				<h3>店家資料修改 - update_store_input.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/store/listAllStore.jsp">回首頁</a>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1" enctype="multipart/form-data">
		
		<table border="0">
			<tr>
				<td>統一編號:</td>
				<td><%=storeVO.getTax_id_no()%></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><%=storeVO.getStore_phone()%></td>
			</tr>
			<tr>

				<td>店家住址:</td>
				<td><%=storeVO.getStore_add()%></td>
				</td>
			</tr>
			<tr>
				<td>店家名稱:</td>
				<td><%=storeVO.getStore_name()%></td>
			</tr>
			<tr>
				<td>緯度:</td>
				<td><%= storeVO.getStore_add_lat()%></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><%= storeVO.getStore_add_lon()%></td>
			</tr>
			
			<tr>
				<td>審核狀態:</td>
				<td><select size="1" name=store_stat>
<%-- 						<option value="<%=storeVO.getStore_stat()%>"><%=storeVO.getStore_stat()%> </option> --%>
						<option value="待審中" <%=(storeVO.getStore_stat().equals("待審中"))? "SELECTED" : ""%>>待審中</option>
　						<option value="審核通過" <%=(storeVO.getStore_stat().equals("審核通過"))? "SELECTED" : ""%>>審核通過</option>
　						<option value="審核不通過" <%=(storeVO.getStore_stat().equals("審核不通過"))? "SELECTED" : ""%>>審核不通過</option>
				</select></td>
			</tr>
			<tr>
				<td>免運費金額:</td>
				<td><%=storeVO.getStore_free_ship()%></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>審核日期:</td>
				<td >
				<%=storeVO.getStore_stat_cdate()%>
				</td>
			</tr>
			<tr>
				<td>證件照:</td>
				<td><img src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=0" width='100'>
				<td></td>
			</tr>
			<tr>
				<td>店家照1:</td>
				<td><img src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=1" width=100></td>
				<td></td>
			</tr>
			<tr>
				<td>店家照2:</td>
				<td><img src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=2" width=100></td>
				<td></td>
				
			</tr>
			<tr>
				<td>店家照3:</td>
				<td><img src="<%=request.getContextPath()%>/Store/StoreImg.do?store_no=${storeVO.store_no}&index=3" width=100></td>
				<td></td>
				
			</tr>
			
		</table>
		<br> <input type="hidden" name="action" value="update_stat"> 
		<input
			type="hidden" name="store_no" value="<%=storeVO.getStore_no()%>">
		<input
			type="hidden" name="store_stat" value="<%=storeVO.getStore_stat()%>">
		<input
			type="hidden" name="store_cont" value="<%=storeVO.getStore_cont()%>">
		<input
			type="hidden" name="tax_id_no" value="<%=storeVO.getTax_id_no()%>">
		<input
			type="hidden" name="store_phone" value="<%=storeVO.getStore_phone()%>">
		<input
			type="hidden" name="store_add" value="<%=storeVO.getStore_add()%>">
		<input
			type="hidden" name="store_name" value="<%=storeVO.getStore_name()%>">
		<input
			type="hidden" name="store_add_lat" value="<%=storeVO.getStore_add_lat()%>">
		<input
			type="hidden" name="store_add_lon" value="<%=storeVO.getStore_add_lon()%>">
		<input
			type="hidden" name="store_free_ship" value="<%=storeVO.getStore_free_ship()%>">
		<input
			type="hidden" name="store_stat_cdate" value="<%=storeVO.getStore_stat_cdate()%>">
		<input
			type="hidden" name="win_id_pic" value="<%=storeVO.getWin_id_pic()%>">
		<input
			type="hidden" name="store_pic1" value="<%=storeVO.getStore_pic1()%>">
		<input
			type="hidden" name="store_pic2" value="<%=storeVO.getStore_pic2()%>">
		<input
			type="hidden" name="store_pic3" value="<%=storeVO.getStore_pic3()%>">
		<input type="submit" value="送出修改">
	
	</FORM>
			
	
</body>
</html>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/../js/index.js"></script>


