<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.ord_list.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	pageContext.setAttribute("store_no", "S1000000002");
	String store_no = (String) pageContext.getAttribute("store_no");
	StoreService storeSvc = new StoreService();
	ProdService prodSvc = new ProdService();
	OrdService ordSvc = new OrdService();
	
	StoreVO storeVO=(StoreVO)storeSvc.getonestore(store_no);
	pageContext.setAttribute("storeVO",storeVO);
	
	
	Set<ProdVO> prodVOs = storeSvc.getProdsByStore_no(store_no);
	pageContext.setAttribute("prodVOs",prodVOs);
	
	
	Set<OrdVO> ordVOs= new LinkedHashSet<OrdVO>();
	for(ProdVO prodVO : prodVOs){
		
		Set<Ord_listVO> ord_listVOs = prodSvc.getOrd_listByProd(prodVO.getProd_no());
		for(Ord_listVO ord_listVO : ord_listVOs){
			ordVOs.add(ordSvc.getOrdByOrdno(ord_listVO.getOrd_no()));
		}
	}
	pageContext.setAttribute("ordVOs",ordVOs);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/res/plugin/jquery.scrollbar.css">
   
    <link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/res/css/store_index.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FrontEnd/res/js/beanlife.base.js"></script>

<title>listAllorder_bystore.sjp</title>

</head>
<body>
	<div class="head1 ">
		<div class="verticnav col-sm-4">
			<ul class="verticnav">
				<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
				<li><img src="LOGO.svg" class="shop_photo"></li>
				<li><a href="#">修改店家資料</a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#">訂單管理</a></li>

			</ul>

		</div>
	</div>
	
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
	
	
	
	
	<div class="shop">
		<div class="product col-sm-8">
			<table class="table-bordered table-responsive ord_all">
				<caption >
					<font size="20">我的訂單</font>
				</caption>
				<tr>
					<th>訂單編號</th>
					<th>收貨人姓名</th>
					<th>訂單成立時間</th>
					<th>結帳商品總金額</th>
					<th>訂單狀態</th>
					<th></th>
					<th></th>
					
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="ordVO" items="${ordVOs}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/Ord_manag.do">
						${ordVO.ord_no}
						<input type="hidden" name="ord_no" value="${ordVO.ord_no}">
						<input type="hidden" name="action" value="getord_display">
						<input type="hidden" name="whichPage"value="<%=whichPage%>">  
						</FORM>
						</td>
						<td>${ordVO.ord_name}</td>
						<td>${ordVO.ord_date}</td>
						<td>${ordVO.total_pay}</td>
						<td>${ordVO.ord_stat}</td>
						<td></td>
						<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/ord/Ord_manag.do">
										<input type="submit" value="進行訂單管理" class="btn btn-info"> 
										<input type="hidden" name="ord_no" value="${ordVO.ord_no}">
										<input type="hidden" name="store_no"	value="<%=store_no%>">  
										<input type="hidden" name="action" value="getOne_For_Update">
										<input type="hidden" name="whichPage"	value="<%=whichPage%>">  
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
				<%@ include file="page2.file"%>
		</div>
	</div>

</body>
</html>