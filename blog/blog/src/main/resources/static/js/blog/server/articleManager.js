/**
 * 界面初始化处理类
 */
var CPageInit = function() {}
CPageInit.prototype = {
	
	// 富文本编辑器index
	EditIndex: '',
	
	/** 界面初始化 */
	pageInit: function() {
		// 初始化表格
		oPageInit.initTable();
		// 初始化字典项
		oPageInit.initDicts();
		// 初始化多选下拉列表
		oPageInit.initSelects();
		// 初始化表格监听事件
		oPageInit.initTabeEvent();
		// 初始化文件上传
		oPageInit.initFileUpload();
	},

	/** 初始化表格 */
	initTable: function() {
		table.render({
		    elem: '#articleList',
		    url: '/blog/getAllArticles',
		    cellMinWidth: 80, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'ARTICLEID', hide: true},
		    	{field:'TITLE', width: 200, title: '文章标题', sort: true, align: 'center'},
		    	{field:'ABSTRACT', title: '文章摘要', hide: true},
		    	{field:'TYPEID', width: 200, title: '文章分类', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getMultDictName("atricle_manager_typeid", d.TYPEID);
		    		}
		    	},
		    	{field:'TAGID', width: 200, title: '文章标签', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getMultDictName("atricle_manager_tagid", d.TAGID);
		    		}
		    	},
		    	{field:'SOURCE', title: '文章来源', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("SOURCE", d.SOURCE);
		    		}
		    	},
		    	{field:'AUTHOR', title: '作者', sort: true, align: 'center'},
		    	{	
		    		field:'SFFB', title: '是否发布', sort: true, align: 'center',
		    		templet: function(d) {
			    		 return fw.getDictName("SFBZ", d.SFFB);
			    	 }
		    	},
		    	{	
		    		field:'VALIDATED', title: '有效标志', sort: true, align: 'center',
		    		templet: function(d) {
			    		 return fw.getDictName("VALIDATED", d.VALIDATED);
			    	 }
		    	},
		    	{field:'PRAISECOUNT', title: '点赞量', sort: true, align: 'center'},
		    	{field:'READCOUNT', title: '阅读量', sort: true, align: 'center'},
		    	{field:'AUTHOR', title: '作者', sort: true, align: 'center'},
		    	{field:'MEMO', hide: true},
		    	{title: '操作', width: 280, toolbar: '#articleBar', align: 'center', fixed: 'right'}
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
	
	initSelects: function() {
		// 初始化多选下拉列表
		formSelects.render('TYPEIDS');
		formSelects.render('TAGIDS');
	},
	
	// 初始化字典项
	initDicts: function() {
		fw.initDict($("select[name='TYPEID']"), []);
		fw.initDict($("select[name='TAGID']"), []);
		fw.initDict($("select[name='SOURCE']"), []);
		fw.initDict($("select[name='SFFB']"), []);
		fw.initDict($("select[name='VALIDATED']"), []);
	},
	
	/** 初始化表格监听事件 */
	initTabeEvent: function() {
		table.on('tool(articleTable)', function(obj){
			if (obj.event === 'update') {  // 更新
				oButtonInit.doUpdate(obj);
			} else if (obj.event === 'edit') {  // 编辑文章
				oButtonInit.doEdit(obj);
			} else if (obj.event === 'delete') {
				oButtonInit.doDelete(obj);
			}
		});
	},
	
	/** 初始化富文本编辑器 */
	initEdit: function() {
		oPageInit.EditIndex = layedit.build('CONTENT', {
			uploadImage: {url: '/common/uploadFile?filePath=/images/blog/articles/', type: 'post'},
			tool: ['strong','italic','underline','del','left','center','right', 'code', 'link', 'unlink', 'face', 'image']
		}); //建立编辑器
	},
	
	/**
	 * 初始化文件上传
	 */
	initFileUpload: function() {
		upload.render({
		    elem: '#uploadPhoto',
		    url: '/common/uploadFile?filePath=/images/blog/articles/',
		    accept: 'images',
		    done: function(res){
		    	// 文件上传成功
		    	if(res.code == 0){
		    		$("#saveForm").find('input[name=PHOTO]').val(res.data.src);
		    	} else {
		    		layer.msg(res.message, {
		    			icon: '2'
		    		});
		    	}
		    },
		    error: function(){
		    	layer.msg('文章封面上传失败', {
	    			icon: '2'
	    		});
		    }
		});
	}
}

