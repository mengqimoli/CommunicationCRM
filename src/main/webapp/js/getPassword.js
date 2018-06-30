$(function() {
	var usernameInput,emailInput,getPasswordBtn,getPasswordForm,checkEmail;
	
	usernameInput = $("#username");
	emailInput = $("#email");
	getPasswordBtn = $("#getPassword");
	getPasswordForm = $("#getPassword-form");
	checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

	usernameInput.on("keyup", function() {
		if ($(this).val() == "" || $(this).val().length < 6) {
			$("#errorMsg").html("登录账号有误！！！");
		} else {
			$("#errorMsg").html("");
		}
	});
	emailInput.on("keyup", function() {
		if (!checkEmail.test($(this).val())) {
			$("#errorMsg").html("邮箱地址有误！！！");
		} else {
			$("#errorMsg").html("");
		}
	});
	getPasswordBtn.on("click", function() {
		var chcekNum = 1;
		if (usernameInput.val() == "" || usernameInput.val().length < 6) {
			$("#errorMsg").html("登录账号有误！！！");
			chcekNum = 0;
		}
		if (!checkEmail.test(emailInput.val())) {
			$("#errorMsg").html("邮箱地址有误！！！");
			chcekNum = 0;
		}
		if (chcekNum == 1) {
			var params = getPasswordForm.serialize();
			$.post("/getPassword.do",params,function(data){
				if (data.success){
					$.messager.alert("温馨提示",data.message,"info");
				}else{
					$.messager.alert("温馨提示",data.message,"warning");
				}
			}, "json");
		}
	});
})