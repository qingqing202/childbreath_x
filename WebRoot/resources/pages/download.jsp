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
    <%@page import="java.util.*"%>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/weui/weui.css"/>
</head>
<body class="container">

<h2 class="text-center">
    文件下载

</h2>

<hr />



<p class="text-right"><small> <a href = "index.jsp">上海儿童医学中心呼吸科</a>
    <br/>
    2016年02月
</small></p>

<c:choose>
    <c:when test="${empty status}"></c:when>
    <c:otherwise>
        <p id="status" value="${status}" hidden>${status}</p>
    </c:otherwise>
</c:choose>

<form onsubmit="return is_valid();" action="<%=request.getContextPath()%>/mgn/download" method="POST">


<section>
    <section class="Powered-by-XIUMI V5" style="position: static;">
        <section class="" style="margin-top: 10px; margin-bottom: 10px; position: static;">
            <section class="" style="display: inline-block; width: 100%; border-radius: 0.7em; box-shadow: rgb(204, 204, 204) 0.2em 0.2em 0.3em; border: 0.14em solid rgb(249, 110, 87);">
                <section class="Powered-by-XIUMI V5">
                    <section class="" style="padding: 10px; position: static;">
                        <section class="" style="text-align: center; font-size: 18px; color: rgb(249, 110, 87);">
                            <section>
                                <strong>
                                    哮喘相关</strong>
                            </section>
                        </section>
                    </section>
                </section>
                <section class="Powered-by-XIUMI V5">
                    <section class="" style="position: static;">
                        <section class="" style="padding-right: 8px; padding-left: 8px;">
                            <section>
                                <div class="weui_cells weui_cells_checkbox">
                                    <label class="weui_cell weui_check_label" for="s11">
                                        <div class="weui_cell_hd">
                                            <input type="checkbox" class="weui_check" name="checkbox1" id="s11" value="1">
                                            <i class="weui_icon_checked"></i>
                                        </div>
                                        <div class="weui_cell_bd weui_cell_primary">
                                            哮喘日记(哮喘儿童长期随访表)
                                        </div>
                                    </label>
                                    <label class="weui_cell weui_check_label" for="s12">
                                        <div class="weui_cell_hd">
                                            <input type="checkbox" name="checkbox1" class="weui_check" id="s12" value="2">
                                            <i class="weui_icon_checked"></i>
                                        </div>
                                        <div class="weui_cell_bd weui_cell_primary">
                                            症状及峰流速值记录表
                                        </div>
                                    </label>
                                </div>
                            </section>
                        </section>
                    </section>
                </section>
            </section>
        </section>
    </section>
</section>

    <br/>


    <div class="form-group">
        <div>
            <input type="email" class="form-control" name="inputEmail" id="inputEmail3" placeholder="邮箱地址">
        </div>
    </div>

    <div class="weui_btn_area">
        <input type="submit" name="btn_sendmail" value="发送致邮箱"
               class="weui_btn weui_btn_primary" onclick="return test(this)">
    </div>
    <hr/>
    <div class="weui_btn_area"> <input type="submit" name="btn_download" value="直接下载" class="weui_btn weui_btn_primary" onclick="return test(this)"></div>

    </form>

<hr/>


<!--BEGIN toast-->
<div id="toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <i class="weui_icon_toast"></i>
        <p class="weui_toast_content">已发送</p>
    </div>
</div>
<!--end toast-->

<!-- loading toast -->
<div id="loadingToast" class="weui_loading_toast" style="display:none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">发送中</p>
    </div>
</div>



<br/>


<div class="weui_dialog_alert" id="dialog2" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="dialog_t">dialog_t</strong></div>
        <div class="weui_dialog_bd" id="dialog_p" name="dialog_p">dialog_p</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
        </div>
    </div>
</div>



    <img class="img-rounded center-block" width=100% src="<%=basePath%>/resources/img/qcode.jpg">

<script src="<%=request.getContextPath()%>/resources/js/download/download.js"></script>
</body>

</html>
