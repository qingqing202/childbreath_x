<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>

    <%@ include file="include/html_head.jsp" %>
    <title>Bootstrap 实例 - 折叠面板</title>
    <script src="<%=basePath%>/resources/js/bootstrap.min-3.3.0.js"></script>
    <script src="<%=basePath%>/resources/js/jquery.min-2.0.0.js"></script>
</head>
<body>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion"
                   href="#collapseOne">
                    点击我进行展开，再次点击我进行折叠。第 1 部分
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                vice lomo.
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion"
                   href="#collapseTwo">
                    点击我进行展开，再次点击我进行折叠。第 2 部分
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                vice lomo.
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion"
                   href="#collapseThree">
                    点击我进行展开，再次点击我进行折叠。第 3 部分
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                vice lomo.
            </div>
        </div>
    </div>
</div>


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
