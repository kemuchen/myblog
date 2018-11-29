package cn.muchen.framework.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName:：Environment 
 * @Description： 系统参数
 * @author ：柯雷
 * @date ：2018年11月19日 上午10:40:46 
 *
 */
public class Environment {
	
	/** 日志打印对象 */
	Logger logger = LoggerFactory.getLogger(Environment.class);
	
	/** 参数map */
	private static Map<String, Object> parameters = new HashMap<>();
	
	/**
	 * @Title：setSystemProperties 
	 * @Description：设置系统参数
	 * @param ：@param params 
	 * @return ：void 
	 * @throws
	 */
	public static void setSystemProperties(List<Map<String, Object>> params) {
		if (Util.isEmpty(params)) {
			for (Map<String, Object> tempMap : params) {
				parameters.put((String) tempMap.get("KEY"), tempMap.get("VALUE"));
			}
		}
	}
	
	/**
	 * @Title：setParameter 
	 * @Description：设置系统参数
	 * @param ：@param key
	 * @param ：@param value 
	 * @return ：void 
	 * @throws
	 */
	public static void setParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	/**
	 * @Title：getParameter 
	 * @Description：获取系统参数
	 * @param ：@param key
	 * @param ：@return 
	 * @return ：Object 
	 * @throws
	 */
	public static Object getParameter(String key) {
		return parameters.get(key);
	}
	
	/**
	 * @Title：getValue 
	 * @Description：获取系统参数，返回string类型
	 * @param ：@param key
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public static String getValue(String key) {
		return (String) parameters.get(key);
	}
}
