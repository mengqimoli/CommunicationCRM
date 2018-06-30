$(function() {
	var registerFrom,emailCodeInput,sendEmailCodeBtn, checkTel, checkrealName, checkUsername, checkEmail, usernameInput, passwordInput, checkPasswordInput, realNameInput, telInput, emailInput, doRegisterBtn;
	var tempEmailCode = "";

	checkTel = /^[0-9]{11}$/;
	checkrealName = /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/;
	checkUsername = /^[0-9a-zA-Z]+$/;// 数字和字母
	checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	usernameInput = $("#username");
	passwordInput = $("#password");
	checkPasswordInput = $("#checkPassword");
	realNameInput = $("#realName");
	telInput = $("#tel");
	emailInput = $("#email");
	doRegisterBtn = $("#doRegister");
	sendEmailCodeBtn = $("#sendEmailCode");
	emailCodeInput = $("#emailCode");
	registerFrom = $("#register-form");
	

	usernameInput.on("keyup", function() {
		if (usernameInput.val().length > 20 || usernameInput.val().length < 6
				|| !checkUsername.test(usernameInput.val())) {
			$("#usernameMsg").html("请输入6-20位的数字或字母");
		} else {
			$.get("/checkUsername.do", {
				username : usernameInput.val()
			}, function(data) {
				$("#usernameMsg").html(data.message);
			}, "json");
		}
	});

	passwordInput.on("keyup", function() {
		if (passwordInput.val().length > 18 || passwordInput.val().length < 6) {
			$("#passwordMsg").html("请输入6-18位的密码");
		} else {
			$("#passwordMsg").html("<font color='green'>√</font>");
		}
	});

	checkPasswordInput.on("keyup", function() {
		if (passwordInput.val() == "" || checkPasswordInput.val() == ""
				|| passwordInput.val() != checkPasswordInput.val()) {
			$("#checkPasswordMsg").html("两次密码输入不一致");
		} else {
			$("#checkPasswordMsg").html("<font color='green'>√</font>");
		}
	});

	realNameInput.on("keyup", function() {
		$("#realNameMsg").html("<font color='green'>√</font>");
	});

	telInput.on("keyup", function() {
		if (!checkTel.test(telInput.val())) {
			$("#telMsg").html("手机号输入有误");
		} else {
			$("#telMsg").html("<font color='green'>√</font>");
		}
	});

	emailInput.on("keyup", function() {
		if (!checkEmail.test(emailInput.val())) {
			$("#emailMsg").html("邮箱地址输入有误");
		} else {
			$("#emailMsg").html("<font color='green'>√</font>");
		}
	});

	sendEmailCodeBtn.on("click", function() {
		$.get("/sendEmailCode.do", {
			email : emailInput.val()
		}, function(data) {
			if (data.success) {
				tempEmailCode = data.message;
				$.messager.alert('温馨提示', '验证码发送成功，请注意接收邮箱信息!', 'info');
			} else {
				$.messager.alert('温馨提示', data.message, 'warning');
			}
		}, "json");
	});

	doRegisterBtn.on("click", function() {
		var flag = 1;
		if (usernameInput.val().length > 20 || usernameInput.val().length < 6
				|| !checkUsername.test(usernameInput.val())) {
			$("#usernameMsg").html("请输入6-20位的数字或字母");
			flag = 0;
		}else{
			$.get("/checkUsername.do", {
				username : usernameInput.val()
			}, function(data) {
				if(!data.success){
					$("#usernameMsg").html(data.message);
					flag = 0;
				} 
			}, "json");
		}
		if (passwordInput.val().length > 18 || passwordInput.val().length < 6) {
			$("#passwordMsg").html("请输入6-18位的密码");
			flag = 0;
		}
		if (passwordInput.val() == "" || checkPasswordInput.val() == ""
				|| passwordInput.val() != checkPasswordInput.val()) {
			$("#checkPasswordMsg").html("两次密码输入不一致");
			flag = 0;
		}
		if(realNameInput.val() == ""){
			$("#realNameMsg").html("请输入真实姓名");
			flag = 0;
		}
		if (!checkTel.test(telInput.val())) {
			$("#telMsg").html("手机号输入有误");
			flag = 0;
		}
		if (!checkEmail.test(emailInput.val())) {
			$("#emailMsg").html("邮箱地址输入有误");
			flag = 0;
		}
		if(emailCodeInput.val()==""||emailCodeInput.val() != tempEmailCode){
			$.messager.alert('温馨提示', "邮箱验证码不正确！！！", 'info');
			flag = 0;
		}
		if(flag == 1){
			registerFrom.form("submit", {
				url : "/register.do",
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert("温馨提示", data.message, "info");
					}else{
						$.messager.alert("温馨提示", "注册失败", "info");
					}
				}
			});
		
		}
	});

})