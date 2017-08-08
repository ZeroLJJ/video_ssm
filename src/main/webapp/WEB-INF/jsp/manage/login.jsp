<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date
: 2017/5/3
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Secure Yourself</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel='stylesheet prefetch' href='<%=path%>/templete/back/css/bootstrap.min.css'>

    <link rel="stylesheet" href="<%=path%>/templete/back/css/style.css">
    <link rel="stylesheet" href="<%=path%>/css/base.css">


</head>

<body>
<div id="render"></div>
<div id="cont">
    <h4 style="margin-bottom: 10px;">管理员登录</h4>
    <div style="margin:10px 0">
        <label>账号&nbsp;:&nbsp;</label>
        <input id="id" type="text" style="color:black;" placeholder="账号"/>
    </div>
    <div style="margin:10px 0">
        <label>密码&nbsp;:&nbsp;</label>
        <input id="password" type="password" style="color:black;" placeholder="密码"/>
    </div>
    <input id="login" type="button" style="width:120px" class="btn btn-danger" value="登录"/>
    <br>
</div>
<script src='<%=path%>/templete/back/js/d3.min.js'></script>

<script src="<%=path%>/templete/back/js/index.js"></script>
<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/js/back/login.js"></script>

</body>
</html>
