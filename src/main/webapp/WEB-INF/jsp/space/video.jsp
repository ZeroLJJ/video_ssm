<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/4/25
  Time: 17:04
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
    <title>个人空间</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/favorite.css">
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
                    <a class="n-btn n-video active" href="<%=path%>/space/${spaceUser.uid}/video">
                        <span class="n-icon n-v-icon"></span>
                        <span class="n-text">视频</span>
                    </a>
                    <a class="n-btn n-favlist n-fav" href="<%=path%>/space/${spaceUser.uid}/favorite">
                        <span class="n-icon n-f-icon"></span>
                        <span class="n-text">收藏夹</span>
                    </a>
                    <a class="n-btn n-bangumi n-subs" href="<%=path%>/space/${spaceUser.uid}/follow">
                        <span class="n-icon n-b-icon"></span>
                        <span class="n-text">关注</span>
                    </a>
                </div>
            </div>
            <div class="n-cursor"></div>
        </div>
    </div>
    <div class="s-space">
        <div class="wrapper">
            <div class="col-full" id="page-fav">
                <div class="row page-head clearfix">
                    <div class="breadcrumb">
                        <p class="item cur"><c:if test="${isOwner == true}">我</c:if><c:if test="${isOwner != true}">TA</c:if>的稿件</p>
                    </div>
                </div>
                <div id="submit-video" class="section">
                    <div id="video-list-style" class="cube">
                        <div id="submit-video-list" class="fav section">
                            <div class="content clearfix selectable-list">
                                <c:forEach var="item" items="${postPage.list}">
                                    <div class="small-item" data-id="${item.vid}">
                                        <a target="_blank" class="cover" href="<%=path%>/video/show/${item.vid}">
                                            <img src="<%=path%>/images/${item.vimg}" alt="${item.vname}">
                                            <span class="length">${item.vduration}</span>
                                            <span class="new-icon"></span>
                                        </a>
                                        <a target="_blank" class="title" href="<%=path%>/video/show/${item.vid}" title="${item.vname}">
                                            ${item.vname}
                                        </a>
                                        <div class="meta">
                                            <span class="play">
                                                <span class="icon"></span>
                                                <c:if test="${item.vplayTimes >= 10000}">
                                                    <fmt:formatNumber type="number" value="${item.vplayTimes/10000}" pattern="0.0" maxFractionDigits="1"></fmt:formatNumber> 万
                                                </c:if>
                                                <c:if test="${item.vplayTimes < 10000}">
                                                    ${item.vplayTimes}
                                                </c:if>
                                            </span>
                                            <span class="time">
                                                <i class="icon"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test="${fn:length(postPage.list) == 0}">
                                    <div class="no-result">
                                        <p class="text">没有相关数据</p>
                                        <div></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <c:if test="${postPage.totalPage > 1}">
                        <ul class="sp-pager">
                            <c:if test="${postPage.hasPreviousPage == true}">
                                <a class="sp-pager-prev" href="<%=path%>/space/Zero/video?pageNo=${postPage.currentPage - 1}">上一页</a>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${postPage.totalPage}">
                                <c:if test="${postPage.currentPage == i}">
                                    <a class="sp-pager-item sp-pager-item-active" href="javascript:;">${i}</a>
                                </c:if>
                                <c:if test="${postPage.currentPage != i}">
                                    <a class="sp-pager-item" href="<%=path%>/space/Zero/video?pageNo=${i}">${i}</a>
                                </c:if>
                            </c:forEach>
                            <c:if test="${postPage.hasNextPage == true}">
                                <a class="sp-pager-next" href="<%=path%>/space/Zero/video?pageNo=${postPage.currentPage + 1}">下一页</a>
                            </c:if>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div class="space-loading" style="display: none;">
        <div class="space-loading-tv"></div>
    </div>
</div>

</body>

<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<!-- 自编 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
<script src="<%=path%>/js/space.js"></script>

</html>
