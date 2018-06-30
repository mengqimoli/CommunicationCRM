$(function() {
	var cmdObj = {
		getPieByCustomerSource3D : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');   
			$("#pieDialog").dialog('open').dialog('center').dialog('setTitle',
					'3D图');
			$
					.post(
							'/pie/getPieByCustomerSource.do',
							{
								inputTimeStart : inputTimeStart,
								inputTimeEnd : inputTimeEnd
							},
							function(result) {
								var datas = [];
								for (var i = 0; i < result.length; i++) {
									var objs = {};
									objs.name = result[i].status;
									if (result[i].status == 1) {
										objs.name = "网络寻找";
									}
									if (result[i].status == 2) {
										objs.name = "熟人介绍";
									}
									if (result[i].status == 3) {
										objs.name = "客户转交";
									}
									if (result[i].status == 40) {
										objs.name = "广告活动";
									}
									if (result[i].status == 41) {
										objs.name = "资料查阅";
									}
									if (result[i].status == 42) {
										objs.name = "委托助手";
									}
									if (result[i].status == 43) {
										objs.name = "交易会";
									}
									objs.y = result[i].count;
									datas[i] = objs;
								}
								$('#container')
										.highcharts(
												{
													chart : {
														type : 'pie',
														options3d : {
															enabled : true,
															alpha : 45,
															beta : 0
														}
													},
													title : {
														text : '客户来源3D比例图'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															depth : 35,
															dataLabels : {
																enabled : true,
																format : '{point.name}'
															}
														}
													},
													series : [ {
														type : 'pie',
														name : '客户类型占比',
														data : datas
													} ]
												});

							}, 'json')
		},
		getPieByCustomerSource : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');  
			$("#barDialog").dialog('open').dialog('center').dialog('setTitle',
					'柱状图');
			$.post('/pie/getPieByCustomerSource.do', {
				inputTimeStart : inputTimeStart,
				inputTimeEnd : inputTimeEnd
			},function(result) {
				console.debug(result);
				var datasc = [];
				for (var i = 0; i < result.length; i++) {

					if (result[i].status == 1) {
						datasc[0] = "网络寻找";
					}
					if (result[i].status == 2) {
						datasc[1] = "熟人介绍";
					}
					if (result[i].status == 3) {
						datasc[2] = "客户转交";
					}
					if (result[i].status == 40) {
						datasc[3] = "广告活动";
					}
					if (result[i].status == 41) {
						datasc[4] = "资料查阅";
					}
					if (result[i].status == 42) {
						datasc[5] = "委托助手";
					}
					if (result[i].status == 43) {
						datasc[6] = "交易会";
					}
				}
				var datasb = [];
				for (var i = 0; i < result.length; i++) {
					datasb[i] = result[i].count;
				}
				// 路径配置
				require.config({
					paths : {
						echarts : '/resources/echarts/build/dist'
					}
				});

				// 使用
				require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
				], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var myChart = ec.init(document.getElementById('main'));

					var option = {
						tooltip : {
							show : true
						},
						legend : {
							data : [ '客户来源' ]
						},
						xAxis : [ {
							type : 'category',
							data : datasc
						} ],
						yAxis : [ {
							type : 'value'
						} ],
						series : [ {
							"name" : "数量",
							"type" : "bar",
							"data" : datasb
						} ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			})

		},
		getPieByCustomerStatus3D : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');   
			$("#pieDialog").dialog('open').dialog('center').dialog('setTitle',
					'3D图');
			$
					.post(
							'/pie/getPieByCustomerStatus.do',{
								inputTimeStart : inputTimeStart,
								inputTimeEnd : inputTimeEnd
							},
							function(result) {
								var datas = [];
								for (var i = 0; i < result.length; i++) {
									var objs = {};
									objs.name = result[i].status;
									if (result[i].status == 22) {
										objs.name = "意向客户";
									}
									if (result[i].status == 23) {
										objs.name = "定金客户";
									}
									if (result[i].status == 24) {
										objs.name = "合同客户";
									}
									if (result[i].status == 25) {
										objs.name = "保修客户";
									}
									objs.y = result[i].count;
									datas[i] = objs;
								}
								$('#container')
										.highcharts(
												{
													chart : {
														type : 'pie',
														options3d : {
															enabled : true,
															alpha : 45,
															beta : 0
														}
													},
													title : {
														text : '客户状态3D比例图'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															depth : 35,
															dataLabels : {
																enabled : true,
																format : '{point.name}'
															}
														}
													},
													series : [ {
														type : 'pie',
														name : '客户类型占比',
														data : datas
													} ]
												});

							}, 'json')
		},
		getPieByCustomerStatus : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');
			$("#barDialog").dialog('open').dialog('center').dialog('setTitle',
					'柱状图');
			$.post('/pie/getPieByCustomerStatus.do', {
				inputTimeStart : inputTimeStart,
				inputTimeEnd : inputTimeEnd
			},function(result) {
				console.debug(result);
				var datasc = [];
				for (var i = 0; i < result.length; i++) {
					if (result[i].status == 22) {
						datasc[0] = "意向客户";
					}
					if (result[i].status == 23) {
						datasc[1] = "定金客户";
					}
					if (result[i].status == 24) {
						datasc[2] = "合同客户";
					}
					if (result[i].status == 25) {
						datasc[3] = "保修客户";
					}
				}
				var datasb = [];
				for (var i = 0; i < result.length; i++) {
					datasb[i] = result[i].count;
				}
				// 路径配置
				require.config({
					paths : {
						echarts : '/resources/echarts/build/dist'
					}
				});

				// 使用
				require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
				], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var myChart = ec.init(document.getElementById('main'));

					var option = {
						tooltip : {
							show : true
						},
						legend : {
							data : [ '客户状态' ]
						},
						xAxis : [ {
							type : 'category',
							data : datasc
						} ],
						yAxis : [ {
							type : 'value'
						} ],
						series : [ {
							"name" : "数量",
							"type" : "bar",
							"data" : datasb
						} ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			})

		},
		// 弹出饼图
		"chart3d" : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');
			$("#pieDialog").dialog('open').dialog('center').dialog('setTitle',
					'3D图');
			$
					.post('/pie/getPie.do',{
								inputTimeStart : inputTimeStart,
								inputTimeEnd : inputTimeEnd
							},
							function(result) {
								var datas = [];
								for (var i = 0; i < result.length; i++) {
									var objs = {};
									objs.name = result[i].status;
									if (result[i].status == 1) {
										objs.name = "普通客户";
									}
									if (result[i].status == 2) {
										objs.name = "中级客户";
									}
									if (result[i].status == 3) {
										objs.name = "高级客户";
									}
									if (result[i].status == 4) {
										objs.name = "VIP客户";
									}
									objs.y = result[i].count;
									datas[i] = objs;
								}
								$('#container')
										.highcharts(
												{
													chart : {
														type : 'pie',
														options3d : {
															enabled : true,
															alpha : 45,
															beta : 0
														}
													},
													title : {
														text : '客户服务级别3D比例图'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															depth : 35,
															dataLabels : {
																enabled : true,
																format : '{point.name}'
															}
														}
													},
													series : [ {
														type : 'pie',
														name : '客户类型占比',
														data : datas
													} ]
												});

							}, 'json')
		},
		// 柱状图
		"echart" : function() {
			var inputTimeStart = $('#inputTimeStart').datebox('getValue');   
			var inputTimeEnd = $('#inputTimeEnd').datebox('getValue');
			$("#barDialog").dialog('open').dialog('center').dialog('setTitle',
					'柱状图');
			$.post('/pie/getPie.do', {
				inputTimeStart : inputTimeStart,
				inputTimeEnd : inputTimeEnd
			}, function(result) {
				console.debug(result);
				var datasc = [];
				for (var i = 0; i < result.length; i++) {
					if (result[i].status == 1) {
						datasc[0] = "普通客户";
					}
					if (result[i].status == 2) {
						datasc[1] = "中级客户";
					}
					if (result[i].status == 3) {
						datasc[2] = "高级客户";
					}
					if (result[i].status == 4) {
						datasc[3] = "VIP客户";
					}
				}
				var datasb = [];
				for (var i = 0; i < result.length; i++) {
					datasb[i] = result[i].count;
				}
				// 路径配置
				require.config({
					paths : {
						echarts : '/resources/echarts/build/dist'
					}
				});

				// 使用
				require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
				], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var myChart = ec.init(document.getElementById('main'));

					var option = {
						tooltip : {
							show : true
						},
						legend : {
							data : [ '客户服务级别' ]
						},
						xAxis : [ {
							type : 'category',
							data : datasc
						} ],
						yAxis : [ {
							type : 'value'
						} ],
						series : [ {
							"name" : "数量",
							"type" : "bar",
							"data" : datasb
						} ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			})

		},
	}

	$("a[data-cmd]").click(function() {

		var dataCmd = $(this).data("cmd");
		cmdObj[dataCmd]();
	});
});
