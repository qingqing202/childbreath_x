<%--
  Created by IntelliJ IDEA.
  User: QQQ
  Date: 15/8/22
  Time: 上午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <%@ include file="include/html_head.jsp" %>
  <title>上海市儿童医学中心呼吸科微信平台首页</title>

  <!-- Site CSS -->
  <link href="<%=basePath%>resources/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=basePath%>resources/css/site.min.css?v5" rel="stylesheet">

  <script>
    var _hmt = _hmt || [];
  </script>

  <meta name="generator" content="Ghost 0.5" />
  <script>
    var qqgroup = '318630708';
  </script>
</head>

<body class="home-template">

<div class="jumbotron masthead">
  <div class="container">
    <h1>上海儿童医学中心呼吸科</h1>
    <h2>微信公众号平台首页</h2>
  </div>
</div>

<div class="container projects">

  <div class="projects-header page-header">
    <h2>功能列表</h2>
    <p>以下列表包含了本公众号所有宣传网页及功能介绍</p>
  </div>

  <div class="row">


<%
  Object[] [] letters={
          // {“模块名称”， “图片地址”， “模块简介”，“链接地址”}
          {"疾病知识", basePath+"resources/img/jibingketang.jpg","介绍包括小儿肺炎、哮喘在内的常见呼吸疾病。",basePath+ "resources/pages/detail_content.jsp?name=diease"},
          {"护理知识", basePath+"resources/img/hulizhishi.jpg","介绍如何正确的护理婴幼儿，特别是患病的婴幼儿。",basePath+ "resources/pages/detail_content.jsp?name=nursing"},
          {"病房介绍", basePath+"resources/img/bingfang.jpg","介绍儿中心呼吸科病房基础设施及住院流程。",basePath+ "resources/pages/detail_content.jsp?name=ward"},
          {"后台管理", basePath+"resources/img/houtai.jpg","对注册用户的信息进行查询、统计和编辑。",basePath+"resources/pages/login.jsp"},
          {"商务", basePath+"resources/img/shangwu.jpg","TBC","__BLANK__"},
  };
%>

    <%
      for(int i=0;i<letters.length;i++){
        Object[] letter=letters[i];
    %>
    <div class="col-sm-6 col-md-4 col-lg-3 ">
      <div class="thumbnail">
        <a href="<%=letter[3]%>" title="<%=letter[0]%>" target="_blank"
           onclick="_hmt.push(['_trackEvent', 'tile', 'click', '<%=letter[0]%>'])">
          <img class="lazy" src="<%=letter[1]%>"
               width="300" height="150"
               alt="<%=letter[0]%>">
        </a>
        <div class="caption">
          <h3>
            <a href="<%=letter[3]%>"
               title="<%=letter[0]%>。"
               target="_blank" onclick="_hmt.push(['_trackEvent', 'tile', 'click', 'codeguide'])">
              <%=letter[0]%><br><br/></a>
          </h3>
          <p>
          <%=letter[2]%>
          </p>
        </div>
      </div>
    </div>
    <%
      }
    %>
  </div>
</div>

<footer class="footer ">
  <div class="container">
    <div class="row footer-top">
      <div class="col-sm-6 col-lg-6">
        <h4>
          <img src="<%=basePath%>resources/img/blogo1.jpg">
          上海市儿童医学中心呼吸科
        </h4>
        <p>上海市儿童医学中心呼吸科微信项目展示网站</p>
      </div>
      <div class="col-sm-6  col-lg-5 col-lg-offset-1">
        <div class="row about">
          <div class="col-xs-3">
          </div>
          <div class="col-xs-3">
          </div>
          <div class="col-xs-3">
            <h4>关于</h4>
            <ul class="list-unstyled">
              <li><a href="/about/">关于我们</a></li>
              <li><a href="/ad/">广告合作</a></li>
              <li><a href="/links/">友情链接</a></li>
            </ul>
          </div>
          <div class="col-xs-3">
            <h4>联系方式</h4>
            <ul class="list-unstyled">
              <li><a href="http://www.scmc.com.cn" title="儿童医学中心网站" target="_blank">儿中心</a></li>
              <li><a href="mailto:qingqing202@hotmail.com">电子邮件</a></li>
            </ul>
          </div>
        </div>

      </div>
    </div>
    <hr/>
    <div class="row footer-bottom">
    </div>
  </div>
</footer>
<%@ include file="include/footer.jsp" %>
</body>
</html>
