var _CAbortInit = function() {};
_CAbortInit.prototype = {
	/**
	 * 页面初始化
	 */
	fnPageInit: function() {
		// 初始化留言列表
		oAbortInit.initMessage();
		// 初始化留言框
		oAbortInit.initEdit();
	},
	
	/**
	 * 初始化留言框
	 */
	initMessage: function() {
		fw.doAjax(
			'/blog/getArticeCritics',
			{CRITICORMESSAGE : '2'},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					// 生成文章列表数据
					oAbortInit.genMessageList(data.CRITICS);
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
	
	initEdit: function() {
		//建立评论编辑器
		oAbortInit.MessageEditIndex = layedit.build('MESSAGE', {
			height: 180,
			uploadImage: {url: '/common/uploadFile?filePath=/images/blog/critics/', type: 'post'},
			tool: ['code', 'link','unlink','face','image']
		}); 
	},
	
	/**
	 * 生成留言列表数据
	 */
	genMessageList: function(data) {
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
						'<a href="javascript: oAbortBtn.replay(' + data[i].BAE002 + "," + data[i].CRITICID + ');">回复</a>&emsp;' +
						'<a href="javascript: oAbortBtn.praiseCritic(' + data[i].CRITICID + ');"><i class="layui-icon">&#xe6c6;</i>（' + data[i].PRAISECOUNT + '）</a>&emsp;' +
						'<a href="javascript: oAbortBtn.tradeCritic(' + data[i].CRITICID + ');"><i class="layui-icon">&#xe6c5;</i>（' + data[i].TREADCOUNT + '）</a>' +
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
								'<a href="javascript: oAbortBtn.replay(' + subCritic[j].BAE002 + "," + data[i].CRITICID + ');">回复</a>&emsp;' +
								'<a href="javascript: oAbortBtn.praiseCritic(' + subCritic[j].CRITICID + ');"><i class="layui-icon">&#xe6c6;</i>（' + subCritic[j].PRAISECOUNT + '）</a>&emsp;' +
								'<a href="javascript: oAbortBtn.tradeCritic(' + subCritic[j].CRITICID + ');"><i class="layui-icon">&#xe6c5;</i>（' + subCritic[j].TREADCOUNT + '）</a>' +
							'</span>' +
						'</div></li>';
				}
				htmlStr += "</ul>";
			}
			
			htmlStr += "</li>";
		}
		
		// 如果没有评论，则显示暂无评论
		if (htmlStr == '') {
			htmlStr = '<div class="empty-critic"><span>还没有留言，快来抢沙发吧！</span></div>';
		}
		

		$(".rightcount").html("共" + count + "条留言")
		$("#criticList").html(htmlStr);
	}
}
var oAbortInit = new _CAbortInit();

var _AbortBtn = function() {};
_AbortBtn.prototype = {
	// 回复
	replay: function(replayUserid, criticid) {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("about");
			return;
		}
		
		oAbortBtn.replyUserid = replayUserid;
		oAbortBtn.replyCriticid = criticid;

		// 弹出新增界面
		layer.open({
			type: 1,
			title: '回复',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '350px'], //宽高
			content: $('#reply')
		});
		
		//建立回复评论编辑器
		oAbortInit.ReplyEditIndex = layedit.build('REPLY_EDIT', {
			height: 180,
			tool: ['code', 'link','unlink','face']
		});
	},
	
	// 评论文章
	critic: function() {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("about");
			return;
		}
		
		// 校验是否填写回复内容
		if (layedit.getText(oAbortInit.MessageEditIndex) == '') {
			layer.alert('请填写评论内容', {
				icon: '5',
				anim: 6
			});
			return;
		}
		
		var params = {
			USERID: '0',
			ARTICLEID: '0',
			CONTENT: layedit.getContent(oAbortInit.MessageEditIndex),
			PARENTID: '0'
		}
		
		oAbortBtn.saveCritic(params);
		// 设置编辑器内容
		$("#CRITIC").val('');
	},
	
	// 填写回复内容
	criticReply: function() {
		// 校验是否填写评论内容
		if (layedit.getText(oAbortInit.ReplyEditIndex) == '') {
			layer.alert('请填写留言内容', {
				icon: '5',
				anim: 6
			});
			return;
		}
		
		var params = {
			USERID: oAbortBtn.replyUserid,
			ARTICLEID: '0',
			CONTENT: layedit.getContent(oAbortInit.ReplyEditIndex),
			PARENTID: oAbortBtn.replyCriticid
		}
		
		oAbortBtn.saveCritic(params);
	},
	
	// 保存评论
	saveCritic: function(params) {
		params.CRITICORMESSAGE = '2';
		
		fw.doAjax(
			'/blog/doCritic',  // 请求url
			params, // 请求参数
			true, true,   // 显示加载层;是否异步
			function(data) {  // 成功回调函数
				if (data.code == '1') {
					layer.msg('留言成功', {
							icon: '1',
							time: 1000,
							shade : [0.5 , '#000' , true]
						},
						function() {
							layer.closeAll();
							// 成功后刷新评论
							oAbortInit.initMessage();
							// 设置评论内容为空
							oAbortInit.initEdit();
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
			window.parent.oButtonInit.loginOrRegister("about");
			return;
		}
		
		var params = {
			PRAORTRE: '1',
			ARTORCRI: '0',
			ARID: criticid
		}
		oAbortBtn.savePraiseOrTrade(params);
	},
	
	/**
	 * 踩评论
	 */
	tradeCritic: function(criticid) {
		// 校验是否登录
		if (fw.isEmpty(userid)) {
			window.parent.oButtonInit.loginOrRegister("about");
			return;
		}
		
		var params = {
			PRAORTRE: '0',
			ARTORCRI: '0',
			ARID: criticid
		}
		oAbortBtn.savePraiseOrTrade(params);
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
					// 成功后刷新留言
					oAbortInit.initMessage();
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
}

var oAbortBtn = new _AbortBtn();