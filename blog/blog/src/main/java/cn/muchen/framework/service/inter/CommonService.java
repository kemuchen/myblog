package cn.muchen.framework.service.inter;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName:：CommonService 
 * @Description： 公共处理接口
 * @author ：柯雷
 * @date ：2018年9月25日 下午5:24:11 
 *
 */
public interface CommonService {
	
	/**
	 * @Title：getDynamicDicts 
	 * @Description：获取动态字典
	 * @param ：@param dynamicConfigId
	 * @param ：@param whereCls
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getDynamicDicts(String dynamicConfigId, String whereCls);
	
	/**
	 * @Title：getDicts 
	 * @Description：获取静态字典
	 * @param ：@param dictType
	 * @param ：@param whereCls
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getDicts(String dictType);
	
	/**
	 * @Title：fileUpload 
	 * @Description：文件上传
	 * @param ：@param filePath
	 * @param ：@param file
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> fileUpload(String filePath, MultipartFile file);
}
