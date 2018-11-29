var _TimelineInit = function() {};
_TimelineInit.prototype = {
	/**
	 * 界面初始化
	 */
	fnPageInit: function() {
		// init timeline
		oTimelineInit.fnInitArticleList();
	},
	
	/**
	 * 查询文章列表
	 */
	fnInitArticleList: function() {
		fw.doAjax(
			'/blog/getRankArticles',
			{},
			true, true,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					// 生成文章列表数据
					oTimelineInit.genTimeLineList(data.data);		
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
	
	genTimeLineList: function(data) {
		var lineListStr = '<span class="box-underline">博客文章</span><hr class="layui-bg-gray">';
		for (var i = 0; i < data.length; i++) {
			lineListStr += '<li class="layui-timeline-item">' +
					'<i class="layui-icon layui-timeline-axis"></i>'+
					'<div class="layui-timeline-content layui-text">' +
						'<div class="layui-timeline-title">' +
							'<div style="font-weight: bold; font-size: 14px;">' + data[i].EDITDATE + '</div>'+
							'<div style="margin-top: 10px;"><a href="/blog/index?url=/blog/toArticle%3FARTICLEID=' + 
							data[i].ARTICLEID + '" title="' + data[i].TITLE + '" target="_blank">' + data[i].TITLE + '</a></div>' +
						'</div>' +
					'</div>' +
				'</li>';
		}
		$("ul").html(lineListStr);
	}
};

var oTimelineInit = new _TimelineInit();