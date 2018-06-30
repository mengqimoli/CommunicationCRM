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
<script type="text/javascript" src="/js/model/systemLog.js"></script>
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
	<table id="systemLog-datagrid" class="easyui-datagrid"
		url="/systemLog/list.do" title="日志管理" fit="true" border="false"
		fitColumns="true" singleSelect="true" rownumbers="true"
		toolbar="#systemLog-toolbar" pagination="true" pageSize="20"
		pageList="[2,5,10,20,50]">
		<thead>
			<tr>
				<th field="id" width="10" hidden="true">日志编号</th>
				<th field="opUser" width="20" formatter="objectFormatter">操作用户</th>
				<th field="opTime" width="20">操作时间</th>
				<th field="opIp" width="20">登录ip</th>
				<th field="function" width="50">操作内容</th>
			</tr>
		</thead>
	</table>
	<!-- 数据表格的工具栏 -->
	<div id="systemLog-toolbar">
		<wyl:checkPermission permissionName="系统日志删除">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cut" plain="true" data-cmd="del">删除</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="系统日志删除全部">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" data-cmd="delALL">删除全部</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		<div>
			<form id="systemLog-search-form" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px"> 录入时间: <input name="inputTimeStart"
					class="easyui-datebox" style="width: 120px"> 到: <input
					name="inputTimeEnd" class="easyui-datebox" style="width: 120px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="doSearch">搜索</a><a href="#" class="easyui-linkbutton c4"
					iconCls="icon-search" data-cmd="openAdvanceSearch">高级搜索</a>
			</form>
		</div>
	</div>
	<!-- 3、查询对话框 -->
	<div id="systemMenuAdvanceSearchDlg" class="easyui-dialog"
		style="width: 400px; height: 230px; padding: 10px 20px" modal="true"
		closed="true" buttons="#systemMenuAdvanceSearchBtn" novalidate>
		<div class="ftitle">日志信息查询</div>
		<form id="systemMenuAdvanceSearchForm" method="post">
			<table align="center">
				<tr>
					<td>操作用户：</td>
					<td><select name="username" class="easyui-textbox"
						style="width: 147px;"></select></td>
				</tr>
				<tr>
					<td align="right">访问ip：</td>
					<td><select name="opIp" class="easyui-textbox"
						style="width: 147px;"></select></td>
				</tr>
				<tr>
					<td>操作内容：</td>
					<td><input name="function" class="easyui-textbox"
						style="width: 147px;"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 查询对话框按钮 -->
	<div id="systemMenuAdvanceSearchBtn">
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