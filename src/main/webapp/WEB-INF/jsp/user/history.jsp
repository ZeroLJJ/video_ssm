<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/4/1
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>历史播放</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/bootstrap.min.css" type="text/css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-history.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="<%=path%>/templete/video/font-awesome-4.4.0/css/font-awesome.min.css" type="text/css">

    <style>
        * {
            box-sizing: content-box;
            -webkit-box-sizing: content-box;
        }

        .col-md-6,.col-sm-6{
            padding-top: 10px;
            width: 47%;
        }
    </style>
</head>
<body>
<!-- Header -->
<nav id="top" style="color: #666;background: #fff;font-size: 16px;line-height: 1.6em;font-weight: 400;height: 100px;">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-6" style="padding: 18px 0 0 30px;">
                <strong>欢迎来到视频分享网站!</strong>
            </div>
            <div class="col-md-6 col-sm-6">
                <ul class="nav nav-pills navbar-right">
                    <c:choose>
                        <c:when test="${empty user}">
                            <li><a target="_blank" href="<%=path%>/login" style="color: black"><i class="fa fa-power-off" style="color: rgba(17, 90, 255, 0.64);"></i> 登录</a></li>
                            <li><a target="_blank" href="<%=path%>/register" style="color: black"><i class="fa fa-user-plus" style="color: rgba(17, 90, 255, 0.64);"></i> 注册</a></li>
                        </c:when>
                        <c:when test="${not empty user}">
                            <li class="dropdown" id="user-menu" style="postion:relative;z-index:1000;">
                                <a target="_blank" href="<%=path%>/space/${user.uid}" class="dropdown-toggle" data-toggle="dropdown" style="padding: 10px 15px 0 15px">
                                    <img id="userImg" src="<%=path%>/images/${user.uimg}" height="32px" width="32px"
                                         style="transition: all 0.5s;border-radius: 50%;border: 1px solid #F36;">
                                </a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="margin-right: -50px; width: 230px;">
                                    <li class="dropdown-header" style="text-align: center;color: #000;font-weight: bold;">
                                        <c:choose>
                                            <c:when test="${user.uname != null}">
                                                ${user.uname}
                                            </c:when>
                                            <c:when test="${user.uid != null}">
                                                ${user.uid}
                                            </c:when>
                                        </c:choose>
                                    </li>
                                    <li class="divider"></li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/user/home" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-account"></i>
                                            个人中心
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/post" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-member"></i>
                                            投稿管理
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/space/${user.uid}/favorite" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-wallet"></i>
                                            收藏夹
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/user/${user.uid}/history" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-live"></i>
                                            播放记录
                                        </a>
                                    </li>
                                    <li class="divider" style="clear: left"></li>
                                    <li style="float:right;">
                                        <a href="<%=path%>/exit"><i class="fa fa-power-off"></i> 退出</a>
                                    </li>
                                </ul>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</nav>

<header style="color: #666;font-size: 16px;line-height: 1.6em;font-weight: 400;">
    <div class="header">
        <div class="num" style="background-color: rgba(53, 26, 206, 0.40)">
            <div class="menu-wrapper">
                <ul class="nav-menu">
                    <li class="m-i home" style="margin: 0 3px 0 -20px;">
                        <a href="<%=path%>/" class="i-link"><em>首页</em></a>
                    </li>
                    <!-- 菜单栏（视频类型） -->
                    <c:forEach items="${videoType}" var="bigType">
                        <li class="m-i" style="margin: 0 3px;">
                            <a href="<%=path%>/video/all?bigType=${bigType.bid}" class="i-link"  style="width: 50px">
                                <em>${bigType.bname}</em>
                                <div class="v-num">
                                    <span class="addnew_1" style="height: 1.2em;width: 2.2em;">${bigType.countRecentlyPost}</span>
                                </div>
                            </a>
                            <ul class="i_num">
                                <c:forEach items="${bigType.smallTypeBOList}" var="smallType">
                                    <li>
                                        <a href="<%=path%>/video/all?smallType=${smallType.sid}">
                                            <b>${smallType.sname}<em></em></b>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
                <div class="search" style="top:10px;left:700px;">
                    <div id="searchform" target="_blank">
                        <input name="keyword" type="text" class="search-keyword" autocomplete="off" placeholder="你不知道的100多件幻想乡秘闻">
                        <button type="submit" class="search-submit"></button>
                    </div>
                    <a class="link-ranking" href="<%=path%>/video/rank" target="_blank">
                        <span style="font-size: 12px;">排行榜</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header -->
<div class="b-page-body">
    <div class="container-body">
        <div class="newlist_info">
            <div class="b-head">
                <i class="b-icon b-icon-history"></i>
                <span class="b-head-t">播放历史</span>
            </div>
        </div>
        <div id="app" class="history">
            <div class="list-contain" style="position:relative">
                <ul class="history-list" id="history_list">
                    <c:forEach items="${history}" var="item">
                        <li class="history-record">
                            <div class="l-info">
                                <div class="lastplay-time">
                                    <i class="history-red-round"></i>
                                    <span class="lastplay-t">
                                        <fmt:formatDate value="${item.pdate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                    </span>
                                </div>
                            </div>
                            <div class="r-info">
                                <a class="preview" href="<%=path%>/video/show/${item.videoBO.vid}" target="_blank">
                                    <img src="<%=path%>/images/${item.videoBO.vimg}">
                                </a>
                                <div class="r-txt">
                                    <a class="title" href="<%=path%>/video/show/${item.videoBO.vid}" target="_blank">
                                        ${item.videoBO.vname}
                                    </a>
                                    <div class="w-info">
                                        <a href="<%=path%>/user/space/${item.videoBO.userBO.uid}" target="_blank">
                                            <i class="userpic" style="background-image: url(<%=path%>/images/${item.videoBO.userBO.uimg});"></i>
                                            <span class="username">
                                                <c:if test="${item.videoBO.userBO.uname == null}">
                                                    ${item.videoBO.userBO.uid}
                                                </c:if>
                                                <c:if test="${item.videoBO.userBO.uname != null}">
                                                    ${item.videoBO.userBO.uname}
                                                </c:if>
                                            </span>
                                        </a>
                                        <a href="<%=path%>/video/all?tag=${item.videoBO.smallTypeBO.sid}" target="_blank">
                                            <span class="name">${item.videoBO.smallTypeBO.sname}</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${fn:length(history) == 0}">
                <div class="nodata-contain" style="text-align: center;font-size: large">
                    <img src="<%=path%>/templete/bilibili/images/Unknown.png">
                    不好意思，查询数据为空
                </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<div class="my-footer" style="margin-top: 100px;">
    <div class="my-right">
        Copyright © 2017.集美大学 计算1312 林俊杰(Zero) 版权所有.
    </div>
</div>

</body>
<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>

<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
</html>
