<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.ord_list.model.*"%>

<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
session.setAttribute("store_no", "S1000000002");
String store_no = (String) session.getAttribute("store_no");
StoreService storeSvc = new StoreService();


StoreVO storeVO=(StoreVO)storeSvc.getonestore(store_no);
session.setAttribute("storeVO",storeVO);

OrdVO ordVO=(OrdVO) session.getAttribute("ordVO");
%>
<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService"/>
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService"/>
<c:set var="ord_listVOs" value="${ordSvc.getOrd_listByOrd(ordVO.ord_no)}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>

</head>
<body>
				<div class="verticnav col-sm-4">
					<ul class="verticnav">
						<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
						<li><img src="" class="shop_photo"></li>
						<li><a href="#">修改店家資料</a></li>
						<li><a href="#">商品管理</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul>
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
				<div class="col-xs-12 col-sm-8">
					<table class="table-bordered table-responsive ord_list">
				<caption >
					<font size="16" class="listcap">訂單明細</font>
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>數量</th>
					<th>單價</th>
					<th>總金額</th>
					<th></th>
				</tr>
				
				<c:forEach var="ord_listVO" items="${ord_listVOs}" >
					<tr>
						<td>${prodSvc.getOneProd(ord_listVO.prod_no).prod_name}</td>
						<td>${ord_listVO.amont}</td>
						<td>${prodSvc.getOneProd(ord_listVO.prod_no).prod_price}</td>
						<td>${ord_listVO.amont*prodSvc.getOneProd(ord_listVO.prod_no).prod_price}</td>
						<td ></td>
					</tr>
				</c:forEach>
			</table>
				<br>
				運費：${ordVO.send_fee}<br>
				結帳總金額：${ordVO.total_pay}
				</div>
			<br>
			<br>
			<br>
		
			
				<div class="col-xs-12 col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<table class="table-bordered customer">
						<caption class="customerca"><B>買家資訊</B></caption>
						<tr><td>收貨人</td><td>${ordVO.ord_name}</td><td>買家帳號</td><td>${ordVO.mem_ac}</td></tr>
						<tr><td>收貨人電話</td><td>${ordVO.ord_phone}</td><td>收貨人住址</td><td>${ordVO.ord_add}</td></tr>
					</table>
				</div>
			

			
			
				<div class="col-xs-12 col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
				<br>
					<span>
						付款資訊：
				<c:if test="${fn:startsWith(ordVO.pay_info, 'B')}"> 
 						${ordVO.pay_info}
				</c:if> 
 
				<c:if test="${fn:startsWith(ordVO.pay_info, 'C')}"> 
 						${ordVO.pay_info}
				</c:if>
					</span>
					<br>
					<br>
				</div>
			
		<fmt:formatDate value="${ordVO.ord_date}" var="ord_date"  
                  pattern="yyyy-MM-dd hh:mm:ss"/> 
		<fmt:formatDate value="${ordVO.pay_date}" var="pay_date"  
                 pattern="yyyy-MM-dd hh:mm:ss"/> 
        <fmt:formatDate value="${ordVO.pay_chk_date}" var="pay_chk_date"  
                 pattern="yyyy-MM-dd hh:mm:ss"/> 
        <fmt:formatDate value="${ordVO.send_date}" var="send_date"  
                pattern="yyyy-MM-dd hh:mm:ss" /> 
	
		
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					
				</div>
				<div class="col-xs-12 col-sm-8">
					<div class="col-xs-12 col-sm-12">
						<br>
						<table class="bar table-bordered">
							<tr><td class="bar1" >訂單成立</td><td  class="w1" >${ord_date}</td></tr>
							<tr><td class="bar1">付款時間</td><td  class="w1">${pay_date}</td></tr>
							<tr><td class="bar1">確認付款時間</td><td  class="w1">${pay_chk_date}</td></tr>
							<tr><td class="bar1">出貨時間</td><td  class="w1">${send_date}</td></tr>
						</table>
						<br>
						訂單目前狀態：${ordVO.ord_stat}<br>
						<p ${empty ordVO.send_id ? "hidden" : ""}>出貨物流編號：${ordVO.send_id.equals("") ? "" : ordVO.send_id}<br></p>
						<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/ord/Ord_manag.do">
										<input type=${ordVO.ord_stat.equals("已確認付款") ? "text" : "hidden" } name="send_id" value="${ordVO.send_id}" >
										<input type=${ordVO.ord_stat.equals("未付款")||ordVO.ord_stat.equals("已出貨") ? "hidden" : "submit"} value="${ordVO.ord_stat.equals("已付款") ? "確認已付款" : '發出出貨通知'}" class="btn-info"> 
										<input type="hidden" name="ord_no" value="${ordVO.ord_no}">
										<input type="hidden" name="ord_stat" value="${ordVO.ord_stat}">
										<input type="hidden" name="action" value="update_stat">
										<span>${ordVO.ord_stat.equals("已確認付款") ? "請輸入物流編號並發出出貨通知" : ''}</span>
							</FORM>
						
					</div>
				</div>
			</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	
	</script>
<style>
	
	.verticnav{
			list-style: none;
			font-size: 25px;
	}
	caption{
			text-align:center;
	}
	.w1{
       	 width:300px;
       	 text-align: center;
       	 padding-bottom: 2px;
       	 text-indent : -2em ;
       }
    .bar1{
       	 width:180px;
       	 padding-bottom: 2px;
       	 text-indent : 0em ;
       }
	.bar{
		text-indent: -7em

		}
	.customer{
		width:700px;
		
	}
	.ord_list{
			width:700px;
	}
	.customerca{
			text-align:left;
	}
</style>
</body>
</html>


