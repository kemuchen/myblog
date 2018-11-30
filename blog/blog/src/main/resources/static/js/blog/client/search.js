var CPageInit = function() {};
CPageInit.prototype = {
	/** 界面初始化 */
	pageInit: function() {
		// 初始化文章列表
		oPageInit.initArticleList();
		// 初始化点击量列表
		oPageInit.initRankList();
	},
	
	/**
	 * 初始化文章列表
	 */
	initArticleList: function() {
		fw.doAjax(
			'/blog/searchArticle',
			{SEARCHECONTENT: $('input[name=search]', window.top.document).val()},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '0') {
					// 生成文章列表数据
					oPageInit.genArticleList(data.data);		
				} else {
					layer.alert(data.message)
				}
			},
			function(data) {   // 失败回调函数
				layer.msg(data.message, {
					icon: '5'
				})
			}
		);
	},
	
	/**
	 * 初始化点击量列表
	 */
	initRankList: function() {
		fw.doAjax(
			'/blog/getRankArticles',
			{VALIDATED: 1, SFFB: 1},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					// 生成文章列表数据
					oPageInit.genRankList(data.data);		
				} else {
					layer.alert(data.message)
				}
			},
			function(data) {   // 失败回调函数
				layer.msg(data.message, {
					icon: '5'
				})
			}
		);
	},
	
	/**
	 * 生成文章列表数据
	 */
	genArticleList: function(data) {
		var articelListStr = '';
		for (var i = 0; i < data.length; i++) {
			articelListStr += '<li>' +
				'<h3 class="blogtitle"><a href="javascript: oButtonInit.gotoArticle(' + data[i].ARTICLEID + ')" target="_blank">' + data[i].TITLE + '</a></h3>' +
				'<span class="blogpic">' +
					'<a href="javascript: oButtonInit.gotoArticle(' + data[i].ARTICLEID + ')" target="_blank" title="' + data[i].TITLE + '">' +
						'<img alt="' + data[i].TITLE + '"  src="' + data[i].PHOTO +'">' +
					'</a>' +
				'</span>' +
				'<span class="blogabstract">' +
					data[i].ABSTRACT +
				'</span>'+
				'<p class="bloginfo">' +
					'<span>' + data[i].AUTHOR + '</span>&emsp;' +
					'<span>' + fw.timestampToTime(data[i].BAE003) + '</span>' +
					'<span>【<a href="" target="_blank">' + fw.getMultDictName("atricle_manager_typeid", data[i].TYPEID) + '</a>】</span>' +
				'</p><a href="javascript: oButtonInit.gotoArticle(' + data[i].ARTICLEID + ')" class="layui-btn layui-btn-sm viewmore">阅读更多</a></li>';
		}
		
		if (articelListStr == '') {
			articelListStr = '<p align="center" style="padding-bottom: 20px;">未查询到相关结果</p>'
		}
		
		$("#articleList").html(articelListStr);
	},
	
	/**
	 * 生成点击量列表数据
	 */
	genRankList: function(data) {
		var rankListStr = '';
		for (var i = 0; i < data.length; i++) {
			if (i % 2 != 0) {
				// 奇数行背景色为灰色
				rankListStr += '<li class="layui-bg-gray">';
			} else {
				// 偶数行背景色为白色
				rankListStr += '<li>';
			}
			
			if (i < 3) {
				// 前三行使用醒目图标
				rankListStr += '<span class="layui-badge">' + (i + 1) + '</span>';
			} else {
				rankListStr += '<span class="layui-badge layui-bg-gray">' + (i + 1) + '</span>';
			}
			
			rankListStr += '&emsp;<a href="/blog/index?url=/blog/toArticle%3FARTICLEID=' + data[i].ARTICLEID + '" title="' 
				+ data[i].TITLE + '" target="_blank">' + data[i].TITLE + '</a></li>';
		}
		$("#rankList").html(rankListStr);
	}
};

var oPageInit = new CPageInit();

/**
 * 按钮事件处理类
 */
var CButtonInit = function() {}
CButtonInit.prototype = {
	/** 跳转到文章信息页面 */
	gotoArticle: function(ARTICLEID) {
		window.parent.oButtonInit.redirect("/blog/toArticle%3FARTICLEID=" + ARTICLEID);
	}
};
var oButtonInit = new CButtonInit();
