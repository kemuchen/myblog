var CPageInit = function() {};
CPageInit.prototype = {
	/** 界面初始化 */
	fnPageInit: function() {
		oPageInit.fnInitNav();
	},
	
	/**
	 * 初始化头部导航栏样式
	 */
	fnInitNav: function() {
		var pageId = $("input[name=pageId]").val();
		if (pageId == 'home' || pageId.indexOf('article') != -1) {
			$("#home").addClass('layui-this');
		} else if (pageId == 'time') {
			$("#time").addClass('layui-this');
		} else if (pageId == 'about') {
			$("#about").addClass('layui-this');
		} else if (pageId == 'music' || pageId == 'album' || pageId == 'read') {
			$("#life").addClass('layui-this');
		}
	},
	
	fnSearchKeyPress: function() {
		var keycode = window.event.keyCode;
	    if (keycode == 0xD)  {
	    	oButtonInit.search();
	    }
	}
};

var oPageInit = new CPageInit();

/**
 * 按钮事件处理类
 */
var CButtonInit = function() {}
CButtonInit.prototype = {
	/** 跳转到选择的页面 */
	search: function() {
		// 如果是搜索，则不进行界面跳转，其他界面均进行界面跳转
		if (fw.isEmpty($("input[name=search]").val())) {
			layer.msg("请输入检索条件", {
				anim: 6
			});
		} else {
			$("#contentBody").attr('src', '/blog/search');
		}
	},
	
	/**
	 * 登录或者注册
	 */
	loginOrRegister: function(url) {
		layer.open({
			type: 2,
			title: false,
			offset: '40px',
			skin: 'layui-layer-rim', // 加上边框
			area: ['420px', '560px'], // 宽高
			content: '/blog/toRegisterOrLogin?url=' + url,
			skin: 'login'
		});
	},
	
	/**
	 * 回到顶部
	 */
	goTop: function() {
		contentBody.window.scrollTo(0, 0);
	},
	
	/**
	 * 控制界面跳转
	 */
	redirect: function(url) {
		console.log(url);
		window.location.href = "/blog/index/" + url;
	}
};
var oButtonInit = new CButtonInit();
