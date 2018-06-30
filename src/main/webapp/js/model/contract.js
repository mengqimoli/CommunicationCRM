// 解决页面两个grid的布局问题
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
	// 声明组件
	var contractDatagrid, contractDatagridDlg, contractDatagridDlgForm, printContract, cg;
	// 获取组件
	contractDatagrid = $("#contractDatagrid");
	contractDatagridDlg = $("#contractDatagridDlg");
	contractDatagridDlgForm = $("#contractDatagridDlgForm")
	printContract = $("#printContract")

	var contractItemDatagrid = $("#contractItemDatagrid");
	var contractItemDatagridDlg = $("#contractItemDatagridDlg");
	var contractItemDatagridDlgForm = $("#contractItemDatagridDlgForm");
	var contractCombo = $("#contractCombo");
	cg = $("#cg")

	var contract = {};

	contractDatagrid.datagrid({
		onClickRow : function(index, rowData) {
			contractItemDatagrid.datagrid("load", {
				"contractId" : rowData.id
			});
			contract = rowData;
		}
	});

	// 抽取cmd执行对象
	var cmdObj = {
		"contractAdd" : function() {
			contractDatagridDlg.dialog('open').dialog('center').dialog(
					'setTitle', '添加对话框');
			contractDatagridDlgForm.form('clear');
			// 加载出员工
			$('#cc').combobox({
				url : '/contract/getSeller.do',
				valueField : 'id',
				textField : 'realName',
				limitToList : true
			})
		},
		"contractEdit" : function() {
			// 获取选中
			var row = contractDatagrid.datagrid('getSelected');
			// 没有选中
			if (!row) {
				$.messager.alert('温馨提示', '请选中要编辑的合同！！！', 'error');
				return;
			}
			contractDatagridDlg.dialog('open').dialog('center').dialog(
					'setTitle', '编辑对话框');
			contractDatagridDlgForm.form('clear');
			contractDatagridDlgForm.form('load', row);
			// 加载出员工
			$('#cc').combobox({
				url : '/contract/getSeller.do',
				valueField : 'id',
				textField : 'realName'
			});
			if (row.seller) {
				$("#cc").combobox("select", row.seller.id);
			}
			if (row.signTime) {
				$("#signTime").datebox("setValue", row.signTime);
			}
			if (row.customer) {
				cg.combogrid("setValue", row.customer.id);
				cg.combogrid("setText", row.customer.name);
			}
		},
		// 删除
		"contractDelete" : function() {
			var row = contractDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要删除客户<font color="red">'
						+ row.customer.name + '</font>相关的数据吗?', function(r) {
					if (r) {
						$.post('/contract/delete.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$.messager.alert('温馨提示', '合同删除成功！！！', 'info');
								contractDatagrid.datagrid('reload'); // reload
								// the
								// user
								// data
							} else {
								$.messager.alert('温馨提示', '删除失败：'
										+ result.massage + '', 'error');
							}
						}, 'json');
					}
				});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要删除的合同！！！', 'info');
			}
		},
		// 刷新
		"contractRefesh" : function() {
			contractDatagrid.datagrid('reload', {});
		},
		"contractSave" : function() {
			contractDatagridDlgForm.form("submit", {
				url : "/contract/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return contractDatagridDlgForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						contractDatagridDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									contractDatagrid.datagrid("reload");
								});
					}
				}
			});

		},
		"contractCancel" : function() {
			contractDatagridDlg.dialog('close')
		},
		"searchForm" : function() {
			var paramObj = $("#contractSearchForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			contractDatagrid.datagrid("load", paramObj);
		},
		//清空查询
		"searchFormClear" : function() {
			$("#contractSearchForm").form("clear");
			// 					location.reload();//js原生重载
			var paramObj = $("#contractSearchForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			contractDatagrid.datagrid("load", paramObj);
		},
		//高级查询弹窗
		"advanceSearch" : function() {
			$("#contractSearchDatagridDlgForm").form('clear');
			$("#contractSearchDatagridDlg").dialog('open').dialog('center')
					.dialog('setTitle', '高级查询');
			$("#order_seller").combobox({
				url : '/order/getSeller.do',
				valueField : 'id',
				textField : 'realName'
			});
		},
		//高级查询执行
		"contractSearchSave" : function() {
			var paramObj = $("#contractSearchDatagridDlgForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			contractDatagrid.datagrid("load", paramObj);
		},
		//高级查询取消
		"contractSearchCancel" : function() {
			$("#contractSearchDatagridDlgForm").form('clear');
			$("#contractSearchDatagridDlg").dialog('close');
		},
		"printContract" : function() {
			var row = contractDatagrid.datagrid('getSelected');
			if (!row) {
				$.messager.alert('消息提示', '请选中想要打印的合同！！！', 'info');
				return;
			}
			if (row.status == -1) {
				$.messager.alert('消息提示', '该合同已经失效，无法打印！！！', 'info');
				return;
			}
			$('#printDialog').dialog('open');
			var printHtml = "";
			printHtml += "<td>" + row.sn + "</td>";
			printHtml += "<td>" + row.customer.name + "</td>";
			printHtml += "<td>" + row.signTime + "</td>";
			printHtml += "<td>" + row.sum + "</td>";
			printHtml += "<td>" + row.seller.realName + "</td>";
			printHtml += "<td>无 </td>";
			$("#printContractList").html(printHtml);
			$("#jiafang").html(row.seller.realName);
			$("#sn").html(row.sn);
			$("#time").html(row.signTime);
			$("#yifang").html(row.customer.name);
			$.get('/contractItem/getItem.do?id=' + row.id, function(data) {
				var itemHtml = "";
				$.each(data, function(i, v) {
					itemHtml += "<tr>";
					itemHtml += "<td>" + v.money + "</td>";
					itemHtml += "<td>" + row.sum + "</td>";
					itemHtml += "<td>" + v.scale + "</td>";
					itemHtml += "<td>" + v.payTime + "</td>";
					itemHtml += "</tr>";
				})
				$("#contractItemList").html(itemHtml);
			});
		},
		// 合同明细添加弹窗
		addItem : function() {
			contractItemDatagridDlgForm.form("clear");
			if (contract) {
				// 绑定目录
				contractCombo.combo('setValue', contract.id);
				contractCombo.combo('setText', contract.sn);
			}
			
			// 清空表单
			// 修改对话框的名称
			contractItemDatagridDlg.dialog("setTitle", "添加对话框");
			// 打开对话框
			contractItemDatagridDlg.dialog("open");
		},
		// 合同明细编辑弹窗
		editItem : function() {
			var rowData = contractItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的合同明细！！！", "info");
				return;
			}
			// 清空表单
			contractItemDatagridDlgForm.form("clear");
			// 设置标题
			contractItemDatagridDlg.dialog("setTitle", "编辑对话框");
			// 特殊数据处理
			if (rowData.contract) {
				rowData["contract.id"] = rowData.contract.id;
			}
			// 回填数据
			contractItemDatagridDlgForm.form("load", rowData);
			// 打开对话框
			contractItemDatagridDlg.dialog("open");
		},
		// 合同明细保存/修改
		saveItem : function() {
			$('#areaSelect').attr("disabled","disabled");
			contractItemDatagridDlgForm.form("submit", {
				url : "/contractItem/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return contractItemDatagridDlgForm.form("validate");
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						contractItemDatagridDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									contractItemDatagrid.datagrid("reload");
								});
					} else {
						
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		// 合同明细删除
		deleteItem : function() {
			var rowData = contractItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要删除的合同明细！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm('删除确认框', '您确定要删除这条合同明细吗?', function(r) {
				if (r) {
					$.get("/contractItem/delete.do", {
						id : rowData.id
					}, function(data) {
						if (data.success) {
							$.messager
									.alert("温馨提示", data.message, "info",
											function() {
												contractItemDatagrid
														.datagrid("reload");
											});
						} else {
							$.messager.alert("错误提示信息",
									"<h3 style= 'color:red;'>" + data.message
											+ "</h3>", 'error');
						}
					})
				}
			})
		},
		// 合同明细刷新
		refreshItem : function() {
			contractItemDatagrid.datagrid("reload", {});
		},
		// 合同明细取消
		cancelItem : function() {
			contractItemDatagridDlg.dialog("close");
		}
		
	}// cmdObj结束
	// 对页面所有的a标签按钮进行监听
	$("a[data-cmd]").click(function() {
		var cmd = $(this).data("cmd");
		if (cmd) {
			cmdObj[cmd]();// 动态调用
		}
	});
});
function statusFormatter(v, r, i) {
	return v == -1 ? "<font color='red'>合同已停用</font>"
			: "<font color='green'>合同已生效</font>";
}
function statusItemFormatter(v, r, i) {
	return v == -1 ? "<font color='red'>未付款</font>"
			: "<font color='green'>已付款</font>";
}
