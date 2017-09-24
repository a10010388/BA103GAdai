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
	ProdVO prodvo = (ProdVO) request.getAttribute("prodvo");
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
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/FrontEnd/res/css/store_list.css">

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
	<div>
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
	</div>
	
	
	
	<div class="shop">
		<div class="product col-sm-8">
			<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/prod/Prod_manag.do" name="form1"	enctype="multipart/form-data">
				<table class="addpro">
					<caption ><font size="20">上架商品</font></caption>
				<tr>
					<td>商品名稱*</td>
					<td><input type="text" name="prod_name"
						value="${(prodvo==null) ? "巴拉巴拉咖啡豆" : ""}" size="35"></td>
					<td></td>
				</tr>
				<tr>
					<td>豆種</td>
					<td><input type="text" name="bean_type"
						value="${(prodvo==null) ? "拉哩哩豆" : ""}" size="35"></td>
					<td></td>
				</tr>
				<tr>
					<td>生豆等級</td>
					<td><input type="radio" name="bean_grade" value="G1" ${(prodvo==null) ? "checked" : ""}>G1 
						<input type="radio" name="bean_grade" value="G2">G2 
						<input type="radio" name="bean_grade" value="G3">G3 
						<input type="radio" name="bean_grade" value="G4">G4 
						<input type="radio" name="bean_grade" value="G5">G5</td>
					<td></td>
				</tr>
				<tr>
					<td>生產國*</td>
					<td><input type="text" name="bean_contry"
						value="${(prodvo==null) ? "小人國" : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>地區</td>
					<td><input type="text" name="bean_region"
						value="${(prodvo==null) ? "亞東" : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>農場</td>
					<td><input type="text" name="bean_farm"
						value="${(prodvo==null) ? "新埔" : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>生產者</td>
					<td><input type="text" name="bean_farmer"
						value="${(prodvo==null) ? "嚕啦啦" : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>海拔</td>
					<td><input type="number" name="bean_el"
						value="${(prodvo==null) ? 1500 : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>處理法*</td>
					<td><select size="1" name="proc">
							<option value="日曬" ${(prodvo==null) ? "SELECTED" : ""}>日曬</option>
							<option value="半水洗">半水洗</option>
							<option value="水洗">水洗</option>
							<option value="蜜處理">蜜處理</option>
					</select></td>
					<td></td>
				</tr>

				<tr>
					<td>烘焙度*</td>
					<td><select size="1" name="roast">
							<option value="極淺焙" ${(prodvo==null) ? "SELECTED" : ""}>極淺焙</option>
							<option value="淺焙">淺焙</option>
							<option value="中焙">中焙</option>
							<option value="中深焙">中深焙</option>
							<option value="城市烘焙">城市烘焙</option>
							<option value="深焙">深焙</option>
							<option value="法式烘焙">法式烘焙</option>
							<option value="重焙">重焙</option>
					</select></td>
					<td></td>
				</tr>
				<tr>
					<td>風味-酸度</td>
					<td><input type="radio" name="bean_attr_acid" value="1">1 
					<input type="radio" name="bean_attr_acid" value="2">2 
					<input type="radio" name="bean_attr_acid" value="3" ${(prodvo==null) ? "checked" : ""}>3 
					<input type="radio" name="bean_attr_acid" value="4">4 
					<input type="radio" name="bean_attr_acid" value="5">5
					</td>
					<td></td>
				</tr>
				<tr>
					<td>風味-香氣</td>
					<td><input type="radio" name="bean_attr_aroma" value="1">1 
					<input type="radio" name="bean_attr_aroma" value="2">2 
					<input type="radio" name="bean_attr_aroma" value="3" ${(prodvo==null) ? "checked" : ""}>3 
					<input type="radio" name="bean_attr_aroma" value="4">4 
					<input type="radio" name="bean_attr_aroma" value="5">5
					</td>
					<td></td>
				</tr>
				<tr>
					<td>風味-醇度</td>
					<td><input type="radio" name="bean_attr_body" value="1">1 
					<input type="radio" name="bean_attr_body" value="2">2 
					<input type="radio" name="bean_attr_body" value="3"	${(prodvo==null) ? "checked" : ""}>3 
					<input type="radio" name="bean_attr_body" value="4">4 
					<input type="radio" name="bean_attr_body" value="5">5
					</td>
					<td></td>
				</tr>
				<tr>
					<td>風味-餘味</td>
					<td><input type="radio" name="bean_attr_after" value="1" >1 
						<input type="radio" name="bean_attr_after" value="2">2 
						<input type="radio" name="bean_attr_after" value="3" ${(prodvo==null) ? "checked" : ""}>3 
						<input type="radio" name="bean_attr_after" value="4">4 
						<input type="radio" name="bean_attr_after" value="5">5
						</td>
					<td></td>
				</tr>
				<tr>
					<td>風味-平衡度</td>
					<td><input type="radio" name="bean_attr_bal" value="1" >1 
						<input type="radio" name="bean_attr_bal" value="2" >2 
						<input type="radio" name="bean_attr_bal" value="3" ${(prodvo==null) ? "checked" : ""}>3 
						<input type="radio" name="bean_attr_bal" value="4">4 
						<input type="radio" name="bean_attr_bal" value="5">5
						</td>
					<td></td>
				</tr>
				<tr>
					<td>香味</td>
					<td><input type="text" name="bean_aroma"
						value="${(prodvo==null) ? "滿滿的奶味" : ""}" size="35"></td>
					<td></td>
				</tr>
				<tr>
					<td>標價 $NT*</td>
					<td><input type="number" name="prod_price"
						value="${(prodvo==null) ? 500 : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>重量 lb(小數後1位)*</td>
					<td><input type="number" name="prod_wt" value="${(prodvo==null) ? 0.5 : ""}" ></td>
					<td></td>
				</tr>
				<tr>
					<td>運費*</td>
					<td><input type="number" name="send_fee"
						value="${(prodvo==null) ? 120 : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>供應數量*</td>
					<td><input type="number" name="prod_sup"value="${(prodvo==null) ? 50 : ""}"></td>
					<td></td>
				</tr>
				<tr>
					<td>商品描述*</td>
					<td><textarea rows="4" cols="50" name="prod_cont" value="">這是個咖啡豆苗數</textarea>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>商品圖片-1*</td>
					<td><output id="pic1"></output></td>
					<td><input type="file" name="prod_pic1" id="propic1"></td>
				</tr>
				<tr>
					<td>商品圖片-2</td>
					<td><output id="pic2"></output></td>
					<td><input type="file" name="prod_pic2" id="propic2"></td>
				</tr>
				<tr>
					<td>商品圖片-3</td>
					<td><output id="pic3"></output></td>
					<td><input type="file" name="prod_pic3" id="propic3"></td>
				</tr>
				<tr>
					<td>上架狀態</td>
					<td><input type="radio" name="prod_stat" value="下架" >下架 
						<input type="radio" name="prod_stat" value="上架" ${(prodvo==null) ? "checked" : ""}>上架 
					</td>
					<td></td>
				</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> 
			 <input type="submit" value="送出新增" class="btn btn-info">
			 <input type="hidden" name="prod_no" value="${prodvo.prod_no}">
			 <input type="hidden" name="store_no" value="${storeVO.store_no}">
			</FORM>
				
			
			
				
		</div>
	</div>

