// 扩展jquery组件，增加一个可以直接把form表单字段信息，封装到一个json
$.fn.serializeJson = function() {
	var paramArr = $(this).serializeArray();

	// 把表单数据封装成json对象结构
	var paramObj = {};
	$.each(paramArr, function(index, obj) {
		// console.debug(arguments);
		paramObj[obj.name] = obj.value;
	});

	return paramObj;
}

// 公共的列的格式化函数
// || ：js中，返回从左到右的第一个有效值
function objectFormatter(v, r, i) {
	return v ? v.name || v.username || v.realName || v.text || v.sn : '';
}
function poolFormatter(v, r, i) {
	return v ? v.name || v.username || v.realName || v.text : '已放入资源池';
}
// 姓别
function sexFormatter(v, r, i) {
	return v == true ? "男" : "女";
}
// 是否放入资源池
function poolFormatter(v, r, i) {
	return v == true ? "<fon color='red'>已放入</font>"
			: "<fon color='green'>没放入</font>";
}
// 服务级别
function customerstatusFormatter(v, r, i) {
	// 姓别
	if (v == 1) {
		return '<a   class="easyui-linkbutton c5"  style="width:90px">普通客户</a>';
	}
	if (v == 2) {
		return '<a   class="easyui-linkbutton c3"  style="width:90px">中级客户</a>';
	}
	if (v == 3) {
		return '<a   class="easyui-linkbutton c8"  style="width:90px">高级客户</a>';
	}
	if (v == 4) {
		return '<a   class="easyui-linkbutton c1"  style="width:90px">VIP客户</a>';
	}
}
// 开发结果
function resultFormatter(v, r, i) {
	if (v == 1) {
		return '<a   class="easyui-linkbutton c1"  style="width:90px">开发成功</a>';
	} else if (v == -1) {
		return '<a   class="easyui-linkbutton c5"  style="width:90px">开发失败</a>';
	} else {
		return '<a   class="easyui-linkbutton c8"  style="width:90px">正在开发</a>';
	}
}
function arrayFormatter(val) {
	var per = "";
	$.each(val, function(index, ele) {
		if (index == 0) {
			per = per + ele.name;
		} else {
			per = per + "，" + ele.name;
		}
	})
	return per;
}

function genderFormatter(v, r, i) {
	switch (v) {
	case 0:
		return '男';
		break;
	case 1:
		return '女';
		break;

	default:
		return '未知';
		break;
	}
}

function goLogin() {
	location.href = "/goLogin.do";
}
$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					/*
					 * isNotExist:{//验证员工管理界面，工号是否重复 validator:function(value){
					 * var a=true; var doctorId=$('#doctor_id').val(); $.ajax({
					 * type:"post", async:false,
					 * url:_basePath+'/base/bas/queryDoctorWorkNo.html',
					 * data:"workno="+value+"&doctor_id="+doctorId,
					 * dataType:"text", success:function(data){
					 * if(data=="true"){ a=false; } } }); return a; },
					 * message:'该工号已经存在' },
					 */
					chinese : {
						validator : function(value, param) {
							return /^[\u0391-\uFFE5]+$/.test(value);
						},
						message : '请输入汉字'
					},
					/*
					 * selectValid : { validator : function(value, param) {
					 * if(value == param[0]){ return false; }else{ return true ; } },
					 * message : '请选择' },
					 */
					integer : {
						validator : function(value, param) {
							return /^[+]?[0-9]+\d*$/i.test(value);
						},
						message : '请输入整数'
					},
					email : {
						validator : function(value, param) {
							return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
									.test(value);
						},
						message : '请输入有效的电子邮件账号(例：abc@163.com)'
					},
					unnormal : {
						validator : function(value, param) {
							return /.+/i.test(value);
						},
						message : '输入值不能为空和包含其他非法字符'
					},
					englishornum : {
						validator : function(value, param) {
							return /^[a-zA-Z0-9_:]{1,}$/.test(value);
						},
						message : '请输入英文、数字、下划线或者:'
					},
					englishornumorxhxOrxg : {
						validator : function(value, param) {
							return /^[a-zA-Z0-9_/]{1,}$/.test(value);
						},
						message : '只能输入英文、数字、下划线或者斜杠'
					},
					username : {
						validator : function(value, param) {
							return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
						},
						message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
					},
					english : {
						validator : function(value, param) {
							return /^[A-Za-z]+$/i.test(value);
						},
						message : '只能输入英文'
					},
					zip : {
						validator : function(value, param) {
							return /^[1-9]\d{5}$/.test(value);
						},
						message : '邮政编码不存在'
					},
					qq : {
						validator : function(value, param) {
							return /^[1-9]\d{4,10}$/.test(value);
						},
						message : 'QQ号码不正确'
					},
					mobile : {
						validator : function(value, param) {
							return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
									.test(value);
						},
						message : '格式不正确,请使用下面格式:020-88888888'
					},
					phone : {
						validator : function(value, param) {
							return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/
									.test(value);
						},
						message : '手机号码不正确'
					},
					loginName : {
						validator : function(value, param) {
							return /^[\u0391-\uFFE5\w]+$/.test(value);
						},
						message : '登录名称只允许汉字、英文字母、数字及下划线。'
					},
					hzOrywOrszOrxhx : {
						validator : function(value, param) {
							return /^[\u0391-\uFFE5\w]+$/.test(value);
						},
						message : '名称只允许汉字、英文字母、数字及下划线。'
					},
					safepassword : {
						validator : function(value, param) {
							return safePassword(value);
						},
						message : '密码由字母和数字组成，至少6位'
					},
					equalTo : {
						validator : function(value, param) {
							return value == $(param[0]).val();
						},
						message : '两次输入的字符不一至'
					},
					number : {
						validator : function(value, param) {
							return /^\d+$/.test(value);
						},
						message : '只能输入数字'
					},
					idcard : {
						validator : function(value, param) {
							return idCard(value);
						},
						message : '请输入正确的身份证号码'
					}
				});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
}

var idCard = function(value) {
	if (value.length == 18 && 18 != value.length)
		return false;
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
			9, 10, 5, 8, 4, 2 ], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number
			.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	if (re == null || a.indexOf(re[1]) < 0)
		return false;
	if (re[2].length == 9) {
		number = number.substr(0, 6) + '19' + number.substr(6);
		d = [ '19' + re[4], re[5], re[6] ].join('-');
	} else
		d = [ re[9], re[10], re[11] ].join('-');
	if (!isDateTime.call(d, 'yyyy-MM-dd'))
		return false;
	for (var i = 0; i < 17; i++)
		sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}

var isDateTime = function(format, reObj) {
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format
			.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length)
		return false;
	for (var i = 0; i < len1; i++)
		if (f3[i] != f4[i])
			return false;
	for (var i = 0; i < len; i++)
		o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0)
		return false;
	if (o.yyyy < 100)
		o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM
			&& d.getDate() == o.dd && d.getHours() == o.hh
			&& d.getMinutes() == o.mm && d.getSeconds() == o.ss
			&& d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;
	function s(s1, s2, s3, s4, s5) {
		s4 = s4 || 60, s5 = s5 || 2;
		var reVal = s3;
		if (s1 != undefined && s1 != '' || !isNaN(s1))
			reVal = s1 * 1;
		if (s2 != undefined && s2 != '' && !isNaN(s2))
			reVal = s2 * 1;
		return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
	}
};