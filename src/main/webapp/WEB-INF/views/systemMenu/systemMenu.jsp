<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志管理</title>
<!-- 引入相关的资源库
	 JSP属于服务端的代码，JSP就是Servlet，所以他的内部是可以直接访问WEB-INF下的资源
  -->
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/model/systemMenu.js"></script>
<!-- 
	在页面加载完毕后
	1、声明需要缓存的对象
	2、做缓存
	3、初始化组件
	4、创建一个命令对象，打包方法
	5、对页面所有按钮，进行一次统一监听
 -->
</head>
<body>
<body>
	<!-- 1、数据表格 -->
	<table id="systemMenuDatagrid" class="easyui-datagrid"
		url="/systemMenu/list.do" title="日志管理" fit="true" border="false"
		fitColumns="true" singleSelect="true" rownumbers="true"
		toolbar="#systemMenu-toolbar" pagination="true" pageSize="20"
		pageList="[2,5,10,20,50]">
		<thead>
			<tr>
				<th field="id" hidden=true width="10"></th>
				<th field="name" width="10">菜单名称</th>
				<th field="sn" width="10">菜单编号</th>
				<th field="url" width="20">菜单URL</th>
				<th field="intro" width="30">描述</th>
				<th field="parent" width="10" formatter="objectFormatter">父菜单</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="systemMenu-toolbar">
		<wyl:checkPermission permissionName="系统菜单保存/修改">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" data-cmd="create">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="系统菜单删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<!-- 简单查询表单 -->
			<form id="systemMenuSearchFrom" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px"> <a href="#" data-cmd="doSearch"
					class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</form>
		</div>
	</div>
	<!-- 添加/编辑对话框 -->
	<div id="systemMenuAddEditDlg" class="easyui-dialog"
		style="width: 420px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#systemMenu-dlg-buttons">
		<div class="ftitle">菜单信息添加/编辑</div>
		<!-- 添加/编辑表单 -->
		<form id="systemMenuAddEditForm" method="post" novalidate>
			<input type="hidden" name="id">
			<table align="center">
				<tr>
					<td>菜单名称:</td>
					<td><input validType="hzOrywOrszOrxhx" required="true"
						name="name" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>菜单编号:</td>
					<td><input validType="englishornumorxhxOrxg" required="true"
						name="sn" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>菜单路径:</td>
					<td><input name="url"
						class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>菜单简介:</td>
					<td><input name="intro" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>菜单图标:</td>
					<td><input name="icon" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>父菜单:</td>
					<td><input name="parent.id" class="easyui-combotree"
						data-options="
				        					url:'/systemMenu/getSystemMenuTree.do'
				        					,method:'get'
				        					,panelHeight:'auto'
				        					">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加/编辑对话框按钮 -->
	<div id="systemMenu-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancel">取消</a>
	</div>
</body>
</html>