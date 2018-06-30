$(function() {
	// 1. 声明页面中可能会用到组件
	var permissionAdvanceForm, permissionDatagrid, permissionSearchFrom, permissionDlg, permissionForm, permissionAdvanceDlg;
	// 2. 缓存页面组件
	// 数据表格
	permissionDatagrid = $("#permissionDatagrid");
	// 简单查询表单
	permissionSearchFrom = $("#permissionSearchFrom");
	// 添加/编辑对话框
	permissionDlg = $("#permissionDlg");
	// 添加/编辑表单
	permissionForm = $("#permissionForm");
	// 高级查询对话框
	permissionAdvanceDlg = $("#permissionAdvanceDlg");
	// 高级查询表单
	permissionAdvanceForm = $("#permissionAdvanceForm ");
	// 3. 初始化组件
/*	// combogrid 就是combo +grid 可以通过这个combogrid得到一个grid
	var gridAddEdit = $('#AddEditCombogrid').combogrid('grid');
	// 然后可以通过grid获取到一个pagination这个分页对象
	var gridAddEdit = gridAddEdit.datagrid("getPager");
	// 再对这个分页对象的属性进行重新绑定 重新设置
	gridAddEdit.pagination({
		"showRefresh" : false,
		"showPageList" : true,
		"displayMsg" : ""
	});
*/	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		// 打开添加/编辑对话框
		create : function() {
			// 在添加窗口弹出之前先把表单清空
			permissionForm.form("clear");
			// 弹出之前先修改弹出框的title
			permissionDlg.dialog('setTitle', "添加对话框");
			// 这个是把添加窗口弹出来
			permissionDlg.dialog('open');
			$("#defaultRadio").prop("checked", true);
		},
		// 修改方法
		edit : function() {
			// 获取选中行
			var rowData = permissionDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要编辑的权限！！！", "info");
				return;
			}
			// 清空表单
			permissionForm.form("clear");
			// 设置标题
			permissionDlg.dialog("setTitle", "编辑对话框");

			// 特殊数据处理
			if (rowData.menu) {
				rowData["menu.id"] = rowData.menu.id;
			}

			/*
			 * 下面的方法是easyUi 里面 把拿到当前行的json数据再依次的添加给表单元素中 原理是这样的
			 * 它是先拿到表单里面的所有input元素，然后再根据能么去判断是哪一个表单元素，
			 * 因为我们每个input里面的name是和json里面的数据的key是对应上的，那么它就只需要把name属性和key相对应的传入就可以了
			 */
			permissionForm.form('load', rowData);
			// 这个是把修改窗口弹出来
			permissionDlg.dialog('open');
		},
		// 删除方法
		del : function() {
			var row = permissionDatagrid.datagrid("getSelected");
			if (!row) {
				// 这个第一个参数是：弹出的模态框的标题 第二参数： 弹出的内容 第三个参数 是添加一个图标
				$.messager.alert("提示信息",
						"<h3 style= 'color:red;'>请您先选择一条数据再删除</h3>", 'info');
			}
			// 弹出一个删除确认框
			$.messager.confirm('删除确认框', '您确定要删除这条权限吗?', function(r) {
				if (r) {
					$.get("/permission/delete.do", {
						id : row.id
					}, function(data) {
						if (data.success) {
							$.messager.alert("温馨提示", data.message,
									"info", function() {
								permissionDatagrid.datagrid("reload");
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
		// 刷新
		refresh : function() {
			permissionDatagrid.datagrid("reload", {});
		},
		// 通过查询条件重新加载datagrid的数据 ，那么加载的地址就是url="/permission/list.do"
		doSearch : function() {
			var paramObj = permissionSearchFrom.serializeJson();
			permissionDatagrid.datagrid('load', paramObj);
		},
		// 添加/编辑对话框里的保存方法
		save : function() {
			permissionForm.form("submit", {
				url : "/permission/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return permissionForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						permissionDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									permissionDatagrid.datagrid("reload");
								});
					} else {
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		// 添加/编辑对话框里的保取消方法
		cancel : function() {
			// 关闭对话框
			permissionDlg.dialog("close");
		},
		// 打开高级查询对话框
		openAdvanceSearch : function() {
			permissionAdvanceDlg.form("clear");
			// 弹出之前先修改弹出框的title
			permissionAdvanceDlg.dialog('setTitle', "查询对话框");
			// 这个是把添加窗口弹出来
			permissionAdvanceDlg.dialog('open');
		},
		// 高级查询
		doAdvanceSearch : function() {
			var json = permissionAdvanceForm.serializeJson();
			permissionDatagrid.datagrid('load', json);
		},
		// 取消高级查询
		cancelSearchDlg : function() {
			permissionAdvanceDlg.dialog("close");
		},
		// 清空高级查询
		clearSearchForm : function() {
			// 清空表单
			permissionAdvanceForm.form("clear");
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
	case 1:
		return "<font color='green'>正常</font>";
		break;

	default:
		return "<font color='red'>停用</font>";
		break;
	}
}