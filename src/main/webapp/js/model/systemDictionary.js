// 解决页面两个grid的布局问题
$(function() {
	$("#onlyNum").on("keyup", function() {
		$(this).val($(this).val().replace(/\D|^0/g, ''));
	});
	
	function autoResizeWin() {
		// 1. 获取页面宽度
		// console.debug(window);
		// console.debug(document);
		var screenWidth = document.documentElement.clientWidth;
		// 2. 获取页面宽度的一半
		var halfWidth = screenWidth / 3;
		// 3. 把west组件的宽度,设置为屏幕的一半宽度
		$("#westPanel").panel("resize", {
			width : halfWidth
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
	var systemDictionaryDialog = $("#systemDictionary-dialog");
	var systemDictionaryForm = $("#systemDictionary-form");
	var systemDictionaryDatagrid = $("#systemDictionary-datagrid");
	var systemDictionaryItemDatagrid = $("#systemDictionaryItem-datagrid");
	var systemDictionaryItemDialog = $("#systemDictionaryItem-dialog");
	var systemDictionaryItemForm = $("#systemDictionaryItem-form");
	var systemDictionaryCombo = $("#systemDictionaryCombo");
	var type = {};

	systemDictionaryDatagrid.datagrid({
		onClickRow : function(index, rowData) {
			systemDictionaryItemDatagrid.datagrid("load", {
				"typeId" : rowData.id
			});
			type = rowData;
		}
	});

	var cmdObj = {
		// 数据字典添加弹框
		create : function() {
			// 在添加窗口弹出之前先把表单清空
			systemDictionaryForm.form("clear");
			// 弹出之前先修改弹出框的title
			systemDictionaryDialog.dialog('setTitle', "添加对话框");
			// 这个是把添加窗口弹出来
			systemDictionaryDialog.dialog('open');
		},
		// 数据字典编辑弹框
		edit : function() {
			var rowData = systemDictionaryDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的数据字典！！！", "info");
				return;
			}
			// 清空表单
			systemDictionaryForm.form("clear");
			// 设置标题
			systemDictionaryDialog.dialog("setTitle", "编辑对话框");

			systemDictionaryForm.form("load", rowData);

			systemDictionaryDialog.dialog("open");
		},
		// 数据字典刷新
		refresh : function() {
			systemDictionaryDatagrid.datagrid("reload",{});
			// $("#systemDictionaryItemCreateBtn").linkbutton('disable');
		},
		// 数据字典保存/修改
		save : function() {
			systemDictionaryForm.form("submit", {
				url : "/systemDictionaryType/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return systemDictionaryForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						systemDictionaryDialog.dialog("close");
						// 提示
						$.messager
								.alert("温馨提示", data.message, "info",
										function() {
											// 刷新
											systemDictionaryDatagrid
													.datagrid("reload");
										});
					} else {
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		// 数据字典取消
		cancel : function() {
			systemDictionaryDialog.dialog("close");
		},
		// 数据字典明细添加弹窗
		addItem : function() {
			systemDictionaryItemForm.form("clear");
			if (type) {
				// 绑定目录
				systemDictionaryCombo.combo('setValue', type.id);
				systemDictionaryCombo.combo('setText', type.name);
			}
			// 清空表单
			// 修改对话框的名称
			systemDictionaryItemDialog.dialog("setTitle", "添加对话框");
			// 打开对话框
			systemDictionaryItemDialog.dialog("open");
		},
		// 数据字典明细编辑弹窗
		editItem : function() {
			var rowData = systemDictionaryItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的数据字典明细！！！", "info");
				return;
			}
			// 清空表单
			systemDictionaryItemForm.form("clear");
			// 设置标题
			systemDictionaryItemDialog.dialog("setTitle", "编辑对话框");
			// 特殊数据处理
			if (rowData.type) {
				rowData["type.id"] = rowData.type.id;
			}
			// 回填数据
			systemDictionaryItemForm.form("load", rowData);
			// 打开对话框
			systemDictionaryItemDialog.dialog("open");
		},
		// 数据字典明细保存/修改
		saveItem : function() {
			systemDictionaryItemForm.form("submit", {
				url : "/systemDictionaryItem/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return systemDictionaryItemForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						systemDictionaryItemDialog.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									systemDictionaryItemDatagrid
											.datagrid("reload");
								});
					} else {
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		// 数据字典明细删除
		deleteItem : function() {
			var rowData = systemDictionaryItemDatagrid.datagrid("getSelected");
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要删除的数据字典明细！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm('删除确认框', '您确定要删除这条数据字典明细吗?', function(r) {
				if (r) {
					$.get("/systemDictionaryItem/delete.do", {
						id : rowData.id
					}, function(data) {
						if (data.success) {
							$.messager.alert("温馨提示", data.message, "info",
									function() {
										systemDictionaryItemDatagrid
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
		// 数据字典明细刷新
		refreshItem : function() {
			systemDictionaryItemDatagrid.datagrid("reload",{});
		},
		// 数据字典明细取消
		cancelItem : function() {
			systemDictionaryItemDialog.dialog("close");
		}
	};

	$("a[data-cmd]").bind("click", function() {
		var cmd = $(this).data("cmd");
		cmdObj[cmd]();
	});
});

function statusFormatter(v, r, i) {
	switch (v) {
	case 1:
		return "<font color='green'>启用</font>";
		break;

	default:
		return "<font color='red'>停用</font>";
		break;
	}
}
