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
import cn.muchen.framework.service.inter.xtgl.RoleService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: RoleController 
 * @Description: 角色控制器
 * @author: 柯雷
 * @date：2018年9月9日 下午9:14:09
 */
@Controller
@RequestMapping("/xtgl")
public class RoleController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleServiceImpl;
	
	/**
	 * 
	 * @Title：toRole 
	 * @Description：跳转到角色管理界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/role")
	public String toRole() {
		return "framework/xtgl/role";
	}
	
	/**
	 * 
	 * @Title：getAllRoles 
	 * @Description：分页查询角色信息
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getAllRoles")
	@ResponseBody
	public String getAllRoles(@RequestParam Map<String, Object> params) {
		logger.info("【RoleController.getAllRoles】分页查询角色信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = roleServiceImpl.getRightsByPage(params);
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
	 * @Title：saveRole 
	 * @Description：保存角色信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【RoleController.saveRole】保存角色信息:" +params);
		
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(roleServiceImpl.saveRole(params));
	}
	
	/**
	 * 
	 * @Title：deleteRole 
	 * @Description：删除角色信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(@RequestParam Map<String, Object> params) {
		logger.info("【RoleController.deleteRole】删除角色信息");
		// 返回map
		Map<String, Object> rtnMap = new HashMap<>();
		if (roleServiceImpl.deleteRole(Integer.parseInt((String) params.get("ROLEID")))) {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} else {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除角色失败");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：authorizeRole 
	 * @Description：角色授权
	 * @param ：@param params
	 * @param ：@param session
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/authorizeRole")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String authorizeRole(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【RoleController.authorizeRole】保存角色授权:" + params);
		
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("OPERATOR", user.get("USERID"));
		return JSONObject.toJSONString(roleServiceImpl.authorizeRole(params));
	}
	
	/**
	 * 
	 * @Title：unAuthorizeRole 
	 * @Description：取消角色授权
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/unAuthorizeRole")
	@ResponseBody
	public String unAuthorizeRole(@RequestParam Map<String, Object> params) {
		logger.info("【RoleController.unAuthorizeRole】取消角色授权：" + params);
		// 返回map
		Map<String, Object> rtnMap = new HashMap<>();
		if (roleServiceImpl.unAuthorizeRole(params)) {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} else {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "取消授权失败");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：getAuthorizeByPage 
	 * @Description：获取角色已有权限信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getAuthorizes")
	@ResponseBody
	public String getAuthorizeByPage(@RequestParam Map<String, Object> params) {
		logger.info("【RoleController.getAuthorizeByPage】分页查询角色已有权限信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = roleServiceImpl.getAuthorizeByPage(params);
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
	 * @Title：getAuthorizeByPage 
	 * @Description：获取角色没有权限信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getUnAuthorizes")
	@ResponseBody
	public String getUnAuthorizes(@RequestParam Map<String, Object> params) {
		logger.info("【RoleController.getUnAuthorizes】分页查询角色没有的权限信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = roleServiceImpl.getUnAuthorizeByPage(params);
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
}
