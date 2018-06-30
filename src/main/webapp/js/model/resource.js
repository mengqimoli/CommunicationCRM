//---页面加载完毕后---
$(function() {
	// 1. 声明页面中可能会用到组件
	var resourceGrid, resourceDlg, resourceForm, resourceTree;
	// 2. 缓存页面组件
	resourceGrid = $("#resourceGrid");
	resourceDlg = $("#resourceDlg");
	resourceForm = $("#resourceForm");
	resourceTree = $("#resourceTree");
	// 3. 初始化组件
	resourceTree.tree({
		url : '/resource/getControllerTree.do',
		method : 'get',
		animate : true,
		lines : true,
		onClick : function(node) {
			// 获取点击节点的ID
			var resource = node.id;
			if (!node.attributes) {
				// resourceGrid.datagrid("load",{"query.parentId":parentId});
				resourceGrid.datagrid("load", {
					"controller" : resource
				});
				resourceGrid.resourceName = resource;
			}
		},
		onDblClick : function(node) {
			resourceTree.tree("toggle", node.target);
		}
	});

	// 4. 定义一个事件处理对象,把事件相关的逻辑都封装到一个对象上(命令对象)
	var cmdObj = {
		create : function() {
			// console.debug('create:function');
			// reset : 重置为初始值(定义方式:1.input的value属性写死;2.使用form的load方法加载后的属性)
			// clear : 清空
			resourceForm.form("clear");
			resourceDlg.dialog("open");
		},
		edit : function() {
			// console.debug('edit:function');
			// 获取行选中数据
			var rowData = resourceGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的资源！！！", "info");
				return;
			}
			// 重置表单
			resourceForm.form("reset");
			// 打开对话框
			resourceDlg.dialog("open");

			// 数据转换/封装
			if (rowData.manager)
				rowData['manager.id'] = rowData.manager.id;
			if (rowData.parent)
				rowData['parent.id'] = rowData.parent.id;

			// 加载数据
			/*
			 * form的load方法运行的机制:根据表单中元素的name属性,去json对象中,取值. 例如: <input
			 * name="resource.name"> , {name:"zs",resource:{name:""}} >>
			 * obj["resource.name"] 针对上面特殊情况,{name:"zs",resource:{name:""}}>>
			 * {name:"zs",resource:{name:"ls"},"resource.name":"zs"}
			 */
			resourceForm.form("load", rowData);

		},
		del : function() {
			console.debug('del:function');

		},
		refresh : function() {
			resourceGrid.datagrid("reload");

		},
		save : function() {
			resourceForm.form("submit", {
				url : "/resource/save.do",
				success : function(data) {
					// 把响应结果转换成JSON对象
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						resourceDlg.dialog("close");
						// 提示,刷新
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 刷新表格数据
							resourceGrid.datagrid("reload");
							// 刷新树的数据
							resourceTree.tree("reload");
							// 刷新comboTree
							parentComboTree.combotree("reload");
						});
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 根据错误码,定位到单元格
						});
					}
				}
			});
		},
		cancel : function() {
			resourceDlg.dialog("close");
		},
		importMoudleResource : function() {
			// 获取行选中数据
			var treeData = resourceTree.tree("getSelected");
			// 判断
			if (!treeData) {
				$.messager.alert("温馨提示", "请先选中左侧一个控制器(controller)！！！", "info");
				return;
			}
			$.post("/resource/importMoudleResource.do", {
				controller : resourceGrid.resourceName
			}, function() {
				// 刷新表格数据
				resourceGrid.datagrid("reload");
			});
		},
		importPermission : function() {
			// 获取行选中数据
			var treeData = resourceTree.tree("getSelected");
			var rowData = resourceGrid.datagrid("getRows");
			if (rowData.length < 1) {
				$.messager.alert("温馨提示", "请先导入资源！！！", "info");
				return;
			}
			// 判断
			if (!treeData) {
				$.messager.alert("温馨提示", "请先选中左侧一个控制器(controller)！！！", "info");
				return;
			}
			$.post("/resource/importPermission.do", {
				controller : resourceGrid.resourceName
			}, function(data) {
				if (data.success) {
					$.messager.alert("温馨提示", data.message, "info");
				} else {
					$.messager.alert("温馨提示", data.message, "info");
				}
			});
		},

	};
	// 5. 对页面所有按钮,"统一"做监听
	$("a[data-cmd]").bind("click", function() {
		// 获取cmd内容
		var cmd = $(this).data("cmd");
		// 判断操作
		if (cmd) {
			cmdObj[cmd]();
		}
	});
});