var oPageInit = new CPageInit();

/**
 * 按钮事件处理类
 */
var CButtonInit = function() {}
CButtonInit.prototype = {
		
	/** 查询文章信息 */
	doQuery: function() {
		table.reload('articleList', {
			where: fw.getFormData("#queryForm")
		});
	},
	
	doAdd: function() {
		// 清空表单
		fw.clearForm('#saveForm');
		
		// 设置多选下拉列表初始值
		//formSelects.value('TYPEIDS', []);
		formSelects.value('TAGIDS', []);
		
		// 设置表单值
		fw.setFormData('#saveForm', {AUTHOR : $("#USERNAME").val()});
		
		// 弹出新增界面
		layer.open({
			type: 1,
			title: '新增文章',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '450px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 更新文章信息弹出更新界面 */
	doUpdate: function(obj) {
		// 清空表单
		fw.clearForm('#saveForm');
		// 设置表单值
		fw.setFormData('#saveForm', obj.data);
		
		// 设置多选下拉列表初始值
		//formSelects.value('TYPEIDS', obj.data.TYPEID.split(','));
		formSelects.value('TAGIDS', obj.data.TAGID.split(','));
		
		// 弹出更新界面
		layer.open({
			type: 1,
			title: '修改文章',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '450px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 删除文章信息 */
	doDelete: function(obj) {
		layer.confirm('真的删除行么?', function(index){
			fw.doAjax(
				'/blog/deleteArticle',
				{ARTICLEID: obj.data.ARTICLEID},
				true, true,   // 显示加载层;是否异步
				function(data) {   // 成功回调函数
					if (data.code == '1') {
						layer.msg('删除文章成功', {
							icon: '1'
						})
						// 刷新菜单列表数据
						oButtonInit.doQuery();
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
	
	/** 保存文章信息（新增或修改） */
	doSave: function() {
		// 首先校验表单录入数据格式
		if (fw.validateForm("#saveForm")) {
			var data = fw.getFormData("#saveForm");
			
			fw.doAjax(
				'/blog/saveArticle',  // 请求url
				data, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					if (data.code == '1') {
						layer.msg('保存文章信息成功', {
								icon: '1',
								time: 1000,
								shade : [0.5 , '#000' , true]
							},
							function() {
								layer.closeAll();
							}
						);
						// 刷新文章列表数据
						oButtonInit.doQuery();
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
	},
	
	/**
	 * 编辑文章内容
	 */
	doEdit: function(obj) {
		// 设置表单值
		fw.setFormData('#editForm', obj.data);
		
		// 设置编辑器内容
		$("#CONTENT").val(obj.data.CONTENT);
		
		// 弹出编辑界面界面
		layer.open({
			type: 1,
			title: '编辑文章',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '450px'], //宽高
			content: $('#edit'),
			maxmin: true, //开启最大化最小化按钮
		});
		
		// 初始化富文本编辑器
		oPageInit.initEdit();
	},
	
	/**
	 * 保存文章内容
	 */
	doSaveEdit: function() {
		var data = fw.getFormData("#editForm");
		data.CONTENT = layedit.getContent(oPageInit.EditIndex);
		fw.doAjax(
			'/blog/saveArticle',  // 请求url
			data, // 请求参数
			true, true,   // 显示加载层;是否异步
			function(data) {  // 成功回调函数
				if (data.code == '1') {
					layer.msg('保存文章信息成功', {
							icon: '1',
							time: 1000,
							shade : [0.5 , '#000' , true]
						},
						function() {
							layer.closeAll();
						}
					);
					// 刷新文章列表数据
					oButtonInit.doQuery();
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

var oButtonInit = new CButtonInit();