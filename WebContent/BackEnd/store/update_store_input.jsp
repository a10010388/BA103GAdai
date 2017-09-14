<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	
%>
<html>
<head>
<title>店家資料修改 - update_emp_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="/BackEnd/store/js/calendar.css">
<script language="JavaScript" src="/BackEnd/store/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>店家資料修改 - update_store_input.jsp</h3> <a href="<%=request.getContextPath()%>/BackEnd/store/select_page.jsp">回首頁</a>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1" enctype="multipart/form-data">
		<% 
			byte[] data=storeVO.getWin_id_pic();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data));
			String win_id_pic = sb.toString();
			
			byte[] data1=storeVO.getStore_pic1();
			StringBuilder sb1 = new StringBuilder();
			sb1.append("data:image/png;base64,");
			sb1.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data1));
			String store_pic1 = sb1.toString();
			
			byte[] data2=storeVO.getStore_pic2();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("data:image/png;base64,");
			sb2.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data2));
			String store_pic2 = sb2.toString();
			
			byte[] data3=storeVO.getStore_pic3();
			StringBuilder sb3 = new StringBuilder();
			sb3.append("data:image/png;base64,");
			sb3.append(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(data3));
			String store_pic3 = sb3.toString();
			%>
		<table border="0">
			<tr>
				<td>統一編號:</td>
				<td><input type="TEXT" name="tax_id_no" size="45"
					value="<%=storeVO.getTax_id_no()%>" /></td>
			</tr>
			<tr>
				<td>公司電話:</td>
				<td><input type="TEXT" name="store_phone" size="45"
					value="<%=storeVO.getStore_phone()%>" /></td>
			</tr>
			<tr>

				<td>店家住址:</td>
				<td><input type="TEXT" name="store_add" size="45"
					value="<%=storeVO.getStore_add()%>" /></td>
				</td>
			</tr>
			<tr>
				<td>店家名稱:</td>
				<td><input type="TEXT" name="store_name" size="45"
					value="<%=storeVO.getStore_name()%>" /></td>
			</tr>
			<tr>
				<td>店家介紹:</td>
				<td>
				<textarea rows="4" cols="50" name="store_cont"><%=storeVO.getStore_cont()%>				
				</textarea>
				</td>
			</tr>
			
			
			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="store_add_lat" size="45"
					value="<%= storeVO.getStore_add_lat()%>" /></td>
			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="store_add_lon" size="45"
					value="<%= storeVO.getStore_add_lon()%>" /></td>
			</tr>
			
			<tr>
				<td>審核狀態:</td>
				<td><%=storeVO.getStore_stat()%></td>
			</tr>
			<tr>
				<td>免運費金額:</td>
				<td><input type="TEXT" name="store_free_ship" size="45"
					value="<%=storeVO.getStore_free_ship()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>雇用日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="store_stat_cdate"
					value="<%=(storeVO == null) ? date_SQL : storeVO.getStore_stat_cdate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','store_stat_cdate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>
			<tr>
				<td>證件照:</td>
				<td><img src="<%=win_id_pic %>" width='100'>
				<td><input type="file" name="win_id_pic"  onchange="addfile(this)"></td>
				<td valign="center">上傳新圖：</td>
				<td><output id="preview"></output></td>
			
				
				
			</tr>
			<tr>
				<td>店家照1:</td>
				<td><img src="<%=store_pic1 %>" width=100></td>
				<td><input type="file" name="store_pic1" onchange="addfile1(this)"></td>
				<td valign="center">上傳新圖：</td>
				<td><output id="preview1"></output></td>
				
				
			</tr>
			<tr>
				<td>店家照2:</td>
				<td><img src="<%=store_pic2 %>" width=100></td>
				<td><input type="file" name="store_pic2" onchange="addfile2(this)"></td>
				<td valign="center">上傳新圖：</td>
				<td><output id="preview2"></output></td>
				
			</tr>
			<tr>
				<td>店家照3:</td>
				<td><img src="<%=store_pic3 %>" width=100></td>
				<td><input type="file" name="store_pic3" onchange="addfile3(this)"></td>
				<td valign="center">上傳新圖：</td>
				<td><output id="preview3"></output></td>
				
			</tr>
			
		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input
			type="hidden" name="store_no" value="<%=storeVO.getStore_no()%>">
		<input
			type="hidden" name="store_stat" value="<%=storeVO.getStore_stat()%>">
		<input type="submit" value="送出修改">
	</FORM>
	
<script>
	function addfile(file){
		file = file.files[0];
		var freader = new FileReader();
		freader.readAsDataURL(file);
		
		freader.onloadend = function(event){
			var preview = document.getElementById("preview");
			var imagediv =document.createElement('span');
			var image = document.createElement('img');
			image.src=freader.result;
			
			var att = document.createAttribute("width");      
			att.value = "100px";                          
			image.setAttributeNode(att); 
			
			preview.appendChild(imagediv);
			imagediv.appendChild(image);
		}
	}
	function addfile1(file){
		file = file.files[0];
		var freader = new FileReader();
		freader.readAsDataURL(file);
		
		freader.onloadend = function(event){
			var preview = document.getElementById("preview1");
			var imagediv =document.createElement('span');
			var image = document.createElement('img');
			image.src=freader.result;
			
			var att = document.createAttribute("width");      
			att.value = "100px";                          
			image.setAttributeNode(att); 
			
			preview.appendChild(imagediv);
			imagediv.appendChild(image);
		}
	}
	function addfile2(file){
		file = file.files[0];
		var freader = new FileReader();
		freader.readAsDataURL(file);
		
		freader.onloadend = function(event){
			var preview = document.getElementById("preview2");
			var imagediv =document.createElement('span');
			var image = document.createElement('img');
			image.src=freader.result;
			
			var att = document.createAttribute("width");      
			att.value = "100px";                          
			image.setAttributeNode(att); 
			
			preview.appendChild(imagediv);
			imagediv.appendChild(image);
		}
	}
	function addfile3(file){
		file = file.files[0];
		var freader = new FileReader();
		freader.readAsDataURL(file);
		
		freader.onloadend = function(event){
			var preview = document.getElementById("preview3");
			var imagediv =document.createElement('span');
			var image = document.createElement('img');
			image.src=freader.result;
			
			var att = document.createAttribute("width");      
			att.value = "100px";                          
			image.setAttributeNode(att); 
			
			preview.appendChild(imagediv);
			imagediv.appendChild(image);
		}
	}
	
</script>
	

</body>
</html>
