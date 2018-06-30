<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通信设备公司CRM首页</title>
<!-- 
 	引入EasyUI资源库
 	JSP作为服务端资源，是可以直接访问WEB-INF下的内容的	
 -->
<%@include file="/WEB-INF/views/common/common.jsp"%>

<script type="text/javascript">
	$(function() {
		$("#systemMenuTree")
				.tree(
						{
							url : "/systemMenu/getMenu.do",
							onClick : function(node) {
								//判断是否有子节点，有的话，就不做事情。没有就创建tabs
								var children = node.children;
								var text = node.text;
								var iconCls = node.iconCls;
								var url = node.url;
								if (children.length == 0 && url) {
									//这个是确定只有子菜单才能进来，父菜单是不能进来的
									var hasTab = $("#mainTabs").tabs("exists", text);
									if (!hasTab) {
										//没有子节点就创建tab
										$("#mainTabs")
												.tabs(
														"add",
														{
															title : text,
															content : ' <iframe  style="width: 100%;height: 100%"  scrolling="auto" frameborder="0" src="'
																	+ url
																	+ '"></iframe>',
															iconCls : iconCls,
															closable : true
														})
									} else {
										//选中
										$("#mainTabs").tabs("select", text);
									}

								}
							}
						})

		$.messager.show({
			title : '温馨提示',
			msg : '<strong>欢迎您！！！</strong',
			showType : 'show',
		});
		$("#mainTabs").tabs({
			fit : true,
			border : false,
			tabWidth : 130
		});
	});
	// 页面加载完毕后、
</script>
<style type="text/css">
.topLogo {
	background-color: #effbf8;
}
</style>
</head>
<body>
	<!-- <iframe src="" frameborder="0" style="height: 100%;width: 100%" ></iframe> -->
	<div class="easyui-layout" fit="true">
		<div class="topLogo" data-options="region:'north'"
			style="height: 68px" border="false">
			<table width="100%">
				<tr>
					<td><img alt="通信设备公司CRM系统" src="/images/logo.png"></td>
					<td align="right" valign="top"><font size=2px;
						" color="#0074a6">当前用户：${sessionScope.user_in_session.realName}！！！&emsp;&emsp;<a
							href="/logout.do" title="退出系统"
							style="text-decoration: none; color: #0074a6">[退出系统]&nbsp;</a></font></td>
				</tr>
			</table>
		</div>
		<div data-options="region:'south'" style="height: 20px;"
			border="false">
			<div align="center">版权归石家庄铁道大学-软件工程-王艺霖所有@2018-2018</div>
		</div>
		<div data-options="region:'west'" title="系统菜单" style="width: 180px;"
			headerCls="border-right-none" bodyCls="border-right-none">
			<ul id="systemMenuTree">
			</ul>
		</div>
		<div data-options="region:'center'">
			<div id="mainTabs">
				<div title="欢迎页">
					<div align="center" style="margin-top: 90px;">
						<font size="20" color="blue" style="font-weight: bold;">欢迎登录通信设备公司CRM系统</font>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>