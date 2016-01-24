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
<head>
<%@ include file="include/html_head.jsp" %>
        <title><%=hash_for_title.get("name")%></title>

        <style type="text/css">
        #top{background-image:url(<%=basePath+"/resources/img/background.jpg"%>);background-position:center; }
        #top{background-image:url(<%=basePath+"/resources/img/background.jpg"%>);background-position:center; }
    </style>
</head>

<body id="top">
<div class="container">
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
      <img width=90% src="<%=basePath%><%=hash_for_content.get("src")%>" class="img-rounded img-thumbnail center-block">
        <% if (hash_for_content.containsKey("url") ) {%>
      </a>
      <%}%>
<%
    } else if (hash_for_content.get("type").equals("img_no_border")) {
%>
    <img class="img-rounded center-block" width=100% src="<%=basePath%><%=hash_for_content.get("src")%>">
        <% if (hash_for_content.containsKey("url") ) {%>
    </a>
        <%}%>
<%
    } else if (hash_for_content.get("type").equals("paragraph")) {
%>
  <div>
    <p><%=hash_for_content.get("detail")%></p>
  </div>
<%
    } else if (hash_for_content.get("type").equals("h1")) {
%>
  <h2 class="text-center"><%=hash_for_content.get("detail")%></h2>
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
    } else if (hash_for_content.get("type").equals("opacity_box")) {
    %>

    <section><section><section><section><section><section>
        <br  />
    </section></section></section></section></section></section>
    <section class="aimeibian" style="border: 0px none;" data-ele="imb"><section style="margin-top: 10px; margin-bottom: 10px; border: 0px; box-sizing: border-box; font-family: 微软雅黑;">
        <section style="max-width: 100%; height: 1em; box-sizing: border-box; word-wrap: break-word !important;">
            <section style="max-width:100%; border-top:0.4em solid rgb(234,101,23); border-left:0.4em solid rgb(234,101,23); height:20px; width:20px; float:left">
            </section>
            <section style="max-width:100%; border-top:0.4em solid rgb(234,101,23); border-right:0.4em solid rgb(234,101,23); height:20px; width:20px; float:right">
            </section></section><section style="border: 1px solid rgb(234, 101, 23); max-width: 100%; box-sizing: border-box; margin-top: -0.8em; margin-right: 0.1em; margin-left: 0.2em; padding: 20px; word-wrap: break-word !important;">
        <section style="word-wrap: break-word; border: none; line-height: 1.5; word-break: break-all; font-size: 14px; background-image: none;">
            <p style="text-indent: 2em";>
                            <span style="color:inherit;">
                                <%=hash_for_content.get("detail")%>
                            </span>
            </p>
        </section>
    </section>
        <section style="max-width: 100%; margin-top: -18px; height: 1em; box-sizing: border-box; word-wrap: break-word !important;">
            <section style="max-width:100%; border-bottom:0.4em solid rgb(234,101,23); border-left:0.4em solid rgb(234,101,23); height:20px; width:20px; float:left">
            </section>
            <section style="max-width:100%; border-bottom:0.4em solid rgb(234,101,23); border-right:0.4em solid rgb(234,101,23); height:20px; width:20px; float:right">
            </section>
        </section>
    </section>
    </section>





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
        <div class="panel panel-default opacity35">
          <%
            } else if(hash_for_content.get("type").equals("panel_red")) {
          %>
        <div class="panel panel-danger opacity35">
        <%
            } else if(hash_for_content.get("type").equals("panel_green")) {
        %>
            <div class="panel panel-success opacity35">
        <%
            } else if(hash_for_content.get("type").equals("panel_blue")) {
        %>
            <div class="panel panel-info opacity35">
        <%
            } else if(hash_for_content.get("type").equals("panel_yellow")) {
        %>
            <div class="panel panel-warning opacity35">
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
                <img width=90% src="<%=basePath%><%=hash_for_content.get("src")%>" class="img-rounded img-thumbnail center-block">
        <%
        } else if ( hash_for_content.get("type").equals("dot_box")) {
        %>

                <section class="aimeibian" style="border: 0px none; padding: 0px;">
                    <section style="margin: 10px 0; color: white;  text-align: justify; white-space: normal; border: 0px;">
                        <section style="padding: 10px; border: 2px dotted white; border-radius:5px; box-shadow: rgb(225, 225, 225) 8px 8px 3px; font-family: inherit; font-weight: inherit; text-align: inherit; text-decoration: inherit; background-color: rgb(95, 156, 239);">
                            <p style="font-family: inherit; font-weight: inherit; text-align:justify; text-decoration: inherit; color: rgb(255,255,255); line-height: 1.5em; text-indent: 2em;">
                                <span style="color:inherit; ">
                            <%=hash_for_content.get("detail")%>
                                </span>
                            </p>
                        </section>
                    </section>
                </section>
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
                                <p style="font-size: 20px;">
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
                    } else if (hash_for_content.get("type").equals("media")) {
                %>

                <div class="list-group">
                    <% if (hash_for_content.containsKey("link")) { %>
                    <a href="<%=basePath + hash_for_content.get("link")%>" class="list-group-item opacity50">
                        <% } else if (hash_for_content.containsKey("outlink")){ %>
                    <a href="<%=hash_for_content.get("outlink")%>" class="list-group-item opacity50">
                        <%}%>

                        <h4><%=hash_for_content.get("title")%></h4>
                        <hr style="margin:0"  />
                        <div class="media">
                            <div class="media-left media-middle">
                                <img class="media-object img-rounded" height="70px" width="70px" src="<%=basePath+hash_for_content.get("img_src")%>">
                            </div>
                            <div class="media-body">
                                <h4>
                                <small><%=hash_for_content.get("body")%></small>
                                    </h4>
                            </div>
                        </div>
                    </a>
                </div>
                <%
                    } else if (hash_for_content.get("type").equals("block_1_begin")){
                %>

                <section class="aimeibian" style="border: 0px none;">
                    <section style="margin-right: auto; margin-left: auto;">
                        <section class="aimeibian" style="border: 0px none;">
                            <section style="text-align: center;">
                                <section style="padding-right: 15px; padding-left: 15px; min-width: 2em; height: 30px; line-height: 30px; color: rgb(255, 255, 255); display: inline-block; border-radius: 0px 0px 10px 10px; font-size: 18px; font-weight: bold; background: rgb(243, 202, 48);">
                                    <section>
                                        <span style="color:inherit">
                                            <% if (hash_for_content.get("title").equals(null)) { %>
                                            <%=hash_for_content.get("detail")%>
                                            <% } else { %>
                                            <%=hash_for_content.get("title")%>
                                            <% } %>
                                        </span>
                                    </section>
                                </section>
                            </section>
                        </section>
                        <section class="aimeibian" style="border: 0px none;" data-ele="imb">
                            <section style="margin-top: -20px; margin-right: 10px; margin-left: 10px; padding-top: 18px; padding-bottom: 18px; color: rgb(51, 120, 155); border-radius: 10px 10px 0px 0px; background: rgb(252, 239, 215);">
                                <section class="aimeibian" style="border: 0px none;" data-ele="">
                                    <section style="margin-top: 10px; padding-right: 10px; padding-left: 20px; color: rgb(0, 0, 0);">
                                        <p style="color:inherit; line-height:1.5em; font-size:14px">
                                            <span style="font-size: 18px;">
                <%
                    } else if (hash_for_content.get("type").equals("block_1_end")) {
                %>
                                            </span>
                                        </p>
                                    </section>
                                </section>
                            </section>
                        </section>
                    </section>
                </section>
                <p><br/></p>
                <%
                } else if (hash_for_content.get("type").equals("red_bar")) {
                %>
                <div class="bs-callout bs-callout-danger">
                <%
                } else if (hash_for_content.get("type").equals("brown_bar")) {
                %>
                <div class="bs-callout bs-callout-warning">
                <%
                } else if (hash_for_content.get("type").equals("green_bar")) {
                %>
                <div class="bs-callout bs-callout-info">
                <%
                } else if (hash_for_content.get("type").equals("end_bar")) {
                %>
                   </div>
                <%
                    } else if (hash_for_content.get("type").equals("flowChart")) {
                %>

                <section class="aimeibian" style="border: 0px none;" data-ele="imb">
                    <section style="margin: 5px auto;">
                        <section style="padding-top: 5px; padding-bottom: 5px; word-wrap: break-word; word-break: normal; font-family: 微软雅黑; text-align: center; color: rgb(255, 255, 255); border-radius: 5px; background-color: rgb(0, 187, 236);">
                            <%=hash_for_content.get("detail")%>
                        </section>
                            <section style="margin-right: auto; margin-left: auto; border-left-width: 20px; border-left-style: solid; border-left-color: transparent; border-right-width: 20px; border-right-style: solid; border-right-color: transparent; border-top-width: 20px; border-top-style: solid; border-top-color: rgb(0, 187, 236); width: 0px; height: 0px;">
                        </section>
                    </section>
                </section>
                <%
                } else if (hash_for_content.get("type").equals("flowChart_end")) {
                %>
                <section class="aimeibian" style="border: 0px none;" data-ele="imb">
                    <section style="margin: 5px auto;">
                        <section style="padding-top: 5px; padding-bottom: 5px; word-wrap: break-word; word-break: normal; font-family: 微软雅黑; text-align: center; color: rgb(255, 255, 255); border-radius: 5px; background-color: rgb(0, 187, 236);">
                            <%=hash_for_content.get("detail")%>
                        </section>
                        </section>
                    </section>
                </section>
                <br />
                <%

                    } else if ( hash_for_content.get("type").equals("section1_begin")) {
                %>

                <section class="aimeibian" style="border: 0px none; padding: 0px;">
                    <section style="margin: 0px auto; padding: 0px;">
                        <section class="aimeibian" style="border: 0px none; padding: 0px;">
                            <section style="margin:0; padding:0; text-align:center">
                                <section style="margin:0; padding:0 15px; min-width:2em; height:30px; line-height:30px; background:rgb(243,202,48); color:rgb(255,255,255);display:inline-block; border-radius:0 0 10px 10px; font-size:18px; font-weight:bold">
                                    <section style="margin:0; padding:0; text-align:center">
                                        <span style="color:inherit">
                                            <%=hash_for_content.get("title")%>
                                        </span>
                                    </section>
                                </section>
                            </section>
                        </section>
                        <section class="aimeibian" style="border: 0px none; padding: 0px;" data-ele="">
                            <section style="margin:-20px 10px 0 10px; padding:18px 0px; background:rgb(252,239,215); color:rgb(51,120,155);border-radius:10px 10px 0 0;">
                                <section class="aimeibian" style="border: 0px none; padding: 0px;" data-ele="">
                                    <section style="margin-top:10px; padding:0 10px 0 20px; color:rgb(0,0,0); ">
                                        <p style="color:inherit; line-height:1.5em;">
                                            <span style="color: inherit; line-height: 1.5em; text-align: center; ">

                        <%
                    } else if ( hash_for_content.get("type").equals("section1_end")) {
                        %>
                                </span>
                                </p>
                            </section>
                        </section>
                    </section>
                </section>
                </section>
                </section>
                <br/>

                <%
                } else if ( hash_for_content.get("type").equals("title_snow")) {
                %>

                <section class="aimeibian" style="border: 0px none; padding: 0px;">
                    <section style="margin:0; padding:0; width:100%; display:inline-block">
                        <section style="border:none;margin:5px 0 0;padding:5px;background:none">
                            <section style="margin-top:-1px;margin-bottom:0;min-height:50px;visibility:visible;height:50px;line-height:50px;border-radius:3px;text-align:center;color:rgb(0,0,0);box-shadow:0 2px 2px rgb(189,189,189);padding-left:10px;background-color:rgb(102,204,255)">
                                <span style="color:inherit;">
                                    <%=hash_for_content.get("detail")%>
                                </span>
                                <img style="float:right" width="57" height="50" src="https://mmbiz.qlogo.cn/mmbiz/PaCPwXIogdsTGIURmJBTGANaHXPhU52bjtvj4L3OsqxrppyBptEKCBnKUsYO5KqseOiatKqXB1MMBUx0tqZgIgA/0?wx_fmt=png"/>
                            </section>
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



                <!--
                <section class="aimeibian" style="border: 0px none; padding: 0px;">
                    <section style="margin: 0px auto; padding: 0px;">
                        <section class="aimeibian" style="border: 0px none; padding: 0px;">
                            <section style="margin:0; padding:0; text-align:center">
                                <section style="margin:0; padding:0 15px; min-width:2em; height:30px; line-height:30px; background:rgb(243,202,48); color:rgb(255,255,255);display:inline-block; border-radius:0 0 10px 10px; font-size:18px; font-weight:bold">
                                    <section style="margin:0; padding:0; text-align:center">
                                        <span style="color:inherit">可单独换色</span>
                                    </section>
                                </section>
                            </section>
                        </section>
                        <section class="aimeibian" style="border: 0px none; padding: 0px;" data-ele="">
                            <section style="margin:-20px 10px 0 10px; padding:18px 0px; background:rgb(252,239,215); color:rgb(51,120,155);border-radius:10px 10px 0 0;">
                                <section class="aimeibian" style="border: 0px none; padding: 0px;" data-ele="">
                                    <section style="margin-top:10px; padding:0 10px 0 20px; color:rgb(0,0,0); ">
                                        <p style="color:inherit; line-height:1.5em;">
                                            <span style="color: inherit; line-height: 1.5em; text-align: center; ">
                                                不够可以复制，点击在后边插入空行再点击复制然后粘贴......
                                            </span>
                                        </p>
                                    </section>
                                </section>
                            </section>
                        </section>
                    </section>
                </section>
                <section class="aimeibian" style="border: 0px none; padding: 0px;">
                    <section style="margin:0; padding:0; width:100%; display:inline-block">
                        <section style="border:none;margin:5px 0 0;padding:5px;background:none">
                            <section style="margin-top:-1px;margin-bottom:0;min-height:50px;visibility:visible;height:50px;line-height:50px;border-radius:3px;text-align:left;color:rgb(0,0,0);box-shadow:0 2px 2px rgb(189,189,189);padding-left:10px;background-color:rgb(102,204,255)">
                                <span style="font-size:16px; color:inherit;">
                                    请输入标题
                                </span> &nbsp; &nbsp; &nbsp; &nbsp;<img style="float:right" width="57" height="50" src="https://mmbiz.qlogo.cn/mmbiz/PaCPwXIogdsTGIURmJBTGANaHXPhU52bjtvj4L3OsqxrppyBptEKCBnKUsYO5KqseOiatKqXB1MMBUx0tqZgIgA/0?wx_fmt=png"/>
                            </section>
                        </section>
                    </section>
                </section>
                <p>
                    <br/>
                </p>
                -->

            </div>





</body>
</html>
