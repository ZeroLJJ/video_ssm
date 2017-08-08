<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/4/19
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>个人空间</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/app_b49a4762.css">
    <!-- 对话框插件 -->
    <link rel="stylesheet" href="<%=path%>/templete/jDialog/jDialog/jDialog.css">

</head>
<body>
<!-- Header -->
<header style="margin-top:40px;">
    <div class="header" style="z-index: 10000;box-shadow: 1px -1px 7px black;height: 50px;">
        <div class="num" style="height: 50px;">
            <div class="menu-wrapper">
                <ul class="nav-menu">
                    <li class="m-i home" style="margin: 0 3px 0 -20px;">
                        <a href="<%=path%>/" class="i-link"><em>首页</em></a>
                    </li>
                    <!-- 菜单栏（视频类型） -->
                    <c:forEach items="${videoType}" var="bigType">
                        <li class="m-i" style="margin: 0 3px;">
                            <a href="<%=path%>/video/all?bigType=${bigType.bid}" class="i-link" style="width: 50px">
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
<div id="space-body" style="width: 86%;left: 7%;position: relative">
    <div class="h">
        <div class="wrapper">
            <div class="h-inner" style="background-image: url(<%=path%>/templete/bilibili/images/wrapper.webp);">
                <div class="h-user">
                    <div class="h-avatar">
                        <img id="h-avatar" src="<%=path%>/images/${spaceUser.uimg}">
                    </div>
                    <div class="h-info">
                        <div class="h-basic">
                            <span id="h-name">
                                <c:if test="${spaceUser.uname!=null}">${spaceUser.uname}</c:if>
                                <c:if test="${spaceUser.uname==null}">${spaceUser.uid}</c:if>
                            </span>
                            <c:if test="${spaceUser.usex=='1'}"><span id="h-gender" class="icon gender male"></span></c:if>
                            <c:if test="${spaceUser.usex=='0'}"><span id="h-gender" class="icon gender female"></span></c:if>
                        </div>
                        <div class="h-sign">${spaceUser.usign}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="n">
        <div class="wrapper">
            <div class="n-inner clearfix">
                <div class="n-tab-links">
                    <a class="n-btn n-index n-fans" href="<%=path%>/space/${spaceUser.uid}">
                        <span class="n-icon n-i-icon"></span>
                        <span class="n-text">主页</span>
                    </a>
                    <a class="n-btn n-video" href="<%=path%>/space/${spaceUser.uid}/video">
                        <span class="n-icon n-v-icon"></span>
                        <span class="n-text">视频</span>
                    </a>
                    <a class="n-btn n-favlist n-fav" href="<%=path%>/space/${spaceUser.uid}/favorite">
                        <span class="n-icon n-f-icon"></span>
                        <span class="n-text">收藏夹</span>
                    </a>
                    <a class="n-btn n-bangumi n-subs active" href="<%=path%>/space/${spaceUser.uid}/follow">
                        <span class="n-icon n-b-icon"></span>
                        <span class="n-text">关注</span>
                    </a>
                </div>
            </div>
            <div class="n-cursor"></div>
        </div>
    </div>
    <div class="s-space">
        <div class="wrapper clearfix" id="page-fans">
            <div class="col-full">
                <div class="tabs">
                    <a href="<%=path%>/space/${spaceUser.uid}/follow">关注 </a>
                    <a href="<%=path%>/space/${spaceUser.uid}/fan" class="active">粉丝 </a>
                    <a href="<%=path%>/space/${spaceUser.uid}/tag">标签 </a>
                </div>
                <div id="fans-list" class="section fans">
                    <div class="content">
                        <c:forEach var="item" items="${fanList}">
                            <div class="list-item clearfix">
                                <a target="_blank" class="cover" href="<%=path%>/space/${item.userBO.uid}">
                                    <img src="<%=path%>/images/${item.userBO.uimg}" alt="${item.userBO.uname}">
                                </a>
                                <div class="content">
                                    <a target="_blank" class="title" href="<%=path%>/space/${item.userBO.uid}">
                                    <span class="vip-name-check fans-name">
                                        <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                        <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                    </span>
                                    </a>
                                    <div class="desc">${item.userBO.usign}</div>
                                    <c:if test="${isOwner == true}">
                                        <div class="fans-action" data-follow="${spaceUser.uid}" data-followed="${item.userBO.uid}">
                                            <c:if test="${item.userBO.userFollowCount > 0}">
                                                <span class="fans-action-btn unfollow">已关注</span>
                                            </c:if>
                                            <c:if test="${item.userBO.userFollowCount <= 0}">
                                                <span class="fans-action-btn follow">+ 关注</span>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<!-- 自编 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
<script src="<%=path%>/js/space.js"></script>
</body>
</html>
