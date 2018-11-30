var _RegisterInit = function() {};
_RegisterInit.prototype = {
	/** 界面初始化 */
	pageInit: function() {
		// 初始化文件上传
		oRegisterInit.initFileUpload();
	},
	
	/**
	 * 初始化文件上传
	 */
	initFileUpload: function() {
		upload.render({
		    elem: '#uploadPhoto',
		    url: '/common/uploadFile?filePath=/images/user/photo/',
		    accept: 'images',
		    done: function(res){
		    	// 文件上传成功
		    	if(res.code == 0){
		    		$("#register").find('input[name=photo]').val(res.data.src);
		    	} else {
		    		layer.msg(res.message, {
		    			icon: '2'
		    		});
		    	}
		    },
		    error: function(){
		    	layer.msg('头像上传失败', {
	    			icon: '2'
	    		});
		    }
		});
	}
};

var oRegisterInit = new _RegisterInit();

/**
 * 按钮事件处理类
 */
var _RegisterBtn = function() {}
_RegisterBtn.prototype = {
	/**
	 * 刷新验证码
	 */
	refresh: function() {
		var src = $("img[name=validateCode]").attr('src');
	    $("img[name=validateCode]").attr('src', src + "?now=" + new Date())
	},
	
	changeTab: function(tabId) {
		$("form").hide();
		$("#" + tabId).show();
	},
	
	/**
	 * 登录
	 */
	login: function() {
		var url = $("#url").val();
		if (fw.isEmpty(url)) {
			url = "home";
		}
		
		// 首先校验表单录入数据格式
		if (fw.validateForm("#login")) {
			var data = fw.getFormData("#login");
			fw.doAjax(
				'/xtgl/login',  // 请求url
				data, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					/** 登录成功 */
					if (data.code == '1') {
						window.parent.location.href = "/blog/index/" + url;
					} else {
						layer.msg(data.message, {
							icon: '2'
						});
						// 刷新验证码
						refresh();
					}
				},
				function(data) {  // 失败回调函数
					layer.msg('登录错误，请稍后重试!', {
						icon: '2'
					});
				}
			);
		}
	},
	
	/**
	 * 注册
	 */
	register: function() {
		var url = $("#url").val();
		if (fw.isEmpty(url)) {
			url = "home";
		}
		
		// 首先校验表单录入数据格式
		if (oRegisterBtn.validateRegister()) {
			var data = fw.getFormData("#register");
			fw.doAjax(
				'/xtgl/register',  // 请求url
				data, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					/** 注册成功 */
					if (data.code == '1') {
						window.parent.location.href = "/blog/index/" + url;
					} else {
						layer.msg(data.message, {
							icon: '2'
						});
						// 刷新验证码
						refresh();
					}
				},
				function(data) {  // 失败回调函数
					layer.msg('注册错误，请稍后重试!', {
						icon: '2'
					});
				}
			);
		}
	},
	
	/**
	 * 校验注册信息
	 */
	validateRegister: function() {
		// 注册数据
		var data = fw.getFormData("#register");
		
		if (!fw.validateForm("#register")) {
			return false;
		}
		
		// 确认密码和密码不一致
		if (data.password != data.compassword) {
			layer.alert('两次输入的密码不一致', {
				type: '2'
			});
			return false;
		}
		
		return true;
	},
	
	/**
	 * 校验登录id是否存在
	 */
	checkEmail: function(obj) {
		var loginid = $(obj).val();
		if (fw.isEmpty(loginid)) {
			return;
		}
 		fw.doAjax(
			'/xtgl/getUserByLoginid',  // 请求url
			{loginid: loginid}, // 请求参数
			true, true,   // 显示加载层;是否异步
			function(data) {  // 成功回调函数
				if (data.code == '1') {
					layer.confirm('已有用户，是否登录？', {
						btn: ['是','否'] //按钮
					}, function(index){
						layer.close(index);
						// 选择是，切换到登录tab页
						oRegisterBtn.changeTab("login");
						fw.setFormData('#register', {email: ''});
					}, function(){
						// 选择否，则清空email
						fw.setFormData('#register', {email: ''});
					});
					
				} else if (data.code != '0') {
					layer.msg(data.msg, {
						icon: '2'
					});
					// 设置表单值
					fw.setFormData('#register', {email: ''});
				}
			},
			function(data) {  // 失败回调函数
				layer.msg('服务器异常，请稍后重试', {
					icon: '2'
				});
				// 设置表单值
				fw.setFormData('#register', {email: ''});
			}
		);
	},
	
	/**
	 * 第三方QQ登录
	 */
	loginQQ: function() {
		var url = $("#url").val();
		if (fw.isEmpty(url)) {
			url = "home";
		}
		QC.Login.showPopup({
		      appId:"101528188",
		      redirectURI:"http://22815l1b14.iask.in/thirdLogin/qqLogin"
		});
	},

	/**
	 * 微博登录
	 */
	weiboLogin: function(){
	    let weiboAppId = '2191145838';
	    let weiboAuthPath = 'http://22815l1b14.iask.in/thirdLogin/weiboLogin';
	    window.open(`https://api.weibo.com/oauth2/authorize?client_id=${weiboAppId}&response_type=code&redirect_uri=${encodeURIComponent(weiboAuthPath)}`);
	},
	
	/**
	 * 登录的回调函数
	 */
	loginCallBack: function(data) {
		// 登录成功
		if (data.code == '1') {
			window.top.location.reload();
		} else {
			// 登录失败
			layer.msg(data.message, {
				icon: '2'
			});
		}
	}
};
var oRegisterBtn = new _RegisterBtn();
