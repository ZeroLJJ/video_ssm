<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/3/11
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>注册</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- Custom Theme files -->
    <link href="<%=path%>/templete/login/css/style.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/templete/video/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <style>
        ::-webkit-input-placeholder { /* WebKit browsers */
            color: rgba(251, 248, 255, 0.78);
        }
        /*  使chrome的使用自动填充时自动添加样式延时5000s，相当于让它失效 */
        input:-webkit-autofill {
            -webkit-text-fill-color: #fff !important;
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
</head>
<body>
<!--header start here-->
<div class="login-form" style="padding-top: 50px">
    <div class="top-login">
        <img src="<%=path%>/templete/login/images/group.png" style="margin: 32px 0 0 32px;" alt="无法显示图片"/>
    </div>
    <div class="login-top">
        <form action="<%=path%>/user/signUp" method="post">
            <div class="login-ic">
                <i class="name"></i>
                <input id="uid" type="text" name="user.uid" placeholder="用户名"/>
                <i id="uidTip"></i>
                <div class="clear"></div>
            </div>
            <div class="login-ic">
                <i class="password"></i>
                <input id="pwd" type="password" name="user.upassword" placeholder="密码"/>
                <i id="pwdTip"></i>
                <div class="clear"></div>
            </div>
            <div class="login-ic">
                <i class="password"></i>
                <input id="pwdCheck" type="password" placeholder="确认密码"/>
                <i id="pwdCheckTip"></i>
                <div class="clear"></div>
            </div>

            <div class="log-bwn">
                <input id="commit" type="submit" value="注册" style="width: 100%">
            </div>
        </form>
    </div>
</div>
<!--header start here-->

</body>
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/js/user.js"></script>

<script type="text/javascript">

</script>
</html>

