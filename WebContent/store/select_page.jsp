<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>beanlife:home</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>beanlife:home</h3>
				<font color=red>( MVC )</font></td>
		</tr>
	</table>
	<h3>資料查詢:</h3>
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

	<ul>
		<li><a href='listAllStore.jsp'>List</a> all Emps.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>輸入店家編號 (如S1000000001):</b> <input type="text" name="store_no">
				<input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="storeSvc" scope="page"
			class="com.store.model.StoreService" />

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>選擇店家編號:</b> <select size="1" name=store_no>
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.store_no}">${StoreVO.store_no}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>選擇店家姓名:</b> <select size="1" name="store_no">
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.store_no}">${StoreVO.store_name}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>

	<h3>店家管理</h3>

	<ul>
		<li><a href='addStore.jsp'>Add</a> a new Store.</li>
	</ul>

</body>
</html>