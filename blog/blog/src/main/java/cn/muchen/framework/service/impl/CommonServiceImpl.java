package cn.muchen.framework.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.muchen.framework.Constants;
import cn.muchen.framework.dao.mapper.CommonMapper;
import cn.muchen.framework.service.inter.CommonService;

/**
 * 
 * @ClassName:：CommonServiceImpl 
 * @Description： 公共处理类
 * @author ：柯雷
 * @date ：2018年9月25日 下午5:27:42 
 *
 */
@Service
public class CommonServiceImpl implements CommonService {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	/**
	 * @Description 公共的数据库操作对象
	 */
	@Autowired
	CommonMapper commonMapper;
	
	/** spring boot上下文环境 */
	@Autowired  
    private Environment environment;


	/**
	 * <p>Title：getDynamicDicts</p> 
	 * <p>Description：获取动态字典 </p> 
	 * @param dynamicConfigId
	 * @param whereCls
	 * @return 
	 * @see cn.muchen.framework.service.inter.CommonService#getDynamicDicts(java.lang.String, java.lang.String)
	 */
	@Cacheable(value = "dict", key="#dynamicConfigId + #whereCls")
	@Override
	public Map<String, Object> getDynamicDicts(String dynamicConfigId, String whereCls) {
		logger.info("【CommonServiceImpl】获取动态字典:configId=" + dynamicConfigId + ";whereCls" + whereCls);
		
		Map<String, Object> rtnMap = new HashMap<>();
		
		/**
		 * 校验查询条件中是否包含增删改查关键字，如有则不允许进行操作，防止sql注入
		 */
		if (whereCls.toLowerCase().indexOf("insert") > 0 || whereCls.toLowerCase().indexOf("delete") > 0||
				whereCls.toLowerCase().indexOf("update") > 0 || whereCls.toLowerCase().indexOf("select") > 0) {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "查询条件中包含非法字符，请检查!");
		} else {
			// 获取查询语句
			String querySql = commonMapper.getDynamicSql(dynamicConfigId) + " where " + whereCls;
			
			logger.info("查询语句为:" + querySql);
			List<Map<String, Object>> listDicts = commonMapper.getDynamicDicts(querySql);
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
			rtnMap.put("DICTS", listDicts);
		}
		
		return rtnMap;
	}

	/**
	 * <p>Title：getDicts</p> 
	 * <p>Description： 获取静态字典</p> 
	 * @param dictType
	 * @param whereCls
	 * @return 
	 * @see cn.muchen.framework.service.inter.CommonService#getDicts(java.lang.String, java.lang.String)
	 */
	@Cacheable(value = "dict", key="#dictType")
	@Override
	public Map<String, Object> getDicts(String dictType) {
		logger.info("【CommonServiceImpl】获取静态字典:" + dictType);
		
		Map<String, Object> rtnMap = new HashMap<>();
		try {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
			rtnMap.put("DICTS", commonMapper.getStaticDicts(dictType));
		} catch (Exception e) {
			logger.error("【CommonServiceImpl】获取静态字典失败:" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "获取静态字典失败，失败原因:" + e.getMessage());
		}
		return rtnMap;
	}

	/**
	 * <p>Title：fileUpload</p> 
	 * <p>Description： 公共的文件上传处理</p> 
	 * @param filePath
	 * @param file
	 * @return 
	 * @see cn.muchen.framework.service.inter.CommonService#fileUpload(java.lang.String, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Map<String, Object> fileUpload(String filePath, MultipartFile file) {
		logger.info("【CommonServiceImpl.fileUpload】文件上传");
		Map<String, Object> rtnMap = new HashMap<>();
		
		String root = environment.getProperty("file.location");
		// 文件输出流
		OutputStream outputStream = null;
		// 文件输入流
		InputStream inputStream = null;
		try {
			// 判断文件夹是否存在，不存在则新增
			File dir = new File(root +  filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
            //获取输出流
            outputStream = new FileOutputStream(root + filePath + file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            inputStream = file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp = inputStream.read()) != -1) {
                outputStream.write(temp);
            }
            outputStream.flush();
           
            // 构造返回数据
            rtnMap.put("code", Constants.PAGE_QUERY_CG);
	   		rtnMap.put("msg", "");
	   		
	   		Map<String, Object> data = new HashMap<>();
	   		data.put("src", filePath + file.getOriginalFilename());
	   		data.put("title", file.getOriginalFilename());
	   		rtnMap.put("data", data);
        } catch(Exception e) {
            logger.error("【CommonServiceImpl.fileUpload】文件上传失败:" + e);
            rtnMap.put("code", Constants.PAGE_QUERY_SB);
    		rtnMap.put("msg", "文件上传处理错误");
        } finally {
        	try {
        		if (outputStream != null) {
            		outputStream.close();
            	}
            	if (inputStream != null) {
            		inputStream.close();
            	}
        	} catch(Exception e) {
        		logger.error("【CommonServiceImpl.fileUpload】文件上传失败:" + e);
                rtnMap.put("code", Constants.PAGE_QUERY_SB);
        		rtnMap.put("msg", "文件上传处理错误");
        	}
        }
        
		return rtnMap;
	}

}
