<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.fo_prod.model.*"%>
<%@ page import="com.review.model.*"%>
<%@ page import="com.like_rev.model.*"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="com.cart_list.model.*"%>
<%@ page import="com.act.model.*"%>


<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
<jsp:useBean id="fo_prodSvc" scope="page" class="com.fo_prod.model.Fo_prodService" />
<jsp:useBean id="reviewSvc" scope="page" class="com.review.model.ReviewService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="like_revSvc" scope="page" class="com.like_rev.model.Like_revService" />
<jsp:useBean id="qaSvc" scope="page" class="com.qa.model.QaService" />
<jsp:useBean id="cart_listSvc" scope="page" class="com.cart_list.model.Cart_listService" />
<jsp:useBean id="ActSvc" scope="page" class="com.act.model.ActService" />


<c:set var="mem_ac" value="${sessionScope.mem_ac}" scope="page"/>
<c:set var="prodlist" value="${prodSvc.all}" scope="page"/>
<c:set var="fo_list" value="${fo_prodSvc.getAllByMem(mem_ac)}" scope="page"/>
<c:set var="like_rev_list" value="${like_revSvc.getAllByMem(mem_ac)}" scope="page"/>
<c:set var="cart_listSet" value="${cart_listSvc.getVOsByMem(mem_ac)}" scope="page"/>

<%
	ActService actSvc = new ActService();
	List<ActVO> actlist = actSvc.getAll();
    pageContext.setAttribute("actlist",actlist);    
%>




<!DOCTYPE html>
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BeanLife</title>
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/FrontEnd/res/img/logo/BeanLifeIco2.ico" />

    <!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/res/plugin/jquery.scrollbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/res/css/beanlife.base.css">
    
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FrontEnd/res/plugin/jquery.scrollbar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FrontEnd/res/js/beanlife.base.js"></script>




  </head>
  <body>

    <!-- NavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbarNavbar -->
    <nav class="navbar navbar-default navbar-fixed-top bg-light-brn border0 cus-nav" role="navigation">
      <div class="container">
        <div>
          <button type="button" class="navbar-toggle pull-left cus-ham" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">選單切換</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>


          <div class="pull-left">
            <a href="<%=request.getContextPath()%>/FrontEnd/index/index.jsp">
              <img class="w50 padt5 padb2 mgr20" src="<%=request.getContextPath()%>/FrontEnd/res/img/BeanLifeLogoR.png">
            </a>
          </div>
          
          <div class="pull-right padt5">
            <div class="dropdown pull-right">
                      <a class="navbar-brand dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                        <span id="cartSize" class="badge cus-badge">${cart_listSvc.getVOsByMem(mem_ac).size()}</span>
                      </a>
                      <ul id="cartList" class="dropdown-menu zidx5">
                      
                    <c:forEach var="cart_listVO" items="${cart_listSet}">
                    	<li><a href="#">
                    		${prodSvc.getOneProd(cart_listVO.prod_no).prod_name}　
                    		<span>$${prodSvc.getOneProd(cart_listVO.prod_no).prod_price}ｘ${cart_listVO.prod_amount}</span>
                    	</a></li>
                    </c:forEach>
                    <li role="presentation" class="divider"></li>
                  </ul>
                    </div>

                    <div class="dropdown pull-right">
                      <a class="navbar-brand dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                      </a>
                      <ul class="dropdown-menu zidx5">
                    <c:if test="${storeSvc.getOneByMem(mem_ac) != null}">
	                    <li><a href="#">店家資訊</a></li>
	                    <li><a href="#">商品管理</a></li>
	                    <li><a href="#">訂單管理</a></li>
	                    <li role="presentation" class="divider"></li>
                    </c:if>
                    <li><a href="#">個人資料</a></li>
                    <li><a href="#">訂單查詢</a></li>
                    <li><a href="#">我的收藏</a></li>
                    <li><a href="#">個人文章</a></li>
                    <li><a href="#">個人活動</a></li>
                    <li><a href="#">積分兌換</a></li>
                  </ul>
                    </div>
                </div>
        </div>


        <div class="h-block-depn-nav"></div>

        <!-- 手機隱藏選單區 -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <!-- 左選單 -->
          <ul class="nav navbar-nav">
            <li><a class="padt20 padb17" href="<%=request.getContextPath()%>/FrontEnd/shop/shop.jsp">找豆</a></li>
            <li><a class="padt20 padb17" href="<%=request.getContextPath()%>/FrontEnd/forum/forum.jsp">討論板</a></li>
            <li><a class="padt20 padb17" href="<%=request.getContextPath()%>/FrontEnd/activity/activity.jsp">辦活動</a></li>
            <li><a class="padt20 padb17" href="#">地圖</a></li>
          </ul>
        </div>
        <!-- 手機隱藏選單區結束 -->
      </div>
    </nav>


