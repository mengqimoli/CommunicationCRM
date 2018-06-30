<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定金订单管理</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript">
	//解决页面两个grid的布局问题
	$(function() {
		function autoResizeWin() {
			// 1. 获取页面宽度
			var screenWidth = document.documentElement.clientWidth;
			// 2. 获取页面宽度的一半
			var halfWidth = screenWidth / 5;
			// 3. 把west组件的宽度,设置为屏幕的一半宽度
			$("#westPanel").panel("resize", {
				width : 3 * halfWidth
			});
			$("#center").panel("resize", {
				width : 2 * halfWidth
			});
			// 4. 通知layout重新布局(center部门自动适应)
			$("#layout").layout();
		}
		// 在窗口改变时,重新布局
		$(window).resize(autoResizeWin);
		autoResizeWin();
	});
	$(function() {
		var $orderDialog = $('#orderDialog');
		var $orderForm = $('#orderForm');
		var $orderDatagrid = $('#orderDatagrid');

		var orderItemDatagrid = $("#orderItemDatagrid");
		var orderItemDatagridDlg = $("#orderItemDatagridDlg");
		var orderItemDatagridDlgForm = $("#orderItemDatagridDlgForm");
		var orderCombo = $("#orderCombo");

		var order = {};

		$orderDatagrid.datagrid({
			onClickRow : function(index, rowData) {
				orderItemDatagrid.datagrid("load", {
					"orderId" : rowData.id
				});
				order = rowData;
			}
		});

		var cmdObj = {
			//添加
			"orderAdd" : function() {
				$orderDialog.dialog('open').dialog('center').dialog('setTitle',
						'添加对话框');
				$orderForm.form('clear');
				$('#cc').combobox({
					url : '/order/getSeller.do',
					valueField : 'id',
					textField : 'realName',
					limitToList : true
				})
			},
			//生成合同
			"orderToContract" : function() {
				var row = $orderDatagrid.datagrid('getSelected');
				if (row) {
					if (row.status == -1) {
						$.messager.confirm('温馨提示', '您确定要将' + row.sn
								+ '定金订单生成合同吗?', function(r) {
							if(r){
								$.post("/order/toContract.do", {
									"id" : row.id,
									"sn" : row.sn,
									"customer.id" : row.customer.id,
									"sum" : row.sum,
									"totalAmount" : row.totalAmount,
									"intro" : row.intro,
									"seller.id" : row.seller.id,
									"singTime" : row.singTime
								}, function(data) {
									if (data.success) {
										$.messager.alert('温馨提示', '定金订单生成合同成功！！！',
												'info');
										// 		                            	$("#tt").tabs("select","定金订单管理"
										// 		                  				);
										location.href = "/order/index.do";
									} else {
										$.messager.alert('温馨提示', '生成合同失败：'
												+ data.massage + '', 'error');
									}
								}, "json");
							}
						});
					} else {
						$.messager.alert('温馨提示', '生成了合同的定金订单不能再次操作！！！', 'info');
					}
				} else {
					$.messager.alert('温馨提示', '请选中要生成合同的定金订单！！！', 'info');
				}
			},
			//编辑
			"orderUpdate" : function() {
				var row = $orderDatagrid.datagrid('getSelected');
				if (row) {
					$orderDialog.dialog('open').dialog('center').dialog(
							'setTitle', '编辑对话框');

					// 特殊数据处理
					if (row.deviceType) {
						row["deviceType.id"] = row.deviceType.id;
					}
					if (row.unitType) {
						row["unitType.id"] = row.unitType.id;
					}

					$orderForm.form('load', row);

					$('#cc').combobox({
						url : '/order/getSeller.do',
						valueField : 'id',
						textField : 'realName'
					})

					$('#cc').combobox('setValue', row.seller.id);
					$('#cg').combogrid('setValue', row.customer.id);

					// 		                url = '/order/save?id='+row.id;
				} else {
					$.messager.alert('温馨提示', '请选中要编辑的定金订单！！！', 'info');
				}
			},
			//删除
			"orderDelete" : function() {
				var row = $orderDatagrid.datagrid('getSelected');
				if (row) {
					$.messager.confirm('温馨提示', '您确定要删除' + row.sn + '定金订单的数据吗?',
							function(r) {
								if (r) {
									$.post('/order/delete.do', {
										id : row.id
									}, function(result) {
										if (result.success) {
											$.messager.alert('温馨提示',
													'定金订单删除成功', 'info');
											$orderDatagrid.datagrid('reload'); // reload the user data
										} else {
											$.messager.alert('温馨提示', '定金订单删除失败'
													+ result.massage + '',
													'error');
										}
									}, 'json');
								}
							});
				} else {
					//如果没有选中删除行
					$.messager.alert('温馨提示', '请选中要删除的定金订单！！！', 'info');
				}
			},
			//刷新
			"orderRefesh" : function() {
				$("#orderDatagrid").datagrid('reload', {});
			},
			"searchForm" : function() {
				var paramObj = $("#orderSearchForm").serializeJson();
				// 调用datagrid的load方法，load只能加载json对象数据
				$orderDatagrid.datagrid("load", paramObj);
			},
			//清空查询
			"searchFormClear" : function() {
				$("#orderSearchForm").form("clear");
				// 					location.reload();//js原生重载
				var paramObj = $("#orderSearchForm").serializeJson();
				// 调用datagrid的load方法，load只能加载json对象数据
				$orderDatagrid.datagrid("load", paramObj);
			},
			//高级查询弹窗
			"advanceSearch" : function() {
				$("#orderSearchDatagridDlgForm").form('clear');
				$("#orderSearchDatagridDlg").dialog('open').dialog('center')
						.dialog('setTitle', '高级查询');
				$("#order_seller").combobox({
					url : '/order/getSeller.do',
					valueField : 'id',
					textField : 'realName'
				});
			},
			//高级查询执行
			"orderSearchSave" : function() {
				var paramObj = $("#orderSearchDatagridDlgForm").serializeJson();
				// 调用datagrid的load方法，load只能加载json对象数据
				$orderDatagrid.datagrid("load", paramObj);
			},
			//高级查询取消
			"orderSearchCancel" : function() {
				$("#orderSearchDatagridDlgForm").form('clear');
				$("#orderSearchDatagridDlg").dialog('close');
			},
			//保存
			"orderSave" : function() {
				$orderForm.form('submit', {
					url : '/order/save.do',
					onSubmit : function() {

					},
					success : function(result) {
						var $result = $.parseJSON(result);
						if ($result.success) {
							$.messager.alert('温馨提示', $result.message, 'info')
							$orderDialog.dialog('close'); // close the dialog
							$orderDatagrid.datagrid('reload'); // reload the user data
						} else {
							$.messager.alert('温馨提示', '保存失败' + $result.message
									+ '', 'error');
						}
					}
				})
			},
			//取消
			"orderCancle" : function() {
				$orderDialog.dialog('close');
			},
			// 合同明细添加弹窗
			addItem : function() {
				orderItemDatagridDlgForm.form("clear");
				if (order) {
					// 绑定目录
					orderCombo.combo('setValue', order.id);
					orderCombo.combo('setText', order.sn);
				}

				// 清空表单
				// 修改对话框的名称
				orderItemDatagridDlg.dialog("setTitle", "添加对话框");
				// 打开对话框
				orderItemDatagridDlg.dialog("open");
			},
			// 合同明细编辑弹窗
			editItem : function() {
				var rowData = orderItemDatagrid.datagrid("getSelected");
				if (!rowData) {
					$.messager.alert("温馨提示", "请选中要编辑的定金订单明细！！！", "info");
					return;
				}
				// 清空表单
				orderItemDatagridDlgForm.form("clear");
				// 设置标题
				orderItemDatagridDlg.dialog("setTitle", "编辑对话框");
				// 特殊数据处理
				
				orderCombo.combo('setValue', rowData.order.id);
				orderCombo.combo('setText', rowData.order.sn);
				
				/* if (rowData.order) {
					rowData["order.id"] = rowData.order.id;
				} */
				if (rowData.deviceType) {
					rowData["deviceType.id"] = rowData.deviceType.id;
				}
				if (rowData.unitType) {
					rowData["unitType.id"] = rowData.unitType.id;
				}

				// 回填数据
				orderItemDatagridDlgForm.form("load", rowData);
				// 打开对话框
				orderItemDatagridDlg.dialog("open");
			},
			// 合同明细保存/修改
			saveItem : function() {
				$('#areaSelect').attr("disabled", "disabled");
				orderItemDatagridDlgForm.form("submit", {
					url : "/orderItem/save.do",
					onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
						return orderItemDatagridDlgForm.form("validate");
					},
					success : function(data) {
						data = $.parseJSON(data);
						if (data.success) {
							// 关闭对话框
							orderItemDatagridDlg.dialog("close");
							// 提示
							$.messager.alert("温馨提示", data.message, "info",
									function() {
										// 刷新
										orderItemDatagrid.datagrid("reload");
									});
						} else {

							$.messager.alert("错误提示信息",
									"<h3 style= 'color:red;'>" + data.message
											+ "</h3>", 'error')
						}
					}
				});
			},
			// 合同明细删除
			deleteItem : function() {
				var rowData = orderItemDatagrid.datagrid("getSelected");
				if (!rowData) {
					$.messager.alert("温馨提示", "请选中要删除的定金订单明细！！！", "info");
					return;
				}
				// 确认删除
				$.messager.confirm('删除确认框', '您确定要删除这条定金订单明细吗?', function(r) {
					if (r) {
						$.get("/orderItem/delete.do", {
							id : rowData.id
						}, function(data) {
							if (data.success) {
								$.messager.alert("温馨提示", data.message, "info",
										function() {
											orderItemDatagrid
													.datagrid("reload");
										});
							} else {
								$.messager.alert("错误提示信息",
										"<h3 style= 'color:red;'>"
												+ data.message + "</h3>",
										'error');
							}
						})
					}
				})
			},
			// 合同明细刷新
			refreshItem : function() {
				orderItemDatagrid.datagrid("reload", {});
			},
			// 合同明细取消
			cancelItem : function() {
				orderItemDatagridDlg.dialog("close");
			}
		}

		$("a[data-cmd]").click(function() {
			var dataCmd = $(this).data("cmd");
			cmdObj[dataCmd]();
		});
	});
	function statusOrderFormatter(v, r, i) {
		return v == -1 ? "<font color='red'>未生成合同</font>"
				: "<font color='green'>已生成合同</font>";
	}
