<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/4/14
  Time: 19:34
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
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/favorite.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/app_b49a4762.css">
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
                    <a class="n-btn n-favlist n-fav active" href="<%=path%>/space/${spaceUser.uid}/favorite">
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
        <div class="wrapper" id="page-favlist">
            <div class="col-full">
                <div class="breadcrumb">
                    <p class="item cur"><c:if test="${isOwner == true}">我</c:if><c:if test="${isOwner != true}">TA</c:if>的收藏夹</p>
                </div>
                <div class="section fav">
                    <div class="content clearfix">
                        <c:if test="${fn:length(favoriteList) == 0}">
                            <div class="no-result" style="width: 1070px;">
                                <p class="text">没有相关数据</p>
                                <div></div>
                            </div>
                        </c:if>
                        <c:forEach var="fav" items="${favoriteList}">
                            <c:if test="${isOwner == true}">
                                <div class="fav-item" data-fav="${fav.fid}">
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
                                    <div class="fav-action">
                                        <span class="delete icon">删除</span>
                                        <span class="edit icon">编辑</span>
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
                        <c:if test="${isOwner == true}">
                            <div class="fav-item fav-create list-create" title="创建新收藏夹">
                                <div class="create-covers-meta">
                                    <div class="icon"></div>
                                    <div class="text">新建收藏夹</div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="modal-container" style="display: none;">
                <div class="modal-mask"></div>
                <div class="modal-wrapper" style="width: 300px;">
                    <div class="modal" style="display: block;">
                        <div class="modal-header">
                            <i class="modal-header-close ion-close-round"></i>
                            <span class="modal-title"> 创建新收藏夹 </span>
                        </div>
                        <div class="modal-body">
                            <div class="sp-input">
                                <input type="text" value="" placeholder="" maxlength="15">
                                <div class="letter-count" style="display: none;">0/15</div>
                            </div>
                            <div class="switcher-container switcher-on">
                                <div class="switcher">
                                    <i class="cursor"></i>
                                </div>
                                <div class="label">
                                    <span>公开该收藏夹</span>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn primary">
                                <span class="btn-content"> 确定 </span>
                            </a>
                            <a class="btn default">
                                <span class="btn-content"> 取消 </span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="space-loading" style="display: none;">
        <div class="space-loading-tv"></div>
    </div>
</div>


</body>
<script>
    var uid = '${user.uid}';
</script>

<!-- JS -->
<script src="<%=path%>/js/jquery-3.1.1.js"></script>
<!-- 自编 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/js/space.js"></script>
<script src="<%=path%>/js/favorite.js"></script>


</html>
