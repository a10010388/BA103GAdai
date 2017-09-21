<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	String mem_ac = (String) request.getAttribute("mem_ac");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLrfGty4UTt0tx-W-iI8kvMI07T7vgapc">
	
</script>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>會員申請店家 - regstore.jsp</h3>
			</td>

		</tr>
	</table>

	<h3>資料店家:</h3>
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

	<FORM METHOD="POST"
		ACTION="<%=request.getContextPath()%>/store/ToStore.do" name="form1"
		enctype="multipart/form-data">
		<table border="1">
			<caption>
				您好：<%=mem_ac%></caption>
			<tr>
				<td>店家名稱:</td>
				<td><input type="TEXT" name="store_name" size="45"
					value="我家咖啡館" /></td>
			</tr>
			<tr>
				<td>統一編號:</td>
				<td><input type="TEXT" name="tax_id_no" size="45" 
					value="00000000" /></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><input type="TEXT" name="store_phone" size="45"
					value="02-12534875" /></td>
			</tr>
			<tr>
				<td>店家住址:</td>
				<td><input type="TEXT" id="address" name="store_add" size="45"
					class="add" value="中壢市中大路300號" /></td>
				</td>

				</td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td><textarea rows="4" cols="50" name="store_cont"
						value="輸入店家介紹"></textarea></td>
			</tr>


			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="store_free_ship" size="45"
					value="1000" /></td>
			</tr>

			<tr>
				<td>證件照:</td>
				<td><input type="file" name="win_id_pic" id="idpic1"></td>
				<td><output id="mylist1"></output></td>
			</tr>
			<tr>
				<td>店家照1:</td>
				<td><input type="file" name="store_pic1" id="stpic1"></td>
				<td><output id="mylist2"></output></td>
			</tr>
			<tr>
				<td>店家照2:</td>
				<td><input type="file" name="store_pic2" id="stpic2"></td>
				<td><output id="mylist3"></output></td>
			</tr>
			<tr>
				<td>店家照3:</td>
				<td><input type="file" name="store_pic3" id="stpic3"></td>
				<td><output id="mylist4"></output></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		<input	type="hidden" name="mem_ac" value="<%=mem_ac%>"> 
		<input	type="hidden" name="store_add_lat" id="lat" size="45" value="" />
		<input	type="hidden" name="store_add_lon" id="lng" size="45" value="" /> 
		<input	type="submit" value="送出新增" />
	</FORM>
</body>
<script>
	$(function() {
		function getLatLngByAddr($address) {
			var geocoder = new google.maps.Geocoder(); //定義一個Geocoder物件
			geocoder.geocode({
				address : $address
			}, //設定地址的字串
			function(results, status) { //callback function
				if (status == google.maps.GeocoderStatus.OK) { //判斷狀態
					$lat = results[0].geometry.location.lat();
					var lat = $lat;
					var lat1 = lat.toFixed(12);
					$("#lat").val(lat1);
					$lng = results[0].geometry.location.lng();
					var lng = $lng;
					var lng1 = lng.toFixed(12);
					$("#lng").val(lng1);

				}
			});
		}
		$("#address").focusout(function() { //地址查詢經緯度
			$address = $("#address").val();
			getLatLngByAddr($address);

		});
	});

	function handleFileSelect1(evt) {
		$("#mylist1").empty();

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
					document.getElementById('mylist1').insertBefore(span, null);
					$(".thumb").width(150);

				};
			})(f);

			// Read in the image file as a data URL.
			reader.readAsDataURL(f);
		}
	}
	document.getElementById('idpic1').addEventListener('change',
			handleFileSelect1, false);

	//=======================================================================================

	function handleFileSelect2(evt) {
		$("#mylist2").empty();

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
					document.getElementById('mylist2').insertBefore(span, null);
					$(".thumb").width(150);

				};
			})(f);

			// Read in the image file as a data URL.
			reader.readAsDataURL(f);
		}
	}
	document.getElementById('stpic1').addEventListener('change',
			handleFileSelect2, false);

	//=======================================================================================

	function handleFileSelect3(evt) {
		$("#mylist3").empty();

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
					document.getElementById('mylist3').insertBefore(span, null);
					$(".thumb").width(150);

				};
			})(f);

			// Read in the image file as a data URL.
			reader.readAsDataURL(f);
		}
	}
	document.getElementById('stpic2').addEventListener('change',
			handleFileSelect3, false);

	//=======================================================================================

	function handleFileSelect4(evt) {
		$("#mylist4").empty();

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
					document.getElementById('mylist4').insertBefore(span, null);
					$(".thumb").width(150);

				};
			})(f);

			// Read in the image file as a data URL.
			reader.readAsDataURL(f);
		}
	}
	document.getElementById('stpic3').addEventListener('change',
			handleFileSelect4, false);


</script>

</html>