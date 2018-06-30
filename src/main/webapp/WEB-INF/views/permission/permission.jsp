<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/model/permission.js"></script>
</head>
<body>
	<!-- 1、数据表格 -->
	<table id="permissionDatagrid" class="easyui-datagrid"
		url="/permission/list.do" title="权限管理" fit="true" border="false"
		fitColumns="true" singleSelect="true" rownumbers="true"
		toolbar="#permission-toolbar" pagination="true" pageSize="20"
		pageList="[2,5,10,20,50]">
		<thead>
			<tr>
				<th field="id" hidden=true width="10"></th>
				<th field="name" width="10">权限名称</th>
				<th field="sn" width="10">权限编号</th>
				<th field="resource" width="20">权限资源</th>
				<th field="status" width="10" formatter="statusFormatter">权限状态</th>
				<th field="menu" width="10" formatter="objectFormatter">权限菜单</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="permission-toolbar">
		<wyl:checkPermission permissionName="权限保存/修改">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" data-cmd="create">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="权限删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<!-- 简单查询表单 -->
			<form id="permissionSearchFrom" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px"> 状态: <select name="status"
					class="easyui-combobox" panelHeight="auto" style="width: 100px">
					<option value="">----请选择----</option>
					<option value="1">正常</option>
					<option value="-1">停用</option>
				</select> <a href="#" data-cmd="doSearch" class="easyui-linkbutton"
					iconCls="icon-search">搜索</a> <a href="#"
					data-cmd="openAdvanceSearch" class="easyui-linkbutton c4"
					iconCls="icon-search">高级搜索</a>
			</form>
		</div>
	</div>
	<!-- 添加/编辑对话框 -->
	<div id="permissionDlg" class="easyui-dialog"
		style="width: 420px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#permission-dlg-buttons">
		<div class="ftitle">权限信息添加/编辑</div>
		<!-- 添加/编辑表单 -->
		<form id="permissionForm" method="post" novalidate>
			<input type="hidden" name="id">
			<table align="center">
				<tr>
					<td>权限名称:</td>
					<td><input validType="hzOrywOrszOrxhx" required="true"
						name="name" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>权限编号:</td>
					<td><input validType="englishornumorxhx" name="sn"
						class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>权限资源:</td>
					<td><input validType="englishornumorxhx" required="true"
						name="resource" class="easyui-validatebox textbox"></td>
				</tr>
				<tr>
					<td>权限状态:</td>
					<td><input name="status" id="defaultRadio" type="radio"
						value="1" class="easyui-validatebox" label="First Name:">正常
						<input name="status" value="-1" type="radio"
						class="easyui-validatebox" label="First Name:">停用</td>
				</tr>
				<tr>
					<td>权限菜单:</td>
					<td><select id="AddEditCombogrid" class="easyui-combogrid"
						name="menu.id" style="width: 145px;"
						data-options="    
					            panelWidth:450,    
					            idField:'id',
					            fitColumns:true,    
					            textField:'name', 
					            url:'/systemMenu/list.do',  
					          	pagination:true,
					            columns:[[    
					                {field:'name',title:'菜单名称',width:100},    
					                {field:'sn',title:'菜单编号',width:120},    
					                {field:'intro',title:'菜单描述',width:100},    
					            ]] ">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加/编辑对话框按钮 -->
	<div id="permission-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancel">取消</a>
	</div>
	<!-- 高级查询对话框 -->
	<div id="permissionAdvanceDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-Advance-buttons" novalidate>
		<div class="ftitle">权限信息查询</div>
		<form id="permissionAdvanceForm" method="post">
			<table align="center">
				<tr>
					<td>权限名称：</td>
					<td><input name="name" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>权限编号：</td>
					<td><input name="sn" class="easyui-textbox"></td>
				</tr>

				<tr>
					<td>权限资源：</td>
					<td><input name="resource" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>权限菜单：</td>
					<td><input name="menuId" class="easyui-combotree"
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
	<!-- 高级查询对话框按钮 -->
	<div id="dlg-Advance-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c5"
			iconCls="icon-search" style="width: 90px" data-cmd="doAdvanceSearch">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton c1"
			iconCls="icon-reload" style="width: 90px" data-cmd="clearSearchForm">清空</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancelSearchDlg">取消</a>
	</div>
</body>
</html>