</script>
</head>
<body>
	<div class="easyui-layout" id="layout" border="false"
		data-options="fit:true,border:false">
		<div id="westPanel" region="west" headerCls="border-right"
			bodyCls="border-right">
			<table id="orderDatagrid" title="定金订单管理" class="easyui-datagrid"
				style="width: 700px; height: 250px" url="/order/list.do"
				toolbar="#toolbar" pagination="true" rownumbers="true" fit="true"
				fitColumns="true" singleSelect="true" pageSize="20"
				pageList="[2,5,10,20,50]">
				<thead>
					<tr>
						<th field="id" hidden=true width="50"></th>
						<th field="sn" width="50">定金订单编号</th>
						<th field="customer" formatter="objectFormatter" width="50">定金客户</th>
						<th field="signTime" width="50">签订时间</th>
						<th field="sum" width="50">定金金额</th>
						<th field="totalAmount" width="50">总金额</th>
						<th field="seller" formatter="objectFormatter" width="50">营销人员</th>
						<th field="intro" width="50">摘要</th>
						<th field="status" formatter="statusOrderFormatter" width="50">是否生成过定金订单</th>
					</tr>
				</thead>
			</table>
		</div>

		<div region="center" id="center" headerCls="border-right">
			<table id="orderItemDatagrid" class="easyui-datagrid"
				url="/orderItem/list.do" title="定金订单明细管理" fit="true" border="false"
				fitColumns="true" singleSelect="true" rownumbers="true"
				toolbar="#orderItemdatagridtoolbar" pagination="true" pageSize="20"
				pageList="[2,5,10,20,50]">
				<thead>
					<tr>
						<th field="order" width="20" formatter="objectFormatter">所属定金订单</th>
						<th field="deviceType" width="10" formatter="objectFormatter">设备</th>
						<th field="unitType" width="10" formatter="objectFormatter">设备型号</th>
						<th field="number" width="5">数量</th>
						<th field="intro" width="10">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>


	<div id="toolbar">
		<wyl:checkPermission permissionName="定金订单保存/修改">
			<a href="javascript:void(0)" data-cmd="orderAdd"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:void(0)" data-cmd="orderUpdate"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="定金订单删除">
			<a href="javascript:void(0)" data-cmd="orderDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="orderRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
		<wyl:checkPermission permissionName="定金订单生成合同">
			<a href="javascript:void(0)" data-cmd="orderToContract"
				class="easyui-linkbutton c6" iconCls="icon-reload" plain="true">生成合同</a>
		</wyl:checkPermission>
		<div>
			<form id="orderSearchForm" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" style="width: 120px;"
					class="easyui-textbox"> <a href="#"
					class="easyui-linkbutton color-blue" iconCls="icon-search"
					data-cmd="searchForm">搜索</a> <a href="#"
					class="easyui-linkbutton color-blue" iconCls="icon-clear"
					data-cmd="searchFormClear">清空查询</a> <a href="#"
					class="easyui-linkbutton c4 color-blue" iconCls="icon-search"
					data-cmd="advanceSearch">高级查询</a>
			</form>
		</div>
	</div>
	<div id="orderItemdatagridtoolbar">
		<wyl:checkPermission permissionName="定金订单明细保存/修改">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				data-cmd="addItem">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" data-cmd="editItem">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="定金订单明细删除">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"
				data-cmd="deleteItem">删除</a>
		</wyl:checkPermission>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload"
			plain="true" data-cmd="refreshItem">刷新</a>
	</div>

	<!-- 定金订单明细的添加或编辑对话框 -->
	<div id="orderItemDatagridDlg" class="easyui-dialog"
		style="width: 400px; height: 320px; padding: 10px 20px" modal="true"
		closed="true" buttons="#orderItemDatagridDlgButtons">
		<div class="ftitle">定金订单明细添加/编辑</div>
		<form id="orderItemDatagridDlgForm" method="post" novalidate>
			<input name="id" type="hidden" />
			<!-- 区分是添加还是编辑 -->
			<table align="center" class="tdright">
				<tr>
					<td>所属订单：</td>
					<td><input id="orderCombo" name="order.id"
						class="easyui-combotree" style="width: 147px;"
						data-options="
	        					method:'get'
	        					,panelHeight:'auto'
	        					,required:'true'
	        					,setValue:'id'
	        					,setText:'sn'
	        					">
	        					
	        					<!-- <select id="orderCombo" name="order.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'设备类型',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select> -->
	        					
	        					</td>
				</tr>
				<tr>
					<td>购买设备：</td>
					<td><select id="deviceTypeId" name="deviceType.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/orderItem/deviceType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'设备类型',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td>设备型号：</td>
					<td><select id="unitTypeId" name="unitType.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/orderItem/unitType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'设备型号',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td valign="top">数量：</td>
					<td><input name="number" class="easyui-textbox"
						style="width: 147px;" required="true"></td>
					</td>
				</tr>
				<tr>
					<td valign="top">备注：</td>
					<td><input name="intro" class="easyui-textbox"
						style="width: 147px; height: 80px" data-options="multiline:true"></td>
					</td>
				</tr>
			</table>
		</form>
		<!-- 添加或编辑明细对话框按钮 -->
		<div id="orderItemDatagridDlgButtons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-save" style="width: 90px" data-cmd="saveItem">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" style="width: 90px" data-cmd="cancelItem">取消</a>
		</div>
	</div>

	<div id="orderDialog" class="easyui-dialog"
		style="width: 400px; height: 360px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">定金订单信息添加/编辑</div>
		<form id="orderForm" method="post" novalidate>
			<input name="id" type="hidden"> <input name="status"
				type="hidden"><input name="sn" type="hidden">
			<table align="center" class="tdright">
				<!-- <tr>
					<td>定金订单编号：</td>
					<td><input name="sn" class="easyui-textbox" required="true"></td>
				</tr> -->
				<!-- <tr>
					<td>购买设备：</td>
					<td><select id="deviceTypeId" name="deviceType.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/order/deviceType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'设备类型',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td>设备型号：</td>
					<td><select id="unitTypeId" name="unitType.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/order/unitType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'设备型号',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr> -->

				<tr>
					<td>定金客户：</td>
					<td><select id="cg" class="easyui-combogrid" required="true"
						name="customer.id" style="width: 147px;"
						data-options="    
				            panelWidth:450,    
				            idField:'id', 
				            textField:'name',    
				            url:'/customer/list.do',   
				            fitColumns:true,
				            pagination:true,
				            columns:[[    
				                {field:'name',title:'客户姓名',width:60},    
				                {field:'age',title:'年龄',width:100},    
				                {field:'email',title:'邮箱',width:120},    
				                {field:'tel',title:'电话',width:100},
				                {field:'qq',title:'QQ',width:100}    
				            ]]    
        				"></select>
					</td>
				</tr>
				<tr>
					<td>签订时间：</td>
					<td><input name="signTime" class="easyui-datebox"
						required="true"></td>
				</tr>
				<tr>
					<td>定金金额：</td>
					<td><input name="sum" class="easyui-numberbox"
						data-options="required:true,min:0,prefix:'￥',precision:2"
						required="true"></input></td>
				</tr>
				<tr>
					<td>总金额：</td>
					<td><input name="totalAmount" class="easyui-numberbox"
						data-options="required:true,min:0,prefix:'￥',precision:2"
						required="true"></input></td>
				</tr>
				<tr>
					<td>营销人员：</td>
					<td><input id="cc" name="seller.id"
						data-options="required:true,editable:false,panelHeight:'auto'"></td>
				</tr>
				<tr>
					<td valign="top">摘要：</td>
					<td><input name="intro" class="easyui-textbox"
						style="width: 147px; height: 80px" data-options="multiline:true"></td>
				</tr>
			</table>
		</form>

	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" data-cmd="orderSave"
			class="easyui-linkbutton c1" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="orderCancle"
			class="easyui-linkbutton c2" iconCls="icon-cancel"
			style="width: 90px">取消</a>
	</div>
	<!-- 高级查询对话框 -->
	<div id="orderSearchDatagridDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#orderSearchDatagridDlgButtons">
		<div class="ftitle">定金订单信息查询</div>
		<form id="orderSearchDatagridDlgForm" method="post" novalidate>
			<table align="center" class="tdright">
				<tr>
					<td>开始时间：</td>
					<td><input name="beginTime" class="easyui-datebox"></input></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input name="endTime" class="easyui-datebox"></input></td>
				</tr>
				<tr>
					<td>定金金额：</td>
					<td><input name="sum" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>定金客户：</td>
					<td><select id="cg" class="easyui-combogrid" name="customerId"
						style="width: 147px;"
						data-options="    
				            panelWidth:450,    
				            idField:'id', 
				            textField:'name',    
				            url:'/customer/list.do',   
				            fitColumns:true,
				            pagination:true,
				            columns:[[    
				                {field:'name',title:'客户姓名',width:60},    
				                {field:'age',title:'年龄',width:100},    
				                {field:'email',title:'邮箱',width:120},    
				                {field:'qq',title:'QQ',width:100}    
				            ]]    
        			"></select>
					</td>
				</tr>
				<wyl:checkPermission permissionName="定金订单管理">
					<tr>
						<td>营销人员：</td>
						<td><input id="order_seller" name="sellerId"
							data-options="editable:false,panelHeight:'auto'" /></td>
					</tr>
				</wyl:checkPermission>
			</table>
		</form>
	</div>
	<!-- 高级查询对话框按钮 -->
	<div id="orderSearchDatagridDlgButtons">
		<a data-cmd="orderSearchSave" href="javascript:void(0)"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">查询</a>
		<a data-cmd="orderSearchCancel" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<!--     <input id="cc" name="seller" value="aa">   -->
</body>
</html>