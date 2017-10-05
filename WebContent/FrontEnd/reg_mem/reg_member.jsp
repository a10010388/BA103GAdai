<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%



%>
<jsp:include page="/FrontEnd/include/head.jsp"/>
<style>
.reg_mem{
	 margin-top: 2cm;
	 height:500px;
}
</style>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	
	
<div  class="reg_mem">
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
	
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/mem/mem.do">
		<table border="1">
			<caption>
				請填寫會員資料，以註冊會員</caption>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="mem_ac" size="45"
					value="${memVO.mem_ac}" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="password" name="mem_pwd" size="45" 
					value="${memVO.mem_pwd}" /></td>
			</tr>
			
			<tr>
				<td>會員手機:</td>
				<td><input type="TEXT"  name="mem_phone" size="45"
					 value="${memVO.mem_phone}" /></td>
			</tr>
		</table>
		<input type="submit" value="確定申請會員"> 
		<input type="hidden" name="action" value="Application">
	</FORM>
</div>
</body>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>


<jsp:include page="/FrontEnd/include/footer.jsp"/>