<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.prod.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>
<%
	pageContext.setAttribute("store_no", "S1000000002");
	String store_no = (String) pageContext.getAttribute("store_no");
	StoreService storeSvc = new StoreService();
	Set<ProdVO> set;
	set = storeSvc.getProdsByStore_no(store_no);
	pageContext.setAttribute("set", set);
	StoreVO storeVO=storeSvc.getonestore(store_no);
	pageContext.setAttribute("storeVO", storeVO); 
%>

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
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/FrontEnd/res/css/store_list.css">

<title>Insert title here</title>

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
			<table class="table-bordered table-responsive pro_all">
				<caption >
					<font size="20">我的商品</font>
					<ul class="verticnav">
						<li><small><a href="<%=request.getContextPath()%>/FrontEnd/prod_mag/addprod.jsp">新增商品</a></small></li>
					</ul>	
				</caption>
				<tr>
					<th>商品名稱</th>
					<th>商品圖片</th>
					<th>豆種</th>
					<th>價格</th>
					<th>烘培度</th>
					<th>狀態</th>
					<th></th>
					
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="prodVO" items="${set}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/Prod_manag.do">
						<a class="aprod">${prodVO.prod_name}</a>
						<input type="hidden" name="prod_no" value="${prodVO.prod_no}">
						<input type="hidden" name="action" value="getprod_display">
						<input type="hidden" name="whichPage"value="<%=whichPage%>">  
						</FORM>
						</td>
						<td><img src="<%=request.getContextPath()%>/prod/prodImg.do?prod_no=${prodVO.prod_no}&index=1"></td>
						<td>${prodVO.bean_type}</td>
						<td>${prodVO.prod_price}</td>
						<td>${prodVO.roast}</td>
						<td>${prodVO.prod_stat}</td>
						<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/prod/Prod_manag.do">
										<input type="submit" value="修改商品資料" class="btn btn-info"> 
										<input type="hidden" name="prod_no" value="${prodVO.prod_no}">
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



	<div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">商品詳情</h4>
					</div>
					<div class="modal-body">
						商品名稱：${prodVO.prod_name}<br>
						豆種：${prodVO.bean_type}<br>
						生豆等級：${prodVO.bean_grade}<br>
						生產國：${prodVO.bean_contry}<br>
						地區：${prodVO.bean_region}<br>
						農場：${prodVO.bean_farm}<br>
						生產者：${prodVO.bean_farmer}<br>
						海拔：${prodVO.bean_el}<br>
						處理法：${prodVO.proc}<br>
						烘焙度：${prodVO.roast}<br>
						風味-酸度：${prodVO.bean_attr_acid}<br>
						風味-香氣：${prodVO.bean_attr_aroma}<br>
						風味-醇度：${prodVO.bean_attr_body}<br>
						風味-餘味：${prodVO.bean_attr_after}<br>
						風味-平衡度：${prodVO.bean_attr_bal}<br>
						香味：${prodVO.bean_aroma}<br>
						標價  $NT：${prodVO.prod_price}<br>
						重量  lb：${prodVO.prod_wt}<br>
						運費：${prodVO.send_fee}<br>
						供應數量：${prodVO.prod_sup}<br>
						商品描述：${prodVO.prod_cont}<br>
						上架狀態：${prodVO.prod_stat}<br>
						最後編輯時間：${prodVO.ed_time}
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/BackEnd/res/js/sorttable.js"></script>
<script>
$(".pro_all img:first-child").css(
	    {"width":"70px"}
	)
	
$(".aprod").click(function(){
	$(this).parent().submit();
})
if(${not empty openModal}){
	$("#modal-id").modal({show:true});
}

</script>
</body>
</html>


