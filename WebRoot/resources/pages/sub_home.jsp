<%--
  Created by IntelliJ IDEA.
  User: QQQ
  Date: 15/8/23
  Time: 上午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<html>
<head>
  <%@ include file="include/html_head.jsp" %>


  <% String param = request.getParameter("sub");
    String CONTENT_FILE = getClass().getResource("/").getPath() + "resources/pages/content/" + param + ".xml";
    HashMap<String, Object> hash_for_all= new HashMap<>();
    XmlReader xmlReader = new XmlReader();
    xmlReader.main(CONTENT_FILE, hash_for_all);
    HashMap<String, String> hash_for_title = (HashMap)hash_for_all.get("title");
    HashMap<String, Object> hash_for_content_all= (HashMap)hash_for_all.get("content");
  %>
  <title><%=hash_for_title.get("name")%></title>
</head>
<body>
<%@page import="utility.XmlReader" language="java" %>
<%@page import="java.util.*"%>


<div class="container bs-docs-container">
  <div class="row">
    <div class="col-md-12" role="main">
      <div class="bs-docs-section">

        <%
        int content_size = hash_for_content_all.size();
        for (int i = 0; i<content_size; i++ ) {
            HashMap<String, String> hash_for_content= (HashMap)hash_for_content_all.get(String.valueOf(i));
            %>
          <%if(hash_for_content.get("type").equals("panel")){
              if(hash_for_content.containsKey("url")) {%>
            <a href="<%=hash_for_content.get("url")%>">
                <% }
              if (i%5 == 0) {%>
              <div class="panel panel-warning">
              <%} else if (i%5 == 1) {%>
                  <div class="panel panel-danger">
              <%} else if (i%5 == 2) {%>
                  <div class="panel panel-success">
                          <%} else if (i%5 == 3) {%>
                      <div class="panel panel-info">
              <%} else {%>
              <div class="panel panel-default">
              <%}%>
                <div class="panel-heading"><h3 class="panel-title"><span class="glyphicon glyphicon-bookmark" aria-hidden="true">
                    <strong><%=hash_for_content.get("name")%></strong></span></h3></div>
                  <% if (hash_for_content.containsKey("intro")) {%>
                  <div class="panel-body">
                  <%=hash_for_content.get("intro")%>
                </div>
                  <%}%>
            </div>
                      <%if(hash_for_content.containsKey("url")) {%>
                      </a>
        <%}
          } else if (hash_for_content.get("type").equals("media")) {
              if(i%3==0) {
            %>
            <div class="bs-callout bs-callout-danger">
            <% } else if (i%3==1) { %>
              <div class="bs-callout bs-callout-info">
                <% } else if (i%3==2) { %>
                <div class="bs-callout bs-callout-warning">
                  <%}%>
            <div class="media">
            <div class="media-left">
            <a href="<%=hash_for_content.get("url")%>">
            <img class="media-object" src="<%=basePath+hash_for_content.get("src")%>" alt="<%=hash_for_content.get("alt")%>">
            </a>
            </div>
            <div class="media-body">
                <a href="<%=hash_for_content.get("url")%>">
            <h4 class="media-heading"><%=hash_for_content.get("name")%></h4>
                <%=hash_for_content.get("intro")%>
                </a>
            </div>
            </div>
            </div>
          <%
            } else if (hash_for_content.get("type").equals("img")) {
              %>
                <tb>
                  <tr>
                    <img class="img-responsive" class="center-block" width=100% src="<%=basePath%><%=hash_for_content.get("src")%>">
                    <% if (hash_for_content.containsKey("url") ) {%>
                    </a>
                    <%}%>
                  </tr>
                </tb>
                <%
            } else if (hash_for_content.get("type").equals("collap"))
        }
        %>

    </div>
      </div></div></div></div>
<tb>
  <tr>
    <img class="img-responsive" class="center-block" width=100% src="<%=basePath%>/resources/img/footer_small3.jpg">
  </tr>
</tb>


  <a class="btn btn-primary" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Link with href
  </a>
  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Button with data-target
  </button>
  <div class="collapse" id="collapseExample">
    <div class="well">
      ...
    </div>
  </div>

</body>
</html>
