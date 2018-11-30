var _ArticleTypeInit = function() {};
_ArticleTypeInit.prototype = {
	/**
	 * 
	 */
	fnPageInit: function() {
		// 初始化表格
		oArticleTypeInit.initTable();
		// 初始化字典项
		oArticleTypeInit.initDicts();
		// 初始化表格监听事件
		oArticleTypeInit.initTabeEvent();
	},
	
	/** 初始化表格 */
	initTable: function() {
		table.render({
		    elem: '#typeList',
		    url: '/blog/getArticleTypes',
		    cellMinWidth: 80, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'TYPEID', hide: true},
		    	{field:'TYPENAME', width: 200, title: '分类标题', sort: true, align: 'center'},
		    	{	
		    		field:'VALIDATED', title: '有效标志', width: 200, sort: true, align: 'center',
		    		templet: function(d) {
			    		 return fw.getDictName("VALIDATED", d.VALIDATED);
			    	 }
		    	},
		    	{field:'MEMO', title: '备注', sort: true, align: 'center'},
		    	{title: '操作', width: 280, toolbar: '#typeBar', align: 'center', fixed: 'right'}
		    ]],
		    request: {
		    	pageName: 'currentPage',
		    	limitName: 'pageSize'
		    },
		    parseData: function(res){ //res 即为原始返回的数据
		        return {
		        	"code": res.code, //解析接口状态
		        	"msg": res.message, //解析提示文本
		        	"count": res.count, //解析数据长度
		        	"data": res.data //解析数据列表
		        }
		    }
		});
	},
	
	// 初始化字典项
	initDicts: function() {
		fw.initDict($("select[name='VALIDATED']"), []);
	},
	
	/** 初始化表格监听事件 */
	initTabeEvent: function() {
		table.on('tool(typeTable)', function(obj){
			if (obj.event === 'update') {  // 更新
				oArticleTypeBtn.doUpdate(obj);
			} else if (obj.event === 'delete') {
				oArticleTypeBtn.doDelete(obj);
			}
		});
	}
}
var oArticleTypeInit = new _ArticleTypeInit();

var _ArticleTypeBtn = function() {};
_ArticleTypeBtn.prototype = {
	/**
	 * doQuery
	 */
	doQuery: function() {
		table.reload('typeList', {
			where: fw.getFormData("#queryForm")
		});
	},

	/**
	 * 新增
	 */
	doAdd: function() {
		// 清空表单
		fw.clearForm('#saveForm');
		
		// 弹出新增界面
		layer.open({
			type: 1,
			title: '新增文章分类',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '300px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/**
	 * 更新
	 */
	doUpdate: function(obj) {
		// 清空表单
		fw.clearForm('#saveForm');
		// 设置表单值
		fw.setFormData('#saveForm', obj.data);
		// 弹出更新界面
		layer.open({
			type: 1,
			title: '修改文章',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '300px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 删除文章分类信息 */
	doDelete: function(obj) {
		layer.confirm('真的删除行么?', function(index){
			fw.doAjax(
				'/blog/deleteArticleType',
				{TYPEID: obj.data.TYPEID},
				true, true,   // 显示加载层;是否异步
				function(data) {   // 成功回调函数
					if (data.code == '1') {
						layer.msg('删除文章分类成功', {
							icon: '1'
						})
						// 刷新文章分类列表数据
						oArticleTypeBtn.doQuery();
					} else {
						layer.msg(data.message, {
							icon: '5'
						})
					}
				},
				function(data) {   // 失败回调函数
					layer.msg(data.message, {
						icon: '5'
					})
				}
			);
		});
	},
	
	/** 保存文章分类信息（新增或修改） */
	doSave: function() {
		// 首先校验表单录入数据格式
		if (fw.validateForm("#saveForm")) {
			var data = fw.getFormData("#saveForm");
			fw.doAjax(
				'/blog/saveArticleType',  // 请求url
				data, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					if (data.code == '1') {
						layer.msg('保存文章分类信息成功', {
								icon: '1',
								time: 1000,
								shade : [0.5 , '#000' , true]
							},
							function() {
								layer.closeAll();
							}
						);
						// 刷新文章分类列表数据
						oArticleTypeBtn.doQuery();
					} else {
						layer.msg(data.message, {
							icon: '5'
						})
					}
				},
				function(data) {  // 失败回调函数
					layer.msg(data.message, {
						icon: '5'
					})
				}
			);
		}
	}
}

var oArticleTypeBtn = new _ArticleTypeBtn();