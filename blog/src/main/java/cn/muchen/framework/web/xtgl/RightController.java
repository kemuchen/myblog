package cn.muchen.framework.web.xtgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.muchen.framework.Constants;
import cn.muchen.framework.service.inter.xtgl.RightService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: RightController 
 * @Description: 菜单管理controller
 * @author: 柯雷
 * @date：2018年9月5日 下午10:24:56
 */
@Controller
@RequestMapping("/xtgl")
public class RightController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(RightController.class);
	
	/**
	 * @Description 菜单DAO操作对象
	 */
	@Autowired
	RightService rightServiceImpl;

	/**
	 * 
	 * @Title：toRight 
	 * @Description：跳转到菜单管理界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/right")
	public String toRight() {
		logger.info("【RightController.toRight】跳转到菜单管理界面");
		return "framework/xtgl/right";
	}
	
	/**
	 * 
	 * @Title：getAllRights 
	 * @Description：分页获取菜单信息
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getAllRights")
	@ResponseBody
	public String getAllRights(@RequestParam Map<String, Object> params) {
		logger.info("【RightController.getAllRights】分页查询菜单信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = rightServiceImpl.getRightsByPage(params);
		if (!Util.isEmpty(rtnMap)) {  // 查询成功
			rtnMap.put("code", Constants.PAGE_QUERY_CG);
			rtnMap.put("message", "查询成功");
		} else {  // 查询失败
			rtnMap = new HashMap<>();
			rtnMap.put("code", Constants.PAGE_QUERY_SB);
			rtnMap.put("message", "查询失败");
		}
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：saveRight 
	 * @Description：保存菜单信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveRight")
	@ResponseBody
	public String saveRight(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【RightController.saveRight】保存菜单信息:" + params);
		
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(rightServiceImpl.saveRight(params));
	}
	
	/**
	 * 
	 * @Title：deleteRight 
	 * @Description：删除菜单
	 * @param ：@param parmas
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteRight")
	@ResponseBody
	public String deleteRight(@RequestParam Map<String, Object> params) {
		logger.info("【RightController.deleteRight】删除菜单：" + params);
		
		// 返回map
		Map<String, Object> rtnMap = new HashMap<>();
		if (rightServiceImpl.deleteRight((String) params.get("RIGHTID"))) {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} else {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除菜单失败");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
}
