<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/2/25
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>视频搜索</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/bootstrap.min.css"  type="text/css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/style.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/search-99923d56de.css">
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
<body class="old-ver">
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

<div class="so-wrap" style="position:relative;margin-top: 20px;">
    <div class="filter-block">
        <ul id="order" style="display: block;">
            <li class="filter-item active" data-id="0"><a href="javascript:;">综合排序</a></li>
            <li class="filter-item" data-id="1"><a href="javascript:;">最多点击</a></li>
            <li class="filter-item" data-id="2"><a href="javascript:;">最新发布</a></li>
            <li class="filter-item" data-id="3"><a href="javascript:;">最多弹幕</a></li>
            <li class="filter-item" data-id="4"><a href="javascript:;">最多收藏</a></li>
        </ul>
        <ul id="duration" style="display: block;">
            <li class="filter-item active" data-id="0"><a href="javascript:;">全部时长</a></li>
            <li class="filter-item" data-id="1"><a href="javascript:;">10分钟以下</a></li>
            <li class="filter-item" data-id="2"><a href="javascript:;">10-30分钟</a></li>
            <li class="filter-item" data-id="3"><a href="javascript:;">30-60分钟</a></li>
            <li class="filter-item" data-id="4"><a href="javascript:;">60分钟以上</a></li>
        </ul>
        <ul id="type" style="display: block;">
            <li class="filter-item active" data-big="0" data-small="0"><a href="javascript:;">全部分区</a></li>
            <c:forEach var="bigType" items="${videoType}">
                <li class="filter-item" data-big="${bigType.bid}"><a href="javascript:;">${bigType.bname}</a></li>
                <c:forEach var="smallType" items="${bigType.smallTypeBOList}">
                    <li class="filter-item" data-small="${smallType.sid}"><a href="javascript:;">${smallType.sname}</a></li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>

    <ul class="ajax-render" style="width:1100px;">
        <c:if test="${fn:length(videoPage.list) != 0}">
        <c:forEach items="${videoPage.list}" var="videoItem" varStatus="Status">
        <li class="video matrix" style="width: 170px;height: 210px">
            <a href="<%=path%>/video/show/${videoItem.vid}"  target="_blank" title="${videoItem.vname}">
                <div class="img">
                    <img src="<%=path%>/images/${videoItem.vimg}" title="${videoItem.vname}">
                    <span class="so-imgTag_rb">
                            ${videoItem.vduration}
                    </span>
                </div>
            </a>
            <div class="info">
                <div class="headline">
                    <a class="title" title="${videoItem.vname}" href="<%=path%>/video/show/${videoItem.vid}" target="_blank">
                        <em class="keyword">${videoItem.vname}</em>
                    </a>
                </div>
                <div class="des hide">
                    ${videoItem.vsummary}
                </div>
                <div class="tags">
                    <span class="so-icon watch-num" title="观看" style="width: 57px;height: 12px">
                        <i class="icon-playtime" ></i>
                        <c:if test="${videoItem.vplayTimes >= 10000}">
                            <fmt:formatNumber type="number" value="${videoItem.vplayTimes/10000}" pattern="0.0" maxFractionDigits="1"></fmt:formatNumber> 万
                        </c:if>
                        <c:if test="${videoItem.vplayTimes < 10000}">
                            ${videoItem.vplayTimes}
                        </c:if>
                    </span>
                    <span class="so-icon hide" title="弹幕">
                        <i class="icon-subtitle"></i>
                        ${videoItem.vdanmu}
                    </span>
                    <span class="so-icon time" title="上传时间" style="width: 83px;height: 12px">
                        <i class="icon-date"></i>
                        <!-- 注意大小写，mm表示分钟，MM表示月份，两个MM表示不足两位时补0 -->
                        <fmt:formatDate value="${videoItem.vaddTime}" pattern="yyyy-MM-dd"/>
                    </span>

                    <span class="so-icon" title="up主" style="width: 61.72px;height: 12px">
                        <i class="icon-uper"></i>
                        <a href="<%=path%>/user/space/${videoItem.userBO.uid}" class="up-name" target="_blank">
                            <c:if test="${videoItem.userBO.uname != null}">
                                ${videoItem.userBO.uname}
                            </c:if>
                            <c:if test="${videoItem.userBO.uname == null}">
                                ${videoItem.userBO.uid}
                            </c:if>
                        </a>
                    </span>
                </div>
            </div>
        </li>
        </c:forEach>
        </c:if>
        <c:if test="${fn:length(videoPage.list) == 0}">
        <div class="no-result">
            <p class="text">没有相关数据</p>
        <div>
        </c:if>
    </ul>
</div>

<div class="paging-wrap" style="display: block;">
    <div class="s-paging">
        <ul id="video-paging">
            <c:if test="${videoPage.hasPreviousPage == true}">
                <a style="width: 72px; height: 38px;" href="javascript:;" class="tcd-number" data-page="${videoPage.currentPage - 1}">上一页</a>
            </c:if>
            <c:forEach var="pageNo" begin="1" end="${videoPage.totalPage}">
                <c:if test="${pageNo == videoPage.currentPage}">
                    <span class="current">${pageNo}</span>
                </c:if>
                <c:if test="${pageNo != videoPage.currentPage}">
                    <a href="javascript:;" class="tcd-number" data-page="${pageNo}">${pageNo}</a>
                </c:if>
            </c:forEach>
            <c:if test="${videoPage.hasNextPage == true}">
                <a style="width: 72px; height: 38px;" href="javascript:;" class="tcd-number" data-page="${videoPage.currentPage + 1}">下一页</a>
            </c:if>
        </ul>
    </div>
</div>

<div class="my-footer" style="margin-top: 40px;">
    <div class="my-right">
        Copyright © 2017.集美大学 计算1312 林俊杰(Zero) 版权所有.
    </div>
</div>

</body>
<!-- JS -->
<script src="<%=path%>/templete/video/js/jquery-2.1.1.js"></script>

<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
<script src="<%=path%>/js/search.js"></script>

</html>
