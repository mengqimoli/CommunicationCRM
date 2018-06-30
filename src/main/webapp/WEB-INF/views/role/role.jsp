<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<!-- 
	统一步骤：
	* 引入通用资源"WebContent/WEB-INF/views/common.jsp"
	-------在页面加载完毕后----------
	1、声明页面中，哪些组件需要获取后，使用的
	2、获取需要使用的组件，并缓存
	3、初始化
	4、创建一个命令对象，来管理页面中，需要调用的方法
	5、对页面中所有按钮，统一添加事件（如何区分不同按钮的逻辑？？）
 -->
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/role.js"></script>
</head>
<body>
	<!-- 1. 表格-->
	<table id="roleGrid" pageList="[2,5,10,20,50]" pageSize="20">
		<thead>
			<tr>
				<th field="id" hidden=true width="10"></th>
				<th field="name" width="10">角色名称</th>
				<th field="sn" width="10">角色编号</th>
				<th field="permissions" width="40" formatter="arrayFormatter">角色权限</th>
				<th field="intro" width="30">角色简介</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="department-toolbar">
		<wyl:checkPermission permissionName="角色保存/修改">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" data-cmd="create">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="角色删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<!-- 简单查询表单 -->
			<form id="roleSearchForm" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px"> <a href="#" data-cmd="doSearch"
					class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</form>
		</div>
	</div>
	<!-- 2. 对话框-->
	<div id="roleDlg">
		<font style="font-weight: bold;"></font>
		<form id="roleForm" method="post">
			<input name="id" type="hidden">
			<table align="center" style="margin-top: 10px;">
				<tr>
					<td height="20" colspan="2">&nbsp;角色编号：<input id="sn"
						name="sn">&emsp;&emsp;角色名称：<input id="name" name="name"></td>
				</tr>
				<tr>
					<td height="20" colspan="2">&nbsp;角色简介：<input id="intro"
						name="intro" title="${intro}" style="width: 600px;"> <select
						id="permissionModelType" name="permissionModelType"
						style="width: 140px;" class="easyui-combobox"
						data-options="panelHeight:'200'">
							<option value="PotentialCustomerController">潜在客户管理</option>
							<option value="CustomerDevPlanController">客户开发计划管理</option>
							<option value="CustomerController">客户信息管理</option>
							<option value="CustomerTraceHistoryController">客户跟进历史管理</option>
							<option value="CustomerTransferController">客户移交管理</option>
							<option value="OrderController">订单管理</option>
							<option value="OrderItemController">订单明细管理</option>
							<option value="ContractController">合同管理</option>
							<option value="ContractItemController">合同明细管理</option>
							<option value="GuaranteeController">保修单管理</option>
							<option value="GuaranteeItemController">保修单明细管理</option>
							<option value="DepartmentController">部门管理</option>
							<option value="EmployeeController">员工管理</option>
							<option value="RoleController">角色管理</option>
							<option value="PermissionController">权限管理</option>
							<option value="SystemMenuControlle">系统菜单管理</option>
							<option value="SystemLogController">系统日志管理</option>
							<option value="SystemDictionaryItemController">数据字典明细管理</option>
							<option value="SystemDictionaryTypeController">数据字典管理</option>
							<option value="PieController">数据统计</option>
					</select>
					</td>
				</tr>
				<tr>
					<td height="15">
						<!-- 已选权限表格 -->
						<table id="selectedPermissionGrid"></table>
					</td>
					<td height="15">
						<!-- 所有权限表格 -->
						<table id="allPermissionGrid"></table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>