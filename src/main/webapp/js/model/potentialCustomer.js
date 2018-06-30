$(function() {
	// 1、声明需要缓存的对象
	var potentialCustomerDialog, potentialCustomerForm, potentialCustomerDatagrid;
	// 2、做缓存
	// 保存/编辑对话框
	potentialCustomerDialog = $('#potentialCustomerDialog');
	// 保存/编辑表单
	potentialCustomerForm = $('#potentialCustomerForm');
	// 数据显示
	potentialCustomerDatagrid = $('#potentialCustomerDatagrid');
	// Excel表导入对话框
	potentialCustomerUploadDlg = $("#potentialCustomerUploadDlg")
	// Excel表导入表单
	potentialCustomerUploadForm = $("#potentialCustomerUploadForm")
	// 3、初始化组件
	$("#potentialCustomerExcle")
			.filebox(
					{
						// required: true,//是否必填
						// multiple: true,//是否多选（默认false,单选）
						// buttonAlign: 'left',//按钮出现的位置(默认right)
						width : '100%',// 宽度
						prompt : '潜在客户Excel.....',// 提示信息
						buttonText : '选择文件',// 按钮文字
						validType : [ 'fileSize[1024,"kb"]' ],// 文件大小限制（好像没卵用）
						accept : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel',// 限制文件类型（也好像没卵用）
						onChange : function() {
							$("#btnUpload").hide();// 隐藏上传按钮
							// 判断选择的文件是不是Excel
							var temp = $("#potentialCustomerExcle").filebox(
									'getValue');
							if (temp != '') {
								var arr = temp.split('.');
								if (arr.length > 1) {
									var expanded_name = arr[arr.length - 1]
											.toLowerCase();// 取得文件扩展名
									if (expanded_name == "xls"
											|| expanded_name == "xlsx") {// 确实是Excel文件
										// 判断文件大小
										var f = document
												.getElementById("filebox_file_id_2").files;// 用F12查看easyui生成的file控件的id
										var size = f[0].size; // 单位byte
										var size = (size / 1024).toFixed(2);// 单位kb
										var maxFileSize = 1024;// 1mb=1024kb
										if (size > maxFileSize) {
											$("#btnUpload").hide();// 隐藏上传按钮
											alert("文件大小：" + size + "KB,超过最大限制："
													+ maxFileSize + "KB");
											$("#potentialCustomerExcle")
													.filebox('reset');
											return;
										} else {
											$("#btnUpload").show();// 显示上传按钮准备上传
										}
									} else {// 选择了其他类型的文件
										$("#btnUpload").hide();// 隐藏上传按钮
										alert("请选择Excel文件!");
										$("#potentialCustomerExcle").filebox(
												'reset');
										return;
									}
								} else {// 选择了无扩展名的文件
									$("#btnUpload").hide();
									alert("请选择Excel文件!!");
									$("#potentialCustomerExcle").filebox(
											'reset');
									return;
								}
							}
						},
					});
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		upload : function() {
			potentialCustomerUploadForm.form('submit', {
				url : '/potentialCustomer/imports.do',
				success : function(data) {
					// 转换消息
					data = $.parseJSON(data);
					if (data.success) {
						// 关闭对话框
						potentialCustomerUploadDlg.dialog("close");
						// 提示
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									potentialCustomerDatagrid
											.datagrid("reload");
								});
					} else {
						$.messager.alert("错误提示信息", "<h3 style= 'color:red;'>"
								+ data.message + "</h3>", 'error')
					}
				}
			});
		},
		openCotentialCustomerUpload : function() {
			// 清空表单
			potentialCustomerUploadForm.form("clear");
			// 修改对话框的名称
			potentialCustomerUploadDlg.dialog("setTitle", "Excel导入对话框");
			// 打开对话框
			potentialCustomerUploadDlg.dialog("open");
			$("#btnUpload").hide();// 隐藏上传按钮
		},
		create : function() {
			console.debug('create!');
			// 清空表单
			potentialCustomerForm.form("clear");
			// 修改对话框的名称
			potentialCustomerDialog.dialog("setTitle", "添加对话框");
			// 打开对话框
			potentialCustomerDialog.dialog("open");
			$("#defaultRadio").prop("checked", true);
		},
		edit : function() {
			console.debug('edit:function!');
			// 获取选中行
			var rowData = potentialCustomerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要编辑的潜在客户！！！", "info");
				return;
			}
			// 清空表单
			potentialCustomerForm.form("clear");
			// 设置标题
			potentialCustomerDialog.dialog("setTitle", "编辑对话框");

			// 特殊数据处理
			if (rowData.customerSource) {
				rowData["customerSource.id"] = rowData.customerSource.id;
			}
			// 回填数据
			potentialCustomerForm.form("load", rowData);

			// 打开对话框
			potentialCustomerDialog.dialog("open");
		},
		del : function() {
			console.debug('del:function!');
			// 获取选中行
			var rowData = potentialCustomerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除的潜在客户！！！", "info");
				return;
			}
			// 确认删除
			$.messager
					.confirm(
							"温馨提示",
							"确认要删除【" + rowData.name + "】潜在客户?",
							function(yes) {
								if (yes) {
									// 获取数据的唯一标识
									var id = rowData.id;
									// 发送AJAX请求，修改ID对应数据状态
									$
											.get(
													"/potentialCustomer/delete.do?id="
															+ id,
													function(data) {
														if (data.success) {
															$.messager
																	.alert(
																			"温馨提示",
																			data.message,
																			"info",
																			function() {
																				potentialCustomerDatagrid
																						.datagrid("reload");
																			});
														}
													}, "json");
								}
							});
		},
		refresh : function() {
			console.debug('refresh:function!');
			potentialCustomerDatagrid.datagrid("reload", {});
		},
		save : function() {
			potentialCustomerForm.form("submit", {
				url : "/potentialCustomer/save.do",
				onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
					return potentialCustomerForm.form("validate");
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success) {
						potentialCustomerDialog.dialog("close");
						$.messager.alert("温馨提示", data.message, "info",
								function() {
									// 刷新
									potentialCustomerDatagrid
											.datagrid("reload");
								});
					} else {
						$.messager.alert("温馨提示", "操作失败！！！", "info")
					}
				}
			})
		},
		cancel : function() {
			console.debug('cancel:function!');
			// 关闭对话框
			potentialCustomerDialog.dialog("close");
		},
		developCustomer : function() {
			// 获取选中行
			var rowData = potentialCustomerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要开发的潜在客户！！！", "info");
				return;
			}
			// 清空表单
			$("#developCustomerForm").form("clear");
			// 设置标题
			$("#developCustomerDialog").dialog("setTitle", "开发潜在客户对话框");

			$('#poteId').combogrid('setValue', rowData.id);

			// 回填数据
			$("#developCustomerForm").form("load", rowData);
			// 打开对话框
			$("#developCustomerDialog").dialog("open");
		},
		doVevelop : function() {
			alert(0);
			$("#developCustomerForm").form(
					"submit",
					{
						url : "/potentialCustomer/developCustomer.do",
						onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
							return $("#developCustomerForm").form("validate");
						},
						success : function(data) {
							data = $.parseJSON(data);
							alert(data.success)
							if (data.success) {
								$("#developCustomerDialog").dialog("close");
								$.messager.alert("温馨提示", "潜在客户开发成功，请继续保持跟进！！！",
										"info", function() {
											// 刷新
											potentialCustomerDatagrid
													.datagrid("reload");
										});
							} else {
								$.messager.alert("温馨提示", "潜在客户开发失败！！！", "info")
							}
						}
					})
		},
		cancleVevelop : function() {
			// 关闭对话框
			$("#developCustomerDialog").dialog("close");
		},
		designateCustomer : function() {
			// 获取选中行
			var rowData = potentialCustomerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要指派的潜在客户！！！", "info");
				return;
			}
			// 清空表单
			$("#designateCustomerForm").form("clear");
			// 设置标题
			$("#designateCustomerDialog").dialog("setTitle", "潜在客户指派对话框");
			// 回填数据
			$("#designateCustomerForm").form("load", rowData);
			// 打开对话框
			$("#designateCustomerDialog").dialog("open");
		},
		doDesignate : function() {
			var designatePoteId = $("#designatePoteId");
			$("#designateCustomerForm")
					.form(
							"submit",
							{
								url : "/potentialCustomer/designateCustomer.do",
								onSubmit : function(param) {// 如果在这里写出，会覆盖原始的表单验证方法
									return $("#designateCustomerForm").form(
											"validate");
								},
								success : function(data) {
									data = $.parseJSON(data);
									if (data.success) {
										$("#designateCustomerDialog").dialog(
												"close");
										$.messager
												.alert(
														"温馨提示",
														"潜在客户指派成功，请通知相关市场专员保持跟进！！！",
														"info",
														function() {
															// 刷新
															potentialCustomerDatagrid
																	.datagrid("reload");
														});
									} else {
										$.messager.alert("温馨提示", "潜在客户指派失败！！！",
												"info")
									}
								}
							})
		},
		cancleDesignate : function() {
			// 关闭对话框
			$("#designateCustomerDialog").dialog("close");
		},
		// 转为正式客户
		changeToCustomer : function() {
			var row = potentialCustomerDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要将(' + row.name
						+ ')提交为正式客户？', function(r) {
					if (r) {
						$("#changeDialog").dialog('open').dialog('center')
								.dialog('setTitle', '潜在客户转正对话框');
						$("#changeForm").form('clear');
						
						$("#changeForm").form('load', row);
						$('#contacts').textbox({    
							value:row.linkMan  
						});
						$('#tel').textbox({    
							value:row.linkManTel 
						});
						$('#sourceCustomId').combogrid('setValue', row.customerSource.id);
						$('#sellerEmpId').combogrid('setValue', row.seller.id);

					}
				});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要转为正式客户的数据！！！', 'info');
			}
		},
		// 潜在客户转正式客户
		"changeSave" : function() {
			$("#changeForm")
					.form(
							'submit',
							{
								url : '/potentialCustomer/changeToCustomer.do',
								onSubmit : function() {
									return $("#changeForm").form("validate");
								},
								success : function(result) {
									var $result = $.parseJSON(result);
									if (!$result.success) {
										$.messager
												.alert('温馨提示', '潜在客户转正失败：'
														+ $result.massage + '',
														'error');
									} else {
										$("#changeDialog").dialog('close');
										$.messager.alert('温馨提示', '升级为正式意向客户成功',
												'info');
										potentialCustomerDatagrid
												.datagrid('reload'); 
									}
								}
							});
		},
		// 转正式客户面板取消
		"changeCancle" : function() {
			$("#changeDialog").dialog('close');
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
		return "<font color='red'>离开</font>";
		break;

	default:
		return "<font color='green'>正常</font>";
		break;
	}
}
function sellerFormatter(v, r, i) {
	return v ? v.name || v.username || v.realName || v.text || v.sn: '无' ;
}