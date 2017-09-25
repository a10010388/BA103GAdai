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

<title>Insert title here</title>

</head>
<body>
 

		<div class="container-floid mgt-depn-nav">
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<ul class="verticnav ">
						<li>&nbsp;&nbsp;&nbsp;${storeVO.store_name}</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;認證狀態：</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${storeVO.store_stat}</li>
						<li><img src="LOGO.svg" class="shop_photo"></li>
						<li><a href="#">修改店家資料</a></li>
						<li><a href="<%=request.getContextPath()%>/FrontEnd/prod/listAllpro_bystore.jsp">商品管理</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4">
					<table class="table-bordered table-responsive ord_table">
						<caption>
					<big>我的訂單</big><a href="<%=request.getContextPath()%>/FrontEnd/prod/listAl"><small>更多</small></a>
						</caption>
					<tr>
						<th>訂單編號</th>
						<th>收貨人姓名</th>
						<th>結帳商品總金額</th>
						<th>訂單成立時間</th>
						<th>訂單狀態</th>
					</tr>
				<c:forEach var="ordVOs" items="${ordVOs}" begin="0" end="4">
					<tr>
						<td>${ordVOs.ord_no}</td>
						<td>${ordVOs.ord_name}</td>
						<td>${ordVOs.total_pay}</td>
						<td>${ordVOs.ord_date}</td>
						<td>${ordVOs.ord_stat}</td>
					</tr>
				</c:forEach>
			</table>
				</div>
				<div class="col-xs-12 col-sm-4">
					<table class="table-bordered table-responsive prod_table">
						<caption>
					<big>我的商品</big><a href="<%=request.getContextPath()%>/FrontEnd/prod_mag/listAllpro_bystore.jsp"><small>更多</small></a>
						</caption>
					<tr>
						<th>商品名稱</th>
						<th>商品圖片</th>
						<th>商品價格</th>
						<th>烘焙度</th>
						<th>狀態</th>
					</tr>
				<c:forEach var="ProdVO" items="${prodVOs}" begin="0" end="4">
					<tr>
						<td>${ProdVO.prod_name}</td>
						<td><img src="<%=request.getContextPath()%>/prod/prodImg.do?prod_no=${ProdVO.prod_no}&index=1" class="product_photo"></td>
						<td>${ProdVO.prod_price}</td>
						<td>${ProdVO.roast}</td>
						<td>${ProdVO.prod_stat}</td>
					</tr>
				</c:forEach>
			</table>
				</div>
			</div>
		</div>
	



	<div class="shop">
		<div class="product col-sm-4 col-sm-offset-4">
			<table class="table_product">
				<caption>
					<big>最新商品評論</big><a href="#"><small>更多</small></a>
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>商品圖片</th>
					<th>商品評分</th>
					<th>商品心得</th>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><img src="car.jpg" class="product_photo"></td>
					<td>商品評分</td>
					<td>商品心得</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="shop">
		<div class="product col-sm-4">
			<table class="table_product">
				<caption>
					<big>最新詢問</big><a href="#"><small>更多</small></a>
				</caption>
				<tr>
					<th>發問時間</th>
					<th>會員帳號</th>
					<th>問題敘述</th>
					<th>商品連結</th>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
				<tr>
					<td>發問時間</td>
					<td>會員帳號</td>
					<td>問題敘述</td>
					<td>商品連結</td>
				</tr>
			</table>
		</div>
	</div>

</body>


</html>