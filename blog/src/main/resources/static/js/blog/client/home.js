var CPageInit = function() {};
CPageInit.prototype = {
	/** 界面初始化 */
	pageInit: function() {
		// 公告的类型id
		oPageInit.noticeId = fw.getDynamicDicts('atricle_manager_typeid', ' TYPENAME = \'系统公告\'')[0].CODE;
		//初始化图片轮播信息
		oPageInit.initCarousel();
		// 初始化文章类型
		oPageInit.initTypes();
		// 初始化文章列表
		oPageInit.initArticleList({VALIDATED: 1, SFFB: 1, TYPEID: oPageInit.initType});
		// 初始化点击量列表和公告列表
		oPageInit.initRankList();
		// 初始化标签云
		oPageInit.initTags();
	},
	
	/**
	 * 初始化图片轮播信息
	 */
	initCarousel: function() {
		carousel.render({
			elem: '#imageCarousel',
			arrow: 'always',
			interval: 18000,
			width: '100%',
			height: '250px'
		});
	},
	
	/**
	 * 初始化文章列表
	 */
	initArticleList: function(params) {
		fw.doAjax(
			'/blog/getAllArticles',
			params,
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
					// 生成公告列表数据
					oPageInit.genNoticeList(data.data);
					// 生成headline数据
					oPageInit.genHeadLine(data.data);
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
						'<img style="width:100%; height: 100%;" alt="' + data[i].TITLE + '"  src="' + data[i].PHOTO +'">' +
					'</a>' +
				'</span>' +
				'<span class="blogabstract">' +
					data[i].ABSTRACT +
				'</span>'+
				'<p class="bloginfo">' +
					'<span>' + data[i].AUTHOR + '</span>&emsp;' +
					'<span>' + fw.timestampToTime(data[i].BAE003) + '</span>' +
					'<span>【<a href="javascript: void(0); return false;" target="_blank">' + 
						fw.getMultDictName("atricle_manager_typeid", data[i].TYPEID) + '</a>】</span>' +
				'</p><a href="javascript: oButtonInit.gotoArticle(' + data[i].ARTICLEID + ')" class="layui-btn layui-btn-sm viewmore">阅读更多</a></li>';
		}
		
		$("#articleList").html(articelListStr);
	},
	
	/**
	 * 生成点击量列表数据
	 */
	genRankList: function(data) {
		var rankListStr = '';
		var count = 0;  // 计数器
		// 最多拼接10条记录
		for (var i = 0; i < data.length && count < 10; i++) {
			// 如果是公告，则不拼接
			if (data[i].TYPEID == oPageInit.noticeId) {
				continue;
			}
			if (i % 2 != 0) {
				// 奇数行背景色为灰色
				rankListStr += '<li class="layui-bg-gray">';
			} else {
				// 偶数行背景色为白色
				rankListStr += '<li>';
			}
			
			if (i < 3) {
				// 前三行使用醒目图标
				rankListStr += '&nbsp;&nbsp;<span class="layui-badge">' + (i + 1) + '</span>';
			} else {
				rankListStr += '&nbsp;&nbsp;<span class="layui-badge layui-bg-gray">' + (i + 1) + '</span>';
			}
			
			rankListStr += '&nbsp;&nbsp;<a href="/blog/index/article' + data[i].ARTICLEID + '" title="' 
				+ data[i].TITLE + '" target="_blank">' + data[i].TITLE + '</a></li>';
			
			count++;
		}
		$("#rankList").html(rankListStr);
	},
	
	
	/**
	 * 生成公告列表
	 */
	genNoticeList: function(data) {
		var noticeListStr = '';
		for (var i = 0; i < data.length; i++) {
			// 如果是公告，则不拼接
			if (data[i].TYPEID != oPageInit.noticeId) {
				continue;
			}
			noticeListStr += '<li class="layui-bg-gray">';
			noticeListStr += '&nbsp;&nbsp;<span class="layui-badge-dot"></span>';
			noticeListStr += '&nbsp;&nbsp;<a href="/blog/index/article' + data[i].ARTICLEID + '" title="' 
				+ data[i].TITLE + '" target="_blank">' + data[i].TITLE + '</a></li>';
		}
		$("#noticeList").html(noticeListStr);
	},
	
	/**
	 * 
	 */
	initTypes: function() {
		var articleTypes = fw.getDynamicDicts('atricle_manager_typeid', ' 1 = 1');
		var typeStr = '';
		for (var i = 0; i < articleTypes.length; i ++) {
			if (i == 0) {
				oPageInit.initType = articleTypes[i].CODE;
				typeStr += '<a href="javascript: oPageInit.queryArticleList(' + articleTypes[i].CODE 
					+ ')" class="layui-this" name="' + articleTypes[i].CODE + '"><span>' + articleTypes[i].NAME + '</span></a>               ';
			} else {
				typeStr += '<a href="javascript: oPageInit.queryArticleList(' + articleTypes[i].CODE 
					+ ')" name="' + articleTypes[i].CODE + '"><span>' + articleTypes[i].NAME + '</span></a>&emsp;            ';
			}
		}
		$("#types").html(typeStr);
		element.init()
	},
	
	/**
	 * 查询文章列表
	 */
	queryArticleList: function(articleType) {
		$("#types").find("a").removeClass("layui-this");
		$("#types").find("a[name=" + articleType + "]").addClass("layui-this");
		oPageInit.initArticleList({VALIDATED: 1, SFFB: 1, TYPEID: articleType});
	},
	
	/**
	 * 初始化标签云
	 */
	initTags: function() {
		var tags = fw.getDynamicDicts('atricle_manager_tagid', ' 1 = 1 ');
		var tagStr = '';
		for (var i = 0; i < tags.length; i++) {
			if (i % 2 == 0) {
				tagStr += '<li><a class="layui-btn layui-btn-warm" style="width: 40%;" href="javascript: oPageInit.queryArticleListByTag(' +
					tags[i].CODE + ')">' + tags[i].NAME + '</a>';
			} else {
				tagStr += '<a class="layui-btn layui-btn-danger" style="width: 40%;" href="javascript: oPageInit.queryArticleListByTag(' +
					tags[i].CODE + ')">' + tags[i].NAME + '</a></li>';
			}
		}
		
		if (tags.length % 2 != 0) {
			tagStr += '<li>';
		}
		$("#tagList").html(tagStr);
	},
	
	/**
	 * 根据标签查询文章列表
	 */
	queryArticleListByTag: function(tagid) {
		oPageInit.initArticleList({VALIDATED: 1, SFFB: 1, TAGID: tagid});
	},
	
	/**
	 * 生成headline数据
	 */
	genHeadLine: function(data) {
		var headLineHtml = '';
		var count = 0;
		for (var i = 0; i < data.length && count < 2; i++) {
			// 如果是公告，则不拼接
			if (data[i].TYPEID == oPageInit.noticeId) {
				continue;
			}
			headLineHtml += '<li><a href="/blog/index/article' + data[i].ARTICLEID + '" target="_blank" title="' + data[i].TITLE + '"> <img' +
								' alt="' + data[i].TITLE + '" src="' + data[i].PHOTO + '"> <span>' + data[i].TITLE + '</span>' +
							'</a></li>';
			count++;
		}
		$("#headline").html(headLineHtml);
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
		window.parent.oButtonInit.redirect("article" + ARTICLEID);
	}
};
var oButtonInit = new CButtonInit();