<!-- ------------------------------------------------------------------------------------------------------- -->






		<!--BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB-->
		<div class="container-floid mgt-depn-nav">
			<div id="index-cas" class="carousel slide" data-ride="carousel">
			    <!-- 幻燈片小圓點區 -->
			    <ol class="carousel-indicators">
			        <li data-target="#index-cas" data-slide-to="0" class=""></li>
			        <li data-target="#index-cas" data-slide-to="1" class=""></li>
			        <li data-target="#index-cas" data-slide-to="2" class="active"></li>
			    </ol>
			    <!-- 幻燈片主圖區 -->
			    <div class="carousel-inner">
			        <div class="item">
			            <img src="<%=request.getContextPath()%>/FrontEnd/res/img/in1.png" alt="">
			            <div class="container">
			                <div class="carousel-caption">
			                    <h2 class="pull-left">快速挑選咖啡豆</h2 class="pull-left">
			                </div>
			            </div>
			        </div>
			        <div class="item">
			            <img src="<%=request.getContextPath()%>/FrontEnd/res/img/in2.png" alt="">
			            <div class="container">
			                <div class="carousel-caption">
			                    <h2 class="pull-left">活動與同好聚會</h2>
			                </div>
			            </div>
			        </div>
			        <div class="item active">
			            <img src="<%=request.getContextPath()%>/FrontEnd/res/img/in3.png" alt="">
			            <div class="container">
			                <div class="carousel-caption">
			                    <h2 class="pull-left">達人經驗分享</h2>
			                </div>
			            </div>
			        </div>
			    </div>
			    <!-- 上下頁控制區 -->
			    <a class="left carousel-control" href="#index-cas" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
			    <a class="right carousel-control" href="#index-cas" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</div>
		





<!--  --------------------------------------------------------------跳窗---------------------------------------------------------------->





    <div class="modal" id="modal-id">
        <div class="modal-dialog modal-lg">
            <div class="modal-content fix-h scrollbar-macosx" id="modalX">

<!-- 				這邊是跳窗塞內容 -->
                
            </div>
        </div>
    </div>
    
