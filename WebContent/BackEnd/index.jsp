<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>��ݭ���0902</title>
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <div class="container_fluid titlebar"><a class="form-inline titlebarForm" href="main.html"><img class="icon" src="images/BeanLifeLogo2.png">
        <h1>Bean-Life</h1></a></div>
    <!-- .left�O��������C������-->
    <!-- .left��᳡��0910�ץ���椺�e-->
    <!-- 0910 �·����s�W�U�Կ��-->
    <div class="container card">
      <div class="row">
        <div class="col-xs-2 left"><a class="h3 title act" href="#action" aria-expanded="false" aria-controls="action" data-toggle="collapse" style="text-decoration: none;">
            <div class="fa fa-futbol-o"></div><a class="h3" href="act.html"> ���ʼf��</a></a><a class="h3 title" href="#check" aria-expanded="false" aria-controls="check" data-toggle="collapse" style="text-decoration: none;">
            <div class="fa fa-check-circle"></div><span class="h3">���|�޲z</span>
            <ul class="collapse" id="check"><a>�������|</a><a>�ӫ~���|</a><a>�Q�װ����|</a></ul></a><a class="h3 title" href="#mem" aria-expanded="false" aria-controls="mem" data-toggle="collapse" style="text-decoration: none;">
            <div class="fa fa-address-card-o"></div><span class="h3">�|���޲z</span>
            <ul class="collapse" id="mem"><a>�|����ƺ޲z</a><a href="<%=request.getContextPath()%>/BackEnd/store/listAllStore.jsp">�t�ө��a���v</a><a>�n���޲z</a></ul></a><a class="h3 title" href="#admin" aria-expanded="false" aria-controls="admin" data-toggle="collapse" style="text-decoration: none;">
            <div class="fa fa-user-o"> </div><span class="h3">�޲z���޲z</span>
            <ul class="collapse" id="admin"><a>�޲z�b����v</a><a>�b��޲z</a></ul></a><a class="h3 title" href="#gift" aria-expanded="false" aria-controls="gift" data-toggle="collapse" style="text-decoration: none;">
            <div class="fa fa-gift"> </div><span class="h3">���x�~�Ⱥ޲z</span>
            <ul class="collapse" id="gift"><a>�s�i�޲z</a><a>�I���ث~�޲z</a><a>�I���ث~�~�Ⱥ޲z</a></ul></a></div>
        <div class="right col-xs-10">
          <div class="col-xs-12 right_top"><img src="images/bear.jpg" alt="">
            <h2>�A�n</h2><a class="fa fa-bell dropdown-toggle" href="#" data-toggle="dropdown"></a>
            <ul class="dropdown-menu">
              <li><a>10�����|���B�z</a></li>
              <li><a>10�����ʥ��f��</a></li>
              <li><a>10���t�ӷ|���ӽХ��f��</a></li>
              <li><a>10���I���ث~�ӽ�</a></li>
            </ul>
          </div>
          <div class="col-xs-12 right_middle">
            <div class="col-xs-12">
              <h2 class="check">�ݼf��</h2>
            </div>
          </div>
          <div class="col-xs-12 right_bottom">
          
          
          
          
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
  </body>
</html>