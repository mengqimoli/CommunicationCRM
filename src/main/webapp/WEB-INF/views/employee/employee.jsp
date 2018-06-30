<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<!-- 引入相关的资源库
	 JSP属于服务端的代码，JSP就是Servlet，所以他的内部是可以直接访问WEB-INF下的资源
  -->
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/model/employee.js"></script>
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
	<!-- 1、数据表格 -->
	<table id="employee-datagrid" class="easyui-datagrid"
		url="/employee/list.do" title="员工管理" fit="true" border="false"
		fitColumns="true" singleSelect="true" rownumbers="true"
		toolbar="#employee-toolbar" pagination="true" pageSize="20"
		pageList="[2,5,10,20,50]">
		<thead>
			<tr>
				<th field="id" width="5" hidden="true">编号</th>
				<th field="username" width="10">用户名</th>
				<th field="password" width="10">密码</th>
				<th field="realName" width="10">真实姓名</th>
				<th field="tel" width="10">电话</th>
				<th field="email" width="15">邮箱</th>
				<th field="inputTime" width="10">入职时间</th>
				<th field="status" width="10" formatter="statusFormatter">状态</th>
				<th field="dept" formatter="objectFormatter" width="10">部门名称</th>
				<th field="roles" formatter="arrayFormatter" width="10">角色</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="employee-toolbar">
		<wyl:checkPermission permissionName="员工保存/修改">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" data-cmd="create">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="员工离职">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" data-cmd="stopUse">离职</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="员工复职">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" data-cmd="startUse">复职</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="员工删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<form id="employee-search-form" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px">
				 录入时间: <input
					name="inputTimeStart" class="easyui-datebox" style="width: 120px">
				到: <input name="inputTimeEnd" class="easyui-datebox"
					style="width: 120px"> 
				状态: <select name="status" class="easyui-combobox" panelHeight="auto"
					style="width: 100px">
					<option value="">----请选择----</option>
					<option value="1">在职</option>
					<option value="-1">离职</option>
				</select> <a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="doSearch">搜索</a><a href="#" data-cmd="openAdvanceSearch"
					class="easyui-linkbutton c4" iconCls="icon-search">高级搜索</a>
			</form>
		</div>
	</div>

	<!-- 2、添加/编辑对话框 -->
	<div id="employee-dlg" class="easyui-dialog"
		style="width: 420px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#employee-dlg-buttons">
		<div class="ftitle">员工信息添加/编辑</div>
		<form id="employee-form" method="post" novalidate>
			<input type="hidden" name="id">
			<input type="hidden" name="password">
			<input type="hidden" name="inputTime">
			<input type="hidden" name="status">
			<table align="center" class="tdright">
				<tr>
					<td>用&nbsp;&nbsp;户&nbsp;名：</td>
					<td><input validType="username" required="true"
						name="username" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td><input validType="chinese" required="true" name="realName"
						class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>用户电话：</td>
					<td><input name="tel" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>用户邮箱：</td>
					<td><input validType="email" required="true" name="email"
						class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>所属部门：</td>
					<td><input name="dept.id" class="easyui-combotree"
						style="width: 147px;"
						data-options="
				        					url:'/department/getDepartmentTree.do'
				        					,method:'get'
				        					,panelHeight:'auto'
				        					">
					</td>
				</tr>
				<tr>
					<td>用户角色：</td>
					<td><input id="roleArr" name="roleArr" class="easyui-combobox"
						data-options="
							url:'/role/getAll.do',
							method:'get',
							valueField:'id',
							textField:'name',
							multiple:true,
							panelHeight:'auto'
							"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加/编辑对话框按钮 -->
	<div id="employee-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancel">取消</a>
	</div>
	<!-- 高级查询对话框 -->
	<div id="employeeAdvanceSearchDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-Advance-buttons" novalidate>
		<div class="ftitle">员工信息查询</div>
		<form id="employeeAdvanceSearchForm" method="post">
			<table align="center">
				<tr>
					<td>用&nbsp;&nbsp;户&nbsp;名：</td>
					<td><input name="username" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td><input name="realName" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>电&emsp;&emsp;话：</td>
					<td><input name="tel" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>邮&emsp;&emsp;箱：</td>
					<td><input name="email" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>所属部门：</td>
					<td><input name="deptId" class="easyui-combotree"
						data-options="
				        					url:'/department/getDepartmentTree.do'
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
			iconCls="icon-reload" style="width: 90px"
			data-cmd="clearAdvanceSearchForm">清空</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px"
			data-cmd="cancelAdvanceSearchDlg">取消</a>
	</div>
</body>
</html>