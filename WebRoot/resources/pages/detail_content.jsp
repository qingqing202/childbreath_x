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
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />

<%@page import="utility.XmlReader" language="java" %>
<%@page import="java.util.*"%>

<%
  Map parameterMap = request.getParameterMap();
  if ( !parameterMap.containsKey("name") ) {return;}
  String param = request.getParameter("name");
  String CONTENT_FILE;
  CONTENT_FILE= getClass().getResource("/").getPath() + "resources/pages/content/" + param + ".xml";

  HashMap<String, Object> hash_for_all= new HashMap<>();
  XmlReader xmlReader = new XmlReader();
  xmlReader.main(CONTENT_FILE, hash_for_all);
  HashMap<String, String> hash_for_title = (HashMap)hash_for_all.get("title");
  HashMap<String, Object> hash_for_content_all= (HashMap)hash_for_all.get("content");
%>
<head></head>
<!--
<link href="http://v3.bootcss.com/assets/css/docs.min.css" rel="stylesheet">
<link href="//basePath%>/resources/css/dialogbox.css" rel="stylesheet">
-->
<head>
<%@ include file="include/html_head.jsp" %>
<title><%=hash_for_title.get("name")%></title>
    <style type="text/css">
        #backgrd {background: #fffbfb;}
    </style>
</head>

