<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.ord_list.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	
	
%>
<c:set var="mem_ac" value="${sessionScope.mem_ac}" scope="page"/>

<c:set var="memVO" value="${memSvc.findByPrimaryKeyNoImg(mem_ac)}"/>


<style>
.byebye{
padding-top:100px;
}
</style>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/FrontEnd/include/head.jsp"/>

<html>
<head>


<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLrfGty4UTt0tx-W-iI8kvMI07T7vgapc"> </script>

<title>Insert title here</title>

</head>
<body>
	
	
	<div class="byebye">
		<div class="shop col-sm-12">
			<table class="table">
    		  <tr class="success" align="center">
       			<td>
       				<form action=""></form>
       				你好${mem_ac}  您店家審核未通過，請修正您的資料，已進行重審動作<br><a href="<%=request.getContextPath()%>/FrontEnd/notstore/store_databynotpass.jsp">回首頁</a>
       			</td>
     		 </tr>
  			</table>
		</div>
	</div>
	<br>
	<br>
	<br>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	


</body>
</html>

<jsp:include page="/FrontEnd/include/footer.jsp"/>


