package cn.muchen.framework.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：CommonMapper 
 * @Description： 公共的处理DAO
 * @author ：柯雷
 * @date ：2018年9月25日 下午5:28:51 
 *
 */
@Repository
public interface CommonMapper {
	
	/**
	 * @Title：getDynamicSql 
	 * @Description：获取动态字典查询语句
	 * @param ：@param dynamicConfigId
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public String getDynamicSql(String dynamicConfigId);
	
	/**
	 * @Title：getDynamicDicts 
	 * @Description：获取动态字典
	 * @param ：@param sql
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public List<Map<String, Object>> getDynamicDicts(@Param("querySql") String querySql);
	
	/**
	 * @Title：getStaticDicts 
	 * @Description：获取静态字典
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public List<Map<String, Object>> getStaticDicts(String dictType);
	
	/**
	 * @Title：getAllQuartas 
	 * @Description：获取所有定时器配置
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getAllQuartas();
}
