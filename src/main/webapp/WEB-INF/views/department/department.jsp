<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<!-- 引入相关的资源库
	 JSP属于服务端的代码，JSP就是Servlet，所以他的内部是可以直接访问WEB-INF下的资源
  -->
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/model/department.js"></script>
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
	<table id="department-datagrid" class="easyui-datagrid"
		url="/department/list.do" title="部门管理" fit="true" border="false"
		fitColumns="true" singleSelect="true" rownumbers="true"
		toolbar="#department-toolbar" pagination="true" pageSize="20"
		pageList="[2,5,10,20,50]">
		<thead>
			<tr>
				<th field="sn" width="10">部门编号</th>
				<th field="name" width="10">部门名称</th>
				<th field="manager" width="10" formatter="objectFormatter">部门经理</th>
				<th field="parent" width="10" formatter="objectFormatter">上级部门</th>
				<th field="status" width="2" formatter="statusFormatter">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="department-toolbar">
		<wyl:checkPermission permissionName="部门保存/修改">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" data-cmd="create">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="部门停用">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" data-cmd="stopUse">停用</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="部门启用">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" data-cmd="startUse">启用</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="部门删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<!-- 简单查询表单 -->
			<form id="deptSimpleSearchForm" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px;"> 状态: <select name="status"
					class="easyui-combobox" panelHeight="auto" style="width: 100px">
					<option value="">----请选择----</option>
					<option value="1">正常</option>
					<option value="-1">停用</option>
				</select> <a href="#" data-cmd="doSearch" class="easyui-linkbutton"
					iconCls="icon-search">搜索</a> <a href="javascript:void(0)"
					class="easyui-linkbutton c4" iconCls="icon-search"
					data-cmd="openSearchDlg">高级查询</a>
			</form>
		</div>
	</div>

	<!-- 2、添加/编辑对话框 -->
	<div id="department-dlg" class="easyui-dialog"
		style="width: 400px; height: 240px; padding: 10px 20px" modal="true"
		closed="true" buttons="#department-dlg-buttons">
		<div class="ftitle">部门信息添加/编辑</div>
		<form id="department-form" method="post" novalidate>
			<input type="hidden" name="id">
			<table align="center">
				<tr>
					<td>部门编号：</td>
					<td><input validType="englishornumorxhx" required="true"
						name="sn" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>部门名称：</td>
					<td><input validType="hzOrywOrszOrxhx" required="true"
						name="name" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>部门经理：</td>
					<td><select name="manager.id" id="department-cg"
						class="easyui-combogrid" style="width: 150px;"
						data-options="    
				            url:'/employee/list.do',
				               
				            panelWidth:350,    
				            idField:'id',
				            textField:'realName',
				            fitColumns:true,    
				            pagination : true,  
				               
				            fitColumns: true,
				            columns:[[    
				                {field:'realName',title:'姓名',width:8},    
				                {field:'tel',title:'电话',width:10},    
				                {field:'email',title:'邮箱',width:15}
				            ]]
				        "></select>
					</td>
				</tr>
				<tr>
					<td>上级部门：</td>
					<td><input name="parent.id" class="easyui-combotree"
						style="width: 150px;"
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
	<!-- 添加/编辑对话框按钮 -->
	<div id="department-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancel">取消</a>
	</div>
	<!-- 3、查询对话框 -->
	<div id="department-search-dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#department-search-dlg-buttons">
		<div class="ftitle">部门信息查询</div>
		<form id="department-search-form" method="post" novalidate>
			<table align="center">
				<tr>
					<td>部门名称：</td>
					<td><input name="name" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>部门编号：</td>
					<td><input name="sn" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>部门经理：</td>
					<td><input name="managerName" class="easyui-textbox">
					</td>
				</tr>
				<tr>
					<td>上级部门：</td>
					<td>
						<!-- 查询对象和javabean都不完全一样的,怎么传就怎么得 --> <input name="parentId"
						class="easyui-combotree" style="width: 150px;"
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
	<!-- 查询对话框按钮 -->
	<div id="department-search-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c5"
			iconCls="icon-search" style="width: 90px" data-cmd="doAdvanceSearch">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton c1"
			iconCls="icon-reload" style="width: 90px" data-cmd="clearSearchForm">清空</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancelSearchDlg">取消</a>
	</div>
</body>

</html>