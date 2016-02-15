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

</head>
<body class="container">

<br/>


    <section>
        <section class="Powered-by-XIUMI V5" style="position: static;">
            <section class="" style="margin-top: 10px; margin-bottom: 10px; position: static;">
                <section class="" style="padding: 0.3em 0.5em; border-radius: 0.5em 0.5em 0px 0px; margin-top: -0.8em; text-align: center; width: 100%; color: white; font-size: 18px; background-color: rgb(249, 110, 87);">
                    <section>
                        <strong>文件下载</strong>
                    </section>
                </section>
                <section class="" style="border: 1px solid rgb(249, 110, 87); border-radius: 0px 0px 0.5em 0.5em; padding: 10px;">
                    <section class="Powered-by-XIUMI V5" style="position: static;">
                        <section class="" style="margin-top: 0.5em; margin-bottom: 0.5em; line-height: 1; position: static;">
                                <span style="display: inline-block; margin-left: 0.4em; color: rgb(249, 110, 87); line-height: 1.4;" class="">
                                    <section>
                                    1.哮喘日记(哮喘儿童长期随访表)
                                    </section></span>
                            <div class="pull-right"><a href="<%=basePath%>download?id=1" class="btn btn-primary active" role="button">下载</a>
                            </div>
                        </section>
                    </section>
                    <hr/>
                    <section class="Powered-by-XIUMI V5" style="position: static;">
                        <section class="" style="margin-top: 0.5em; margin-bottom: 0.5em; line-height: 1; position: static;">
                                <span style="display: inline-block; margin-left: 0.4em; color: rgb(249, 110, 87); line-height: 1.4;" class=""><section>
                                2.症状及峰流速值记录表
                            </section></span>
                            <div class="pull-right"><a href="<%=basePath%>download?id=2" class="btn btn-primary active" role="button">下载</a>
                            </div>
                        </section>
                    </section>
                </section>
            </section>
        </section>
    </section>
    <p>
        <br/>
    </p>

    <img class="img-rounded center-block" width=100% src="<%=basePath%>/resources/img/qcode.jpg">
</body>

</html>
