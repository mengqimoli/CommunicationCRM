//在页面加载完毕后
$(function() {
	// 1、声明需要缓存的对象
	var employeeAdvanceSearchForm, employeeAdvanceSearchDlg, employeeGrid, employeeDlg, employeeForm, employeeSearchForm;
	// 2、做缓存
	employeeGrid = $("#employee-datagrid");
	employeeDlg = $("#employee-dlg");
	employeeForm = $("#employee-form");
	employeeSearchForm = $("#employee-search-form")
	// 高级查询对话框
	employeeAdvanceSearchDlg = $("#employeeAdvanceSearchDlg");
	// 高级查询表单
	employeeAdvanceSearchForm = $("#employeeAdvanceSearchForm ");
	// 3、初始化组件
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		create : function() {
			console.debug('create!');
			// 清空表单
			employeeForm.form("clear");
			// 修改对话框的名称
			employeeDlg.dialog("setTitle", "添加对话框");
			// 打开对话框
			employeeDlg.dialog("open");
		},
		edit : function() {
			console.debug('edit:function!');
			// 获取选中行
			var rowData = employeeGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要编辑的员工！！！", "info");
				return;
			}
			console.debug(rowData);
			// 清空表单
			employeeForm.form("clear");
			// 设置标题
			employeeDlg.dialog("setTitle", "编辑对话框");

			// 特殊数据处理
			if (rowData.dept) {
				rowData["dept.id"] = rowData.dept.id;
			}
			console.debug(rowData);

			// 加载角色数据
			// 发ajax请求，加载用户对应的角色
			$.get("/role/getUserRoles.do", {
				"id" : rowData.id
			}, function(data) {
				$.each(data, function(i, v) {
					$("#roleArr").combobox("select", v.id);
				})
			}, "json");

			// 回填数据
			employeeForm.form("load", rowData);

			// 打开对话框
			employeeDlg.dialog("open");
		},
		stopUse : function() {
			console.debug('stopUse:function!');
			// 获取选中行
			var rowData = employeeGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要离职的员工！！！", "info");
				return;
			}
			if (rowData.status == -1) {
				$.messager.alert("温馨提示", "【"+rowData.name+"】员工已经离职！！！", "warning");
				return;
			}
			// 确认停用
			$.messager
					.confirm(
							"温馨提示",
							"确认要离职【" + rowData.realName + "】员工?",
							function(yes) {
								if (yes) {
									// 获取数据的唯一标识
									var id = rowData.id;
									// 发送AJAX请求，修改ID对应数据状态
									$
											.get(
													"/employee/stopUse.do?id="
															+ id,
													function(data) {
														if (data.success) {
															$.messager
																	.alert(
																			"温馨提示",
																			data.message,
																			"info",
																			function() {
																				employeeGrid
																						.datagrid("reload");
																			});
														}
													}, "json");
								}
							});
		},
		startUse : function() {
			console.debug('startUse:function!');
			// 获取选中行
			var rowData = employeeGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要复职的员工！！！", "info");
				return;
			}
			if (rowData.status == 1) {
				$.messager.alert("温馨提示", "【"+rowData.name+"】员工为在职状态，无需复职！！！", "warning");
				return;
			}
			// 确认启用
			$.messager
					.confirm(
							"温馨提示",
							"确认要复职【" + rowData.realName + "】员工?",
							function(yes) {
								if (yes) {
									// 获取数据的唯一标识
									var id = rowData.id;
									// 发送AJAX请求，修改ID对应数据状态
									$
											.get(
													"/employee/startUse.do?id="
															+ id,
													function(data) {
														if (data.success) {
															$.messager
																	.alert(
																			"温馨提示",
																			data.message,
																			"info",
																			function() {
																				employeeGrid
																						.datagrid("reload");
																			});
														}
													}, "json");
								}
							});
		},
		del : function() {
			console.debug('del:function!');
			// 获取选中行
			var rowData = employeeGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除的员工！！！", "info");
				return;
			}
			// 确认删除
			$.messager
					.confirm(
							"温馨提示",
							"确认要删除【" + rowData.realName + "】员工?",
							function(yes) {
								if (yes) {
									// 获取数据的唯一标识
									var id = rowData.id;
									// 发送AJAX请求，修改ID对应数据状态
									$
											.get(
													"/employee/delete.do?id="
															+ id,
													function(data) {
														if (data.success) {
															$.messager
																	.alert(
																			"温馨提示",
																			data.message,
																			"info",
																			function() {
																				employeeGrid
																						.datagrid("reload");
																			});
														} else {
															$.messager
																	.alert(
																			"温馨提示",
																			"该员工有关联信息，无法删除",
																			"warning",
																			function() {
																				employeeGrid
																						.datagrid("reload");
																			});
														}
													}, "json");
								}
							});
		},
		refresh : function() {
			console.debug('refresh:function!');
			employeeGrid.datagrid("reload", {});
		},
		save : function() {
			console.debug('save:function!');
			employeeForm.form("submit", {
				url : "/employee/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法

					// 获取roleArr字段的值（应该是个array）
					console.debug($("#roleArr").combobox("getValue")); // 3
					// console.debug($("#roleArr").combobox("getValues"));//
					// [3,4,5]
					var roleArr = $("#roleArr").combobox("getValues");
					// 遍历数组
					for (var i = 0; i < roleArr.length; i++) {
						var roleId = roleArr[i];
						// 添加指定格式的参数,格式 roles[index].id=value
						param["roles[" + i + "].id"] = roleId;
					}
					return employeeForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						employeeDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									employeeGrid.datagrid("reload");
								});
					} else {
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		cancel : function() {
			console.debug('cancel:function!');
			// 关闭对话框
			employeeDlg.dialog("close");
		},
		doSearch : function() {// 过滤查询
			console.debug('doSearch:function!');
			/*
			 * console.debug("serializeArray:",employeeSearchForm.serializeArray());
			 * console.debug("serialize:",employeeSearchForm.serialize());
			 * console.debug("serializeJson:",employeeSearchForm.serializeJson());
			 */
			var paramObj = employeeSearchForm.serializeJson();
			// 查询
			employeeGrid.datagrid("load", paramObj);
		},
		// 打开高级查询对话框
		openAdvanceSearch : function() {
			employeeAdvanceSearchDlg.form("clear");
			// 弹出之前先修改弹出框的title
			employeeAdvanceSearchDlg.dialog('setTitle', "查询对话框");
			// 这个是把添加窗口弹出来
			employeeAdvanceSearchDlg.dialog('open');
		},
		// 高级查询
		doAdvanceSearch : function() {
			var json = employeeAdvanceSearchForm.serializeJson();
			employeeGrid.datagrid('load', json);
		},
		// 清空高级查询
		clearAdvanceSearchForm : function() {
			// 清空表单
			employeeAdvanceSearchForm.form("clear");
		},
		// 取消高级查询
		cancelAdvanceSearchDlg : function() {
			employeeAdvanceSearchDlg.dialog("close");
		}
	}
	// 5、对页面所有按钮，进行一次统一监听
	$("a[data-cmd]").on("click", function() {
		// 获取命令信息
		var cmd = $(this).data("cmd");
		if (cmd) {
			// 根据cmd命令值，调用方法
			cmdObj[cmd]();
		}
	});
});

function statusFormatter(v, r, i) {
	switch (v) {
	case -1:
		return "<font color='red'>离职</font>";
		break;

	default:
		return "<font color='green'>在职</font>";
		break;
	}
}