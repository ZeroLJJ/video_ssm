<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/2/24
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>

<html>
<head>
    <title>首页</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/bootstrap.min.css"  type="text/css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/style.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="<%=path%>/templete/video/font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css">

    <style>
        .b-icon{
            position:relative;
            margin:5px;
            bottom:2px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <nav id="top" style="color: #666;background: #fff;font-size: 16px;line-height: 1.6em;font-weight: 400;height: 100px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6" style="padding-top: 10px;">
                    <strong>欢迎来到视频分享网站!</strong>
                </div>
                <div class="col-md-6 col-sm-6">
                    <ul class="nav nav-pills navbar-right">
                        <c:choose>
                            <c:when test="${empty user}">
                                <li><a target="_blank" href="<%=path%>/login"><i class="fa fa-power-off" style="color: rgba(17, 90, 255, 0.64);"></i> 登录</a></li>
                                <li><a target="_blank" href="<%=path%>/register"><i class="fa fa-user-plus" style="color: rgba(17, 90, 255, 0.64);"></i> 注册</a></li>
                            </c:when>
                            <c:when test="${not empty user}">
                                <li class="dropdown" id="user-menu" style="postion:relative;z-index:1000;">
                                    <a target="_blank" href="<%=path%>/space/${user.uid}" class="dropdown-toggle"  data-toggle="dropdown" style="padding: 10px 15px 0 15px">
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
                    <div class="search" style="width: 342px;height: 36px;position: absolute;top:10px;left:700px;">
                        <div id="searchform" target="_blank" style="width: 268px;height: 32px">
                            <input name="keyword" type="text" class="search-keyword" autocomplete="off" placeholder="你不知道的100多件幻想乡秘闻"
                                    style="position: relative;bottom: 10px;width: 224px;height: 32px">
                            <button type="submit" class="search-submit" style="width: 48px;height: 32px"></button>
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
    <!-- /////////////////////////////////////////Content -->
    <div id="page-content" class="index-page">

        <div class="container">
            <div class="row">
                <div class="featured">
                    <div class="main-vid">
                        <div class="col-md-6">
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${hotList[0].vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${hotList[0].vname}</p>
                                </div>
                                <img src="<%=path%>/images/${hotList[0].vimg}" alt="暂无第一名"/>
                            </div>
                        </div>
                    </div>
                    <div class="sub-vid">
                        <div class="col-md-3">
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${hotList[1].vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${hotList[1].vname}</p>
                                </div>
                                <img src="<%=path%>/images/${hotList[1].vimg}" alt="暂无第二名"/>
                            </div>
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${hotList[2].vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${hotList[2].vname}</p>
                                </div>
                                <img src="<%=path%>/images/${hotList[2].vimg}" alt="暂无第三名"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${hotList[3].vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${hotList[3].vname}</p>
                                </div>
                                <img src="<%=path%>/images/${hotList[3].vimg}" alt="暂无第四名"/>
                            </div>
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${hotList[4].vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${hotList[4].vname}</p>
                                </div>
                                <img src="<%=path%>/images/${hotList[4].vimg}" alt="暂无第五名"/>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="row">
                <div id="main-content" class="col-md-8">
                    <c:forEach var="type" items="${typeList}">
                    <div class="box" style="height: 500px;">
                        <div class="box-header">
                            <h2><i class="fa fa-play-circle-o"></i> ${type.bname}</h2>
                        </div>
                        <div class="box-content">
                            <div class="row">
                                <c:forEach var="item" items="${type.randomVideoList}">
                                <div class="col-md-4" style="width: 256px;height: 218px;">
                                    <div class="wrap-vid">
                                        <div class="zoom-container">
                                            <div class="zoom-caption">
                                                <a target="_blank" href="<%=path%>/video/show/${item.vid}">
                                                    <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                                </a>
                                                <p>${item.vname}</p>
                                            </div>
                                            <img src="<%=path%>/images/${item.vimg}"/>
                                        </div>
                                        <div class="info">
                                            <h4 style="padding: 10px 0">
                                                <a href="<%=path%>/space/${item.userBO.uid}">
                                                    <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                                    <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                                </a>
                                            </h4>
                                            <span><i class="fa fa-calendar"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                            <span><i class="fa fa-heart" style="color: #ff3a44"></i>${item.vfavoriteTimes}</span>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div id="sidebar" class="col-md-4">
                    <c:forEach var="type" items="${typeList}">
                    <div class="widget wid-post" style="height: 480px;margin-bottom: 20px;">
                        <div class="heading" style="margin-bottom: 34px"><h4><i class="fa fa-globe"></i> 排行：</h4></div>
                        <div class="content" style="height: 418px;">
                            <c:forEach var="item" items="${type.rankVideoList}">
                            <div class="post wrap-vid" >
                                <div class="zoom-container">
                                    <div class="zoom-caption">
                                        <a target="_blank" href="<%=path%>/video/show/${item.vid}">
                                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                        </a>
                                        <p>${item.vname}</p>
                                    </div>
                                    <img src="<%=path%>/images/${item.vimg}"/>
                                </div>
                                <div class="wrapper">
                                    <h6 class="vid-name">
                                        <a target="_blank" href="<%=path%>/video/show/${item.vid}">${item.vname}</a>
                                    </h6>
                                    <div class="info">
                                        <h6>
                                            <a target="_blank" href="<%=path%>/space/${item.userBO.uid}">
                                                <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                                <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                            </a>
                                        </h6>
                                        <span><i class="fa fa-calendar"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                        <span><i class="fa fa-heart" style="color: #ff3a44"></i>${item.vfavoriteTimes}</span>

                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>

    <div class="my-footer">
        <div class="my-right">
            Copyright © 2017.集美大学 计算1312 林俊杰(Zero) 版权所有.
        </div>
    </div>
    <!-- Footer -->
</body>
<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>

</html>