<body id="backgrd">
<div class="container ">
<%
    int content_size = hash_for_content_all.size();
    int collapse_group_id = 0;
    for(int i = 0; i < content_size; i++ ) {
    HashMap<String, String> hash_for_content = (HashMap)hash_for_content_all.get(String.valueOf(i));
%>
<%
    if ( hash_for_content.containsKey("url") ) {
%>
    <a href="<%=hash_for_content.get("url")%>">
<%
    }
    if ( hash_for_content.get("type").contains("half_box_")) {
        if( hash_for_content.get("type").equals("half_box_red")) {
%>
        <div class="bs-callout bs-callout-danger">
        <% } else if( hash_for_content.get("type").equals("half_box_brown") ) { %>
        <div class="bs-callout bs-callout-warning">
        <% } else if( hash_for_content.get("type").equals("half_box_green") ) {%>
        <div class="bs-callout bs-callout-info">
        <% } %>

        <% if (hash_for_content.containsKey("head") ) {%>
        <h4><%=hash_for_content.get("head")%></h4>
            <br/>
        <%}%>
           <%=hash_for_content.get("detail")%>
        </div>
<%
    } else if (hash_for_content.get("type").equals("img")) {
%>
  <tb>
    <tr>
      <img width=90% src="<%=basePath%><%=hash_for_content.get("src")%>" class="img-rounded img-thumbnail">
        <% if (hash_for_content.containsKey("url") ) {%>
      </a>
      <%}%>
    </tr>
  </tb>
<%
    } else if (hash_for_content.get("type").equals("paragraph")) {
%>
  <div>
    <p><%=hash_for_content.get("detail")%></p>
  </div>
<%
    } else if (hash_for_content.get("type").equals("h1")) {
%>
  <h1 class="text-center"><%=hash_for_content.get("detail")%></h1>
<%
    } else if (hash_for_content.get("type").equals("html")) {
%>
    <%= hash_for_content.get("detail")%>
<%
    } else if (hash_for_content.get("type").equals("signature")) {
%>
    <p class="text-right"><small><%=hash_for_content.get("author")%> <br/> <%=hash_for_content.get("date")%></small></p>
<%
    } else if (hash_for_content.get("type").equals("video")) {
%>
    <iframe width=100% height=100% id="video" src="<%=hash_for_content.get("detail")%>" frameborder=0 allowfullscreen></iframe>
    <script>
        document.getElementById("video").height=document.getElementById("video").scrollWidth*0.8+"px"
        window.onresize=Init;
        function Init(){
            document.getElementById("video").width=document.body.clientWidth*0.8+"px";
            document.getElementById("video").height=document.body.clientWidth*0.45+"px";
        }
    </script>
    <%
    } else if (hash_for_content.get("type").equals("grave_box")) {
    %>
    <div class="highlight"><pre>
        <%=hash_for_content.get("detail")%>
    </pre>
    </div>
<%
    } else if ( hash_for_content.get("type").equals("blockquote") ) {
%>
    <blockquote>
<%
        if ( hash_for_content.containsKey("head")) {
%>
        <h2><%=hash_for_content.get("head")%></h2>
<%
        }
%>
    <p><%=hash_for_content.get("detail")%></p>
    </blockquote>
<%
     } else if (hash_for_content.get("type").equals("title_red")){
            %>
             <div class="alert alert-danger" role="alert"><%=hash_for_content.get("detail")%></div>
            <%
     } else if (hash_for_content.get("type").equals("title_yellow")) {
         %>
            <div class="alert alert-warning" role="alert"><%=hash_for_content.get("detail")%></div>
         <%
     } else if (hash_for_content.get("type").equals("title_blue")) {
         %>
            <div class="alert alert-info" role="alert"><%=hash_for_content.get("detail")%></div>
         <%
     } else if (hash_for_content.get("type").equals("title_green")) {
         %>
            <div class="alert alert-success" role="alert"><%=hash_for_content.get("detail")%></div>
         <%
     } else if(hash_for_content.get("type").contains("panel_")) {
            if(hash_for_content.get("type").equals("panel_gray")) {
         %>
        <div class="panel panel-default">
          <%
            } else if(hash_for_content.get("type").equals("panel_red")) {
          %>
        <div class="panel panel-danger">
        <%
            } else if(hash_for_content.get("type").equals("panel_green")) {
        %>
            <div class="panel panel-success">
        <%
            } else if(hash_for_content.get("type").equals("panel_blue")) {
        %>
            <div class="panel panel-info">
        <%
            } else if(hash_for_content.get("type").equals("panel_yellow")) {
        %>
            <div class="panel panel-warning">
        <%
            } else {
                return;
            }
        %>
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                    <strong><%=hash_for_content.get("title")%></strong></h3>
                </div>
                <div class="panel-body">

                    <%if (hash_for_content.containsKey("body")) {%>
                    <%=hash_for_content.get("body")%>
                <%}if ( hash_for_content.containsKey("img_body")) {%>
                    <div class="thumbnail">
                    <img class="media-object" src="<%=basePath%><%=hash_for_content.get("img_body")%>">
                        </div>
                <%}%>
            </div>
        </div>
         <%
         } else if ( hash_for_content.get("type").equals("img_with_intro")) {
         %>
                <p><%=hash_for_content.get("detail")%></p>
                <img width=90% src="<%=basePath%><%=hash_for_content.get("src")%>" class="img-rounded img-thumbnail">
        <%
        } else if ( hash_for_content.get("type").equals("dot_box")) {
        %>
                <section class="tn-Powered-by-XIUMI" style="white-space: normal; border: 0px; max-width: 100%; margin: 0.5em 0px 0px; padding: 0px 0.5em 0.5em 0px; box-sizing: border-box;">
                    <section class="tn-Powered-by-XIUMI" style="border: 2px dotted white; padding: 10px; border-top-left-radius: 8px 64px; border-top-right-radius: 48px 8px; border-bottom-right-radius: 8px 16px; border-bottom-left-radius: 0px 48px; box-shadow: rgb(225, 225, 225) 8px 8px 3px; line-height: 1.4; font-size: 1em; font-family: inherit; text-decoration: inherit; color: rgb(255, 255, 255); background-color: rgb(72, 189, 193); box-sizing: border-box; word-wrap: break-word !important;">
                        <section class="tn-Powered-by-XIUMI" style="box-sizing: border-box;">
                            <%=hash_for_content.get("detail")%>
                    </section>
                    </section>
                </section>
                <br/>
         <%
         }else if ( hash_for_content.get("type").equals("squre_box")) { %>
                <section class="wxqq Powered-by-WeixinQuanquan.com">
                    <section style="border: none rgb(255, 129, 36); margin-top: 0.5em; margin-bottom: 0.5em; text-align: center; font-family: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(255, 129, 36); box-sizing: border-box; background-color: rgb(245, 238, 232);" class="tn-Powered-by-Weixinquanquan">
                        <section style="width: 100%; display: inline-block; vertical-align: top; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan" data-width="100%">
                            <section style="width: 100%; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan" data-width="100%">
                                <section style="width: 0px; border-top-width: 1em; border-top-style: solid; border-top-color: #00bbec; float: left; border-left-width: 1em; border-left-style: solid; border-left-color: #00bbec; border-right-width: 1em !important; border-right-style: solid !important; border-right-color: transparent !important; border-bottom-width: 1em !important; border-bottom-style: solid !important; border-bottom-color: transparent !important; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan wxqq-borderLeftColor wxqq-borderTopColor">
                                </section>
                                <section style="width: 0px; border-top-width: 1em; border-top-style: solid; border-top-color: rgb(207, 190, 120); float: right; border-right-width: 1em; border-right-style: solid; border-right-color: rgb(207, 190, 120); border-left-width: 1em !important; border-left-style: solid !important; border-left-color: transparent !important; border-bottom-width: 1em !important; border-bottom-style: solid !important; border-bottom-color: transparent !important; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan">
                                </section>
                            </section>
                            <section style="width: 100%; padding-top: 2em; padding-right: 0.5em; padding-left: 0.5em; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan" data-width="100%">
                                <p style="box-sizing: border-box;">
                                    <%=hash_for_content.get("detail")%>
                                </p>
                            </section>
                            <section style="display: inline-block; vertical-align: top; width: 100%; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan" data-width="100%">
                                <section style="width: 0px; float: left; border-left-width: 1em; border-left-style: solid; border-left-color: rgb(207, 190, 120); border-bottom-width: 1em; border-bottom-style: solid; border-bottom-color: rgb(207, 190, 120); border-top-width: 1em !important; border-top-style: solid !important; border-top-color: transparent !important; border-right-width: 1em !important; border-right-style: solid !important; border-right-color: transparent !important; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan">
                                </section>
                                <section style="width: 0px; float: right; display: inline-block; border-right-width: 1em; border-right-style: solid; border-right-color: #00bbec; border-bottom-width: 1em; border-bottom-style: solid; border-bottom-color: #00bbec; border-top-width: 1em !important; border-top-style: solid !important; border-top-color: transparent !important; border-left-width: 1em !important; border-left-style: solid !important; border-left-color: transparent !important; box-sizing: border-box;" class="tn-Powered-by-Weixinquanquan wxqq-borderRightColor wxqq-borderBottomColor">
                                </section>
                            </section>
                        </section>
                        <section style="width: 0px; height: 0px; clear: both;">
                        </section>
                    </section>
                </section>


                <%
        } else if ( hash_for_content.get("type").equals("collapse")) {
            boolean first_collapse = false;
                if (hash_for_content_all.containsKey(String.valueOf(i-1))) {
                HashMap<String, String> last_hash_for_content = (HashMap)hash_for_content_all.get(String.valueOf(i-1));
                if(!last_hash_for_content.get("type").equals("collapse")) {
                    first_collapse = true;
            %>

                <script>
                    function changeArrow(id)
                    {
                        $("glyphicon").className ="glyphicon glyphicon-chevron-right";

                        x=document.getElementById("collapseArrow"+id);  // 找到元素
                        y=document.getElementById("collapse"+id);  // 找到元素

                        if(y.className.match("in")) {
                            x.className="glyphicon glyphicon-chevron-right";
                        } else {
                            x.className="glyphicon glyphicon-chevron-down";
                        }

                    }
                </script>
            <div class="panel-group" id="accordion<%=collapse_group_id%>">
            <% }} %>

            <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion<%=collapse_group_id%>"
                                   href="#collapse<%=i%>" onclick="changeArrow(<%=i%>)">
                                    <%=hash_for_content.get("title")%>
                                    <div class="pull-right"><span id="collapseArrow<%=i%>" class="glyphicon glyphicon-chevron-right"></span></div>
                                </a>
                            </h4>
                        </div>
                <%if ( hash_for_content.get("title").contains("病房设施") ) {
                    %>
                        <div id="collapse<%=i%>" class="panel-collapse collapse in">
                            <% } else { %>
                            <div id="collapse<%=i%>" class="panel-collapse collapse">
                                <% } %>

                            <div class="panel-body">
                                <%=hash_for_content.get("detail")%>
                            </div>
                        </div>
                    </div>
                <%
            if (hash_for_content_all.containsKey(String.valueOf(i+1))) {
                HashMap<String, String> next_hash_for_content = (HashMap)hash_for_content_all.get(String.valueOf(i+1));
                if(!next_hash_for_content.get("type").equals("collapse")) {
                    collapse_group_id+=1;
            %>
                </div>
            <%}
            } else {
                collapse_group_id+=1;
                %>
            </div>
                <%
            }
        }
    if( hash_for_content.containsKey("url")) {
%>
    </a>
<%
    }
  }
%>

            </div>
    </body>
</html>
