package cn.muchen.framework.web;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.muchen.framework.Constants;
import cn.muchen.framework.service.inter.CommonService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：CommonController @Description： 公共的controller
 * @author ：柯雷
 * @date ：2018年9月25日 下午5:14:04
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * @Description 公共的处理对象
	 */
	@Autowired
	CommonService commonServiceImpl;

	/**
	 * 
	 * @Title：getDynamicDicts @Description：获取动态字典 @param ：@param params @param
	 * ：@return @return ：String @throws
	 */
	@RequestMapping("/getDynamicDicts")
	@ResponseBody
	public String getDynamicDicts(@RequestParam Map<String, Object> params) {
		logger.info("【CommonController.getDynamicDicts】获取动态字典: " + params);

		Object dynamicConfigId = params.get("dynamicConfigId"); // 动态字典配置id
		Object whereCls = params.get("whereCls"); // 动态字典查询条件
		Map<String, Object> rtnMap = new HashMap<>(); // 返回参数

		// 动态字典配置id不能为空
		if (Util.isEmpty(dynamicConfigId)) {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "动态字典配置不能为空");
		} else {
			// 如果查询条件为空，则默认 1 = 1
			if (Util.isEmpty(whereCls)) {
				whereCls = "1 = 1";
			}

			// 查询动态字典
			rtnMap = commonServiceImpl.getDynamicDicts("" + dynamicConfigId, "" + whereCls);
		}

		return JSONObject.toJSONString(rtnMap);
	}

	/**
	 * @Title：getDicts @Description：TODO @param ：@param params @param
	 * ：@return @return ：String @throws
	 */
	@RequestMapping("/getDicts")
	@ResponseBody
	public String getDicts(@RequestParam Map<String, Object> params) {
		logger.info("【CommonController.getDicts】获取静态字典:" + params);
		return JSONObject.toJSONString(commonServiceImpl.getDicts((String) params.get("dictType")));
	}

	/**
	 * @Title：uploadFile @Description：公共的文件上传 @param ：@param params @param
	 * ：@return @return ：String @throws
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam String filePath, @RequestParam("file") MultipartFile file) {
		logger.info("【CommonController.uploadFile】文件上传:" + filePath + "===========" + file);
		return JSONObject.toJSONString(commonServiceImpl.fileUpload(filePath, file));
	}

	
	/**
	 * @Title：valicode 
	 * @Description：生成图片验证码
	 * @param ：@param response
	 * @param ：@param session
	 * @param ：@throws Exception 
	 * @return ：void 
	 * @throws
	 */
	@RequestMapping("/valicode")
	public void valicode(HttpServletResponse response, HttpSession session) throws Exception {
		// 利用图片工具生成图片
		// 第一个参数是生成的验证码，第二个参数是生成的图片
		Object[] objs = Util.createImage();
		// 将验证码存入Session
		session.setAttribute("validateCode", objs[0].toString().toUpperCase());
		// 将图片输出给浏览器
		BufferedImage image = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "png", os);
	}
}
