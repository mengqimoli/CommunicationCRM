$(function() {
	// 1. 声明页面中可能会用到组件
	var systemMenuDatagrid, systemMenuSearchFrom, systemMenuAddEditDlg, systemMenuAddEditForm;
	// 2. 缓存页面组件
	// 数据表格
	systemMenuDatagrid = $("#systemMenuDatagrid");
	// 简单查询表单
	systemMenuSearchFrom = $("#systemMenuSearchFrom");
	// 添加/编辑对话框
	systemMenuAddEditDlg = $("#systemMenuAddEditDlg");
	// 添加/编辑表单
	systemMenuAddEditForm = $("#systemMenuAddEditForm");
	// 3. 初始化组件
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		// 打开添加/编辑对话框
		create : function() {
			// 在添加窗口弹出之前先把表单清空
			systemMenuAddEditForm.form("clear");
			// 弹出之前先修改弹出框的title
			systemMenuAddEditDlg.dialog('setTitle', "添加对话框");
			// 这个是把添加窗口弹出来
			systemMenuAddEditDlg.dialog('open');
		},
		// 修改方法
		edit : function() {
			// 获取选中行
			var rowData = systemMenuDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要编辑的系统菜单！！！", "info");
				return;
			}
			// 清空表单
			systemMenuAddEditForm.form("clear");
			// 设置标题
			systemMenuAddEditDlg.dialog("setTitle", "编辑对话框");
			
			// 特殊数据处理
			if (rowData.parent) {
				rowData["parent.id"] = rowData.parent.id;
			}
			/*
			 * 下面的方法是easyUi 里面 把拿到当前行的json数据再依次的添加给表单元素中 原理是这样的
			 * 它是先拿到表单里面的所有input元素，然后再根据能么去判断是哪一个表单元素，
			 * 因为我们每个input里面的name是和json里面的数据的key是对应上的，那么它就只需要把name属性和key相对应的传入就可以了
			 */
			//回显
			systemMenuAddEditForm.form('load', rowData);
			// 打开对话框
			systemMenuAddEditDlg.dialog('open');
		},
		// 删除方法
		del : function() {
			var row = systemMenuDatagrid.datagrid("getSelected");
			
			if (!row) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除的系统菜单！！！", "info");
				return;
			}
			
			// 弹出一个删除确认框
			$.messager.confirm('删除确认框', '您确定要删除【' + row.name + '】这个系统菜单吗?', function(r) {
				if (r) {
					$.get("/systemMenu/delete.do", {
						id : row.id
					}, function(data) {
						if (data.success) {
							systemMenuDatagrid.datagrid("reload");
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
			systemMenuDatagrid.datagrid("reload", {});
		},
		// 通过查询条件重新加载datagrid的数据 ，那么加载的地址就是url="/systemMenu/list.do"
		doSearch : function() {
			var paramObj = systemMenuSearchFrom.serializeJson();
			systemMenuDatagrid.datagrid('load', paramObj);
		},
		// 添加/编辑对话框里的保存方法
		save : function() {
			systemMenuAddEditForm.form("submit", {
				url : "/systemMenu/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return systemMenuAddEditForm.form("validate");
				},
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						systemMenuAddEditDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									systemMenuDatagrid.datagrid("reload");
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
			systemMenuAddEditDlg.dialog("close");
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
