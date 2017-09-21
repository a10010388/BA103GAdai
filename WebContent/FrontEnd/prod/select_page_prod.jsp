<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
		<title>Product Select</title>
	</head>
	<body>
		<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
		<table border ='1'>
			<caption>Product MVC</caption>
			
			<thead>
				<tr>
					<th>Product �d��</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td><a href='listAllProd.jsp'>List All Product</a></td>
				</tr>
				<tr>
					<td>
						<form method="post" action="#">
							<b>��J�ӫ~�s��:</b>
							<input type="text" name="prod_no">
							<input type="submit" value="�e�X">
							<input type="hidden" name="action" value="getOne_For_Display">						
						</form>
					</td>
				</tr>
				
				<tr>
					<td>
						<form method="post" action="#">
							<b>��ܰӫ~�s��:</b>							
							<select size="1" name="prod_no">
								<c:forEach var="prodVO" items="${prodSvc.all}"> 
									<option value="{prodVO.prod_no}">${prodVO.prod_no}
								</c:forEach>
							</select>
							<input type="submit" value="�e�X">
       						<input type="hidden" name="action" value="getOne_For_Display">						
						</form>							
					</td>
				</tr>
				
				<tr>
					<td>
						<form method="post" action="#">
							<b>��ܰӫ~�W��:</b>							
							<select size="1" name="prod_name">
								<c:forEach var="prodVO" items="${prodSvc.all}"> 
									<option value="{prodVO.prod_name}">${prodVO.prod_name}
								</c:forEach>
							</select>
							<input type="submit" value="�e�X">
       						<input type="hidden" name="action" value="getOne_For_Display">						
						</form>							
					</td>
				</tr>
				<!--
				<tr>
					<td>
						<a href='#'>�s�W�ӫ~</a>
					</td>				
				</tr>
				 -->
			</tbody>
		</table>
</body>
</html>