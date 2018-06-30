<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/resource.js"></script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false">
		<div
			data-options="{region:'west',width:300,bodyCls:'border-right',split:true}">
			<ul id="resourceTree">
			</ul>
		</div>
		<div data-options="{region:'center',border:false}">
			<!-- 1. 表格
		url="resource_list.action"-->
			<table id="resourceGrid" class="easyui-datagrid"
				url="/resource/list.do" fit="true" border="false" fitColumns="true"
				striped="true" toolbar="#resourceTb" rownumbers="true"
				singleSelect="true" pagination="true" pageList="[2,5,10,20,50]" pageSize="20">
				<thead>
					<tr>
						<th data-options="{field:'id',width:1,hidden:'true'}">资源编号</th>
						<th data-options="{field:'name',width:10}">资源名称</th>
						<th data-options="{field:'url',width:15}">资源地址</th>
						<th data-options="{field:'controller',width:15}">模块名称</th>
					</tr>
				</thead>
			</table>
			<!-- 2. 表格工具栏-->
			<div id="resourceTb">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" data-cmd="create">添加</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cut" plain="true" data-cmd="del">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true" data-cmd="importMoudleResource">导入资源</a><a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-redo" plain="true" data-cmd="importPermission">加入权限</a>
			</div>
			<!-- 3. 添加/编辑对话框-->
			<div id="resourceDlg" class="easyui-dialog"
				style="width: 400px; height: 180px;" title="添加/编辑资源信息" closed="true"
				modal="true" buttons="#resourceDlgBtn">
				<form id="resourceForm" method="post">
					<input name="id" type="hidden">
					<table align="center" style="margin-top: 15px;">
						<tr>
							<td>资源名称:</td>
							<td><input required="true" width="300px;" name="name"
								class="easyui-validatebox textbox"></td>
						</tr>
						<tr>
							<td>资源地址:</td>
							<td><input validType="englishornumorxhx" required="true"
								width="300px;" name="url" class="easyui-validatebox textbox"></td>
						</tr>
						<tr>
							<td>资源模块:</td>
							<td><input validType="englishornumorxhx" required="true"
								width="300px;" name="controller"
								class="easyui-validatebox textbox"></td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 4. 对话框按钮-->
			<div id="resourceDlgBtn">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" data-cmd="save">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" data-cmd="cancel">取消</a>
			</div>
		</div>
	</div>
</body>
</html>