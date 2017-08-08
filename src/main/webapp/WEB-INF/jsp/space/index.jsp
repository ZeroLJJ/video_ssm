<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/4/1
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/app_dd333c69.css">
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
                    <a class="n-btn n-index active" href="<%=path%>/space/${spaceUser.uid}">
                        <span class="n-icon n-i-icon"></span>
                        <span class="n-text">主页</span>
                    </a>
                    <a class="n-btn n-video" href="<%=path%>/space/${spaceUser.uid}/video">
                        <span class="n-icon n-v-icon"></span>
                        <span class="n-text">视频</span>
                    </a>
                    <a class="n-btn n-fav" href="<%=path%>/space/${spaceUser.uid}/favorite">
                        <span class="n-icon n-f-icon"></span>
                        <span class="n-text">收藏夹</span>
                    </a>
                    <a class="n-btn n-follow" href="<%=path%>/space/${spaceUser.uid}/follow">
                        <span class="n-icon n-b-icon"></span>
                        <span class="n-text">关注</span>
                    </a>
                </div>
            </div>
            <div class="n-cursor"></div>
        </div>
    </div>
    <div class="s-space">
        <div class="wrapper clearfix" id="page-index">
            <div class="col-1">
                <div class="section video full-rows">
                    <h3 class="section-title">
                        <a class="t" href="<%=path%>/space/${spaceUser.uid}/video"><c:if test="${isOwner == true}">我</c:if><c:if test="${isOwner != true}">TA</c:if>的稿件</a>
                        <c:if test="${fn:length(videoList) == 0}">
                            <div class="sec-empty-hint sec-empty-hint-host">
                                还没有上传过视频哦~
                                <c:if test="${isOwner == true}"><a href="<%=path%>/post" target="_blank">立即上传</a></c:if>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(videoList) != 0}">
                            <a class="more" href="<%=path%>/space/${spaceUser.uid}/video">更多</a>
                        </c:if>
                    </h3>
                    <div class="content clearfix">
                        <c:forEach var="video" items="${videoList}">
                        <div class="small-item fakeDanmu-item">
                            <a target="_blank" class="cover" href="<%=path%>/video/show/${video.vid}">
                                <img src="<%=path%>/images/${video.vimg}">
                                <span class="length">${video.vduration}</span>
                                <span class="new-icon"></span>
                            </a>
                            <a target="_blank" class="title" href="<%=path%>/video/show/${video.vid}" title="<%=path%>/video/show/${video.vname}">
                                ${video.vname}
                            </a>
                            <div class="meta">
                                <span class="play">
                                    <span class="icon"></span>
                                    <c:if test="${video.vplayTimes >= 10000}">
                                        <fmt:formatNumber type="number" value="${video.vplayTimes/10000}" pattern="0.0" maxFractionDigits="1"></fmt:formatNumber> 万
                                    </c:if>
                                    <c:if test="${video.vplayTimes < 10000}">
                                        ${video.vplayTimes}
                                    </c:if>
                                </span>
                                <span class="time">
                                    <i class="icon"></i><fmt:formatDate value="${video.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                </span>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="section fav">
                    <h3 class="section-title">
                        <a class="t" href="<%=path%>/space/${spaceUser.uid}/favorite"><c:if test="${isOwner == true}">我</c:if><c:if test="${isOwner != true}">TA</c:if>的收藏夹</a>
                        <c:if test="${fn:length(favoriteList) == 0}">
                            <div class="sec-empty-hint sec-empty-hint-host">
                                还没有新增过收藏夹哦~
                            </div>
                        </c:if>
                        <c:if test="${fn:length(favoriteList) != 0}">
                            <a class="more" href="<%=path%>/space/${spaceUser.uid}/favorite">更多</a>
                        </c:if>
                    </h3>
                    <div class="content clearfix">
                        <c:forEach var="fav" items="${favoriteList}">
                            <c:if test="${isOwner == true}">
                                <div class="fav-item">
                                    <a class="fav-covers" href="<%=path%>/space/${spaceUser.uid}/favorite_item?fid=${fav.fid}">
                                        <c:forEach var="cover" items="${fav.covorList}" varStatus="status">
                                        <div class="fav-cover fav-cover-${status.index}" style="background-image: url(<%=path%>/images/${cover});"></div>
                                        </c:forEach>
                                    </a>
                                    <span class="fav-count">${fav.fhave}</span>
                                    <div class="m">
                                        <span class="state">
                                            <c:if test="${fav.fprivacy == '1'}">公开</c:if>
                                            <c:if test="${fav.fprivacy != '1'}">私密</c:if>
                                        </span>
                                        <a class="name" title="${fav.fname}" href="<%=path%>/space/${spaceUser.uid}/favorite_item?fid=${fav.fid}">${fav.fname}</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${isOwner != true && fav.fprivacy == '1'}">
                                <div class="fav-item">
                                    <a class="fav-covers" href="<%=path%>/space/${spaceUser.uid}/favorite_item?fid=${fav.fid}">
                                        <c:forEach var="cover" items="${fav.covorList}" varStatus="status">
                                            <div class="fav-cover fav-cover-${status.index}" style="background-image: url(<%=path%>/images/${cover});"></div>
                                        </c:forEach>
                                    </a>
                                    <span class="fav-count">${fav.fhave}</span>
                                    <div class="m">
                                        <a class="name" title="${fav.fname}" href="<%=path%>/space/${spaceUser.uid}/favorite_item?fid=${fav.fid}">${fav.fname}</a>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="section coin">
                    <h3 class="section-title" title="最近投过硬币的视频">
                        最近投币的视频
                        <c:if test="${fn:length(scoreList) == 0}">
                            <div class="sec-empty-hint sec-empty-hint-host">
                                还没投过硬币视频哦~
                            </div>
                        </c:if>
                    </h3>
                    <div class="content clearfix">
                        <c:forEach var="video" items="${scoreList}">
                        <div class="small-item fakeDanmu-item" style="width: 100px;">
                            <a target="_blank" class="cover" href="<%=path%>/video/show/${video.vid}">
                                <img src="<%=path%>/images/${video.vimg}" alt="${video.vname}">
                            </a>
                            <a target="_blank" class="title" href="<%=path%>/video/show/${video.vid}" title="${video.vname}">${video.vname}</a>
                            <div class="meta">
                                <span class="play">
                                    <span class="icon"></span>
                                      <c:if test="${video.vplayTimes >= 10000}">
                                          <fmt:formatNumber type="number" value="${video.vplayTimes/10000}" pattern="0.0" maxFractionDigits="1"></fmt:formatNumber> 万
                                      </c:if>
                                      <c:if test="${video.vplayTimes < 10000}">
                                          ${video.vplayTimes}
                                      </c:if>
                                </span>
                                <span class="comments">
                                    <span class="icon"></span>${video.vcomment}
                                </span>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-2">
                <div class="section i-m">
                    <div class="i-m-r1">
                        <span class="icon i-m-icon"></span>
                        <a href="http://member.bilibili.com/v/?from=bilibili#!/home" class="i-m-title" target="_blank">
                            创作中心<span class="icon i-m-ra"></span>
                        </a>
                    </div>
                    <div class="i-m-r2 clearfix">
                        <a href="http://member.bilibili.com/v/video/submit.html" class="i-m-btn i-m-upload" target="_blank">
                            <span class="i-m-u-icon icon"></span>
                            <span class="i-m-text">视频投稿</span>
                        </a>
                        <a href="http://member.bilibili.com/v/#!/article" class="i-m-btn i-m-v" target="_blank">
                            <span class="i-m-v-icon icon"></span>
                            <span class="i-m-text">投稿管理</span>
                        </a>
                    </div>
                </div>
                <div class="section i-ann">
                    <h3 class="section-title">公告</h3>
                    <div class="content">
                        <div class="sp-textarea" _v-04ca2ca5="" id="i-ann-content">
                            <textarea _v-04ca2ca5="" placeholder="编辑我的空间公告" maxlength="150">
                                hello,world
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="section user">
                    <div class="info">
                        <div class="content">
                            <div class="meta">
                                <div class="row">
                                    <div class="item uid">
                                        <span class="icon"></span>
                                        <span class="text">${spaceUser.uid}</span>
                                    </div>
                                    <div class="item regtime">
                                        <span class="icon"></span>
                                        <span class="text">注册于 <fmt:formatDate value="${spaceUser.uregdate}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item birthday">
                                        <span class="icon"></span>
                                        <span class="text">
                                            <c:if test="${spaceUser.ubirthday != null}">
                                                <fmt:formatDate value="${spaceUser.ubirthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </c:if>
                                            <c:if test="${spaceUser.ubirthday == null}">
                                                未填写
                                            </c:if>
                                        </span>
                                    </div>
                                    <div class="item geo">
                                        <span class="icon"></span>
                                        <span class="text">
                                            <c:if test="${spaceUser.uemail != null}">${spaceUser.uemail}</c:if>
                                            <c:if test="${spaceUser.uemail == null}">未填写</c:if>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
