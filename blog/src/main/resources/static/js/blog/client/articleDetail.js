var CPageInit = function() {};
CPageInit.prototype = {
	/** 界面初始化 */
	pageInit: function() {
		// 公告的类型id
		oPageInit.noticeId = fw.getDynamicDicts('atricle_manager_typeid', ' TYPENAME = \'系统公告\'')[0].CODE;
		// 初始化文章信息
		oPageInit.initArticle();
		// 初始化评论列表
		oPageInit.initCritic();
		// 初始化评论框
		oPageInit.initEdit();
		// 初始化点击量列表
		oPageInit.initRankList();
	},
	
	/**
	 * 初始化文章信息
	 */
	initArticle: function() {
		fw.doAjax(
			'/blog/getArticle',
			{ARTICLEID: $("#ARTICLEID").val(), CRITICORMESSAGE : '1'},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					var article = data.data;
					$(".article_text").html(article.CONTENT);
					$(".article_tilte").html(article.TITLE);
					$(".article_author").html(article.AUTHOR);
					$(".article_date").html(fw.timestampToTime(article.BAE003));
					$(".article_tyle").html('【' + fw.getMultDictName("atricle_manager_typeid", article.TYPEID) + '】');
					$(".article_read").html(article.READCOUNT + '人已围观');
					$(".article_abstract").html('<span>简介&nbsp;&nbsp;</span>' + article.ABSTRACT);
					$("#article-dz").html(article.PRAISECOUNT);
					$("#article-undz").html(article.TREADCOUNT);
				} else {
					layer.msg(data.message, {
						icon: '2'
					})
				}
			},
			function(data) {   // 失败回调函数
				layer.msg(data.message, {
					icon: '2'
				})
			}
		);
	},
	
	/** 初始化富文本编辑器 */
	initEdit: function() {
		//建立评论编辑器
		oPageInit.CriticEditIndex = layedit.build('CRITIC', {
			height: 180,
			uploadImage: {url: '/common/uploadFile?filePath=/images/blog/critics/', type: 'post'},
			tool: ['code', 'link','unlink','face','image']
		}); 
	},
	
	/** 初始化评论 */
	initCritic: function() {
		fw.doAjax(
			'/blog/getArticeCritics',
			{ARTICLEID: $("#ARTICLEID").val()},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					// 生成文章列表数据
					oPageInit.genCriticList(data.CRITICS);
				} else {
					layer.msg(data.message, {
						icon: '2'
					})
				}
			},
			function(data) {   // 失败回调函数
				layer.msg(data.message, {
					icon: '2'
				})
			}
		);
	},
	
	/** 生成评论列表数据 */
	genCriticList: function(data) {
		var htmlStr = '';
		var count = 0;  // 评论数量
		// 遍历评论信息
		for (var i = 0; i < data.length; i++) {
			count++;
			htmlStr += '<li>' + 
				'<div class="critic-area">' +
					'<a href="javascript: void(0)" title="' + data[i].BAE002NAME +'"><img class="avater" alt="' + data[i].BAE002NAME +'" src="' + data[i].PHOTO + '"></a>' +
					'<div style="width: 95%">' +
						'<div class="criticInfo">' +
							'<span><a href="javascript: void(0)" title="' + data[i].BAE002NAME + '">' + data[i].BAE002NAME + '</a></span>' +
							'<span class="criticTime">' + fw.timestampToTime(data[i].BAE001) + '</span>' +
						'</div>' +
						'<div class="criticContent">' + data[i].CONTENT + '</div>' +
					'</div>' +
				'</div>' +
				'<div class="criticAction">' +
					'<span>' +
						'<a href="javascript: oButtonInit.replay(' + data[i].BAE002 + "," + data[i].CRITICID + ');">回复</a>&emsp;' +
						'<a href="javascript: oButtonInit.praiseCritic(' + data[i].CRITICID + ');"><i class="layui-icon">&#xe6c6;</i>（' + data[i].PRAISECOUNT + '）</a>&emsp;' +
						'<a href="javascript: oButtonInit.tradeCritic(' + data[i].CRITICID + ');"><i class="layui-icon">&#xe6c5;</i>（' + data[i].TREADCOUNT + '）</a>' +
					'</span>' +
				'</div>';
			
			// 如果存在子评论，则拼接子评论信息
			if (!fw.isEmpty(data[i].SUBCRITIC) && data[i].SUBCRITIC.length > 0) {
				htmlStr += '<ul class="subcritic">';
				var subCritic = data[i].SUBCRITIC;
				for (var j = 0; j < subCritic.length; j++) {
					count++;
					htmlStr += '<li>' + 
						'<div class="critic-area">' +
							'<a href="javascript: void(0)" title="' + subCritic[j].BAE002NAME +'"><img class="avater" alt="' + subCritic[j].BAE002NAME +'" src="' + subCritic[j].PHOTO + '"></a>' +
							'<div style="width: 95%">' +
								'<div class="criticInfo">' +
									'<span><a href="javascript: void(0)" title="' + subCritic[j].BAE002NAME + '">' + subCritic[j].BAE002NAME + '</a>&emsp;回复&emsp;<a href="javascript: void(0)" title="' + subCritic[j].USERNAME + '">' + subCritic[j].USERNAME + '</a></span>' +
									'<span class="criticTime">' + fw.timestampToTime(subCritic[j].BAE001) + '</span>' +
								'</div>'+ 
								'<div class="criticContent">' + subCritic[j].CONTENT + '</div>' +
							'</div>' +
						'</div>' +
						'<div class="criticAction">' +
							'<span>' +
								'<a href="javascript: oButtonInit.replay(' + subCritic[j].BAE002 + "," + data[i].CRITICID + ');">回复</a>&emsp;' +
								'<a href="javascript: oButtonInit.praiseCritic(' + subCritic[j].CRITICID + ');"><i class="layui-icon">&#xe6c6;</i>（' + subCritic[j].PRAISECOUNT + '）</a>&emsp;' +
								'<a href="javascript: oButtonInit.tradeCritic(' + subCritic[j].CRITICID + ');"><i class="layui-icon">&#xe6c5;</i>（' + subCritic[j].TREADCOUNT + '）</a>' +
							'</span>' +
						'</div></li>';
				}
				htmlStr += "</ul>";
			}
			
			htmlStr += "</li>";
		}
		
		// 如果没有评论，则显示暂无评论
		if (htmlStr == '') {
			htmlStr = '<div class="empty-critic"><span>还没有评论，快来抢沙发吧！</span></div>';
		}
		

		$(".rightcount").html("共" + count + "条评论")
		$("#criticList").html(htmlStr);
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
};

