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
		// 初始化表单事件
		oPageInit.initForm();
	},
	
	/** 初始化表格 */
	initTable: function() {
		table.render({
		    elem: '#rightList',
		    url: '/xtgl/getAllRights',
		    cellMinWidth: 80, // cell 全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    page: true,     // page分页展示
		    limit: 5,
		    limits: [10,20,30,40,50,60,70,80,90],  // page页面大小
		    loading: true,  // loading显示加载框
		    cols: [[
		    	{field:'RIGHTID', title: '权限ID', sort: true, align: 'center'},
		    	{field:'ICON', title: '权限图标', sort: true, align: 'center', hide: true},
		    	{field:'RIGHTNAME', title: '权限名称', sort: true, align: 'center'},
		    	{field:'SUBSYSTYPE', title: '所属系统', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("SUBSYSTYPE", d.SUBSYSTYPE);
		    		}
		    	},
		    	{field:'RIGHTTYPE', title: '权限类别', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("RIGHTTYPE", d.RIGHTTYPE);
		    		}
		    	},
		    	{field:'RIGHTLEVEL', title: '权限级别', sort: true, align: 'center', 
		    		templet: function(d) {
		    			return fw.getDictName("RIGHTLEVEL", d.RIGHTLEVEL);
		    		}
		    	},
		    	{field:'URL', title: '请求URL', sort: true, align: 'center'},
		    	{	
		    		field:'VALIDATED', title: '有效标志', sort: true, align: 'center',
		    		templet: function(d) {
			    		 return fw.getDictName("VALIDATED", d.VALIDATED);
			    	 }
		    	},
		    	{field:'MEMO', title: '备注', sort: true, align: 'center'},
		    	{field:'AUTHED', title: '是否认证', sort: true, align: 'center', hide: true},
		    	{title: '操作', width: 200, toolbar: '#rightBar', align: 'center'}
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
		table.on('tool(rightTable)', function(obj){
			if (obj.event === 'update') {  // 更新
				oButtonInit.doUpdate(obj);
			} else if (obj.event === 'delete') {
				oButtonInit.doDelete(obj);
			}
		});
	},
	
	/** 初始化界面字典项 */
	initDict: function() {
		fw.initDict($("select[name='SUBSYSTYPE']"), []);
		fw.initDict($("select[name='RIGHTLEVEL']"), []);
		fw.initDict($("select[name='VALIDATED']"), []);
		fw.initDict($("select[name='AUTHED']"), []);
		fw.initDict($("select[name='RIGHTTYPE']"), []);
	},
	
	/**
	 * 初始化表单
	 */
	initForm: function() {
		form.on('select(selectRightLevel)', function(data){
			// 选择的是一级菜单，则父菜单不能不可编辑
			if (data.value == 1) {
				$("input[name=URL]").attr('lay-verify', 'maxlength100|url');
				$("input[name=PARENTID]").attr('lay-verify', '');
				$("input[name=PARENTID]").attr("disabled","disabled");
			} else if (data.value == 2) {  // 否则父菜单可编辑且必录
				$("input[name=URL]").attr('lay-verify', 'required|maxlength100|url');
				$("input[name=PARENTID]").attr('lay-verify', 'required');
				$("input[name=PARENTID]").removeAttr("disabled");
			}
		});
		
		iconPicker.render({
            // 选择器，推荐使用input
            elem: '#iconPicker',
            // 数据类型：fontClass/unicode，推荐使用fontClass
            type: 'fontClass',
            // 是否开启搜索：true/false
            search: true,
            // 点击回调
            click: function (data) {
                $("#saveForm").find("input[name='ICON']").val(data.icon);
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
	/** 查询菜单信息 */
	doQuery: function() {
		table.reload('rightList', {
			where: fw.getFormData("#queryForm")
		});
	},
	
	/** 新增菜单，弹出新增界面 */
	doAdd: function() {
		// 清空表单
		fw.clearForm('#saveForm');
		
		/**
         * 选中图标 （常用于更新时默认选中图标）
         * @param filter lay-filter
         * @param iconName 图标名称，自动识别fontClass/unicode
         */
        iconPicker.checkIcon('iconPicker', '');
        
		// 弹出新增界面
		layer.open({
			type: 1,
			title: '新增菜单',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '475px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 更新菜单信息弹出更新界面 */
	doUpdate: function(obj) {
		// 清空表单
		fw.clearForm('#saveForm');
		
		// 如果菜单级别为2，则设置父菜单ID
		if (obj.data.RIGHTLEVEL == '2') {
			obj.data.PARENTID = obj.data.RIGHTID.substring(0, 2);
		}
		// 设置表单值
		fw.setFormData('#saveForm', obj.data);
		
		// 根据菜单级别设置url和父菜单ID校验规则
		if (obj.data.RIGHTLEVEL == 1) {
			$("input[name=URL]").attr('lay-verify', 'maxlength100|url');
			$("input[name=PARENTID]").attr('lay-verify', '');
			$("input[name=PARENTID]").attr("disabled","disabled");
		} else if (obj.data.RIGHTLEVEL == 2) {  // 否则父菜单可编辑且必录
			$("input[name=URL]").attr('lay-verify', 'required|maxlength100|url');
			$("input[name=PARENTID]").attr('lay-verify', 'required');
			$("input[name=PARENTID]").removeAttr("disabled");
		}
		
		/**
         * 选中图标 （常用于更新时默认选中图标）
         * @param filter lay-filter
         * @param iconName 图标名称，自动识别fontClass/unicode
         */
        iconPicker.checkIcon('iconPicker', obj.data.ICON);

		// 弹出更新界面
		layer.open({
			type: 1,
			title: '修改菜单',
			skin: 'layui-layer-demo', //加上边框
			area: ['800px', '475px'], //宽高
			content: $('#saveOrUpdate')
		});
	},
	
	/** 删除菜单信息 */
	doDelete: function(obj) {
		layer.confirm('真的删除菜单么?', function(index){
			fw.doAjax(
				'/xtgl/deleteRight',
				{RIGHTID: obj.data.RIGHTID},
				true, true,   // 显示加载层;是否异步
				function(data) {   // 成功回调函数
					if (data.code == '1') {
						layer.msg('删除菜单成功', {
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
	
	/** 保存菜单信息（新增或修改） */
	doSave: function() {
		// 首先校验表单录入数据格式
		if (fw.validateForm("#saveForm")) {
			var data = fw.getFormData("#saveForm");
			
			// 校验表格数据业务规则
			if (oButtonInit.doFormValidate(data)) {
				fw.doAjax(
					'/xtgl/saveRight',  // 请求url
					data, // 请求参数
					true, true,   // 显示加载层;是否异步
					function(data) {  // 成功回调函数
						if (data.code == '1') {
							layer.msg('保存菜单成功', {
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
	},
	
	/**
	 * 表单校验
	 */
	doFormValidate: function(data) {
		// 如果菜单级别为2校验菜单id和父菜单id
		if (data.RIGHTLEVEL == '2' && data.RIGHTID.substring(0, 2) != data.PARENTID) {
			layer.msg('菜单ID必须以父菜单ID开始', {
				icon: '5'
			});
			return false;
		}
		return true;
	}
}

var oButtonInit = new CButtonInit();