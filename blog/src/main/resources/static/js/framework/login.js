/**
 * 登录
 * @returns
 */
function login() {
	var username = $("#username").val();
	var password = $("#password").val();
	var validateCode = $("#code").val();
	
	/** 用户名密码校验未通过 */
	if (!validateLogin(username, password, validateCode)) {
		return;
	}
	
	fw.doAjax('/xtgl/login', {username: username, password: password, validateCode: validateCode}, true, true,
		function(data) {
			/** 登录成功 */
			if (data.code == '1') {
				window.location.href = '/xtgl/index';
			} else {
				layer.msg(data.message, {
					icon: '2'
				});
				// 刷新验证码
				refresh();
			}
		},
		function(data) {
			layer.msg('登录错误，请稍后重试!', {
				icon: '2'
			});
		}
	);
}

/**
 * 登录前校验
 * 
 * @returns
 */
function validateLogin(username, password, validateCode) {
	/** 用户名不能为空 */
	if (fw.isEmpty(username)) {
		layer.msg('用户名不能为空', {
			icon: '2'
		});
		return false;
	}
	
	/** 密码不能为空 */
	if (fw.isEmpty(password)) {
		layer.msg('密码不能为空', {
			icon: '2'
		});
		return false;
	}
	
	/** 密码不能为空 */
	if (fw.isEmpty(validateCode)) {
		layer.msg('验证码不能为空', {
			icon: '2'
		});
		return false;
	}
	return true;
}

/**
 * 刷新验证码
 */
function refresh(){
    var src = $("#validateCode").attr('src');
    $("#validateCode").attr('src', src + "?now=" + new Date())
}