<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/BackEnd/res/js/sorttable.js"></script>
<script>
function handleFileSelect1(evt) {
	$("#pic1").empty();

	var files = evt.target.files; // FileList object

	// Loop through the FileList and render image files as thumbnails.
	for (var i = 0, f; f = files[i]; i++) {

		// Only process image files.
		if (!f.type.match('image.*')) {
			continue;
		}

		var reader = new FileReader();

		// Closure to capture the file information.
		reader.onload = (function(theFile) {
			return function(e) {
				// Render thumbnail.
				var span = document.createElement('span');
				span.innerHTML = [ '<img class="thumb" src="',
						e.target.result, '" title="', escape(theFile.name),
						'"/>' ].join('');
				document.getElementById('pic1').insertBefore(span, null);
				$(".thumb").width(150);

			};
		})(f);

		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}
document.getElementById('propic1').addEventListener('change',
		handleFileSelect1, false);

//=======================================================================================

function handleFileSelect2(evt) {
	$("#pic2").empty();

	var files = evt.target.files; // FileList object

	// Loop through the FileList and render image files as thumbnails.
	for (var i = 0, f; f = files[i]; i++) {

		// Only process image files.
		if (!f.type.match('image.*')) {
			continue;
		}

		var reader = new FileReader();

		// Closure to capture the file information.
		reader.onload = (function(theFile) {
			return function(e) {
				// Render thumbnail.
				var span = document.createElement('span');
				span.innerHTML = [ '<img class="thumb" src="',
						e.target.result, '" title="', escape(theFile.name),
						'"/>' ].join('');
				document.getElementById('pic2').insertBefore(span, null);
				$(".thumb").width(150);

			};
		})(f);

		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}
document.getElementById('propic2').addEventListener('change',
		handleFileSelect2, false);

//=======================================================================================

function handleFileSelect3(evt) {
	$("#pic3").empty();

	var files = evt.target.files; // FileList object

	// Loop through the FileList and render image files as thumbnails.
	for (var i = 0, f; f = files[i]; i++) {

		// Only process image files.
		if (!f.type.match('image.*')) {
			continue;
		}

		var reader = new FileReader();

		// Closure to capture the file information.
		reader.onload = (function(theFile) {
			return function(e) {
				// Render thumbnail.
				var span = document.createElement('span');
				span.innerHTML = [ '<img class="thumb" src="',
						e.target.result, '" title="', escape(theFile.name),
						'"/>' ].join('');
				document.getElementById('pic3').insertBefore(span, null);
				$(".thumb").width(150);
				
			};
		})(f);

		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}
document.getElementById('propic3').addEventListener('change',
		handleFileSelect3, false);



</script>
</body>
</html>


