//在页面加载完毕后
$(function() {
	// 1、声明需要缓存的对象
	var deptSimpleSearchForm,deptGrid, deptDlg, deptForm, deptSearchDlg, deptSearchForm;
	// 2、做缓存
	deptGrid = $("#department-datagrid");
	deptDlg = $("#department-dlg");
	deptForm = $("#department-form");
	deptSearchDlg = $("#department-search-dlg");
	deptSearchForm = $("#department-search-form");
	deptSimpleSearchForm = $("#deptSimpleSearchForm");
	// 3、初始化组件
	// combogrid 就是combo +grid 可以通过这个combogrid得到一个grid
	var grid = $('#department-cg').combogrid('grid');
	// 然后可以通过grid获取到一个pagination这个分页对象
	var pager = grid.datagrid("getPager");
	// 再对这个分页对象的属性进行重新绑定 重新设置
	pager.pagination({
		"showRefresh" : false,
		"showPageList" : false,
		"displayMsg" : ""
	})
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		create : function() {
			console.debug('create!');
			// 清空表单
			deptForm.form("clear");
			// 修改对话框的名称
			deptDlg.dialog("setTitle", "添加对话框");
			// 打开对话框
			deptDlg.dialog("open");
		},
		edit : function() {
			console.debug('edit:function!');
			// 获取选中行
			var rowData = deptGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要编辑的部门！！", "info");
				return;
			}
			// 清空表单
			deptForm.form("clear");
			// 设置标题
			deptDlg.dialog("setTitle", "编辑对话框");

			// 特殊数据处理
			if (rowData.manager) {
				rowData["manager.id"] = rowData.manager.id;
			}
			if (rowData.parent) {
				rowData["parent.id"] = rowData.parent.id;
			}
			// 回填数据
			deptForm.form("load", rowData);

			// 打开对话框
			deptDlg.dialog("open");
		},
		stopUse : function() {
			console.debug('stopUse:function!');
			// 获取选中行
			var rowData = deptGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要停用的部门！！！", "info");
				return;
			}
			if (rowData.status == -1) {
				$.messager.alert("温馨提示", "该部门已经停用，不能再次停用！！！", "warning");
				return;
			}
			// 确认停用
			$.messager.confirm("温馨提示", "确认要停用【" + rowData.name + "】部门?",
					function(yes) {
						if (yes) {
							// 获取数据的唯一标识
							var id = rowData.id;
							// 发送AJAX请求，修改ID对应数据状态
							$.get("/department/stopUse.do?id=" + id, function(
									data) {
								if (data.success) {
									$.messager.alert("温馨提示", data.message,
											"info", function() {
												deptGrid.datagrid("reload");
											});
								}
							}, "json");
						}
					});
		},
		startUse : function() {
			console.debug('startUse:function!');
			// 获取选中行
			var rowData = deptGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要启用的部门！！！", "info");
				return;
			}
			if (rowData.status == 1) {
				$.messager.alert("温馨提示", "该部门状态正常，无需启用！！！", "warning");
				return;
			}
			// 确认启用
			$.messager.confirm("温馨提示", "确认要启用【" + rowData.name + "】部门?",
					function(yes) {
						if (yes) {
							// 获取数据的唯一标识
							var id = rowData.id;
							// 发送AJAX请求，修改ID对应数据状态
							$.get("/department/startUse.do?id=" + id, function(
									data) {
								if (data.success) {
									$.messager.alert("温馨提示", data.message,
											"info", function() {
												deptGrid.datagrid("reload");
											});
								}
							}, "json");
						}
					});
		},
		del : function() {
			console.debug('del:function!');
			// 获取选中行
			var rowData = deptGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除的部门！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm("温馨提示", "确认要删除【" + rowData.name + "】部门?",
					function(yes) {
						if (yes) {
							// 获取数据的唯一标识
							var id = rowData.id;
							// 发送AJAX请求，修改ID对应数据状态
							$.get("/department/delete.do?id=" + id, function(
									data) {
								if (data.success) {
									$.messager.alert("温馨提示", data.message,
											"info", function() {
												deptGrid.datagrid("reload");
											});
								}
							}, "json");
						}
					});
		},
		refresh : function() {
			console.debug('refresh:function!');
			deptGrid.datagrid("reload", {});
		},
		save : function() {
			console.debug('save:function!');
			deptForm.form("submit", {
				url : "/department/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return deptForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						deptDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									deptGrid.datagrid("reload");
								});
					}
				}
			});
		},
		doSearch : function() {
			var paramObj = deptSimpleSearchForm.serializeJson();
			// 查询
			deptGrid.datagrid("load", paramObj);
		},
		cancel : function() {
			console.debug('cancel:function!');
			// 关闭对话框
			deptDlg.dialog("close");
		},
		openSearchDlg : function() { // 打开搜索对话框
			console.debug('openSearchDlg:function!');
			// 清空表单
			// 设置标题
			deptSearchDlg.dialog("setTitle", "查询对话框");
			// 打开搜索对话框
			deptSearchDlg.dialog("open");
		},
		doAdvanceSearch : function() {// 过滤查询
			console.debug('doSearch:function!');
			// 关闭查询对话框
			deptSearchDlg.dialog("close");
			/*
			 * console.debug("serializeArray:",deptSearchForm.serializeArray());
			 * console.debug("serialize:",deptSearchForm.serialize());
			 * console.debug("serializeJson:",deptSearchForm.serializeJson());
			 */
			var paramObj = deptSearchForm.serializeJson();
			// 查询
			deptGrid.datagrid("load", paramObj);
		},
		cancelSearchDlg : function() {
			console.debug('cancelSearchDlg:function!');
			deptSearchDlg.dialog("close");
		},
		clearSearchForm : function() {
			// 清空表单
			deptSearchForm.form("clear");
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
		return "<font color='red'>停用</font>";
		break;

	default:
		return "<font color='green'>正常</font>";
		break;
	}
}