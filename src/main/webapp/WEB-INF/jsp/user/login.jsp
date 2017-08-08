<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/3/11
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <!-- Custom Theme files -->
    <link href="<%=path%>/templete/login/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src='<%=path%>/js/jquery-3.1.1.js'></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<div class="login-form">
    <div class="top-login">
        <img id="img1" src="<%=path%>/templete/login/images/group.png" style="margin: 32px 0 0 32px;" alt="无法显示图片"/>
        <img id="img2" src="" style="border-radius: 50%;display: none" height="130px" width="130px">
    </div>
    <div class="login-top">
        <form action="<%=path%>/user/signIn" method="post">
            <div class="login-ic">
                <i class="name"></i>
                <input id="userId" name="user.uid" type="text" placeholder="用户名" onfocus="this.placeholder = '';"
                       onblur="getImg(this.value)"/>
                <div class="clear"> </div>
            </div>
            <div class="login-ic">
                <i class="password"></i>
                <input type="password" name="user.upassword" placeholder="密码" onfocus="this.placeholder = '';"
                       onblur="if (this.value == '') {this.placeholder = '密码';}"/>
                <div class="clear"> </div>
            </div>

            <p class="copy" style="margin-top: 0;margin-bottom: 15px;color: #ff3366">
                <c:if test="${errorMsg != null}">
                    ${errorMsg}
                </c:if>
            </p>

            <div class="log-bwn">
                <input type="submit"  value="登录" >
                <a href="<%=path%>/register">注册</a>
            </div>
        </form>
    </div>
</div>
<!--header start here-->

<script type="text/javascript">

    //获取头像，用jquery+ajax+json
    function getImg(value) {
        if(value != ""){
            url = "<%=path%>/user/getImg";
            $.ajax({
                url: url, //请求验证页面
                type:"POST", //请求方式 可换为post 注意验证页面接收方式
                data:{userId:$("#userId").val()},//取得表文本框数据，作为提交数据 注意前面的 user 此处格式 key=value 其他方式请参考ajax手册
                dataType:"text",
                success: function(data){
                    if(data != "")
                    { //请求成功时执行操作
                        var img1 = document.getElementById('img1');
                        img1.style.display="none";
                        var img2 = document.getElementById('img2');
                        img2.src="<%=path%>/images/"+data;
                        img2.style.display="block";
                    }else{
                        //说明查询不到头像
                    }
                },
                error:function(){
                    return false;
                }
            });
        }else{
            var userId = document.getElementById('userId');
            userId.placeholder="用户名";
            var img1 = document.getElementById('img1');
            img1.style.display="block";
            var img2 = document.getElementById('img2');
            img2.style.display="none";
        }
    }

</script>
</body>
</html>
