/**
 * 菜单隐藏或显示
 * @returns
 */
function menuSwitch() {
	if ($(".layui-layout-admin .layui-side").css("left") == '0px') {
		$("#menu-switch").html("&#xe66b;");
		$(".layui-layout-admin .layui-side").animate({left: "-200px"});
		$(".layui-layout-admin .content-body").animate({left: "0px"});
   } else {
		$("#menu-switch").html("&#xe668;");
		$(".layui-layout-admin .layui-side").animate({left: "0px"});
		$(".layui-layout-admin .content-body").animate({left: "200px"});
   }
}

function addTab(objct) {
	var title = $(objct).find('span').text();
    var path = $(objct).children('a').attr('path');
    var tabId = $(objct).children('a').attr('tab-id');
    var icon = $(objct).children('a').attr('icon');
    // 去重复选项卡
    for (var i = 0; i < $('.frame').length; i++) {
        if ($('.frame').eq(i).attr('tab-id') == tabId) {
            element.tabChange("tab", tabId);
            event.stopPropagation();
            return;
        }
    }
    // 添加选项卡
    element.tabAdd("tab", {
        title: '<i class="layui-icon ' + icon + '"></i>' + title,
        content: "<iframe src='" + path + "' tab-id='" + tabId + "' class='frame' frameborder='0' scrolling='yes' width='99%' height='100%'></iframe>",
        id: tabId
    });
    // 切换选项卡
    element.tabChange("tab", tabId);
}

/**
 * 激活tab页
 * @param tabId
 * @returns
 */
function activeTab(tabId) {
	element.tabChange("tab", tabId);
}

// 退出登录
function logout() {
	layer.confirm('确定要退出系统？', {
		icon: '3',
		title: '提示'
	}, function(index) {
		window.location.href = "/xtgl/logout";
	});
}