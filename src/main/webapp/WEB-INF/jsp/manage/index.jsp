<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/css/style.css" />
<title>后台管理</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:;">后台管理</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li class="dropDown dropDown_hover">
						<a href="#" class="dropDown_A">${admin.uname} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="<%=path%>/adminExit">退出</a></li>
                        </ul>
                    </li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-video">
			<dt><i class="Hui-iconfont">&#xe603;</i> 视频管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>/manage/video-list" data-title="视频列表" href="javascript:void(0)">视频列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-tag">
            <dt><i class="Hui-iconfont">&#xe69e;</i> 标签管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="<%=path%>/manage/tag-list" data-title="标签列表" href="javascript:void(0)">标签列表</a></li>
                </ul>
            </dd>
        </dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>/manage/user-list" data-title="用户列表" href="javascript:;">用户列表</a></li>
                </ul>
            </dd>
        </dl>

		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>/manage/admin-list" data-title="全员列表" href="javascript:void(0)">全员列表</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="欢迎界面" data-href="<%=path%>/manage/welcome">欢迎界面</span>
					<em></em>
                </li>
            </ul>
        </div>
		<div class="Hui-tabNav-more btn-group">
            <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;">
                <i class="Hui-iconfont">&#xe6d4;</i>
            </a>
            <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;">
                <i class="Hui-iconfont">&#xe6d7;</i>
            </a>
        </div>
    </div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="<%=path%>/manage/welcome"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

</script> 

</body>
</html>