<!--  --------------------------------------------------------------跳窗結束---------------------------------------------------------------->






		<!--CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-->
		<div class="container cus-tab-block">
			<div class="row">
				<div class="col-xs-12 col-sm-8 col-sm-offset-2">
					<div role="tabpanel">
					    <!-- 標籤面板：標籤區 -->
					    <ul class="nav nav-tabs " role="tablist">
					        <li role="presentation" class="active w33p text-center bold">
					            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">熱門商品</a>
					        </li>
					        <li role="presentation" class="w33p text-center bold">
					            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">熱門文章</a>
					        </li>
					        <li role="presentation" class="w33p text-center bold">
					            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">熱門活動</a>
					        </li>
					    </ul>
					
					    <!-- 標籤面板：內容區 -->
					    <div class="tab-content cus-tab-content">



					    	<!-- tab1111111111111111111111111111111 -->
					        <div role="tabpanel" class="tab-pane active" id="tab1">
								<div class="container-floid">
									<div class="row">


									<c:forEach var="prodVO" items="${prodlist}">
									<%
										String prod_no = ((ProdVO)pageContext.getAttribute("prodVO")).getProd_no();
										//此會員對此商品是否Follow的Boolean
		                              	Boolean isFollow = false;
		                              	for (Fo_prodVO fo_prodVO: (List<Fo_prodVO>)pageContext.getAttribute("fo_list")){
		                              		if(fo_prodVO.getProd_no().equals(prod_no)){	                              			
		                              			isFollow = true;
		                              		}
		                              	}
		                              	pageContext.setAttribute("isFollow",isFollow);


		                              	
		                              	//此商品的分數轉換星星Boolean
		                              	Boolean [] star = new Boolean[5];
		                              	Double score = ((ReviewService)(pageContext.getAttribute("reviewSvc"))).getScoreByProd(prod_no);
		                              	long scoreLong = Math.round(score);
		                              	for (int i = 0 ; i < scoreLong ; i++){
		                              		star[i] = true;
		                              	}
		                              	pageContext.setAttribute("star",star);
		                              	      	
		                            %>

				                      <!-- ////////////////////////////// -->
				                      <div class="col-xs-12 col-sm-3 padt10">
				                        <a id="${prodVO.prod_no}" href='#modal-id' data-toggle="modal">
				                          
				                          <img class="img-responsive  mg-auto vam-img  rd10" src="<%=request.getContextPath()%>/prod/prodImg.do?prod_no=${prodVO.prod_no}&index=1">
				                          
				                          <h4 class="bold">${prodVO.prod_name}</h4>
				                          <p class="inline-b bold text-info">NT$ ${prodVO.prod_price}</p>
				                         
										  
				                          <button type="button" class="btn btn-default btn-xs zidx5 pull-right ${(isFollow)?'bor-info':''}" aria-label="Left Align">
				                              <span class="${(isFollow)?'text-info':'tx-gray'}">${fo_prodSvc.getCountByProd(prodVO.prod_no)}</span>
				                              <span class="glyphicon glyphicon-bookmark ${(isFollow)?'text-info':'tx-gray'}" aria-hidden="true"></span>	
				                          </button>

				                          <p class="bold">${prodVO.bean_contry}　${prodVO.proc}　${prodVO.roast}</p>

				                          <div title="${reviewSvc.getScoreByProd(prodVO.prod_no)}/5.0">
				                          	  
				                              <span class="glyphicon glyphicon-star ${(star['0'])? 'tx-brn' : 'tx-gray'}" aria-hidden="true"></span>
				                              <span class="glyphicon glyphicon-star ${(star['1'])? 'tx-brn' : 'tx-gray'}" aria-hidden="true"></span>
				                              <span class="glyphicon glyphicon-star ${(star['2'])? 'tx-brn' : 'tx-gray'}" aria-hidden="true"></span>
				                              <span class="glyphicon glyphicon-star ${(star['3'])? 'tx-brn' : 'tx-gray'}" aria-hidden="true"></span>
				                              <span class="glyphicon glyphicon-star ${(star['4'])? 'tx-brn' : 'tx-gray'}" aria-hidden="true"></span>
				                              <span>(${reviewSvc.getCountByProd(prodVO.prod_no)})</span>
				                          </div>
				                        </a>
				                      </div>
				                      
				                      
<script>
var $modalX = $("#modalX");

