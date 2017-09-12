<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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
	<h3>��Ƭd��:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:
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
				<b>��J���a�s�� (�pS1000000001):</b> <input type="text" name="STORE_NO">
				<input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="storeSvc" scope="page"
			class="com.store.model.StoreService" />

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>��ܩ��a�s��:</b> <select size="1" name=STORE_NO>
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.STORE_NO}">${StoreVO.STORE_NO}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>��ܩ��a�m�W:</b> <select size="1" name="STORE_NO">
					<c:forEach var="StoreVO" items="${storeSvc.all}">
						<option value="${StoreVO.STORE_NO}">${StoreVO.STORE_NAME}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>

	<h3>���a�޲z</h3>

	<ul>
		<li><a href='addStore.jsp'>Add</a> a new Store.</li>
	</ul>

</body>
</html>