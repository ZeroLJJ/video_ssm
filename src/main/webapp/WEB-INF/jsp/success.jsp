<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/3/16
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<html>
<head>
    <title>操作成功</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">

</head>
<body id="my-success">
<div class="pane">
    <div class="text">
        <c:if test="${msg != null}">${msg}</c:if><br/>
        在<span id="s" style="color: #6ea7ff">5</span>秒后返回首页<br/>
        <a href="<%=path%>/">或点击此处返回首页</a>
    </div>
</div>
</body>
<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>

<script type="text/javascript">
    $(function () {
        var time = 5;

        setInterval(count,1000);

        function count(){
            time--;
            $("#s").text(time);
            if(time == 0){
                window.location.href="<%=path%>/";
            }
        }
    })
</script>
</html>
