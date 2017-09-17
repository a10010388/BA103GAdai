<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%

	StoreService storeSvc = new StoreService();
	List<StoreVO> list;
	if(((List<StoreVO>) request.getAttribute("storeVOlist"))==null){
		
		list = storeSvc.getAll();
	} else{
		list = (List<StoreVO>) request.getAttribute("storeVOlist");
	}
		pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>後端首頁0902</title>
<link rel="stylesheet prefetch"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/BackEnd/res/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/BackEnd/res/css/store.css">

</head>
<body>
	<div class="container_fluid titlebar">
		<a class="form-inline titlebarForm" href="main.html"><img
			class="icon" src="<%=request.getContextPath()%>/BackEnd/res/images/BeanLifeLogo2.png">
			<h1>Bean-Life</h1></a>
	</div>
	<!-- .left是左邊導覽列的部分-->
	<!-- .left選擔部分0910修正選單內容-->
	<!-- 0910 玲當部份新增下拉選單-->
	<div class="container card">
		<div class="row">
			<div class="col-xs-2 left">
				<a class="h3 title act" href="#action" aria-expanded="false"
					aria-controls="action" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-futbol-o"></div> <a class="h3" href="act.html">
						活動審核</a>
				</a><a class="h3 title" href="#check" aria-expanded="false"
					aria-controls="check" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-check-circle"></div> <span class="h3">檢舉管理</span>
					<ul class="collapse" id="check">
						<a>評論檢舉</a>
						<a>商品檢舉</a>
						<a>討論區檢舉</a>
					</ul>
				</a><a class="h3 title" href="#mem" aria-expanded="false"
					aria-controls="mem" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-address-card-o"></div> <span class="h3">會員管理</span>
					<ul class="collapse" id="mem">
						<a>會員資料管理</a>
						<a>廠商店家授權</a>
						<a>積分管理</a>
					</ul>
				</a><a class="h3 title" href="#admin" aria-expanded="false"
					aria-controls="admin" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-user-o"></div> <span class="h3">管理員管理</span>
					<ul class="collapse" id="admin">
						<a>管理帳戶授權</a>
						<a>帳戶管理</a>
					</ul>
				</a><a class="h3 title" href="#gift" aria-expanded="false"
					aria-controls="gift" data-toggle="collapse"
					style="text-decoration: none;">
					<div class="fa fa-gift"></div> <span class="h3">平台業務管理</span>
					<ul class="collapse" id="gift">
						<a>廣告管理</a>
						<a>兌換贈品管理</a>
						<a>兌換贈品業務管理</a>
					</ul>
				</a>
			</div>
			<div class="right col-xs-10">
				<div class="col-xs-12 right_top">

					<h2>你好</h2>
					<a class="fa fa-bell dropdown-toggle" href="#"
						data-toggle="dropdown"></a>
					<ul class="dropdown-menu">
						<li><a>10項檢舉未處理</a></li>
						<li><a>10項活動未審核</a></li>
						<li><a>10項廠商會員申請未審核</a></li>
						<li><a>10項兌換贈品申請</a></li>
					</ul>
				</div>

				<div class="col-xs-12 right_bottom">
					<table class="store_tittle">
						<tr>
							<td><h3>所有店家資料-listALLStore.jsp</h3></td>
							<td width="100"></td>
							
							<td>依審核狀態選取：</td>
							<td></td>
							<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/store/store.do">
										<select size="1" name=store_stat>
											<option value="待審中" >待審中</option>
　											<option value="審核通過" >審核通過</option>
　											<option value="審核不通過">審核不通過</option>
										</select>
											<input type="submit" value="送出"> 
											<input	type="hidden" name="action" value="selstat">
								</FORM>	
							</td>
						</tr>
					</table>
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
					<table class="sortable table-bordered">
						<tr>
							<th>廠商編號</th>
							<th>會員帳號</th>
							<th>統一編號</th>
							<th>廠商住址</th>
							<th>廠商名稱</th>
							<th>證件照</th>
							<th>店家照1</th>
							<th>店家照2</th>
							<th>店家照3</th>
							<th>審核狀態</th>
							<th></th>
							<th></th>
						</tr>
						<%@ include file="page1.file"%>

						<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr align='center' valign='middle'>
								<td>${storeVO.store_no}</td>
								<td>${storeVO.mem_ac}</td>
								<td>${storeVO.tax_id_no}</td>
								<td>${storeVO.store_add}</td>            
								<td>${storeVO.store_name}</td>							
								<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=0"></td>
								<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=1" ></td>
								<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=2" ></td>
								<td><img width="100px" src="<%=request.getContextPath()%>/store/StoreImg.do?store_no=${storeVO.store_no}&index=3" ></td>
								<td>${storeVO.store_stat}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/store/store.do">
										<input type="submit" value="審核修改狀態"> <input
											type="hidden" name="store_no" value="${storeVO.store_no}">
										<input type="hidden" name="store_stat_cont" value="${storeVO.store_stat_cont}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/store/store.do">
										<input type="submit" value="刪除"> <input type="hidden"
											name="store_no" value="${storeVO.store_no}"> <input
											type="hidden" name="action" value="delete">
									</FORM>
								</td>
							</tr>
						</c:forEach>



					</table>


					<%@ include file="page2.file"%>
				</div>

			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/BackEnd/res/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/BackEnd/res/js/sorttable.js"></script>
</body>
</html>