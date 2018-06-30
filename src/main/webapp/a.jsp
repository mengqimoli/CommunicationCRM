<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
</head>
<body>

	<input validType="hzOrywOrszOrxhx" required="true" name="name"
		class="easyui-validatebox textbox"> 金额
	<input name="sum" class="easyui-numberbox" value="100"
		data-options="min:0,prefix:'￥',precision:2" required="true"></input>
	下拉
	<select name="status" class="easyui-combobox"
		data-options="required:true,editable:false,panelHeight:'auto'"
		style="width: 147px;">
		<option value="1">生效</option>
		<option value="-1">停用</option>
	</select> 文本域
	<input name="intro" class="easyui-textbox"
		style="width: 147px; height: 80px" data-options="multiline:true">
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-blank'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-search'">查找</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-save'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-cut'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-ok'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-no'">添加</a>
	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	<a id="btn" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit'">修改</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-reload'">添加</a>
	<a id="btn" class="easyui-linkbutton"
		data-options="iconCls:'icon-cancel'">删除</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-print'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-help'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-undo'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-redo'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-back'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-sum'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-tip'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-mini-add'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-mini-edit'">添加</a>
	<a id="btn" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-mini-refresh'">添加</a>
</body>
</html>