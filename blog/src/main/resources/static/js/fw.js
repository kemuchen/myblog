// 框架的js函数
var FW = function() {
};

FW.prototype = {
	
	dicts: {},   // 字典项缓存
		
	/**
	 * 判断数据是否为空，未定义、null、空串均为空
	 * 
	 * @param data:
	 *            要判断的数据
	 */
	isEmpty : function(data) {
		if (data == undefined || data == null || data == '') {
			return true;
		} else {
			return false;
		}
	},
	
	/**
	 * 公共的ajax调用方法
	 * 
	 * @param url:
	 *            调用的url
	 * @param params:
	 *            调用入参
	 * @param isShowLoading:
	 *            是否显示加载层
	 * @param isSync:
	 *            是否异步
	 * @param successFun:
	 *            成功回调函数
	 * @param failFun:
	 *            失败回调函数
	 * @returns
	 */
	doAjax : function(url, params, isShowLoading, isSync, successFun, failFun) {
		// 显示加载框
		if (isShowLoading) {
			layer.load(2);
		}
		$.ajax({
			url : url,
			data : params,
			dataType : 'json',
			cache : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : 'post',
			async : isSync,
			success : function(data) {
				layer.closeAll('loading');
				successFun(data);
			},
			error : function(data) {
				layer.closeAll('loading');
				failFun(data);
			}
		})
	},

	/**
	 * 公共设置表单值的方法
	 * 
	 * @param formObj:
	 *            表单ID
	 * @param data:
	 * 			  表单数据
	 * @returns
	 */
	setFormData : function(form, data) {
		// 获取表单对象
		var formObj = $(form);
		if (typeof formObj != 'object') {
			throw "form:" + form + " is not exists";
		}
		
		// data不是json对象
		if (typeof data != 'object') {
			throw "data no object";
		}
		
		for (var key in data) {
            if (data.hasOwnProperty(key)) {
                var inputs = formObj.find('input[name = "' + key + '"]');
                var textArea = formObj.find('textarea[name = "' + key + '"]');
                var select = formObj.find('select[name="' + key + '"]');
                if (inputs.length > 0) {
                    var input = inputs[0];
                    switch (input.type) {
                    case "text":
                        input.value = data[key];
                        break;
                    case "hidden":
                        input.value = data[key];
                        break;
                    case "radio":
                        this.setRadioVal(formObj, key, data[key]);
                        break;
                    case "checkbox":
                        if (data[key] === true) {
                            this.setCheckboxVal(formObj, key, data[key]);
                        }
                        break;
                    }
                } else if (select.length > 0) {
                    var select = formObj.find('select[name="' + key + '"]');
                    this.setSelectVal(formObj, key, data[key], true);
                } else if (textArea.length > 0) {
                	textArea[0].value = data[key];
                }
            }
        }
        return this;
	},
	
	/**
	 * 设置单选钮
	 * 
	 * @param formObj表单对象
	 * @param name 单选钮
	 * @param val 选中值
	 */
	setRadioVal: function(formObj, name, val) {
		if (name === undefined) {
            throw "name no undefined";
        }
        formObj.find('input[type="radio"][name="' + name + '"][value="' + val + '"]').prop("checked", true);
        form.render('radio');
        return this;
	},
	
	/**
	 * 设置多选框
	 * 
	 * @param formObj表单对象
	 * @param name 多选框
	 * @param val 选中值
	 */
	setCheckboxVal: function(formObj, name, val) {
		if (name === undefined) {
            throw "name no undefined";
        }
        formObj.find('input[type="checkbox"][name="' + name + '"]').prop("checked", true);
        form.render('checkbox');
        return this;
	},
	
	/**
	 * 设置下拉选择
	 * 
	 * @param formObj表单对象
	 * @param key 多选框 
	 * @param val 选中值
	 * @param isOnSelect
	 */
	setSelectVal: function(formObj, name, val, isOnSelect) {
		if (name === undefined) {
            throw "name no undefined";
        }
        formObj.find('select[name="' + name + '"]').val(val);
        form.render('select');
        if (typeof (isOnSelect) === "boolean") {
            if (isOnSelect) {
                formObj.find("dd[lay-value='" + val + "']").prop('click');
            }
        }
        return this;
	},
	
	/**
	 * 清空表单
	 */
	clearForm: function(form) {
		// 获取表单对象
		var formObj = $(form);
		if (typeof formObj != 'object') {
			throw "form:" + form + " is not exists";
		}
		
		formObj.find('input').val('');
		formObj.find('select').val('');
		formObj.find('textarea').val('');
	},
	
	/**
	 * 
	 */
	getFormData: function(form) {
		// 获取表单对象
		var formObj = $(form);
		if (typeof formObj != 'object') {
			throw "form:" + form + " is not exists";
		}
		
		// 临时数组
		var jsonData = {};
		
		// 获取所有的input，并遍历获取所有值
		var inputs = formObj.find('input');
		for (var i = 0; i < inputs.length; i++) {
			if (!this.isEmpty($(inputs[i]).attr('name'))) {
				jsonData[$(inputs[i]).attr('name')] = $(inputs[i]).val();
			}
		}
		
		// 获取所有的select，并遍历获取所有值
		var selects = formObj.find('select');
		for (var i = 0; i < selects.length; i++) {
			if (!this.isEmpty($(selects[i]).attr('name'))) {
				jsonData[$(selects[i]).attr('name')] = $(selects[i]).val();
			}
		}
		
		// 获取所有的select，并遍历获取所有值
		var textareas = formObj.find('textarea');
		for (var i = 0; i < textareas.length; i++) {
			if (!this.isEmpty($(textareas[i]).attr('name'))) {
				jsonData[$(textareas[i]).attr('name')] = $(textareas[i]).val();
			}
		}
		return jsonData;
	},
	
	/**
	 * 表单校验
	 */
	validateForm: function(form) {
		// 获取表单对象
		var formObj = $(form);
		if (typeof formObj != 'object') {
			throw "form:" + form + " is not exists";
		}
		
		// 获取所有的input，并遍历获取所有值
		var inputs = formObj.find('input');
		for (var i = 0; i < inputs.length; i++) {
			// 如果校验不通过，则返回
			if (!this.validate(inputs[i])) {
				return false;
			}
		}
		
		// 获取所有的select，并遍历获取所有值
		var selects = formObj.find('select');
		for (var i = 0; i < selects.length; i++) {
			// 如果校验不通过，则返回
			if (!this.validate(selects[i])) {
				return false;
			}
		}
		
		// 获取所有text-area，并遍历所有值
		var textareas = formObj.find('textarea');
		for (var i = 0; i < textareas.length; i++) {
			// 如果校验不通过，则返回
			if (!this.validate(textareas[i])) {
				return false;
			}
		}
		return true;
	},
	
	/**
	 * 具体校验某个jQuery对象
	 * 
	 * @param object
	 * @returns
	 */
	validate: function(object) {
		var errMsg = ''; // 错误码和错误提示信息
		// 校验规则
		var validateRule = $(object).attr('lay-verify');
		// jQuery对象label名称
		var name = $(object).attr('name');
		var nameDesc = fw.isEmpty($(object).parent().prev('label').html()) ? '必录项' : $(object).parent().prev('label').html();
		// jQuery对象值
		var value = $(object).val();
		// 名称和规则都不为空的情况下进行校验
		if (!this.isEmpty(name) && !this.isEmpty(validateRule)) {
			var rules = validateRule.split("|");
			for (var i = 0; i < rules.length; i++) {
				// 校验必录
				if (rules[i] == 'required' && this.isEmpty(value)) {
					errMsg = nameDesc + '不能为空';
					break;
				}
				
				// 校验必须为数字
				if (rules[i] == 'number' && !this.isEmpty(value) && !/^[0-9]+$/.test(value)) {
					errMsg = nameDesc + '必须是数字';
					break;
				}
				
				// 校验手机号
				if (rules[i] == 'phone' && !this.isEmpty(value) && !/^[1][3,4,5,7,8][0-9]{9}$/.test(value)) {
					errMsg = nameDesc + '格式不正确';
					break;
				}
				
				// 校验email
				if (rules[i] == 'email' && !this.isEmpty(value) && !/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value)) {
					errMsg = nameDesc + '格式不正确';
					break;
				}
				
				// 校验url
				if (rules[i] == 'url' && !this.isEmpty(value) && ! /^(?:([A-Za-z]+):)?(\/{0,3})([0-9.\-A-Za-z]+)(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/.test(value)) {
					errMsg = nameDesc + '格式不正确';
					break;
				}
				
				if (rules[i].substring(0, 9) == 'maxlength' && !this.isEmpty(value) && value.length > parseInt(rules[i].substring(9))) {
					errMsg = nameDesc + '长度不能超过' + rules[i].substring(9);
					break;
				}
				
				if (rules[i].substring(0, 9) == 'minlength' && !this.isEmpty(value) && value.length < parseInt(rules[i].substring(9))) {
					errMsg = nameDesc + '长度不能小于' + rules[i].substring(9);
					break;
				}
			}

			// 错误信息不为空表示校验不通过
			if (!this.isEmpty(errMsg)) {
				layer.msg(errMsg, {
					icon: '2'
				});
				return false;
			}
		}
		
		// 校验通过返回true
		return true;
	},
	
	/**
	 * 初始化字典项
	 * 
	 * @param selectObj 下拉列表jquery对象
	 * @param removeKey 过滤字典项
	 */
	initDict: function(selectObj, removeKey) {
		var dictType = selectObj.attr('dictType');  // 字典项类型
		var isSelect = selectObj.attr('isSelect');  // 是否需要请选择
		var isDynamic = selectObj.attr('isDynamic');  // 是否动态字典
		
		// 字典项类别不允许为空
		if (fw.isEmpty(dictType)) {
			throw "dictType id not allow null";
		}

		// 获取此类型字典项，配置为动态字典
		var tempDicts = [];
		if (!fw.isEmpty(isDynamic) && isDynamic == 'true') {
			tempDicts = this.getDynamicDicts(dictType, removeKey);
		} else {
			// 否则为静态字典
			tempDicts = this.getDicts(dictType);
		}
		
		// 字典项拼接串
		var html = "";
		if (isSelect) {
			html += "<option value=''>请选择</option>";
		}
		for (var i = 0; i < tempDicts.length; i++) {
			if (!fw.isEmpty(isDynamic) && isDynamic == 'true') {
				html += "<option value='" + tempDicts[i].CODE + "'>" + tempDicts[i].NAME + "</option>"
			} else {
				if ($.inArray(tempDicts[i].CODE, removeKey) >= 0) {
					continue;
				} else {
					html += "<option value='" + tempDicts[i].CODE + "'>" + tempDicts[i].NAME + "</option>"
				}
			}
		}
		selectObj.html(html);
		
		// 重新加载字典项
		form.render('select');
		return this;
	},
	
	/**
	 * 获取字典项
	 * 
	 * @param dictType 字典项类别
	 */
	getDicts: function(dictType) {
		// 查询是否存在缓存中
		if (dictType in fw.dicts) {
			return fw.dicts[dictType];    // 直接返回缓存中对象
		}
		
		var dict = [];
		
		fw.doAjax(
			'/common/getDicts',
			{dictType: dictType},
			false, false,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					dict = data.DICTS;
					fw.dicts[dictType] = data.DICTS;
				} else {
					dict = [];
					throw "获取字典失败:" + data.message;
				}
			},
			function(data) {   // 失败回调函数
				dict = [];
				throw "获取字典失败:" + data;
			}
		);
		return dict;
	},
	
	/**
	 * 获取字典项名称
	 * 
	 * @param dictType 字典项类型
	 * @param dictCode 字典项代码
	 */
	getDictName: function(dictType, dictCode) {
		// 获取字典项
		var tempDicts = this.getDicts(dictType);
		for (var i = 0; i < tempDicts.length; i++) {
			if (tempDicts[i].CODE == dictCode) {
				return tempDicts[i].NAME;
			}
		}
		
		return '';
	},
	
	/**
	 * 获取动态字典项
	 * 
	 * @param dynamicConfigId 动态字典项配置id
	 * @param whereCls 动态字典项过滤条件
	 * @returns
	 */
	getDynamicDicts: function(dynamicConfigId, whereCls) {
		if (fw.isEmpty(dynamicConfigId)) {
			throw "字典项配置不能为空"; 
		}
		
		fw.doAjax(
			'/common/getDynamicDicts',
			{dynamicConfigId: dynamicConfigId, whereCls: whereCls},
			false, false,   // 显示加载层;是否异步
			function(data) {   // 成功回调函数
				if (data.code == '1') {
					dict = data.DICTS;
				} else {
					dict = [];
					throw "获取字典失败:" + data.message;
				}
			},
			function(data) {   // 失败回调函数
				dict = [];
				throw "获取字典失败:" + JSON.stringify(data);
			}
		);
		return dict;
	},
	
	/**
	 * 获取动态字典
	 */
	getMultDictName: function(dynamicConfigId, dictsKey) {
		if (fw.isEmpty(dictsKey) || fw.isEmpty(dynamicConfigId)) {
			return '';
		}
		
		var keys = dictsKey.split(',');
		// 获取字典项
		var dicts = fw.getDynamicDicts(dynamicConfigId, '1 = 1');
		// 遍历字典项拼接返回信息
		var dictName = '';
		for (var i = 0; i < dicts.length; i++) {
			if ($.inArray(dicts[i].CODE + '', keys) >= 0) {
				dictName = dictName + dicts[i].NAME + ' ';
			}
		}
		
		return dictName;
	},
	
	/**
	 * 时间戳转日期
	 */
	timestampToTime: function(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
        var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours())  + ':';
        var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
        var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
        return Y+M+D+h+m+s;
    }
}

var fw = new FW();