var $btn = $("#${prodVO.prod_no}").click(function(){
	var prodNo =  $("#${prodVO.prod_no}").attr("id");
	var urlstr = '<%=request.getContextPath()%>/FrontEnd/prod/prodPage.jsp?prodNo='+prodNo+'&memAc=${mem_ac}';
	$.ajax({
		url : urlstr,
		type : 'GET',
		dataType: "html",
		async: false,
		success : function(result) {
			while($modalX.children().length > 0){
				$modalX.empty();
			}
			
			$modalX.html(result);
		},
		error : function(xhr) {
			alert('Ajax request 發生錯誤');
		}
	});
});
</script>                   
				                      

				                    </c:forEach>





									</div>
								</div>

					        </div>

















							<!-- tab22222222222222222222222222222 -->
							<div role="tabpanel" class="tab-pane" id="tab2">
						        <div class="container-floid">


						        	<!-- //////////////////////////////////// -->
						        	<div class="row cus-art-row zidx0">
						        		<a href="#">
							        		<div class="col-xs-12 col-sm-10 fixed-panel">
							        			<span><h5 class="text-info">Java123　　手沖達人</h5></span>
							        			<h4 class="inline-b bold">手沖咖啡必知的12個專有名詞</h4><h5 class="inline-b pull-right">2017-09-04</h5>
							        			<p>电信种子看出校长各国读书最多变，预测优质看来结合球员性能是指具备协助便是威望，一句话知道平衡男性生物限制联系人，命运天天露出名称多媒体。</p>

							        			<button type="button" class="btn btn-default btn-sm zidx5" aria-label="Left Align">
						        					<span class="tx-gray">42</span>
													<span class="glyphicon glyphicon-heart tx-gray" aria-hidden="true"></span>
												</button>
							        			<button type="button" class="btn btn-default btn-sm zidx5" aria-label="Left Align">
						        					<span class="tx-gray">42</span>
													<span class="glyphicon glyphicon-bookmark tx-gray" aria-hidden="true"></span>
												</button>
												<span class="mgl20 tx-gray">5則回應</span>
							        		</div>
						        		</a>
						        		<div class="col-xs-12 col-sm-2 vam-div150 padt10">
						        			<img class="img-responsive mg-auto vam-img" src="res/img/w1.jpg">
						        		</div>
						        	</div>

						        	<!-- ///////////////////////////////// -->
						        	<div class="row cus-art-row zidx0">
						        		<a href="#">
							        		<div class="col-xs-12 col-sm-10 fixed-panel">
							        			<span><h5 class="text-info">Java123　　手沖達人</h5></span>
							        			<h4 class="inline-b bold">手沖咖啡必知的12個專有名詞</h4><h5 class="inline-b pull-right">2017-09-04</h5>
							        			<p>准确微微形势风云电信兴趣本报百度解决，表情限制具有水平如有一会儿十年配合屏幕体现画面不可以特，人体用户那天只是尴尬包括清除不见即将大，报名寻求只好合同走到党员学历基本上建筑具备编。</p>

							        			<button type="button" class="btn btn-default btn-sm zidx5 bor-like" aria-label="Left Align">
						        					<span class="tx-like">42</span>
													<span class="glyphicon glyphicon-heart tx-like" aria-hidden="true"></span>
												</button>
							        			<button type="button" class="btn btn-default btn-sm zidx5 bor-info" aria-label="Left Align">
						        					<span class="text-info">42</span>
													<span class="glyphicon glyphicon-bookmark text-info" aria-hidden="true"></span>
												</button>
												<span class="mgl20 tx-gray">5則回應</span>
							        		</div>
						        		</a>
						        		<div class="col-xs-12 col-sm-2 vam-div150 padt10">
						        			<img class="img-responsive mg-auto vam-img" src="res/img/w2.jpg">
						        		</div>
						        	</div>
						        	
						        	<!-- //////////////////////////////////// -->
						        	<div class="row cus-art-row zidx0">
						        		<a href="#">
							        		<div class="col-xs-12 col-sm-10 fixed-panel">
							        			<span><h5 class="text-info">Java123　　手沖達人</h5></span>
							        			<h4 class="inline-b bold">手沖咖啡必知的12個專有名詞</h4><h5 class="inline-b pull-right">2017-09-04</h5>
							        			<p>电信种子看出校长各国读书最多变，预测优质看来结合球员性能是指具备协助便是威望，一句话知道平衡男性生物限制联系人，命运天天露出名称多媒体。</p>

							        			<button type="button" class="btn btn-default btn-sm zidx5" aria-label="Left Align">
						        					<span class="tx-gray">42</span>
													<span class="glyphicon glyphicon-heart tx-gray" aria-hidden="true"></span>
												</button>
							        			<button type="button" class="btn btn-default btn-sm zidx5" aria-label="Left Align">
						        					<span class="tx-gray">42</span>
													<span class="glyphicon glyphicon-bookmark tx-gray" aria-hidden="true"></span>
												</button>
												<span class="mgl20 tx-gray">5則回應</span>
							        		</div>
						        		</a>
						  <!--       		<div class="col-xs-12 col-sm-2 vam-div150 padt10">
						        			<img class="img-responsive mg-auto vam-img" src="">
						        		</div> -->
						        	</div>

						        	<!-- ///////////////////////////////// -->
						        	<div class="row cus-art-row zidx0">
						        		<a href="#">
							        		<div class="col-xs-12 col-sm-10 fixed-panel">
							        			<span><h5 class="text-info">Java123　　手沖達人</h5></span>
							        			<h4 class="inline-b bold">手沖咖啡必知的12個專有名詞</h4><h5 class="inline-b pull-right">2017-09-04</h5>
							        			<p>准确微微形势风云电信兴趣本报百度解决，表情限制具有水平如有一会儿十年配合屏幕体现画面不可以特，人体用户那天只是尴尬包括清除不见即将大，报名寻求只好合同走到党员学历基本上建筑具备编。</p>

							        			<button type="button" class="btn btn-default btn-sm zidx5 bor-like" aria-label="Left Align">
						        					<span class="tx-like">42</span>
													<span class="glyphicon glyphicon-heart tx-like" aria-hidden="true"></span>
												</button>
							        			<button type="button" class="btn btn-default btn-sm zidx5 bor-info" aria-label="Left Align">
						        					<span class="text-info">42</span>
													<span class="glyphicon glyphicon-bookmark text-info" aria-hidden="true"></span>
												</button>
												<span class="mgl20 tx-gray">5則回應</span>
							        		</div>
						        		</a>
						        		<div class="col-xs-12 col-sm-2 vam-div150 padt10">
						        			<img class="img-responsive mg-auto vam-img" src="res/img/w2.jpg">
						        		</div>
						        	</div>






						        </div>
					        </div>









					        <!-- tab333333333333333333333333333333333 -->
					        <div role="tabpanel" class="tab-pane" id="tab3">
								<div class="container-floid">

									<c:forEach var="actVO" items="${actlist}">
										<div class="row mgb30 mgt20 ">
											<div class="col-xs-12 col-sm-6">
												<img class="img-responsive  mg-auto vam-img" src="<%=request.getContextPath()%>/act/actImg.do?act_no=${actVO.act_no}&index=1">
											</div>
											<div class="col-xs-12 col-sm-6">
												<h4 class="inline-b bold">${actVO.act_name}</h4>
												<p>${fn:substring(actVO.act_cont,0,160)}...</p>
												<h5 class="inline-b pull-left text-info">${actVO.act_op_date}</h5>
												<h5 class="inline-b pull-left mgl20 text-info">${fn:substring(actVO.act_add,0,3)}</h5>
												<button type="button" class="btn btn-default btn-sm zidx5 pull-right" aria-label="Left Align">
						        					<span class="tx-gray">42</span>
													<span class="glyphicon glyphicon-bookmark tx-gray" aria-hidden="true"></span>
												</button>
											</div>
										</div>
									</c:forEach>
									




									
								</div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>















<!-- ------------------------------------------------------------------------------------------------------- -->






		<!--FFFFFFFFFFFFFFFFFFFFFFFFFFfff-->
		<div class="footer">
			<div class="container text-center">
				<div class="row">
					<div class="col-xs-12 col-sm-1 col-sm-offset-4">
						<a href="#">服務條款</a>
					</div>
					<div class="col-xs-12 col-sm-1">
						<a href="#">常見問題</a>
					</div>
					<div class="col-xs-12 col-sm-1">
						<a href="#">聯絡我們</a>
					</div>
					<div class="col-xs-12 col-sm-1">
						<a href="#">商業合作</a>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<h5 class="small">COPYRIGHT (C) 2017 BeanLife, Inc. ALL RIGHTS RESERVED.</h5>
				</div>
			</div>
		</div>




  </body>
</html>