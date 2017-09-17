<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
pageContext.setAttribute("mem_ac", "mamabeak");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/store/ToStore.do">
		<input type="submit" value="申請店家"> 
		<input type="hidden" name="mem_ac" value="mamabeak">
		<input type="hidden" name="action" value="Application">
	</FORM>
1111111111111111
</body>
</html>