var oPageInit = new CPageInit();

/**
 * 按钮事件处理类
 */
var CButtonInit = function() {}
CButtonInit.prototype = {
	// 回复
	replay: function(replayUserid, criticid) {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		oButtonInit.replyUserid = replayUserid;
		oButtonInit.replyCriticid = criticid;

		// 弹出新增界面
		layer.open({
			type: 1,
			title: '回复',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '350px'], //宽高
			content: $('#reply')
		});
		
		//建立回复评论编辑器
		oPageInit.ReplyEditIndex = layedit.build('REPLY_EDIT', {
			height: 180,
			tool: ['code', 'link','unlink','face']
		});
	},
	
	// 评论文章
	critic: function() {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		// 校验是否填写回复内容
		if (layedit.getText(oPageInit.CriticEditIndex) == '') {
			layer.alert('请填写评论内容', {
				icon: '5',
				anim: 6
			});
			return;
		}
		
		var params = {
			USERID: '0',
			ARTICLEID: $("#ARTICLEID").val(),
			CONTENT: layedit.getContent(oPageInit.CriticEditIndex),
			PARENTID: '0'
		}
		
		oButtonInit.saveCritic(params);
		// 设置编辑器内容
		$("#CRITIC").val('');
	},
	
	// 填写回复内容
	criticReply: function() {
		// 校验是否填写评论内容
		if (layedit.getText(oPageInit.ReplyEditIndex) == '') {
			layer.alert('请填写评论内容', {
				icon: '5',
				anim: 6
			});
			return;
		}
		
		var params = {
			USERID: oButtonInit.replyUserid,
			ARTICLEID: $("#ARTICLEID").val(),
			CONTENT: layedit.getContent(oPageInit.ReplyEditIndex),
			PARENTID: oButtonInit.replyCriticid
		}
		
		oButtonInit.saveCritic(params);
	},
	
	// 保存评论
	saveCritic: function(params) {
		params.CRITICORMESSAGE = '1';
		
		fw.doAjax(
			'/blog/doCritic',  // 请求url
			params, // 请求参数
			true, true,   // 显示加载层;是否异步
			function(data) {  // 成功回调函数
				if (data.code == '1') {
					layer.msg('评论成功', {
							icon: '1',
							time: 1000,
							shade : [0.5 , '#000' , true]
						},
						function() {
							layer.closeAll();
							// 成功后刷新评论
							oPageInit.initCritic();
							// 设置评论内容为空
							oPageInit.initEdit();
						}
					);
				} else {
					layer.msg(data.message, {
						icon: '2'
					})
				}
			},
			function(data) {  // 失败回调函数
				layer.msg(data.message, {
					icon: '2'
				})
			}
		);
	},
	
	/**
	 * 点赞评论
	 */
	praiseCritic: function(criticid) {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		var params = {
			PRAORTRE: '1',
			ARTORCRI: '0',
			ARID: criticid
		}
		oButtonInit.savePraiseOrTrade(params);
	},
	
	/**
	 * 踩评论
	 */
	tradeCritic: function(criticid) {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		var params = {
			PRAORTRE: '0',
			ARTORCRI: '0',
			ARID: criticid
		}
		oButtonInit.savePraiseOrTrade(params);
	},
	
	/**
	 * 点赞文章
	 */
	praiseAritcle: function() {
		// 校验是否登录，若为登录，则弹出登录框
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		var params = {
			PRAORTRE: '1',
			ARTORCRI: '1',
			ARID: $("#ARTICLEID").val()
		}
		oButtonInit.savePraiseOrTrade(params);
	},
	
	/**
	 * 踩文章
	 */
	tradeArticel: function() {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("article" + $("#ARTICLEID").val());
			return;
		}
		
		var params = {
			PRAORTRE: '0',
			ARTORCRI: '1',
			ARID: $("#ARTICLEID").val()
		}
		oButtonInit.savePraiseOrTrade(params);
	},
	
	/**
	 * 保存点赞或踩
	 */
	savePraiseOrTrade: function(params) {
		fw.doAjax(
			'/blog/doPraiseOrTrade',  // 请求url
			params, // 请求参数
			false, true,   // 显示加载层;是否异步
			function(data) {  // 成功回调函数
				if (data.code == '1') {
					// 初始化文章信息
					oPageInit.initArticle();
					// 成功后刷新评论
					oPageInit.initCritic();
				} else {
					layer.msg(data.message, {
						icon: '2'
					})
				}
			},
			function(data) {  // 失败回调函数
				layer.msg(data.message, {
					icon: '2'
				})
			}
		);
	}
};
var oButtonInit = new CButtonInit();
