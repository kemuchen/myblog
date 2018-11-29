/**
 * 界面初始化处理类
 */
var CPageInit = function() {}
CPageInit.prototype = {
	/** 界面初始化 */
	pageInit: function() {
		// 初始化表格
		oPageInit.initTable();
		// 初始化表格监听事件
		oPageInit.initTabeEvent();
		// 初始化字典项
		oPageInit.initDict();
		// 初始化文件上传事件
		oPageInit.initFileUpload();
	},
	
	/** 初始化表格 */
	initTable: function() {
		// 角色列表
		table.render({
		    elem: '#userList',
		    url: '/xtgl/getAllUsers',
		    cellMinWidth: 80, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'USERID', title: '用户id', sort: true, align: 'center', hide: true},
		    	{field:'USERNAME', title: '用户名', sort: true, align: 'center'},
		    	{field:'LOGINID', title: '登录id', sort: true, align: 'center'},
		    	{field:'PASSWORD', title: '密码', sort: true, align: 'center', hide: true},
		    	{field:'USERTYPE', title: '用户类别', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("USERTYPE", d.USERTYPE);
		    		}
		    	},
		    	{field:'EMAIL', title: '邮箱', sort: true, align: 'center'},
		    	{field:'TELEPHONE', title: '电话', sort: true, align: 'center'},
		    	{field:'STATE', title: '用户状态', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("STATE", d.STATE);
		    		}
		    	},
		    	{field:'MEMO', title: '备注', sort: true, align: 'center'},
		    	{title: '操作', width: 360, toolbar: '#roleBar', align: 'center'}
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
	
	/** 初始化表格监听事件 */
	initTabeEvent: function() {
		table.on('tool(userTable)', function(obj){
			if (obj.event === 'update') {  // 更新
				oButtonInit.doUpdate(obj);
			} else if (obj.event === 'delete') {
				oButtonInit.doDelete(obj);
			} else if (obj.event === 'authorize') {
				oButtonInit.doAuthorize(obj);
			} else if (obj.event === 'authorized') {
				oButtonInit.doQueryAuthorized(obj);
			} else if (obj.event === 'unauthorized') {
				oButtonInit.doUnAuthorize(obj);
			}
		});
	},
	
	/** 初始化界面字典项 */
	initDict: function() {
		fw.initDict($("select[name='STATE']"), []);
		fw.initDict($("select[name='USERTYPE']"), []);
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
		    		$("#saveForm").find('input[name=PHOTO]').val(res.data.src);
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

var oPageInit = new CPageInit();

/**
 * 按钮事件处理类
 */
var CButtonInit = function() {}
CButtonInit.prototype = {
	
	/** 选中进行操作的用户id */
	userid: '',
	
	/** 查询 */
	doQuery: function() {
		table.reload('userList', {
			where: fw.getFormData("#queryForm")
		});
	},
	
	/** 新增 */
	doAdd: function() {
		// 清空表单
		fw.clearForm('#saveForm');
		
		// 弹出新增界面
		layer.open({
			type: 1,
			title: '新增用户',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '400px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 更新 */
	doUpdate: function(obj) {
		// 清空表单
		fw.clearForm('#saveForm');
		// 设置表单值
		fw.setFormData('#saveForm', obj.data);
		// 弹出更新界面
		layer.open({
			type: 1,
			title: '修改用户',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '400px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 删除 */
	doDelete: function(obj) {
		layer.confirm('真的删除用户么?', function(index){
			fw.doAjax(
				'/xtgl/deleteUser',
				{USERID: obj.data.USERID},
				true, true,   // 显示加载层;是否异步
				function(data) {   // 成功回调函数
					if (data.code == '1') {
						layer.msg('删除用户成功', {
							icon: '1'
						})
						// 刷新菜单列表数据
						oButtonInit.doQuery();
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
		});
	},
	
	/** 保存用户 */
	doSave: function() {
		// 首先校验表单录入数据格式
		if (fw.validateForm("#saveForm")) {
			var data = fw.getFormData("#saveForm");
			
			fw.doAjax(
				'/xtgl/saveUser',  // 请求ur
				data, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					if (data.code == '1') {
						layer.msg('保存用户成功',{
								icon: '1',
								time: 1000,
								shade : [0.5 , '#000' , true]
							},
							function() {
								layer.closeAll();
							}
						);
						// 刷新菜单列表数据
						oButtonInit.doQuery();
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
	},
	
	/** 授权 */
	doAuthorize: function(obj) {
		// 授权列表（即角色没有的权限信息列表，且带多选框）
		table.render({
		    elem: '#authorizeList',
		    url: '/xtgl/getUserUnAuthorizes',
		    cellMinWidth: 120, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'SELECT', title: '', align: 'center', type: 'checkbox'},
		    	{field:'ROLEID', title: '角色id', sort: true, align: 'center'},
		    	{field:'ROLENAME', title: '角色名称', sort: true, align: 'center'},
		    	{field:'DESCRIPTION', title: '角色描述', sort: true, align: 'center'},
		    	{field:'MEMO', title: '备注', sort: true, align: 'center'}
		    ]],
		    request: {
		    	pageName: 'currentPage',
		    	limitName: 'pageSize'
		    },
		    where: {
		    	USERID: obj.data.USERID
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
		
		// 弹出授权界面
		layer.open({
			type: 1,
			title: '授权',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '400px'], //宽高
			content: $('#authorize')
		});
		
		oButtonInit.userid = obj.data.USERID;
	},
	
	/** 取消授权 */
	doUnAuthorize: function(obj) {
		// 已有权限列表(带多选框)
		table.render({
		    elem: '#unAuthorizeList',
		    url: '/xtgl/getUserAuthorizes',
		    cellMinWidth: 100, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'USER2ROLEID', title: '', hide: true},
		    	{field:'SELECT', title: '', align: 'center', type: 'checkbox'},
		    	{field:'ROLEID', title: '角色id', sort: true, align: 'center'},
		    	{field:'ROLENAME', title: '角色名称', sort: true, align: 'center'},
		    	{field:'DESCRIPTION', title: '角色描述', sort: true, align: 'center'},
		    	{field:'MEMO', title: '备注', sort: true, align: 'center'}
		    ]],
		    request: {
		    	pageName: 'currentPage',
		    	limitName: 'pageSize'
		    },
		    where: {
		    	USERID: obj.data.USERID
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
		
		// 弹出授权界面
		layer.open({
			type: 1,
			title: '取消授权',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '400px'], //宽高
			content: $('#unAuthorize')
		});
		
		oButtonInit.userid = obj.data.USERID;
	},
	
	/**
	 * 保存授权信息
	 */
	doSaveAuthorize: function() {
		// 获取选中行数据
		var selectData = table.checkStatus('authorizeList');
		
		if (selectData.data.length == 0) {
			layer.alert('请选择角色进行授权', {
				icon: '5'
			})
		} else {
			var roleIds = '';
			// 拼接菜单id串
			for (var i = 0; i < selectData.data.length; i++) {
				if (i == selectData.data.length - 1) {
					roleIds += selectData.data[i].ROLEID;
				} else {
					roleIds += selectData.data[i].ROLEID + ',';
				}
			}
			// 调用服务器保存授权信息
			fw.doAjax(
				'/xtgl/authorizeUserRole',  // 请求ur
				{USERID: oButtonInit.userid, ROLEIDS: roleIds}, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					if (data.code == '1') {
						layer.msg('授权成功', {
								icon: '1',
								time: 1000,
								shade : [0.5 , '#000' , true]
							},
							function() {
								layer.closeAll();
							}
						);
						// 刷新菜单列表数据
						oButtonInit.doQuery();
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
	},
	
	/**
	 * 保存取消授权信息
	 */
	doSaveUnAuthorize: function() {
		// 获取选中行数据
		var selectData = table.checkStatus('unAuthorizeList');
		
		if (selectData.data.length == 0) {
			layer.alert('请选择要取消授权的角色', {
				icon: '5'
			})
		} else {
			var userRoleIds = '';
			// 拼接菜单id串
			for (var i = 0; i < selectData.data.length; i++) {
				if (i == selectData.data.length - 1) {
					userRoleIds += selectData.data[i].USER2ROLEID;
				} else {
					userRoleIds += selectData.data[i].USER2ROLEID + ',';
				}
			}
			
			// 调用服务器保存授权信息
			fw.doAjax(
				'/xtgl/unAuthorizeUserRole',  // 请求ur
				{USER2ROLEID: userRoleIds}, // 请求参数
				true, true,   // 显示加载层;是否异步
				function(data) {  // 成功回调函数
					if (data.code == '1') {
						layer.msg('取消授权成功', {
								icon: '1',
								time: 1000,
								shade : [0.5 , '#000' , true]
							},
							function() {
								layer.closeAll();
							}
						);
						// 刷新菜单列表数据
						oButtonInit.doQuery();
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
};

var oButtonInit = new CButtonInit();