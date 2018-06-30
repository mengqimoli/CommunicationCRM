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
	var guaranteeDatagrid, guaranteeDatagridDlg, guaranteeDatagridDlgForm, printContract, cg;
	// 获取组件
	guaranteeDatagrid = $("#guaranteeDatagrid");
	guaranteeDatagridDlg = $("#guaranteeDatagridDlg");
	guaranteeDatagridDlgForm = $("#guaranteeDatagridDlgForm")
	printContract = $("#printContract")

	var guaranteeItemDatagrid = $("#guaranteeItemDatagrid");
	var guaranteeItemDatagridDlg = $("#guaranteeItemDatagridDlg");
	var guaranteeItemDatagridDlgForm = $("#guaranteeItemDatagridDlgForm");
	var guaranteeCombo = $("#guaranteeCombo");
	cg = $("#cg")

	var guarantee = {};

	guaranteeDatagrid.datagrid({
		onClickRow : function(index, rowData) {
			guaranteeItemDatagrid.datagrid("load", {
				"guaranteeId" : rowData.id
			});
			guarantee = rowData;
		}
	});

	// 抽取cmd执行对象
	var cmdObj = {
		"guaranteeAdd" : function() {
			guaranteeDatagridDlg.dialog('open').dialog('center').dialog(
					'setTitle', '添加对话框');
			guaranteeDatagridDlgForm.form('clear');
		},
		"guaranteeEdit" : function() {
			// 获取选中
			var row = guaranteeDatagrid.datagrid('getSelected');
			// 没有选中
			if (!row) {
				$.messager.alert('温馨提示', '请选中要编辑的合同！！！', 'error');
				return;
			}
			guaranteeDatagridDlg.dialog('open').dialog('center').dialog(
					'setTitle', '编辑对话框');
			guaranteeDatagridDlgForm.form('clear');
			if (row.signTime) {
				$("#expireTime").datebox("setValue", row.expireTime);
			}
			if (row.customer) {
				cg.combogrid("setValue", row.customer.id);
				cg.combogrid("setText", row.customer.name);
			}
			guaranteeDatagridDlgForm.form('load', row);
		},
		// 删除
		"guaranteeDelete" : function() {
			var row = guaranteeDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要删除客户<font color="red">'
						+ row.customer.name + '</font>相关的数据吗?', function(r) {
					if (r) {
						$.post('/guarantee/delete.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$.messager.alert('温馨提示', '合同删除成功！！！', 'info');
								guaranteeDatagrid.datagrid('reload'); // reload
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
		"guaranteeRefesh" : function() {
			guaranteeDatagrid.datagrid('reload', {});
		},
		"guaranteeSave" : function() {
			guaranteeDatagridDlgForm.form("submit", {
				url : "/guarantee/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return guaranteeDatagridDlgForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						guaranteeDatagridDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									guaranteeDatagrid.datagrid("reload");
								});
					}
				}
			});

		},
		"guaranteeCancel" : function() {
			guaranteeDatagridDlg.dialog('close')
		},
		"searchForm" : function() {
			var paramObj = $("#guaranteeSearchForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			guaranteeDatagrid.datagrid("load", paramObj);
		},
		//清空查询
		"searchFormClear" : function() {
			$("#guaranteeSearchForm").form("clear");
			// 					location.reload();//js原生重载
			var paramObj = $("#guaranteeSearchForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			guaranteeDatagrid.datagrid("load", paramObj);
		},
		//高级查询弹窗
		"advanceSearch" : function() {
			$("#guaranteeSearchDatagridDlgForm").form('clear');
			$("#guaranteeSearchDatagridDlg").dialog('open').dialog('center')
					.dialog('setTitle', '高级查询');
			$("#order_seller").combobox({
				url : '/order/getSeller.do',
				valueField : 'id',
				textField : 'realName'
			});
		},
		//高级查询执行
		"guaranteeSearchSave" : function() {
			var paramObj = $("#guaranteeSearchDatagridDlgForm").serializeJson();
			// 调用datagrid的load方法，load只能加载json对象数据
			guaranteeDatagrid.datagrid("load", paramObj);
		},
		//高级查询取消
		"guaranteeSearchCancel" : function() {
			$("#guaranteeSearchDatagridDlgForm").form('clear');
			$("#guaranteeSearchDatagridDlg").dialog('close');
		},
		"printContract" : function() {
			var row = guaranteeDatagrid.datagrid('getSelected');
			if (!row) {
				$.messager.alert('消息提示', '请选中想要打印的合同！！！', 'info');
				return;
			}
			if (row.status = -1) {
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
			$.get('/guaranteeItem/getItem.do?id=' + row.id, function(data) {
				var itemHtml = "";
				$.each(data, function(i, v) {
					itemHtml += "<tr>";
					itemHtml += "<td>" + v.money + "</td>";
					itemHtml += "<td>" + row.sum + "</td>";
					itemHtml += "<td>" + v.scale + "</td>";
					itemHtml += "<td>" + v.payTime + "</td>";
					itemHtml += "</tr>";
				})
				$("#guaranteeItemList").html(itemHtml);
			});
		},
		// 合同明细添加弹窗
		addItem : function() {
			guaranteeItemDatagridDlgForm.form("clear");
			if (guarantee) {
				// 绑定目录
				guaranteeCombo.combo('setValue', guarantee.id);
				guaranteeCombo.combo('setText', guarantee.sn);
			}
			
			$('#cc').combobox({
				url : '/guaranteeItem/getRepairer.do',
				valueField : 'id',
				textField : 'realName',
				limitToList : true
			});
			
			// 清空表单
			// 修改对话框的名称
			guaranteeItemDatagridDlg.dialog("setTitle", "添加对话框");
			// 打开对话框
			guaranteeItemDatagridDlg.dialog("open");
		},
		// 合同明细编辑弹窗
		editItem : function() {
			var rowData = guaranteeItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的合同明细！！！", "info");
				return;
			}
			// 清空表单
			guaranteeItemDatagridDlgForm.form("clear");
			// 设置标题
			guaranteeItemDatagridDlg.dialog("setTitle", "编辑对话框");
			// 特殊数据处理
			// 加载出员工
			$('#cc').combobox({
				url : '/guaranteeItem/getRepairer.do',
				valueField : 'id',
				textField : 'realName',
				limitToList : true
			});
			if (rowData.employee) {
				$("#cc").combobox("select", rowData.employee.id);
			}
			if (rowData.guarantee) {
				rowData["guarantee.id"] = rowData.guarantee.id;
			}
			// 回填数据
			guaranteeItemDatagridDlgForm.form("load", rowData);
			// 打开对话框
			guaranteeItemDatagridDlg.dialog("open");
		},
		// 合同明细保存/修改
		saveItem : function() {
			$('#areaSelect').attr("disabled","disabled");
			guaranteeItemDatagridDlgForm.form("submit", {
				url : "/guaranteeItem/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return guaranteeItemDatagridDlgForm.form("validate");
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						guaranteeItemDatagridDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									guaranteeItemDatagrid.datagrid("reload");
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
			var rowData = guaranteeItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要删除的合同明细！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm('删除确认框', '您确定要删除这条合同明细吗?', function(r) {
				if (r) {
					$.get("/guaranteeItem/delete.do", {
						id : rowData.id
					}, function(data) {
						if (data.success) {
							$.messager
									.alert("温馨提示", data.message, "info",
											function() {
												guaranteeItemDatagrid
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
			guaranteeItemDatagrid.datagrid("reload", {});
		},
		// 合同明细取消
		cancelItem : function() {
			guaranteeItemDatagridDlg.dialog("close");
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
	return v == -1 ? "<font color='red'>保修单已停用</font>"
			: "<font color='green'>保修单已生效</font>";
}
function statusItemFormatter(v, r, i) {
	return v == -1 ? "<font color='red'>未解决</font>"
			: "<font color='green'>已解决</font>";
}
